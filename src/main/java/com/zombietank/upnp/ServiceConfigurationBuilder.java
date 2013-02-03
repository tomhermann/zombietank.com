package com.zombietank.upnp;

import java.util.Collection;

import org.teleal.cling.DefaultUpnpServiceConfiguration;
import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.binding.xml.ServiceDescriptorBinder;
import org.teleal.cling.model.types.ServiceType;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class ServiceConfigurationBuilder {
	private Collection<ServiceType> exclusiveServiceTypes = Lists.newArrayList();
	private Optional<ServiceDescriptorBinder> serviceDescriptorBinder = Optional.absent();
	private boolean discoveryEnabled = true;
	
	public ServiceConfigurationBuilder withSupportedServiceType(ServiceType serviceType) {
		exclusiveServiceTypes.add(serviceType);
		return this;
	}
	
	/***
	 * Specifies whether to drop incoming notifications and search responses immediately. Defaults to enabled.
	 */
	public ServiceConfigurationBuilder withDiscoveryEnabled(boolean enabled) {
		this.discoveryEnabled = enabled;
		return this;
	}
	
	public ServiceConfigurationBuilder withServiceDescriptorBinder(ServiceDescriptorBinder serviceDescriptorBinder) {
		this.serviceDescriptorBinder = Optional.of(serviceDescriptorBinder);
		return this;
	}
	
	public UpnpServiceConfiguration build() {
		return new DefaultUpnpServiceConfiguration() {
			@Override
			public ServiceType[] getExclusiveServiceTypes() {
				return discoveryEnabled ? exclusiveServiceTypes.toArray(new ServiceType[exclusiveServiceTypes.size()]) : null;
			}
			
			@Override
			protected ServiceDescriptorBinder createServiceDescriptorBinderUDA10() {
				return serviceDescriptorBinder.isPresent() ? serviceDescriptorBinder.get() : super.createServiceDescriptorBinderUDA10();
			}
		};
	}
}
