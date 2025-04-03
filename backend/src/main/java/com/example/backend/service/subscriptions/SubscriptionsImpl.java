package com.example.backend.service.subscriptions;

import com.example.backend.entity.Subscriptions;
import com.example.backend.repository.SubscriptionsRepositpry;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Package;

import java.util.List;

@Service
public class SubscriptionsImpl implements SubscriptionsService {
    private SubscriptionsRepositpry subscriptionsRepositpry;

    public SubscriptionsImpl(SubscriptionsRepositpry theSubscriptionsRepositpry) {
        subscriptionsRepositpry = theSubscriptionsRepositpry;
    }

    @Override
    public List<Subscriptions> findByAccountid(Long id) {

        return subscriptionsRepositpry.findAllByIdaccount(id);
    }

    @Override
    public List<Subscriptions> findAll() {
        return subscriptionsRepositpry.findAll();
    }

    @Override
    public List<Object> getCountAccountSub() {
        return subscriptionsRepositpry.getCountAccountSub();
    }

    @Override
    public List<Object> getCountPackage() {
        return subscriptionsRepositpry.getCountPackage();
    }

    @Override
    public List<Object> getCountPackagePerMonth() {
        return subscriptionsRepositpry.getCountPackagePerMonth();
    }

    @Override
    public void loadSub(Package pack) {

        subscriptionsRepositpry.loadSub(String.valueOf(pack.getId()), String.valueOf(pack.getPricemonth()), String.valueOf(pack.getPriceyear()));
    }

    @Override
    public int Cancel(Long idSub) {
        return subscriptionsRepositpry.Cancel(idSub.toString());
    }

    @Override
    public int check(Long idAccount) {
        return subscriptionsRepositpry.check(idAccount.toString());
    }

}
