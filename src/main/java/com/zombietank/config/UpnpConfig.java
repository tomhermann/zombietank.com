package com.zombietank.config;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;

import com.zombietank.upnp.UpnpManager;

@Configuration
public class UpnpConfig {

	@Bean(destroyMethod = "shutdown")
	public UpnpService upnpService() {
		return new UpnpServiceImpl();
	}

	@Bean
	@Singleton
	public UpnpManager upnpManager() {
		return new UpnpManager(upnpService());
	}
}
