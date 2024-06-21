package com.customer_service_app.message_factory;

import java.util.HashMap;
import java.util.Map;

public class MessageProviderFactory {
    private Map<String, Class<? extends MessageProvider>> providers = new HashMap<>();

    public MessageProviderFactory() {
        providers.put("helloWorld", HelloWorldProvider.class);
        providers.put("greeting", GreetingProvider.class);
    }

    public MessageProvider create(String providerName) throws Exception {
        Class<? extends MessageProvider> providerClass = providers.get(providerName);
        if (providerClass == null) {
            throw new IllegalArgumentException("Unknown provider name: " + providerName);
        }
        return providerClass.getDeclaredConstructor().newInstance();
    }
}
