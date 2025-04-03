package com.example.backend.service.paymentmethod;

import com.example.backend.entity.PaymentMethod;
import com.example.backend.model.dto.PaymentRequest;
import com.example.backend.repository.PaymentMethodRepository;
import com.example.backend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private AccountService accountService;

    public void addPaymentMethod(PaymentRequest paymentRequest) {
        if (paymentRequest.getFirstTime() == 1) {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setPaymentid(paymentRequest.getToken());
            paymentMethod.setAccount(accountService.findById((long) paymentRequest.getIdCus()));
            paymentMethodRepository.save(paymentMethod);
        }

    }

    public List<PaymentMethod> findAllByAccount_Id(Long id) {
        return paymentMethodRepository.findAllByAccount_Id(id);
    }
}
