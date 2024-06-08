package com.customer_service_app.repo;

import com.customer_service_app.entity.Customer;
import com.customer_service_app.entity.Customer_;
import jakarta.annotation.Nullable;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.data.jpa.domain.Specification.where;

public interface CustomerJpaRepo extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    List<Customer> findAllByCountry(String country);

    default List<Customer> findByCountryAndBirthDateBetween(String country, @Nullable LocalDate from, @Nullable LocalDate to) {
        val specification = where(getByCountry(country)).and(dateBetween(from, to));
        return findAll(specification);
    }

    static Specification<Customer> getByCountry(String country) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Customer_.country), country));
    }

    static Specification<Customer> dateBetween(@Nullable LocalDate from, @Nullable LocalDate to) {
        if (isNull(from) && isNull(to)) {
            return null;
        } else if (nonNull(from) && isNull(to)) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Customer_.birthDate), from));
        } else if (isNull(from)) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Customer_.birthDate), to));
        } else {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get(Customer_.birthDate), from),
                    criteriaBuilder.lessThanOrEqualTo(root.get(Customer_.birthDate), to)
            ));
        }
    }
}
