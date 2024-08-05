package com.example.redis.configuration;

import com.example.redis.model.hash.ExchangeRate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
////        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
////        template.setHashKeySerializer(new StringRedisSerializer());
////        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
////        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        return template;
//    }

//    @Bean
//    public RedisTemplate<String, ExchangeRate> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        final RedisTemplate<String, ExchangeRate> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        // Setting up a JSON serializer for values to store JSON objects
////        template.setKeySerializer(new StringRedisSerializer());
////        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//
//        // You might want to use a Jackson2JsonRedisSerializer or similar for specific configurations
////        template.setHashKeySerializer(new StringRedisSerializer());
////        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        return template;
//    }
}
