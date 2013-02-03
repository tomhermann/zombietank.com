package com.zombietank.upnp;

import java.util.Collection;

import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.model.message.header.STAllHeader;
import org.teleal.cling.model.meta.DeviceIdentity;

public class UpnpManager {
	private static final Logger logger = LoggerFactory.getLogger(UpnpManager.class);
	private final UpnpService upnpService;
	private final DeviceRepository<DeviceIdentity> listener;
	
	@Inject
	public UpnpManager(UpnpService upnpService) {
		logger.info("Starting upnp service");
		this.upnpService = upnpService;
		this.listener = new DeviceRepository<DeviceIdentity>(DeviceFunction.IDENTITY);
		upnpService.getRegistry().addListener(listener);
	}

	@PreDestroy
	public void dispose() {
		logger.info("Disposing");
		upnpService.getRegistry().removeListener(listener);
	}
	
	public void searchAll() {
		upnpService.getControlPoint().search(new STAllHeader());
	}
	
	public Collection<DeviceIdentity> getDiscoveredDevices() {
		return listener.getDevices();
	}
	
	public static void main(String[] args) {
		UpnpServiceImpl upnpService2 = new UpnpServiceImpl();
		
		UpnpManager upnpManager = new UpnpManager(upnpService2);
		upnpManager.searchAll();
	}
}
