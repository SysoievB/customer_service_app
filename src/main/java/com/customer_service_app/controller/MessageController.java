package com.customer_service_app.controller;

import com.customer_service_app.message_factory.MessageProvider;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MessageController {
    private final MessageProvider messageProvider;

    @GetMapping("/message")
    public String getMessage() {
        return messageProvider.getMessage();
    }
}