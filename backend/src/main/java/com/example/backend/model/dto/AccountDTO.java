package com.example.backend.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Data // annotation này sẽ tự động khai báo getter và setter cho class
@AllArgsConstructor // dùng để khai báo constructor với tất cả các properties
@NoArgsConstructor // dùng để khai báo constructor rỗng không có param
public class AccountDTO {

    private Long id;
    private String name;
    private String numberphone;
    private String gmail;
    private String role;
    private boolean isLogin;
    private LocalDate timecreate;
}