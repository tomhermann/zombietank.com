package com.zombietank.upnp;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.DeviceIdentity;

@RunWith(MockitoJUnitRunner.class)
public class DeviceFunctionTest {
	@Mock
	@SuppressWarnings("rawtypes")
	private Device device;
	@Mock
	private DeviceIdentity deviceIdentity;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void identityFunctionReturnsIdentityForDevice() {
		when(device.getIdentity()).thenReturn(deviceIdentity);
		
		DeviceIdentity result = DeviceFunction.IDENTITY.apply(device);
		
		assertSame(deviceIdentity, result);
	}
	
	@Test
	public void identityFunctionDoesNotAcceptNullDevices() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("Device cannot be null");

		DeviceFunction.IDENTITY.apply(null);
	}

}
