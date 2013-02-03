package com.zombietank.config;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.binding.xml.DeviceDescriptorBinder;
import org.teleal.cling.binding.xml.UDA10DeviceDescriptorBinderImpl;

import com.zombietank.upnp.UpnpManager;
import com.zombietank.upnp.UpnpServiceConfigurationBuilder;
import com.zombietank.upnp.wemo.WemoBasicEventServiceType;

@Configuration
public class UpnpConfig {

	@Bean(destroyMethod = "shutdown")
	public UpnpService upnpService() {
		return new UpnpServiceImpl(upnpServiceConfiguration());
	}

	protected UpnpServiceConfiguration upnpServiceConfiguration() {
		return new UpnpServiceConfigurationBuilder().withSupportedServiceType(new WemoBasicEventServiceType()).withDeviceDescriptorBinder(deviceBinder()).build();
	}

	private DeviceDescriptorBinder deviceBinder() {
		// TODO: This is where the magic needs to happen.
		return new UDA10DeviceDescriptorBinderImpl();
	}

	@Bean
	@Singleton
	public UpnpManager upnpManager() {
		return new UpnpManager(upnpService());
	}
	
}
