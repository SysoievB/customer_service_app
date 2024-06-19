package com.customer_service_app.config;

import com.customer_service_app.utils.ZoneDateTimeToUTCConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomerConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ZoneDateTimeToUTCConverter());
    }
}