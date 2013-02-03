package com.zombietank.upnp;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.teleal.cling.DefaultUpnpServiceConfiguration;
import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.binding.xml.DeviceDescriptorBinder;
import org.teleal.cling.binding.xml.ServiceDescriptorBinder;
import org.teleal.cling.model.types.ServiceType;

public class UpnpServiceConfigurationBuilderTest {
	private UpnpServiceConfigurationBuilder builder;
	
	@Before
	public void setup() {
		this.builder = new UpnpServiceConfigurationBuilder();
	}
	
	@Test
	public void whenDiscoveryIsDisabledExclusiveServiceTypesIsNull() {
		UpnpServiceConfiguration configuration = builder.withDiscoveryEnabled(false).build();
		
		assertThat(configuration.getExclusiveServiceTypes(), is(nullValue()));
	}

	@Test
	public void whenDiscoveryIsEnabledExclusiveServiceTypesIsEmptyArray() {
		UpnpServiceConfiguration configuration = builder.withDiscoveryEnabled(true).build();
		
		assertThat(configuration.getExclusiveServiceTypes().length, is(0));
	}
	
	@Test
	public void discoveryEnabledDefaultsToTrue() {
		UpnpServiceConfiguration configuration = builder.build();
		
		assertThat(configuration.getExclusiveServiceTypes().length, is(0));
	}
	
	@Test
	public void whenDiscoveryEnabledAndServiceTypesSpecifiedOnlyUseSpecifiedTypes() {
		ServiceType serviceType = mock(ServiceType.class);
		ServiceType otherServiceType = mock(ServiceType.class);
		
		UpnpServiceConfiguration configuration = builder
			.withSupportedServiceType(serviceType)
			.withSupportedServiceType(otherServiceType)
		.build();
		
		Collection<ServiceType> exclusiveServiceTypes = Arrays.asList(configuration.getExclusiveServiceTypes());
		assertThat(exclusiveServiceTypes.size(), is(2));
		assertThat(exclusiveServiceTypes, hasItems(serviceType, otherServiceType));
	}

	@Test
	public void customServiceDescriptorBinderCanBeSpecified() {
		ServiceDescriptorBinder serviceDescriptorBinder = mock(ServiceDescriptorBinder.class);
		
		UpnpServiceConfiguration configuration = builder.withServiceDescriptorBinder(serviceDescriptorBinder).build();
		
		assertThat(configuration.getServiceDescriptorBinderUDA10(), is(sameInstance(serviceDescriptorBinder)));
	}
	
	@Test
	public void whenNoServiceDescriptorBinderSpecifiedUseDefault() {
		DefaultUpnpServiceConfiguration defaultConfiguration = new DefaultUpnpServiceConfiguration();
		Class<? extends ServiceDescriptorBinder> defaultBinderClass = defaultConfiguration.getServiceDescriptorBinderUDA10().getClass();
		
		UpnpServiceConfiguration configuration = builder.build();
		
		assertThat(configuration.getServiceDescriptorBinderUDA10(), is(instanceOf(defaultBinderClass)));
	}

	@Test
	public void customDeviceDescriptorBinderCanBeSpecified() {
		DeviceDescriptorBinder deviceDescriptorBinder = mock(DeviceDescriptorBinder.class);
		
		UpnpServiceConfiguration configuration = builder.withDeviceDescriptorBinder(deviceDescriptorBinder).build();
		
		assertThat(configuration.getDeviceDescriptorBinderUDA10(), is(sameInstance(deviceDescriptorBinder)));
	}
	
	@Test
	public void whenNoDeviceDescriptorBinderSpecifiedUseDefault() {
		DefaultUpnpServiceConfiguration defaultConfiguration = new DefaultUpnpServiceConfiguration();
		Class<? extends DeviceDescriptorBinder> defaultBinderClass = defaultConfiguration.getDeviceDescriptorBinderUDA10().getClass();
		
		UpnpServiceConfiguration configuration = builder.build();
		
		assertThat(configuration.getDeviceDescriptorBinderUDA10(), is(instanceOf(defaultBinderClass)));
	}
}
