package com.customer_service_app.controller;

import com.customer_service_app.entity.Customer;
import com.customer_service_app.repo.CustomerJpaRepo;
import com.customer_service_app.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerJpaRepo repository;
    private final CustomerService service;

    @GetMapping
    ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> get(@PathVariable Long id) {
        val found = repository.findById(id).orElse(null);
        return ResponseEntity.ofNullable(found);
    }

    @PutMapping("/{id}")
    ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<Customer> create(@RequestBody CustomerDto request) {
        return ResponseEntity.ok(service.save(request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
