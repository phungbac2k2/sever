package com.example.backend.entity;

import jakarta.persistence.Entity;
import lombok.*;

@ToString
@Builder
@Data // annotation này sẽ tự động khai báo getter và setter cho class
@AllArgsConstructor // dùng để khai báo constructor với tất cả các properties
@NoArgsConstructor // dùng để khai báo constructor rỗng không có param
public class AuthenticationRequest {
    private String gmail;
    private String password;
}
