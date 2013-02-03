package com.zombietank.config;

import javax.inject.Singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.binding.xml.ServiceDescriptorBinder;
import org.teleal.cling.binding.xml.UDA10ServiceDescriptorBinderImpl;

import com.zombietank.upnp.ServiceConfigurationBuilder;
import com.zombietank.upnp.UpnpManager;
import com.zombietank.upnp.wemo.WemoBasicEventServiceType;

@Configuration
public class UpnpConfig {

	@Bean(destroyMethod = "shutdown")
	public UpnpService upnpService() {
		return new UpnpServiceImpl(upnpServiceConfiguration());
	}

	protected UpnpServiceConfiguration upnpServiceConfiguration() {
		return new ServiceConfigurationBuilder().withSupportedServiceType(new WemoBasicEventServiceType()).withServiceDescriptorBinder(binder()).build();
	}

	protected ServiceDescriptorBinder binder() {
		// TODO: Make this bastard parse WeMo's device descriptor... I think this is the right hook?
		UDA10ServiceDescriptorBinderImpl uda10ServiceDescriptorBinderImpl = new UDA10ServiceDescriptorBinderImpl();
		return uda10ServiceDescriptorBinderImpl;
	}

	@Bean
	@Singleton
	public UpnpManager upnpManager() {
		return new UpnpManager(upnpService());
	}
	
}
