package com.inditex.coreplatform;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class CoreplatformApplication {


	@Bean
	@ConditionalOnMissingBean
	public CacheManager cacheManager() {
		return new CaffeineCacheManager("findPricesCache");
	}

	public static void main(String[] args) {
		SpringApplication.run(CoreplatformApplication.class, args);
	}

}
