package com.example.backend.service.pack;

import com.example.backend.entity.Account;
import com.example.backend.entity.Package;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PackageService {
     List<Package> findAll ();
     List<Package> findAllById (Long id);
     Page<Object> getPackageByIdAccount(Long idAccount, int size , int page);
     Package Save (Package pack);

}
