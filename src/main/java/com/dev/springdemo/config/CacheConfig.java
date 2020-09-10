package com.dev.springdemo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {

        List<CaffeineCache> caches = List.of(
                new CaffeineCache("customers", Caffeine.newBuilder().expireAfterAccess(5, MINUTES).build()),
                new CaffeineCache("roles", Caffeine.newBuilder().expireAfterAccess(10, MINUTES).build()),
                new CaffeineCache("users", Caffeine.newBuilder().expireAfterAccess(15, MINUTES).build())
        );


        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
