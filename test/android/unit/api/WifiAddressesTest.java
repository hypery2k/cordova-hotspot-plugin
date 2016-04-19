package unit.api;

import com.mady.wifi.api.WifiAddresses;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mreinhardt on 16.02.16.
 */
public class WifiAddressesTest {


    @Test
    public void shouldGetAllDevices() throws Exception {
        WifiAddresses wifiAddresses = new WifiAddresses(null);
        List<String> devices = wifiAddresses.getAllDevicesIp();
        assertTrue(devices.size() > 0);
    }

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
        boolean success = wifiAddresses.pingCmd("192.168.255.250");
        String result = wifiAddresses.getPingResulta("192.168.255.250");
        assertFalse(success);
        assertTrue(result.length() > 0);
        assertTrue(result.contains("Request timeout"));
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