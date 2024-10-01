package com.ruoyi.common.config;

import com.ruoyi.common.config.serializer.TimestampLocalDateTimeDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LocalDateTimeSerializerConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
//            builder.serializerByType(LocalDateTime.class, new TimestampLocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, new TimestampLocalDateTimeDeserializer());
        };
    }
}