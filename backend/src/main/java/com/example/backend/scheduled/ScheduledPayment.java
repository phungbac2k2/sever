package com.example.backend.scheduled;

import com.example.backend.entity.Account;
import com.example.backend.entity.Invoices;
import com.example.backend.entity.Package;
import com.example.backend.entity.Subscriptions;
import com.example.backend.model.dto.PaymentRequest;
import com.example.backend.repository.InvoicesRepository;
import com.example.backend.repository.SubscriptionsRepositpry;
import com.example.backend.service.account.AccountService;
import com.example.backend.service.pack.PackageServiceImpl;
import com.example.backend.service.payment.StripeService;
import com.example.backend.service.paymentmethod.PaymentMethodService;
import com.example.backend.service.subscriptions.SubscriptionsService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ScheduledPayment {
    @Autowired
    private StripeService stripeService;

    @Autowired
    private PackageServiceImpl packageServiceImpl;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubscriptionsService subscriptionsService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private SubscriptionsRepositpry subscriptionsRepositpry;

    @Autowired
    private InvoicesRepository invoicesRepository;

    @Scheduled(fixedRate = 10000)
    public void ScanSystemPayment() throws StripeException {
        LocalDate today = LocalDate.now();
        int typeOfOnDemand = 4;
        int active = 1;
        int cancelled = -1;
        int cancelRenewal = 0;
        int paymentSuccess = 1;
        int feeOnDemand = 20;
        List<Subscriptions> subscriptionsList = subscriptionsService.findAll();

        for (Subscriptions subscriptions : subscriptionsList) {

            Package pack = packageServiceImpl.findAllById((long) subscriptions.getIdpackage()).get(0);
            Account account = accountService.findById((long) subscriptions.getIdaccount());

            if (ChronoUnit.DAYS.between(subscriptions.getLastpayment(), today) != subscriptions.getRecurring())
                continue;

            if (subscriptions.getStatus() == active) {

                if (pack.getType() == active) {
                    subscriptions.setStatus(cancelled);
                    subscriptionsRepositpry.save(subscriptions);
                } else {
                    //set amount
                    PaymentRequest paymentRequest = PaymentRequest.builder()
                            .amount(subscriptions.getPrice())
                            .build();
                    if (pack.getType() == typeOfOnDemand)
                        paymentRequest.setAmount(paymentRequest.getAmount() + feeOnDemand);

                    //get payment method and payment
                    paymentRequest.setToken(paymentMethodService.findAllByAccount_Id((long) subscriptions.getIdaccount()).get(0).getPaymentid());
                    stripeService.usePaymentMethod(paymentRequest, account.getCustomerid());

                    //update last payment
                    LocalDate date = LocalDate.now();
                    subscriptions.setLastpayment(date);
                    subscriptionsRepositpry.save(subscriptions);

                    //create invoices
                    Invoices invoices = Invoices.builder()
                            .time(date)
                            .price(subscriptions.getPrice())
                            .status(paymentSuccess)
                            .idsubscriptions(subscriptions.getId())
                            .build();
                    invoicesRepository.save(invoices);
                }
            } else if (subscriptions.getStatus() == cancelRenewal) {
                subscriptions.setStatus(cancelled);
                subscriptionsRepositpry.save(subscriptions);
            }
        }
    }
}
