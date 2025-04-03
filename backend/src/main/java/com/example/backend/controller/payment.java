package com.example.backend.controller;

import com.example.backend.entity.Account;
import com.example.backend.entity.Invoices;
import com.example.backend.entity.Subscriptions;
import com.example.backend.model.dto.CardDTO;
import com.example.backend.model.dto.PaymentRequest;
import com.example.backend.repository.InvoicesRepository;
import com.example.backend.repository.SubscriptionsRepositpry;
import com.example.backend.service.account.AccountService;
import com.example.backend.service.payment.StripeService;
import com.example.backend.service.paymentmethod.PaymentMethodService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class payment {

    @Autowired
    private StripeService stripeService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SubscriptionsRepositpry subscriptionsRepositpry;

    @Autowired
    private InvoicesRepository invoicesRepository;

    private static final Logger logger = LoggerFactory.getLogger(auth.class);
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    private static final int paymentSuccess = 1;
    private static final int lengthKey = 20;

    // Create key random
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @PostMapping("/payment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        logger.info("/api/payment");

        try {
            String id = stripeService.chargeCustomer(paymentRequest);
            return ResponseEntity.ok(id);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi thanh toán: " + e.getMessage());
        }
    }


    @PostMapping("/createpaymentmethod")
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        logger.info("/api/createpaymentmethod");
        try {
            Account account = accountService.findById((long) paymentRequest.getIdCus());
            String idCus;

            if (!account.getCustomerid().equals("x"))
                idCus = account.getCustomerid();  // x is default of Customerid in DB
            else idCus = stripeService.createCustomer(paymentRequest);

            accountService.updateAccount((long) paymentRequest.getIdCus(), idCus);
            PaymentMethod paymentMethod =
                    PaymentMethod.retrieve(
                            paymentRequest.getToken()
                    );

            Map<String, Object> params = new HashMap<>();
            params.put("customer", idCus);
            paymentMethod.attach(params);
            paymentMethodService.addPaymentMethod(paymentRequest);

            LocalDate date = LocalDate.now();
            if (paymentRequest.getAmount() != 0) stripeService.usePaymentMethod(paymentRequest, idCus);

            Subscriptions subscriptions = Subscriptions.builder()
                    .daycreate(date)
                    .idaccount(paymentRequest.getIdCus())
                    .idpackage(paymentRequest.getIdPack())
                    .price(paymentRequest.getAmount())
                    .keyserver(generateRandomString(lengthKey))
                    .recurring(paymentRequest.getRecurring())
                    .status(paymentSuccess)
                    .lastpayment(date)
                    .build();
            Subscriptions theSubscriptions = subscriptionsRepositpry.save(subscriptions);

            Invoices invoices = Invoices.builder()
                    .time(date)
                    .price(paymentRequest.getAmount())
                    .status(paymentSuccess)
                    .idsubscriptions(theSubscriptions.getId())
                    .build();
            invoicesRepository.save(invoices);


            return ResponseEntity.ok("created payment method");
        } catch (StripeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lỗi thanh toán: " + e.getMessage());
        }
    }

    @GetMapping("/getpaymentmethod")
    public ResponseEntity<?> getAllPaymentMethod(@RequestParam Long id) throws StripeException {
        Stripe.apiKey = stripeSecretKey;
        logger.info("/api/getpaymentmethod");
        List<CardDTO> cardDTOList = new ArrayList<>();
        for (com.example.backend.entity.PaymentMethod element : paymentMethodService.findAllByAccount_Id(id)) {
            String IdPayMethod = element.getPaymentid();
            PaymentMethod paymentMethod =
                    PaymentMethod.retrieve(IdPayMethod);


            CardDTO cardDTO = CardDTO.builder()
                    .IdPayment(element.getPaymentid())
                    .fLast(paymentMethod.getCard().getLast4())
                    .type(paymentMethod.getCard().getNetworks().getAvailable().get(0))
                    .build();
            cardDTOList.add(cardDTO);
        }

        return ResponseEntity.ok(cardDTOList);
    }
}
