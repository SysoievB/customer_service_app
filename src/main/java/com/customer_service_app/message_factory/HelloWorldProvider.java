package com.customer_service_app.message_factory;

public class HelloWorldProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, world!";
    }
}
