package com.example.backend.repository;

import com.example.backend.entity.Package;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package,Long> {
     List<Package> findAll();
     List<Package> findAllById(Long id);
    @Query(value = "SELECT * " +
            "FROM subscriptions " +
            "JOIN package " +
            "ON subscriptions.IdPackage = package.Id " +
            "WHERE subscriptions.IdAccount = :idAccount", nativeQuery = true)
     Page<Object> getPackageByIdAccount(@Param("idAccount") String idAccount, Pageable pageable);
     Package save (Package pack);
}

