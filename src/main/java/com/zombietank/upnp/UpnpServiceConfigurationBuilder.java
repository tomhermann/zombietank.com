package com.zombietank.upnp;

import java.util.Collection;

import org.teleal.cling.DefaultUpnpServiceConfiguration;
import org.teleal.cling.UpnpServiceConfiguration;
import org.teleal.cling.binding.xml.DeviceDescriptorBinder;
import org.teleal.cling.binding.xml.ServiceDescriptorBinder;
import org.teleal.cling.model.types.ServiceType;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

public class UpnpServiceConfigurationBuilder {
	private Collection<ServiceType> exclusiveServiceTypes = Lists.newArrayList();
	private Optional<ServiceDescriptorBinder> serviceDescriptorBinder = Optional.absent();
	private Optional<DeviceDescriptorBinder> deviceDescriptorBinder = Optional.absent();
	private boolean discoveryEnabled = true;
	
	public UpnpServiceConfigurationBuilder withSupportedServiceType(ServiceType serviceType) {
		exclusiveServiceTypes.add(serviceType);
		return this;
	}
	
	/***
	 * Specifies whether or not to drop incoming notifications and search responses immediately. Defaults to enabled.
	 */
	public UpnpServiceConfigurationBuilder withDiscoveryEnabled(boolean enabled) {
		this.discoveryEnabled = enabled;
		return this;
	}
	
	public UpnpServiceConfigurationBuilder withServiceDescriptorBinder(ServiceDescriptorBinder serviceDescriptorBinder) {
		this.serviceDescriptorBinder = Optional.of(serviceDescriptorBinder);
		return this;
	}
	public UpnpServiceConfigurationBuilder withDeviceDescriptorBinder(DeviceDescriptorBinder deviceDescriptorBinder) {
		this.deviceDescriptorBinder = Optional.of(deviceDescriptorBinder);
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
			
			@Override
			protected DeviceDescriptorBinder createDeviceDescriptorBinderUDA10() {
				return deviceDescriptorBinder.isPresent() ? deviceDescriptorBinder.get() : super.createDeviceDescriptorBinderUDA10();
			}
		};
	}
}
