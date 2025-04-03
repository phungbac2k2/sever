package com.example.backend.service.pack;

import com.example.backend.entity.Account;
import com.example.backend.entity.Package;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.PackageRepository;
import com.example.backend.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;

    @Autowired
    public PackageServiceImpl(PackageRepository thePackageRepository) {
        packageRepository = thePackageRepository;
    }

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll();

    }

    @Override
    public List<Package> findAllById(Long id) {
        return packageRepository.findAllById(id);

    }

    @Override
    public Page<Object> getPackageByIdAccount(Long idAccount, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        return packageRepository.getPackageByIdAccount(idAccount.toString(), pageable);
    }

    @Override
    public Package Save(Package pack) {
        return packageRepository.save(pack);
    }
}
