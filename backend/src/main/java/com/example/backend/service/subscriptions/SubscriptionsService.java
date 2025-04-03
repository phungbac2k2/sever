package com.example.backend.service.subscriptions;

import com.example.backend.entity.Package;
import com.example.backend.entity.Subscriptions;

import java.util.List;
public interface SubscriptionsService {
     List<Subscriptions> findByAccountid (Long id);
     List<Subscriptions> findAll ();
     List<Object> getCountAccountSub();
     List<Object> getCountPackage();
     List<Object> getCountPackagePerMonth();
     void loadSub(Package pack);
     int Cancel (Long idSub);
     int check(Long idAccount);
}
