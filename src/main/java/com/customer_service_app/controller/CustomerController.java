package com.customer_service_app.controller;

import com.customer_service_app.entity.Customer;
import com.customer_service_app.service.CustomerService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping
    ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> get(@PathVariable Long id) {
        val found = service.findById(id);
        return ResponseEntity.ofNullable(found);
    }

    @GetMapping("/country")
    ResponseEntity<List<Customer>> findAllByCountry(@RequestParam String country) {
        return ResponseEntity.ok(service.findAllByCountry(country));
    }

    @GetMapping("/country/{country}")
    ResponseEntity<List<Customer>> findAllByCountry(
            @PathVariable String country,
            @Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(service.findByCountryAndBirthDateBetween(country, from, to));
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

    @PostMapping("/advice")
    ResponseEntity<String> advice(@RequestParam String str) {
        return ResponseEntity.ok(service.forAroundAdvice(str));
    }

    @PostMapping("/set-country-advice")
    ResponseEntity<List<Customer>> setCountryAdvice(@RequestParam String country) {
        return ResponseEntity.ok(service.setCountryToEachCustomer(country));
    }
}
