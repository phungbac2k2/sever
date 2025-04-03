package com.example.backend.service.Invoices;

import com.example.backend.entity.Invoices;
import com.example.backend.entity.Subscriptions;
import com.example.backend.repository.InvoicesRepository;
import com.example.backend.service.subscriptions.SubscriptionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InvoicesServiceImpl implements InvoicesService{
    private InvoicesRepository invoicesRepository;

    public InvoicesServiceImpl (InvoicesRepository theInvoicesRepository){
        invoicesRepository= theInvoicesRepository;
    }
    @Override
    public Page<Object> findAllByIdSubscriptions(Long id, int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size);
        return invoicesRepository.getInvoices(id.toString(),pageable);
    }
}
