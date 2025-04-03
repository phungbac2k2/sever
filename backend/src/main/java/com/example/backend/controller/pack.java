package com.example.backend.controller;

import com.example.backend.entity.Package;
import com.example.backend.service.pack.PackageServiceImpl;
import com.example.backend.service.subscriptions.SubscriptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/package")
public class pack {
    private final PackageServiceImpl packageServiceImpl;
    private final SubscriptionsService subscriptionsService;
    private static final Logger logger = LoggerFactory.getLogger(auth.class);


    @Autowired
    public pack(PackageServiceImpl thePackageServiceImpl, SubscriptionsService theSubscriptionsService) {
        packageServiceImpl = thePackageServiceImpl;
        subscriptionsService = theSubscriptionsService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getallpack() {
        logger.info("/package/getall");

        return ResponseEntity.ok(packageServiceImpl.findAll());
    }

    @GetMapping("/findbyaccountid")
    public ResponseEntity<?> getInvoices(@RequestParam Long id, int size, int page) {
        logger.info("/package/findbyaccountid");

        return ResponseEntity.ok(packageServiceImpl.getPackageByIdAccount(id, size, page - 1));


    }

    @GetMapping("getCountPackage")
    public ResponseEntity<?> getCountPackage() {
        logger.info("/package/getCountPackage");

        return ResponseEntity.ok(subscriptionsService.getCountPackage());


    }

    @GetMapping("getcountpackagepermonth")
    public ResponseEntity<?> getCountPackagePerMonth() {
        logger.info("/package/getcountpackagepermonth");

        return ResponseEntity.ok(subscriptionsService.getCountPackagePerMonth());

    }

    @PostMapping("update")
    public ResponseEntity<?> Update(@RequestBody Package pack) {
        logger.info("/package/update");

        Package resultPack = packageServiceImpl.Save(pack);
        subscriptionsService.loadSub(resultPack);
        return ResponseEntity.ok("update success");

    }

    @GetMapping("cancel")
    public ResponseEntity<?> Cancel(@RequestParam Long idSub) {
        return ResponseEntity.ok(subscriptionsService.Cancel(idSub));
    }

    @GetMapping("check")
    public ResponseEntity<?> Check(@RequestParam Long idAccount) {
        return ResponseEntity.ok(subscriptionsService.check(idAccount));
    }


}
