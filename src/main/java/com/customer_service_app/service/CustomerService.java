package com.customer_service_app.service;

import com.customer_service_app.controller.CustomerDto;
import com.customer_service_app.entity.Customer;
import com.customer_service_app.repo.CustomerJpaRepo;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private  final CustomerJpaRepo repository;

    public Customer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<Customer> findAllByCountry(String country) {
        return repository.findAllByCountry(country);
    }

    public List<Customer> findByCountryAndBirthDateBetween(String country, @Nullable LocalDate from, @Nullable LocalDate to) {
        return repository.findByCountryAndBirthDateBetween(country, from, to);
    }

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
        val customer = findById(id);
        repository.delete(customer);
    }

    public String forAroundAdvice(String str) {
        return str;
    }

    public List<Customer> setCountryToEachCustomer(String str) {
        return findAll();
    }
}
