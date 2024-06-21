package com.customer_service_app.config;

import com.customer_service_app.message_factory.MessageProvider;
import com.customer_service_app.message_factory.MessageProviderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MessageFactoryConfig {
    @Bean
    public MessageProviderFactory messageProviderFactory() {
        return new MessageProviderFactory();
    }

    @Bean
    @Scope("prototype")
    public MessageProvider messageProvider(MessageProviderFactory factory) throws Exception {
        return factory.create("greeting");
    }
}