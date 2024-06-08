package com.customer_service_app.service;

import com.customer_service_app.controller.CustomerDto;
import com.customer_service_app.entity.Customer;
import com.customer_service_app.repo.CustomerJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private  final CustomerJpaRepo repository;

    public Customer save(CustomerDto dto) {
        Customer customer = new Customer(dto.name(), dto.surname(), dto.birthDate(), dto.country());
        return repository.save(customer);
    }

    public Customer update(Long id, CustomerDto dto) {
        return repository.findById(id)
                .map(cust -> cust.update(dto.name(), dto.surname(), dto.birthDate(), dto.country()))
                .map(repository::save)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void delete(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        repository.delete(customer);
    }
}
