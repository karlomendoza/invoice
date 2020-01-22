package com.example.restservice.config;

import com.launchdarkly.client.LDClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanConfig {

    @Bean
    LDClient getLdClient() {
        return new LDClient("sdk-d3e4544e-0461-4340-86a2-77d6f70fbc06");
    }
}
