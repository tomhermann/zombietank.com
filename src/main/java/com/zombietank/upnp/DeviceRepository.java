package com.zombietank.upnp;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.RemoteDevice;
import org.teleal.cling.registry.DefaultRegistryListener;
import org.teleal.cling.registry.Registry;

import com.google.common.collect.Sets;

public class DeviceRepository<T> extends DefaultRegistryListener {
	private static final Logger logger = LoggerFactory.getLogger(DeviceRepository.class);
	private final Set<T> devices = Sets.newHashSet();
	private final DeviceFunction<T> deviceFunction;

	public DeviceRepository(DeviceFunction<T> deviceFunction) {
		this.deviceFunction = deviceFunction;
	}
	
	@Override
	public void remoteDeviceDiscoveryStarted(Registry registry,	RemoteDevice device) {
		logger.info("Started remote device discovery.");
		logger.debug("RemoteDevice: {}", device.getDisplayString());
	}
	
	@Override
	public void remoteDeviceDiscoveryFailed(Registry registry, RemoteDevice device, Exception ex) {
		logger.error("Remote device discovery failed! Device: {}", device.getDisplayString(), ex);
	}
	
	@Override
	public void remoteDeviceUpdated(Registry registry, RemoteDevice device) {
		logger.info("Device updated: {}", device.getDisplayString());
	}
	
	@Override
	public void deviceAdded(Registry registry, @SuppressWarnings("rawtypes") Device device) {
		logger.info("Device added: {}", device.getDisplayString());
		getDevices().add(deviceFunction.apply(device));
	}
	
	@Override
	public void deviceRemoved(Registry registry, @SuppressWarnings("rawtypes") Device device) {
		logger.info("Device removed: {}", device.getDisplayString());
		getDevices().remove(deviceFunction.apply(device));
	}

	public Set<T> getDevices() {
		return devices;
	}
}