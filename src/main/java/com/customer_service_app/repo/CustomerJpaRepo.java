package com.customer_service_app.repo;

import com.customer_service_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepo extends JpaRepository<Customer, Long> {
}
