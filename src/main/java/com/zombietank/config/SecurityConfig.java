package com.zombietank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:/com/zombietank/config/security-context.xml")
public class SecurityConfig {
}
