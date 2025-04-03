package com.example.backend.service.Invoices;

import org.springframework.data.domain.Page;

import java.util.List;
public interface InvoicesService {
     Page<Object> findAllByIdSubscriptions (Long id, int size, int page);
}
