package com.ssafy.c202.formybaby.global.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Map<String, LatLon>> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Map<String, LatLon>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Optionally, configure serializers/deserializers, etc.
        return template;
    }
}