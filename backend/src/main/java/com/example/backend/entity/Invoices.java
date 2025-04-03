package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@ToString
@Builder
@Entity // dùng để khai báo với Spring Boot rằng đây là 1 entity biểu diễn table trong database
@Data // annotation này sẽ tự động khai báo getter và setter cho class
@AllArgsConstructor // dùng để khai báo constructor với tất cả các properties
@NoArgsConstructor // dùng để khai báo constructor rỗng không có param
public class Invoices {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private int idsubscriptions;
    private int price;
    private LocalDate time;
    private int status;

}
