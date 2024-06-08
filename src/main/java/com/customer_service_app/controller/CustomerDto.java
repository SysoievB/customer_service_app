package com.customer_service_app.controller;

import java.time.LocalDate;

public record CustomerDto(String name, String surname, LocalDate birthDate, String country) {
}

