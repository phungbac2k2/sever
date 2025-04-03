package com.example.backend.repository;

import com.example.backend.entity.Invoices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoicesRepository extends JpaRepository<Invoices, Long> {
    @Query(value = "SELECT * " +
            "FROM subscriptions " +
            "INNER JOIN invoices ON subscriptions.Id = invoices.IdSubscriptions " +
            "INNER JOIN package ON subscriptions.IdPackage = package.Id " +
            "WHERE subscriptions.IdAccount = :id ",
            nativeQuery = true)
     Page<Object> getInvoices(@Param("id") String id, Pageable pageable);

}
