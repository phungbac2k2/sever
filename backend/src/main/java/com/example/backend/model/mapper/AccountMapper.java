package com.example.backend.model.mapper;

import com.example.backend.entity.Account;
import com.example.backend.model.dto.AccountDTO;

public class AccountMapper  {

    public static AccountDTO toAccountDTO (Account account){
        AccountDTO accountDTO= new AccountDTO();

        accountDTO.setGmail(account.getGmail());
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setNumberphone(account.getNumberphone());
        accountDTO.setRole(account.getRole());
        accountDTO.setLogin(true);
        accountDTO.setTimecreate(account.getTimecreate());
    return accountDTO;
    }
    public static AccountDTO toAccountDTOFalse (){
        AccountDTO accountDTO= new AccountDTO();

        accountDTO.setGmail(null);
        accountDTO.setId(null);
        accountDTO.setName(null);
        accountDTO.setNumberphone(null);
        accountDTO.setRole(null);
        accountDTO.setLogin(false);
        accountDTO.setTimecreate(null);

        return accountDTO;
    }


}
