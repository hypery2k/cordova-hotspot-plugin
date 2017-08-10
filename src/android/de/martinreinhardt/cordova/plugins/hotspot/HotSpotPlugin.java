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


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.mady.wifi.api.WifiAddresses;
import com.mady.wifi.api.WifiHotSpots;
import com.mady.wifi.api.WifiStatus;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class HotSpotPlugin extends CordovaPlugin {

    /**
     * Logging Tag
     */
    private static final String LOG_TAG = "HotSpotPlugin";


    public static String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static final int REQUEST_CODE_SETTINGS_INTENT = 400;

    private CallbackContext callback;
    private String action;
    private String rawArgs;

    private Boolean writeSettings;

    private interface HotspotFunction {
        void run(JSONArray args, CallbackContext callback) throws Exception;
    }

    public boolean hasPermissions() {
        for (String p : permissions) {
            if (!PermissionHelper.hasPermission(this, p)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Executes the request.
     * <p/>
     * This method is called from the WebView thread.
     * To do a non-trivial amount of work, use:
     * cordova.getThreadPool().execute(runnable);
     * <p/>
     * To run on the UI thread, use:
     * cordova.getThreadPool().execute(runnable);
     *
     * @param action   The action to execute.
     * @param rawArgs  The exec() arguments in String form.
     * @param callback The callback context used when calling
     *                 back into JavaScript.
     * @return Whether the action was valid.
     */
    @Override
    public boolean execute(String action, String rawArgs,
                           CallbackContext callback) throws JSONException {
        this.callback = callback;
        this.action = action;
        this.rawArgs = rawArgs;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP + 1) {
            Class systemClass = Settings.System.class;
            try {
                Method canWriteMethod = systemClass.getDeclaredMethod("canWrite", Context.class);
                boolean retVal = (Boolean) canWriteMethod.invoke(null, this.cordova.getActivity());
                Log.d(LOG_TAG, "Can Write Settings: " + retVal);
                if (!retVal && !action.equals("requestWriteSettings") && !action.equals("getWriteSettings")) {
                    // With Android 6.0+/API level>= 23, user can turn on/off permissions as necessary.
                    // Permissions are explicitely granted during the app runtime.
                    if (Build.VERSION.SDK_INT < 23) {
                       // can't write Settings
                       callback.error("write settings: false");
                       return false;
                    }
                }
                this.writeSettings = retVal;
            } catch (Exception ignored) {
                Log.e(LOG_TAG, "Could not perform permission check");
                this.callback.sendPluginResult(new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION));
            }
        }
        if (!this.hasPermissions()) {
            PermissionHelper.requestPermissions(this, 0, HotSpotPlugin.permissions);
            return true;
        } else {
            // pre Android 6 behaviour
            return executeInternal(action, rawArgs, callback);
        }
        // Returning false results in a "MethodNotFound" error.
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) {
                this.callback.sendPluginResult(new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION));
                return;
            }
        }
        executeInternal(this.action, this.rawArgs, this.callback);
    }

    private void requestWriteSettings(CallbackContext callback) {
        Intent intent = new Intent("android.settings.action.MANAGE_WRITE_SETTINGS");
        intent.setData(Uri.parse("package:" + cordova.getActivity().getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // intent.
        try {
            cordova.getActivity().startActivity(intent);
            callback.success();
        } catch (Exception e) {
            Log.e(LOG_TAG, "error starting permission intent", e);
            callback.error("error starting permission intent");
        }
    }

    private boolean executeInternal(String action, String rawArgs, CallbackContext callback) {
        Log.i(LOG_TAG, "Running executeInternal() ");
        Log.i(LOG_TAG, "     action: " + action);
        Log.i(LOG_TAG, "     rawArgs: " + rawArgs);
        if ("requestWriteSettings".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    requestWriteSettings(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("getWriteSettings".equals(action)) {
            final boolean temp = this.writeSettings;
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                  if(temp)
                    callback.success(1);
                  else
                    callback.success(0);
                }
            }, rawArgs, callback);
            return true;
        }


        if ("isWifiOn".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (isWifiOn()) {
                        callback.success();
                    } else {
                        callback.error("Wifi is off.");
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("toggleWifi".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (toggleWifi()) {
                        callback.success(1);
                    } else {
                        callback.success(0);
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("createHotspot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    createHotspot(args, true, true, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("configureHotspot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    createHotspot(args, false, true, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("startHotspot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    createHotspot(null, true, false, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("stopHotspot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    stopHotspot(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("isHotspotEnabled".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    isHotspotEnabled(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("getAllHotspotDevices".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    getAllHotspotDevices(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("scanWifi".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    scanWifi(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("scanWifiByLevel".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    scanWifiByLevel(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("startWifiPeriodicallyScan".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    startWifiPeriodicallyScan(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("stopWifiPeriodicallyScan".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    stopWifiPeriodicallyScan(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("isConnectedToInternet".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (isConnectedToInternet()) {
                        callback.success();
                    } else {
                        callback.error("Device is not connected to internet");
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("isConnectedToInternetViaWifi".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (isConnectedToInternetViaWifi()) {
                        callback.success();
                    } else {
                        callback.error("Device is not connected to internet via WiFi");
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("getNetConfig".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    getNetConfig(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("getConnectionInfo".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    getConnectionInfo(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("pingHost".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    pingHost(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("dnsLive".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    dnsLive(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("portLive".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    portLive(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("getMacAddressOfHost".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    getMacAddressOfHost(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("checkRoot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    checkRoot(callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("isWifiSupported".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (isWifiSupported()) {
                        callback.success();
                    } else {
                        callback.error("Wifi is not supported.");
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("isWifiDirectSupported".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    if (isWifiDirectSupported()) {
                        callback.success();
                    } else {
                        callback.error("Wifi direct is not supported.");
                    }
                }
            }, rawArgs, callback);
            return true;
        }

        if ("addWifiNetwork".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    addWifiNetwork(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("removeWifiNetwork".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    removeWifiNetwork(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("connectToWifi".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    connectToWifi(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("connectToWifiAuthEncrypt".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    connectToWifiAuthEncrypt(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }

        if ("configureHotspot".equals(action)) {
            threadhelper(new HotspotFunction() {
                @Override
                public void run(JSONArray args, CallbackContext callback) throws Exception {
                    configureHotspot(args, callback);
                }
            }, rawArgs, callback);
            return true;
        }
        return false;
    }

    // IMPLEMENTATION

    public void checkRoot(CallbackContext callback) {
        WifiAddresses wu = new WifiAddresses(this.cordova.getActivity());
        if (wu.isDevicesRooted()) {
            callback.success(1);
        } else {
            callback.success(0);
        }
    }

    public void dnsLive(JSONArray args, CallbackContext callback) {
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

    public void portLive(JSONArray args, CallbackContext callback) {
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

    public void getConnectionInfo(CallbackContext callback) {
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

    public void getNetConfig(CallbackContext callback) {

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

    public void pingHost(JSONArray args, final CallbackContext callback) throws JSONException {
        final String host = args.getString(0);
        final Activity activity = this.cordova.getActivity();

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

    public void getMacAddressOfHost(JSONArray args, final CallbackContext callback) throws JSONException {
        final String host = args.getString(0);
        final Activity activity = this.cordova.getActivity();
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

    public void startWifiPeriodicallyScan(JSONArray args, final CallbackContext callback) throws JSONException {
        final long interval = args.getLong(0);
        final long duration = args.getLong(1);
        final Activity activity = this.cordova.getActivity();
        try {
            new WifiHotSpots(activity).startScan(interval, duration);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Got unkown error during starting scan", e);
            callback.error("Scan start failed");
        }
    }

    public void stopWifiPeriodicallyScan(final CallbackContext callback) {
        final Activity activity = this.cordova.getActivity();
        try {
            new WifiHotSpots(activity).stopScan();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Got unkown error during stopping scan", e);
            callback.error("Scan stop failed");
        }
    }

    public void configureHotspot(JSONArray args, final CallbackContext callback) throws JSONException {
        final String ssid = args.getString(0);
        final String mode = args.getString(1);
        final String password = args.getString(2);
        final Activity activity = this.cordova.getActivity();
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

    private List<ScanResult> getScanResult(final WifiHotSpots hotspot,
                                           final boolean sortByLevel) throws InterruptedException {
        List<ScanResult> response = hotspot.getHotspotsList();
        // if null wait and try again
        if (response == null || response.size() == 0) {
            Thread.sleep(5000);
            Log.i(LOG_TAG, "   Trying scan again.");
            response = hotspot.getHotspotsList();
            if (sortByLevel) {
                response = hotspot.sortHotspotsByLevel();
            }
        }
        return response;
    }

    private void scanWifi(final CallbackContext callback, final boolean sortByLevel) {
        Log.i(LOG_TAG, "Running scanWifi() ");
        final Activity activity = this.cordova.getActivity();
        try {
            Log.i(LOG_TAG, "   Starting WiFi scan.");
            WifiHotSpots hotspot = new WifiHotSpots(activity);
            if (isHotspotEnabled()) {
                hotspot.startHotSpot(false);
                Thread.sleep(3000);
            }
            if (!isWifiOn()) {
                toggleWifi();
                Thread.sleep(2000);
            }
            List<ScanResult> response = getScanResult(hotspot, sortByLevel);
            // if null wait and try again
            if (response == null || response.size() == 0) {
                response = getScanResult(hotspot, sortByLevel);
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
            } else {
                Log.i(LOG_TAG, "   Got empty response");
            }
            callback.success(results);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Wifi scan failed", e);
            callback.error("Wifi scan failed.");
        }
    }

    public void scanWifi(CallbackContext pCallback) {
        Log.i(LOG_TAG, "Running scanWifi() ");
        scanWifi(pCallback, false);
    }

    public void scanWifiByLevel(CallbackContext pCallback) {
        scanWifi(pCallback, true);
    }

    public void removeWifiNetwork(JSONArray args, final CallbackContext callback) throws JSONException {
        final String ssid = args.getString(0);
        final Activity activity = this.cordova.getActivity();
        WifiHotSpots hotspot = new WifiHotSpots(activity);
        hotspot.removeWifiNetwork(ssid);
        callback.success();
    }

    public void addWifiNetwork(JSONArray args, final CallbackContext callback) throws JSONException {
        final String ssid = args.getString(0);
        final String password = args.getString(1);
        final String mode = args.getString(2);
        final Activity activity = this.cordova.getActivity();
        WifiHotSpots hotspot = new WifiHotSpots(activity);
        hotspot.addWifiNetwork(ssid, password, mode);
        callback.success();
    }

    public void isHotspotEnabled(final CallbackContext callback) {
        if (isHotspotEnabled()) {
            callback.success();
        } else {
            callback.error("Hotspot check failed.");
        }
    }

    public void createHotspot(JSONArray args, final boolean start, boolean configure, final CallbackContext callback) throws JSONException {
        final Activity activity = this.cordova.getActivity();

        if (configure) {
            final String ssid = args.getString(0);
            final String mode = args.getString(1);
            final String password = args.getString(2);
            try {
                WifiHotSpots hotspot = new WifiHotSpots(activity);
                if (start) {
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
                callback.error("Unknown error during hotspot configuration: " + e.getMessage());
            }
        } else {
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
                    callback.error("Unknown error during hotspot start: " + e.getMessage());
                }
            } catch (Exception e) {
                Log.e(LOG_TAG, "Got unknown error during hotspot start", e);
                callback.error("Existing hotspot stop failed.: " + e.getMessage());
            }
        }
    }

    public void stopHotspot(final CallbackContext callback) throws JSONException {
        final Activity activity = this.cordova.getActivity();
        WifiHotSpots hotspot = new WifiHotSpots(activity);
        if (isHotspotEnabled()) {
            if (!hotspot.startHotSpot(false)) {
                callback.error("Hotspot creation failed.");
            }
        }
        callback.success();
    }

    public void getAllHotspotDevices(final CallbackContext callback) {
        final Activity activity = this.cordova.getActivity();
        WifiAddresses au = new WifiAddresses(activity);
        List<String> ipList = au.getAllDevicesIp();
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

    public boolean connectToWifi(JSONArray args, CallbackContext pCallback) throws JSONException {
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

    private boolean connectToWifiNetwork(final CallbackContext callback,
                                         final String ssid,
                                         final String password,
                                         final Integer authentication,
                                         final Integer[] encryption) {
        final Activity activity = this.cordova.getActivity();
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

    public boolean isConnectedToInternetViaWifi() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return isConnectedToWifi() && wu.isConnectedToInternet();

    }

    private boolean isConnectedToWifi() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.checkWifi(wu.DATA_BY_WIFI);

    }

    public boolean isConnectedToInternet() {
        WifiStatus wu = new WifiStatus(this.cordova.getActivity());
        return wu.isConnectedToInternet();
    }

    // HELPER

    /**
     * Called when an activity you launched exits, giving you the reqCode you
     * started it with, the resCode it returned, and any additional data from it.
     *
     * @param requestCode The request code originally supplied to startActivityForResult(),
     *                    allowing you to identify who this result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param intent      An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE_SETTINGS_INTENT) {
            try {
                this.execute(action, rawArgs, callback);
            } catch (Exception ignored) {
                Log.e(LOG_TAG, "Could not perform onActivityResult after intent callback");
                this.callback.sendPluginResult(new PluginResult(PluginResult.Status.ILLEGAL_ACCESS_EXCEPTION));
            }
        }
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

    /* helper to execute functions async and handle the result codes
     *
     */
    private void threadhelper(final HotspotFunction f, final String rawArgs, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    JSONArray args = new JSONArray(rawArgs);
                    f.run(args, callbackContext);
                } catch (SecurityException e) {
                    logError(callbackContext, "Got permissions error in Hotpspot plugin", e);
                } catch (Exception e) {
                    logError(callbackContext, "Got unknown error in Hotpspot plugin", e);
                }
            }
        });
    }

    /**
     * Helper function to log to logcat and log back to plugin result
     *
     * @param callbackContext
     * @param msg
     * @param e
     */
    private void logError(final CallbackContext callbackContext, final String msg, final Exception e) {
        Log.e(LOG_TAG, msg, e);
        callbackContext.error(msg + ": " + e.getMessage());
    }
}
