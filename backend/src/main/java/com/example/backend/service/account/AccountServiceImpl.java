package com.example.backend.service.account;

import com.example.backend.entity.Account;
import com.example.backend.model.dto.AccountDTO;
import com.example.backend.model.dto.AccountForMonthDTO;
import com.example.backend.model.mapper.AccountMapper;
import com.example.backend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository theAccountRepository) {
        accountRepository = theAccountRepository;
    }

    @Override
    public Account findByGmail(String gmail) {
        return accountRepository.findByGmail(gmail).get(0);

    }

    @Override
    public Account findById(Long id) {
        Optional<Account> data = accountRepository.findById(id);
        if (data.isPresent()) {
            return data.get();
        } else {
            return null;
        }
    }

    @Override
    public void updateAccount(Long id, String idCus) {
        Account account = this.findById(id);
        account.setCustomerid(idCus);
        accountRepository.save(account);

    }

    @Override
    public List<Object> findAccountForMonth() {

        return accountRepository.getAccountCountByMonth();
    }

    @Override
    public List<Object> getCountAccount() {
        return accountRepository.getCountAccount();
    }

    @Override
    public Page<Object> searchByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.searchByName(name, pageable);

    }

    @Override
    public Page<Object> searchByNumberPhone(String numPhone, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.searchByNumberPhone(numPhone, pageable);
    }

    @Override
    public Page<Object> searchByGmail(String gmail, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.searchByGmail(gmail, pageable);
    }

    @Override
    public Page<Object> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.getAll(pageable);
    }
}
