package com.zombietank.upnp;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.teleal.cling.model.meta.DeviceIdentity;

@Controller
@RequestMapping("upnp")
public class UpnpController {
	@Inject
	private UpnpManager upnpService;

	@RequestMapping("search/all")
	@ResponseBody
	public String start() {
		upnpService.searchAll();
		return "started search";
	}

	@RequestMapping("devices")
	@ResponseBody
	public Collection<DeviceIdentity> devices() {
		return upnpService.getDiscoveredDevices();
	}
}
