package com.mady.wifi.api;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mreinhardt on 16.02.16.
 */
public class WifiAddressesTest {

    @Test
    public void shouldSucceedWithValidAddress() throws Exception {
        WifiAddresses wifiAddresses = new WifiAddresses(null);
        boolean success = wifiAddresses.pingCmd("8.8.8.8");
        String result = wifiAddresses.getPingResulta("8.8.8.8");
        assertTrue(success);
        assertTrue(result.length() > 0);
    }

    @Test
    public void shouldNotWorkWithPrivateIP() throws Exception {
        WifiAddresses wifiAddresses = new WifiAddresses(null);
        boolean success = wifiAddresses.pingCmd("10.27.1.1");
        String result = wifiAddresses.getPingResulta("10.27.1.1");
        assertFalse(success);
        assertTrue(result.length() == 0);
    }

    @Test
    public void shouldFailWithInvalidAddress() throws Exception {
        WifiAddresses wifiAddresses = new WifiAddresses(null);
        boolean success = wifiAddresses.pingCmd("8.8.8.811");
        String result = wifiAddresses.getPingResulta("8.8.8.811");
        assertFalse(success);
        assertTrue(result.length() == 0);
    }
}