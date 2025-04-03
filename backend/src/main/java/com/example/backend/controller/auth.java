package com.example.backend.controller;

import com.example.backend.config.SecurityConfig;
import com.example.backend.entity.Account;
import com.example.backend.entity.AuthenticationRequest;
import com.example.backend.model.mapper.AccountMapper;
import com.example.backend.service.account.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class auth {
    private AccountServiceImpl accountServiceImpl;
    private SecurityConfig securityConfig;
    private static final Logger logger = LoggerFactory.getLogger(auth.class);

    @Autowired
    public auth(AccountServiceImpl theAccountServiceImpl, SecurityConfig theSecurityConfig) {
        accountServiceImpl = theAccountServiceImpl;
        securityConfig = theSecurityConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        Account account = accountServiceImpl.findByGmail(authenticationRequest.getGmail());
        if (securityConfig.passwordEncoder().matches(authenticationRequest.getPassword(), account.getPassword())) {
            logger.info("Login thành công");
            return ResponseEntity.ok(AccountMapper.toAccountDTO(account));
        } else {
            logger.info("Login thất bại");
            return ResponseEntity.ok(AccountMapper.toAccountDTOFalse());
        }


    }


}
