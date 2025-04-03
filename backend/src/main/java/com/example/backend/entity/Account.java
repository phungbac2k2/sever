package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ToString
@Builder
@Entity // dùng để khai báo với Spring Boot rằng đây là 1 entity biểu diễn table trong database
@Data // annotation này sẽ tự động khai báo getter và setter cho class
@AllArgsConstructor // dùng để khai báo constructor với tất cả các properties
@NoArgsConstructor // dùng để khai báo constructor rỗng không có param
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String numberphone;
    private String gmail;
    private String password;
    private String role;
    private String customerid;
    private LocalDate timecreate;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<PaymentMethod> paymentMethods = new ArrayList<>();


}
