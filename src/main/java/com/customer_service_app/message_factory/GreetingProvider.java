package com.customer_service_app.message_factory;

public class GreetingProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Greetings!";
    }
}
