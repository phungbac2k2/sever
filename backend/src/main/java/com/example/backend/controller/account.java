package com.example.backend.controller;

import com.example.backend.service.account.AccountServiceImpl;
import com.example.backend.service.subscriptions.SubscriptionsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class account {
    private AccountServiceImpl accountServiceImpl;
    private SubscriptionsImpl subscriptionsImpl;
    private static final Logger logger = LoggerFactory.getLogger(auth.class);


    @Autowired
    public account(AccountServiceImpl theAccountServiceImpl, SubscriptionsImpl theSubscriptionsImpl) {
        accountServiceImpl = theAccountServiceImpl;
        subscriptionsImpl = theSubscriptionsImpl;
    }

    @GetMapping("/getformonth")
    public ResponseEntity<?> getAllAccount() {
        logger.info("/account/getformonth");
        return ResponseEntity.ok(accountServiceImpl.findAccountForMonth());
    }

    @GetMapping("/accountsub")
    public ResponseEntity<?> getAccountSub() {
        logger.info("/account/accountsub");
        List<Long> accountSub = new ArrayList<>();
        accountSub.add((Long) accountServiceImpl.getCountAccount().get(0));
        accountSub.add((Long) subscriptionsImpl.getCountAccountSub().get(0));
        return ResponseEntity.ok(accountSub);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String data, String type, int page, int size) {
        logger.info("/account/search");
        return switch (type) {
            case "numberPhone" -> ResponseEntity.ok(accountServiceImpl.searchByNumberPhone(data, page - 1, size));
            case "name" -> ResponseEntity.ok(accountServiceImpl.searchByName(data, page - 1, size));
            default -> ResponseEntity.ok(accountServiceImpl.searchByGmail(data, page - 1, size));
        };

    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(@RequestParam int page, int size) {
        logger.info("/account/getall");
        return ResponseEntity.ok(accountServiceImpl.getAll(page - 1, size));
    }
}

