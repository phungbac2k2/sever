package com.example.backend.controller;

import com.example.backend.service.Invoices.InvoicesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

public class invoices {
    private final InvoicesServiceImpl invoicesServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(auth.class);

    @Autowired
    public invoices(InvoicesServiceImpl theInvoicesServiceimpl) {
        invoicesServiceImpl = theInvoicesServiceimpl;
    }

    @GetMapping("/invoices")
    public ResponseEntity<?> getInvoices(@RequestParam Long id, int size, int page) {
        logger.info("/invoices");

        return ResponseEntity.ok(
                invoicesServiceImpl.findAllByIdSubscriptions(id, size, page)
        );


    }
}
