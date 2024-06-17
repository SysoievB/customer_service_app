package com.customer_service_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Customer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String surname;

    @Setter
    String country;

    LocalDate birthDate;

    @Transient
    int age;

    @CreationTimestamp
    ZonedDateTime createdAt;

    @UpdateTimestamp
    ZonedDateTime updatedAt;

    public Customer(String name, String surname, LocalDate birthDate, String country) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.createdAt = ZonedDateTime.now();
    }

    @PostLoad
    public void getAge() {
        this.age = (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    public Customer update(String name, String surname, LocalDate birthDate, String country) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.country = country;
        this.updatedAt = ZonedDateTime.now();
        return this;
    }

    public static Customer getEmptyCustomer() {
        return new Customer("", "", LocalDate.now(), "");
    }
}
