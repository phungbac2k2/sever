package com.example.backend.repository;

import com.example.backend.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long>{

     List<Account> findByGmail(String gmail);
     Optional<Account> findById(Long id);
    @Query(value = "SELECT MONTHNAME(a.timecreate) AS month, COUNT(*) AS countaccount " +
            "FROM Account a " +
            "WHERE a.timecreate >= DATE_SUB(CURRENT_DATE, INTERVAL 5 MONTH) " +
            "AND a.role = 'ROLE_USER' " +
            "GROUP BY month", nativeQuery = true)
     List<Object> getAccountCountByMonth();

    @Query( value = "SELECT *  FROM account WHERE role = 'ROLE_USER' ; ",
            countQuery = "SELECT count(*)  FROM account WHERE role = 'ROLE_USER' ; ",
            nativeQuery = true)
     Page<Object> getAll(@Param("pageable") Pageable pageable);

    @Query(value = "SELECT COUNT(DISTINCT id) AS TotalUniqueIdAccounts " +
            "FROM account;", nativeQuery = true)
     List<Object> getCountAccount();

    @Query(value = "SELECT * FROM account WHERE name LIKE %:name% AND role = 'ROLE_USER'",
            countQuery = "SELECT count(*) FROM account WHERE name LIKE %:name% AND role = 'ROLE_USER'",
            nativeQuery = true)
     Page<Object> searchByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT * FROM account WHERE numberPhone LIKE :numPhone AND role = 'ROLE_USER'",
            countQuery = "SELECT count(*) FROM account WHERE numberPhone LIKE :numPhone AND role = 'ROLE_USER'",
            nativeQuery = true)
     Page<Object> searchByNumberPhone(@Param("numPhone") String numPhone,Pageable pageable);

    @Query(value = "SELECT * FROM account WHERE gmail LIKE :gmail AND role = 'ROLE_USER'",
            countQuery = "SELECT count(*) FROM account WHERE gmail LIKE :gmail AND role = 'ROLE_USER'",
            nativeQuery = true)
     Page<Object> searchByGmail(@Param("gmail") String gmail,Pageable pageable);
 }

