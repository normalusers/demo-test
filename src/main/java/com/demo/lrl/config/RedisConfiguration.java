package com.demo.lrl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;

/**
 * @author liuruilin
 */
@Configuration
public class RedisConfiguration {

    @Resource
    private RedisConnectionFactory connectionFactory;

    @Bean
    public ObjectRedisTemplate objectRedisTemplate(){
        ObjectRedisTemplate redisTemplate = new ObjectRedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.java());

        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.java());
        return redisTemplate;
    }
}
