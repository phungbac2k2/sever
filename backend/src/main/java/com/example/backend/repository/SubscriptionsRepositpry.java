package com.example.backend.repository;

import com.example.backend.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionsRepositpry extends JpaRepository<Subscriptions, Long> {
    List<Subscriptions> findAllByIdaccount(Long id);

    List<Subscriptions> findAll();

    @Query(value = "SELECT COUNT(DISTINCT IdAccount) AS TotalUniqueIdAccounts " +
            "FROM subscriptions;", nativeQuery = true)
    List<Object> getCountAccountSub();

    @Query(value = "SELECT package.name, COUNT(*) AS SoLanXuatHien " +
            "FROM subscriptions " +
            "JOIN package ON subscriptions.IdPackage = package.Id " +
            "GROUP BY package.name;", nativeQuery = true)
    List<Object> getCountPackage();

    @Query(value = "SELECT MONTHNAME(a.daycreate) AS month, COUNT(*) AS countaccount " +
            "            FROM subscriptions a " +
            "            WHERE a.daycreate >= DATE_SUB(CURRENT_DATE, INTERVAL 5 MONTH)  " +
            "            GROUP BY month;", nativeQuery = true)
    List<Object> getCountPackagePerMonth();

    @Modifying
    @Transactional
    @Query(value = "UPDATE subscriptions " +
            "SET price = CASE " +
            "    WHEN recurring = 30 THEN :newPriceMonth " +
            "    WHEN recurring = 365 THEN :newPriceYear " +
            "    ELSE price " +
            "END " +
            "WHERE IdPackage = :idPackage ", nativeQuery = true)
    void loadSub(@Param("idPackage") String idPackage, @Param("newPriceMonth") String newPriceMonth, @Param("newPriceYear") String newPriceYear);

    @Modifying
    @Transactional
    @Query(value = " UPDATE subscriptions " +
            "    SET status = 0 " +
            "    WHERE Id= :idSub", nativeQuery = true)
    int Cancel(@Param("idSub") String idSub);


    @Query(value = " SELECT COUNT(*) " +
            "FROM subscriptions " +
            "WHERE price = 0 AND IdAccount = :idAccount", nativeQuery = true)
    int check(@Param("idAccount") String idAccount);
}
