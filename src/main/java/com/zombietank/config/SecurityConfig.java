package com.zombietank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("META-INF/spring/security-context.xml")
public class SecurityConfig {
}
