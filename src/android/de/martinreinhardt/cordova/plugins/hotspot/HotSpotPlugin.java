/*
 The MIT License (MIT)
 Copyright (c) 2016 Martin Reinhardt

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 HotSpot Plugin for Cordova
 */

package de.martinreinhardt.cordova.plugins.hotspot;


import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.util.Log;
import com.mady.wifi.api.WifiAddresses;
import com.mady.wifi.api.WifiHotSpots;
import com.mady.wifi.api.WifiStatus;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class HotSpotPlugin extends CordovaPlugin {

    /**
     * Logging Tag
     */
    private static final String LOG_TAG = "HotSpotPlugin";

    private CallbackContext command;

    /**
     * Executes the request.
     * <p/>
     * This method is called from the WebView thread.
     * To do a non-trivial amount of work, use:
     * cordova.getThreadPool().execute(runnable);
     * <p/>
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action   The action to execute.
     * @param args     The exec() arguments in JSON form.
     * @param callback The callback context used when calling
     *                 back into JavaScript.
     * @return Whether the action was valid.
     */
    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callback) throws JSONException {

        this.command = callback;

        if ("isWifiOn".equals(action)) {
            if (isWifiOn()) {
                callback.success();
            } else {
                callback.error("Wifi is off.");
            }
            return true;
        }

        if ("toggleWifi".equals(action)) {
            try {
                if (toggleWifi()) {
                    callback.success(1);
                } else {
                    callback.success(1);
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "Got unknown error during toggle wifi", e);
                callback.error("Toggle wifi failed.");
                return true;
            }
        }

        if ("createHotspot".equals(action)) {
            createHotspot(args, true, true, callback);
            return true;
        }

        if ("configureHotspot".equals(action)) {
            createHotspot(null, false, true, callback);
            return true;
        }

        if ("startHotspot".equals(action)) {
            createHotspot(null, true, false, callback);
            return true;
        }

        if ("stopHotspot".equals(action)) {
            stopHotspot(callback);
            return true;
        }

        if ("isHotspotEnabled".equals(action)) {
            isHotspotEnabled(callback);
            return true;
        }

        if ("getAllHotspotDevices".equals(action)) {
            getAllHotspotDevices(callback);
            return true;
        }

        if ("scanWifi".equals(action)) {
            scanWifi(callback);
            return true;
        }

        if ("scanWifiByLevel".equals(action)) {
            scanWifiByLevel(callback);
            return true;
        }

        if ("startPeriodicallyScan".equals(action)) {
            startPeriodicallyScan(args, callback);
            return true;
        }

        if ("stopPeriodicallyScan".equals(action)) {
            stopPeriodicallyScan(callback);
            return true;
        }

        if ("isConnectedToInternet".equals(action)) {
            if (isConnectedToInternet()) {
                callback.success();
            } else {
                callback.error("Device is not connected to internet");
            }
            return true;
        }

        if ("isConnectedToInternetViaWifi".equals(action)) {
            if (isConnectedToInternetViaWifi()) {
                callback.success();
            } else {
                callback.error("Device is not connected to internet via WiFi");
            }
            return true;
        }

        if ("getNetConfig".equals(action)) {
            getNetConfig(callback);
            return true;
        }

        if ("getConnectionInfo".equals(action)) {
            getConnectionInfo(callback);
            return true;
        }

        if ("pingHost".equals(action)) {
            pingHost(args, callback);
            return true;
        }

        if ("dnsLive".equals(action)) {
            dnsLive(args, callback);
            return true;
        }

        if ("portLive".equals(action)) {
            portLive(args, callback);
            return true;
        }

        if ("getMacAddressOfHost".equals(action)) {
            getMacAddressOfHost(args, callback);
            return true;
        }

        if ("checkRoot".equals(action)) {
            checkRoot(callback);
            return true;
        }

        if ("isWifiSupported".equals(action)) {
            if (isWifiSupported()) {
                callback.success();
            } else {
                callback.error("Wifi is not supported.");
            }
            return true;
        }

        if ("isWifiDirectSupported".equals(action)) {
            if (isWifiDirectSupported()) {
                callback.success();
            } else {
                callback.error("Wifi direct is not supported.");
            }
            return true;
        }

        if ("addWifiNetwork".equals(action)) {
            addWifiNetwork(args, callback);
            return true;
        }

        if ("removeWifiNetwork".equals(action)) {
            removeWifiNetwork(args, callback);
            return true;
        }

        if ("connectToHotspot".equals(action)) {
            connectToHotspot(args, callback);
            return true;
        }

        if ("connectToWifiAuthEncrypt".equals(action)) {
            connectToWifiAuthEncrypt(args, callback);
            return true;
        }

        if ("configureHotspot".equals(action)) {
            configureHotspot(args, callback);
            return true;
        }

        // Returning false results in a "MethodNotFound" error.
        return false;
    }

    // IMPLEMENTATION

    private void checkRoot(CallbackContext callback) {
        WifiAddresses wu = new WifiAddresses(this.cordova.getActivity());
        if (wu.isDevicesRooted()) {
            callback.success(1);
        } else {
            callback.success(0);
        }
    }

    private void dnsLive(JSONArray args, CallbackContext callback) {
        try {
            final String host = args.getString(0);
            WifiAddresses wu = new WifiAddresses(this.cordova.getActivity());
            if (wu.dnsIsALive(host)) {
                callback.success(1);
            } else {
                callback.success(0);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error checking DNS.", e);
            callback.error("Error checking DNS.");
        }
    }

    private void portLive(JSONArray args, CallbackContext callback) {
        try {
            final String host = args.getString(0);
            WifiAddresses wu = new WifiAddresses(this.cordova.getActivity());
            if (wu.portIsALive(host)) {
                callback.success(1);
            } else {
                callback.success(0);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error checking port.", e);
            callback.error("Error checking port.");
        }
    }

    private void getConnectionInfo(CallbackContext callback) {
        WifiInfo wifiInfo = new WifiHotSpots(this.cordova.getActivity()).getConnectionInfo();
        JSONObject result = new JSONObject();
        try {

            result.put("SSID", wifiInfo.getSSID());
            result.put("BSSID", wifiInfo.getBSSID());
            result.put("linkSpeed", wifiInfo.getLinkSpeed());
            result.put("IPAddress", intToInetAddress(wifiInfo.getIpAddress())).toString();
            result.put("networkID", wifiInfo.getNetworkId());
            callback.success(result);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error during reading connection info.", e);
            callback.error("Error during reading connection info.");
        }
    }

    private void getNetConfig(CallbackContext callback) {

        WifiAddresses wu = new WifiAddresses(this.cordova.getActivity());
        JSONObject result = new JSONObject();
        try {
            result.put("deviceIPAddress", wu.getDeviceIPAddress());
            result.put("deviceMacAddress", wu.getDeviceMacAddress());
            result.put("gatewayIPAddress", wu.getGatewayIPAddress());
            result.put("gatewayMacAddress", wu.getGatWayMacAddress());
            callback.success(result);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error during reading network config.", e);
            callback.error("Error during reading network config.");
        }
    }

    private void pingHost(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String host = args.getString(0);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiAddresses wu = new WifiAddresses(activity);
                try {
                    if (wu.pingCmd(host)) {
                        callback.success(wu.getPingResulta(host));
                    } else {
                        callback.error(wu.getPingResulta(host));
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Ping to host " + host + " failed", e);
                    callback.error("Ping failed");
                }
            }
        });
    }

    private void getMacAddressOfHost(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String host = args.getString(0);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    WifiAddresses wu = new WifiAddresses(activity);
                    if (wu.pingCmd(host)) {
                        callback.success(wu.getArpMacAddress(host));
                    } else {
                        callback.success();
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "ARP request to host " + host + " failed", e);
                    callback.error("ARP request");
                }
            }
        });
    }

    private void startPeriodicallyScan(JSONArray args, CallbackContext pCallback) throws JSONException {
        final long interval = args.getLong(0);
        final long duration = args.getLong(1);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    new WifiHotSpots(activity).startScan(interval, duration);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Got unkown error during starting scan", e);
                    callback.error("Scan start failed");
                }
            }
        });
    }

    private void stopPeriodicallyScan(CallbackContext pCallback) {
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    new WifiHotSpots(activity).stopScan();
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Got unkown error during stopping scan", e);
                    callback.error("Scan stop failed");
                }
            }
        });
    }

    private void configureHotspot(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String ssid = args.getString(0);
        final String mode = args.getString(1);
        final String password = args.getString(2);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (isHotspotEnabled()) {
                    WifiHotSpots hotspot = new WifiHotSpots(activity);
                    if (hotspot.setHotSpot(ssid, mode, password)) {
                        callback.success();
                    } else {
                        callback.error("Hotspot config was not successfull");
                    }
                } else {
                    callback.error("Hotspot not enabled");
                }
            }
        });
    }

    private void scanWifi(CallbackContext pCallback, final boolean sortByLevel) {
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    WifiHotSpots hotspot = new WifiHotSpots(activity);
                    List<ScanResult> response = sortByLevel ? hotspot.getHotspotsList() : hotspot.sortHotspotsByLevel();
                    // if null wait and try again
                    if (response == null || response.size() == 0) {
                        Thread.sleep(4000);
                        response = sortByLevel ? hotspot.getHotspotsList() : hotspot.sortHotspotsByLevel();
                    }
                    JSONArray results = new JSONArray();
                    if (response != null && response.size() > 0) {
                        for (ScanResult scanResult : response) {
                            JSONObject result = new JSONObject();
                            result.put("SSID", scanResult.SSID);
                            result.put("BSSID", scanResult.BSSID);
                            result.put("frequency", scanResult.frequency);
                            result.put("level", scanResult.level);
                            result.put("timestamp", String.valueOf(scanResult.timestamp));
                            result.put("capabilities", scanResult.capabilities);
                            results.put(result);
                        }
                    }
                    callback.success(results);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Wifi scan failed", e);
                    callback.error("Wifi scan failed.");
                }
            }
        });
    }

    private void scanWifi(CallbackContext pCallback) {
        scanWifi(pCallback, false);
    }

    private void scanWifiByLevel(CallbackContext pCallback) {
        scanWifi(pCallback, true);
    }

    private void removeWifiNetwork(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String ssid = args.getString(0);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiHotSpots hotspot = new WifiHotSpots(activity);
                hotspot.removeWifiNetwork(ssid);
                callback.success();
            }
        });
    }

    public void addWifiNetwork(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String ssid = args.getString(0);
        final String password = args.getString(1);
        final String mode = args.getString(2);
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiHotSpots hotspot = new WifiHotSpots(activity);
                hotspot.addWifiNetwork(ssid, password, mode);
                callback.success();
            }
        });
    }

    public void isHotspotEnabled(CallbackContext pCallback) {
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (isHotspotEnabled()) {
                    callback.success();
                } else {
                    callback.error("Hotspot check failed.");
                }
            }
        });
    }

    public void createHotspot(JSONArray args, final boolean start, boolean configure, CallbackContext pCallback) throws JSONException {

        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;

        if (configure) {
            final String ssid = args.getString(0);
            final String mode = args.getString(1);
            final String password = args.getString(2);

            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        WifiHotSpots hotspot = new WifiHotSpots(activity);
                        if (isHotspotEnabled() && start) {
                            hotspot.startHotSpot(false);
                        }
                        if (hotspot.setHotSpot(ssid, mode, password)) {

                            try {
                                if (start) {
                                    // Wait to connect
                                    Thread.sleep(4000);
                                    if (hotspot.startHotSpot(true)) {
                                        callback.success();
                                    } else {
                                        callback.error("Hotspot customization failed.");
                                    }
                                } else {
                                    callback.success();
                                }
                            } catch (Exception e) {
                                Log.e(LOG_TAG, "Got unknown error during hotspot configuration", e);
                                callback.error("Hotspot configuration failed.: " + e.getMessage());
                            }
                        } else {
                            callback.error("Hotspot creation failed.");
                        }
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Got unknown error during hotspot start", e);
                        callback.error("Hotspot start failed.: " + e.getMessage());
                    }
                }
            });
        } else {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        WifiHotSpots hotspot = new WifiHotSpots(activity);
                        if (isHotspotEnabled()) {
                            hotspot.startHotSpot(false);
                        }
                        try {
                            if (hotspot.startHotSpot(true)) {
                                // Wait to connect
                                Thread.sleep(4000);
                                callback.success();
                            } else {
                                callback.error("Hotspot start failed.");
                            }
                        } catch (Exception e) {
                            Log.e(LOG_TAG, "Got unknown error during hotspot start", e);
                            callback.error("Hotspot start failed.: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Got unknown error during hotspot start", e);
                        callback.error("Existing hotspot stop failed.: " + e.getMessage());
                    }
                }
            });
        }
    }

    public void stopHotspot(CallbackContext pCallback) throws JSONException {
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;

        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiHotSpots hotspot = new WifiHotSpots(activity);
                if (isHotspotEnabled()) {
                    if (!hotspot.startHotSpot(false)) {
                        callback.error("Hotspot creation failed.");
                    }
                }
                callback.success();
            }
        });
    }

    public void getAllHotspotDevices(CallbackContext pCallback) {
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;
        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiAddresses au = new WifiAddresses(activity);
                ArrayList<String> ipList = au.getAllDevicesIp();
                if (ipList != null) {
                    try {
                        Log.d(LOG_TAG, "Checking following IPs: " + ipList);
                        JSONArray result = new JSONArray();
                        for (String ip : ipList) {
                            String mac = au.getArpMacAddress(ip);
                            JSONObject entry = new JSONObject();
                            entry.put("ip", ip);
                            entry.put("mac", mac);
                            // push entry to list
                            result.put(entry);
                        }
                        callback.success(result);
                    } catch (JSONException e) {
                        Log.e(LOG_TAG, "Got JSON error during device listing", e);
                        callback.error("Hotspot device listing failed.");
                    }
                } else {
                    callback.error("Hotspot device listing failed.");
                }
            }
        });
    }


    public boolean connectToHotspot(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String ssid = args.getString(0);
        final String password = args.getString(1);
        return connectToWifiNetwork(pCallback, ssid, password, null, null);
    }

    public boolean connectToWifiAuthEncrypt(JSONArray args, CallbackContext pCallback) throws JSONException {
        final String ssid = args.getString(0);
        final String password = args.getString(1);
        final String authentication = args.getString(2);
        final JSONArray encryption = args.getJSONArray(3);
        List<Integer> encryptions = new ArrayList<Integer>();
        for (int i = 0; i < encryption.length(); i++) {

            if (encryption.getString(i).equalsIgnoreCase("CCMP")) {
                encryptions.add(WifiConfiguration.GroupCipher.CCMP);
            } else if (encryption.getString(i).equalsIgnoreCase("TKIP")) {
                encryptions.add(WifiConfiguration.GroupCipher.TKIP);
            } else if (encryption.getString(i).equalsIgnoreCase("WEP104")) {
                encryptions.add(WifiConfiguration.GroupCipher.WEP104);
            } else {
                encryptions.add(WifiConfiguration.GroupCipher.WEP40);
            }
        }
        Integer authAlgorihm = new Integer(-1);
        if (authentication.equalsIgnoreCase("LEAP")) {
            authAlgorihm = WifiConfiguration.AuthAlgorithm.LEAP;
        } else if (authentication.equalsIgnoreCase("SHARED")) {
            authAlgorihm = WifiConfiguration.AuthAlgorithm.SHARED;
        } else {
            authAlgorihm = WifiConfiguration.AuthAlgorithm.OPEN;
        }
        return connectToWifiNetwork(pCallback, ssid, password, authAlgorihm, encryptions.toArray(new Integer[encryptions.size()]));
    }

    private boolean connectToWifiNetwork(CallbackContext pCallback,
                                         final String ssid,
                                         final String password,
                                         final Integer authentication,
                                         final Integer[] encryption) {
        final Activity activity = this.cordova.getActivity();
        final CallbackContext callback = pCallback;

        cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WifiHotSpots hotspot = new WifiHotSpots(activity);
                try {
                    if (hotspot.connectToHotspot(ssid, password, authentication, encryption)) {
                        int retry = 130;
                        boolean connected = false;
                        // Wait to connect
                        while (retry > 0 && !connected) {
                            connected = hotspot.isConnectedToAP();
                            retry--;
                            Thread.sleep(100);
                        }
                        if (connected) {
                            callback.success("Connection was successfull");
                        } else {
                            callback.error("Connection was not successfull");
                        }
                    } else {
                        callback.error("Connection was not successfull");
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Got unknown error during hotspot connect", e);
                    callback.error("Hotspot connect failed.");
                }
            }
        });
        return true;
    }

    public boolean isHotspotEnabled() {
        if (new WifiHotSpots(this.cordova.getActivity()).isWifiApEnabled()) {
            return true;
        } else {

            return false;
        }
    }

    public boolean toggleWifi() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.wifiToggle();
    }


    public boolean isWifiOn() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.isWifiEnabled();
    }

    public boolean isWifiSupported() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.isSupportWifi();
    }

    public boolean isWifiDirectSupported() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.isSupportWifiDirect();
    }

    private boolean isConnectedToInternetViaWifi() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return isConnectedToWifi() && wu.isConnectedToInternet();

    }

    private boolean isConnectedToWifi() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.checkWifi(wu.DATA_BY_WIFI);

    }

    private boolean isConnectedToInternet() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.isConnectedToInternet();
    }

    // HELPER

    /**
     * Called when an activity you launched exits, giving you the reqCode you
     * started it with, the resCode it returned, and any additional data from it.
     *
     * @param reqCode The request code originally supplied to startActivityForResult(),
     *                allowing you to identify who this result came from.
     * @param resCode The integer result code returned by the child activity
     *                through its setResult().
     * @param intent  An Intent, which can return result data to the caller
     *                (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int reqCode, int resCode, Intent intent) {
        command.success();
    }

    /**
     * Convert a IPv4 address from an integer to an InetAddress.
     *
     * @param hostAddress an int corresponding to the IPv4 address in network byte order
     */
    public InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = {(byte) (0xff & hostAddress),
                (byte) (0xff & (hostAddress >> 8)),
                (byte) (0xff & (hostAddress >> 16)),
                (byte) (0xff & (hostAddress >> 24))};

        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }
}
