package com.zombietank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.zombietank.config.annotation.Dev;
import com.zombietank.config.annotation.Prod;

@Configuration
@ComponentScan(basePackages = "com.zombietank", excludeFilters = { @Filter(Configuration.class) })
@ImportResource("classpath:/com/zombietank/config/task-context.xml")
public class ComponentConfig {

	/** Properties to support the 'embedded' mode of operation. */
	@Dev @Configuration
	@PropertySource("config/dev.properties")
	static class Development {
	}

	/** Properties to support the 'standard' mode of operation. */
	@Prod @Configuration
	@PropertySource("config/prod.properties")
	static class Production {
	}
}
