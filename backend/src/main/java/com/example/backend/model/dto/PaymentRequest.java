package com.example.backend.model.dto;

import lombok.*;

@ToString
@Builder
@Data // annotation này sẽ tự động khai báo getter và setter cho class
@AllArgsConstructor // dùng để khai báo constructor với tất cả các properties
@NoArgsConstructor // dùng để khai báo constructor rỗng không có param
public class PaymentRequest {

    public String token;
    public int idCus;
    public int idPack;
    public int amount;
    public String nameCus;
    public String gmail;
    public int recurring;
    public int firstTime;
}
