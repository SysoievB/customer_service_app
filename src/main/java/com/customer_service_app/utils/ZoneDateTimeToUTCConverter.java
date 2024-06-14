package com.customer_service_app.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class ZoneDateTimeToUTCConverter implements Converter<ZonedDateTime, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(ZonedDateTime source) {
        return ZonedDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
    }
}
