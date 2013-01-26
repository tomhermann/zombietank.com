package com.zombietank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource("WEB-INF/spring/*-context.xml")
@PropertySource("config/application.properties")
@ComponentScan(basePackages = "com.zombietank", excludeFilters = { @Filter(Configuration.class) })
public class ApplicationConfig {
}
