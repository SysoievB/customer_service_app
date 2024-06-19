package com.customer_service_app.utils;

import jakarta.annotation.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

/**
 * Purpose is to convert all local ZoneDateTime in a project to ZoneDateTime UTC
 */
@Component
public class ZoneDateTimeToUTCConverter implements Converter<ZonedDateTime, ZonedDateTime> {

    @Nullable
    @Override
    public ZonedDateTime convert(@Nullable ZonedDateTime source) {
        return Optional.ofNullable(source)
                .map(zonedDateTime -> zonedDateTime.withZoneSameInstant(UTC))
                .orElse(null);
    }
}
