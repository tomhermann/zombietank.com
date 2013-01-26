package com.zombietank.upnp;

import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.DeviceIdentity;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;


public abstract class DeviceFunction<T> implements Function<Device<?, ?, ?>, T> {

	public static final DeviceFunction<DeviceIdentity> IDENTITY = new DeviceFunction<DeviceIdentity>() {
		@Override
		public DeviceIdentity apply(Device<?, ?, ?> input) {
			Preconditions.checkNotNull(input, "Device cannot be null");
			return input.getIdentity();
		}
	}; 
}
