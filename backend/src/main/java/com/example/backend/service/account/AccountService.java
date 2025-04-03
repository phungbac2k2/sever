package com.example.backend.service.account;

import com.example.backend.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccountService {
     Account findByGmail (String gmail);
     Account findById (Long id);
     void updateAccount (Long Id, String idCus);
     List<Object> findAccountForMonth ();
     List<Object> getCountAccount ();
     Page<Object> searchByName(String name, int page, int size);
     Page<Object> searchByNumberPhone(String numPhone, int page, int size);
     Page<Object> searchByGmail(String gmail, int page, int size);
     Page<Object> getAll (int page, int size);




}
