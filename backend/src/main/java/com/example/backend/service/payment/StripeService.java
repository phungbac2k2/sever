package com.example.backend.service.payment;

import com.example.backend.model.dto.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String secretKey;

    public String chargeCustomer(PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentRequest.getAmount());
        params.put("currency", "usd");
        params.put("source", paymentRequest.getToken());

        Charge charge = Charge.create(params);

        return charge.getId();
    }

    public void usePaymentMethod(PaymentRequest paymentRequest, String idCus) throws StripeException {
        Stripe.apiKey = secretKey;
        Map<String, Object> automaticPaymentMethods =
                new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        automaticPaymentMethods.put("allow_redirects", "never");
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentRequest.getAmount() * 100);
        params.put("currency", "usd");
        params.put("payment_method", paymentRequest.getToken());
        params.put("customer", idCus);
        params.put("automatic_payment_methods", automaticPaymentMethods);
        params.put("confirm", true);
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        paymentIntent.getId();
    }

    public String createCustomer(PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = secretKey;

        Customer customer = Customer.create(
                CustomerCreateParams.builder()
                        .setName(paymentRequest.getNameCus())
                        .setEmail(paymentRequest.getGmail())
                        .build()
        );

        return customer.getId();
    }
}

