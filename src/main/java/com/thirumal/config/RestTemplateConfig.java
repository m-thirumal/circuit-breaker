package com.thirumal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Thirumal
 */
@Configuration
public class RestTemplateConfig {
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
