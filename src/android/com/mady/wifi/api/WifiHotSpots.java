/* 
 * Copyright (C) 2013-2014 www.Andbrain.com 
 * Faster and more easily to create android apps
 * 
 * */
package com.mady.wifi.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class WifiHotSpots {
    /**
     * Logging Tag
     */
    private static final String LOG_TAG = "WifiHotSpots";

    public static boolean isConnectToHotSpotRunning = false;
    WifiManager mWifiManager;
    WifiInfo mWifiInfo;
    Context mContext;
    List<ScanResult> mResults;
    /**
     * Get WiFi password From wpa_supplicant.conf file By SSID
     *
     * @param SSID
     */
    boolean gotRoot = false;
    ScanTimer twoSecondTimer;

    public WifiHotSpots(Context c) {
        mContext = c;
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();

    }

    /**
     * Run command as root.
     *
     * @param command
     * @return true, if command was successfully executed
     */
    private static boolean runAsRoot(final String command) {
        DataOutputStream outStr = null;
        try {

            Process pro = Runtime.getRuntime().exec("su");
            outStr = new DataOutputStream(pro.getOutputStream());

            outStr.writeBytes(command);
            outStr.writeBytes("\nexit\n");
            outStr.flush();

            int retval = pro.waitFor();

            return (retval == 0);

        } catch (Exception ex) {
            Log.e(LOG_TAG, "Unknown error during running as root.", ex);
            return false;
        } finally {
            try {
                if (outStr != null) {
                    outStr.close();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Unkown error during closing output stream", e);
            }
        }
    }

    /**
     * Method for Connecting  to WiFi Network (hotspot)
     *
     * @param netSSID        of WiFi Network (hotspot)
     * @param netPass        put password or  "" for open network
     * @param authentication (optional) authentication algorithm to use
     * @param encryptions    (optional) set group ciphers. @see <a href="http://developer.android.com/reference/android/net/wifi/WifiConfiguration.AuthAlgorithm.html">WifiConfiguration.AuthAlgorithm</a>
     * @return true if connected to hotspot successfully @see <a href="http://developer.android.com/reference/android/net/wifi/WifiConfiguration.GroupCipher.html">WifiConfiguration.GroupCipher</a>
     */
    public boolean connectToHotspot(String netSSID, String netPass, Integer authentication, Integer[] encryptions) {

        isConnectToHotSpotRunning = true;
        WifiConfiguration wifiConf = new WifiConfiguration();
        if (authentication != null && encryptions != null && encryptions.length > 0) {
            removeWifiNetwork(netSSID);
            if (WifiConfiguration.AuthAlgorithm.LEAP == authentication.intValue()) {

                wifiConf.SSID = "\"" + netSSID + "\"";
                wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                for (int cipher : encryptions) {
                    wifiConf.allowedGroupCiphers.set(cipher);
                }
                int res = mWifiManager.addNetwork(wifiConf);
                mWifiManager.disconnect();
                mWifiManager.enableNetwork(res, true);
                mWifiManager.reconnect();
                mWifiManager.setWifiEnabled(true);
                isConnectToHotSpotRunning = false;
                return true;
            } else if (WifiConfiguration.AuthAlgorithm.SHARED == authentication.intValue()) {

                wifiConf.SSID = "\"" + netSSID + "\"";
                wifiConf.wepKeys[0] = "\"" + netPass + "\"";
                wifiConf.wepTxKeyIndex = 0;
                wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                for (int cipher : encryptions) {
                    wifiConf.allowedGroupCiphers.set(cipher);
                }
                int res = mWifiManager.addNetwork(wifiConf);
                mWifiManager.disconnect();
                mWifiManager.enableNetwork(res, true);
                mWifiManager.reconnect();
                mWifiManager.setWifiEnabled(true);
                isConnectToHotSpotRunning = false;
                return true;

            } else {

                wifiConf.SSID = "\"" + netSSID + "\"";
                wifiConf.preSharedKey = "\"" + netPass + "\"";
                wifiConf.hiddenSSID = false;
                wifiConf.status = WifiConfiguration.Status.ENABLED;
                for (int cipher : encryptions) {
                    wifiConf.allowedGroupCiphers.set(cipher);
                }
                wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                int res = mWifiManager.addNetwork(wifiConf);
                mWifiManager.disconnect();
                mWifiManager.enableNetwork(res, true);
                mWifiManager.reconnect();
                mWifiManager.saveConfiguration();
                mWifiManager.setWifiEnabled(true);
                isConnectToHotSpotRunning = false;
                return true;

            }
        } else {
            List<ScanResult> scanResultList = mWifiManager.getScanResults();

            if (mWifiManager.isWifiEnabled()) {

                for (ScanResult result : scanResultList) {

                    if (result.SSID.equals(netSSID)) {

                        removeWifiNetwork(result.SSID);
                        String mode = getSecurityMode(result);

                        if (mode.equalsIgnoreCase("OPEN")) {
                            Log.i(LOG_TAG, "Connecting to  hotspot with security: OPEN");
                            wifiConf.SSID = "\"" + netSSID + "\"";
                            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                            int res = mWifiManager.addNetwork(wifiConf);
                            mWifiManager.disconnect();
                            mWifiManager.enableNetwork(res, true);
                            mWifiManager.reconnect();
                            mWifiManager.setWifiEnabled(true);
                            isConnectToHotSpotRunning = false;
                            return true;

                        } else if (mode.equalsIgnoreCase("WEP")) {

                            Log.i(LOG_TAG, "Connecting to  hotspot with security: WEP");
                            wifiConf.SSID = "\"" + netSSID + "\"";
                            wifiConf.wepKeys[0] = "\"" + netPass + "\"";
                            wifiConf.wepTxKeyIndex = 0;
                            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                            int res = mWifiManager.addNetwork(wifiConf);
                            mWifiManager.disconnect();
                            mWifiManager.enableNetwork(res, true);
                            mWifiManager.reconnect();
                            mWifiManager.setWifiEnabled(true);
                            isConnectToHotSpotRunning = false;
                            return true;

                        } else {

                            Log.i(LOG_TAG, "Connecting to  hotspot with security: WPA");
                            wifiConf.SSID = "\"" + netSSID + "\"";
                            wifiConf.preSharedKey = "\"" + netPass + "\"";
                            wifiConf.hiddenSSID = false;
                            wifiConf.status = WifiConfiguration.Status.ENABLED;
                            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                            wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                            wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                            wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                            wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                            int res = mWifiManager.addNetwork(wifiConf);
                            mWifiManager.disconnect();
                            mWifiManager.enableNetwork(res, true);
                            mWifiManager.reconnect();
                            mWifiManager.saveConfiguration();
                            mWifiManager.setWifiEnabled(true);
                            isConnectToHotSpotRunning = false;
                            return true;

                        }
                    }
                }
            }
        }
        isConnectToHotSpotRunning = false;
        return false;
    }

    /**
     * Check if The Device Is Connected to Hotspot using wifi
     *
     * @return true if device connect to Hotspot
     */

    public boolean isConnectedToAP() {
        ConnectivityManager connectivity = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method to Get hotspot Max Level of all Hotspots Around you
     *
     * @return a highest level hotspot
     */
    public ScanResult getHotspotMaxLevel() {
        List<ScanResult> hotspotList = mWifiManager.getScanResults();
        if (hotspotList != null) {
            final int size = hotspotList.size();
            if (size == 0) {
                return null;
            } else {
                ScanResult maxLevel = hotspotList.get(0);
                for (ScanResult result : hotspotList) {
                    if (WifiManager.compareSignalLevel(maxLevel.level,
                            result.level) < 0) {
                        maxLevel = result;
                    }
                }
                return maxLevel;
            }
        } else {
            return null;
        }
    }

    /**
     * Method to Get hotspot Max Level of all Hotspots in hotspotList list
     *
     * @param hotspotList list of Hotspots
     * @return a highest level hotspot
     */
    public ScanResult getHotspotMaxLevel(List<ScanResult> hotspotList) {

        if (hotspotList != null) {
            final int size = hotspotList.size();
            if (size == 0) {
                return null;
            } else {
                ScanResult maxSignal = hotspotList.get(0);

                for (ScanResult result : hotspotList) {
                    if (WifiManager.compareSignalLevel(maxSignal.level,
                            result.level) < 0) {
                        maxSignal = result;
                    }
                }
                return maxSignal;
            }
        } else {
            return null;
        }

    }

    /**
     * sort All  Hotspots Around you By Level
     *
     * @return sorted hotspots List
     */
    public List<ScanResult> sortHotspotsByLevel() {
        List<ScanResult> hotspotList = mWifiManager.getScanResults();
        List<ScanResult> sorthotspotsList = new ArrayList<ScanResult>();
        ScanResult result;
        while (!hotspotList.isEmpty()) {
            result = getHotspotMaxLevel(hotspotList);
            sorthotspotsList.add(result);
            hotspotList.remove(result);
        }

        return sorthotspotsList;
    }

    /**
     * sort Hotspots in hotspotList By Level
     *
     * @return sorted hotspots List
     */
    public List<ScanResult> sortHotspotsByLevel(List<ScanResult> hotspotList) {
        List<ScanResult> hotspotList2 = hotspotList;
        List<ScanResult> sorthotspotsList = new ArrayList<ScanResult>();
        ScanResult result;
        while (!hotspotList2.isEmpty()) {
            result = getHotspotMaxLevel(hotspotList2);
            sorthotspotsList.add(result);
            hotspotList2.remove(result);
        }
        return sorthotspotsList;
    }

    /**
     * Method to Get  List of  WIFI Networks (hotspots) Around you
     *
     * @return List  of networks (hotspots)
     */
    public List<ScanResult> getHotspotsList() {

        if (mWifiManager.isWifiEnabled()) {
            Log.i(LOG_TAG, "Wifi is enabled");
            if (mWifiManager.startScan()) {
                return mWifiManager.getScanResults();
            }
        } else {
            Log.i(LOG_TAG, "Wifi not enabled");
        }
        return null;
    }

    public void scanNetworks() {
        boolean scan = mWifiManager.startScan();

        if (scan) {
            mResults = mWifiManager.getScanResults();

        } else
            switch (mWifiManager.getWifiState()) {
                case WifiManager.WIFI_STATE_DISABLING:
                    Log.d(LOG_TAG, "wifi disabling");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    Log.d(LOG_TAG, "wifi disabled");
                    break;
                case WifiManager.WIFI_STATE_ENABLING:
                    Log.d(LOG_TAG, "wifi enabling");
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    Log.d(LOG_TAG, "wifi enabled");
                    break;
                case WifiManager.WIFI_STATE_UNKNOWN:
                    Log.d(LOG_TAG, "wifi unknown state");
                    break;
            }

    }

    /**
     * Method to turn ON/OFF a  Access Point
     *
     * @param enable Put true if you want to start  Access Point
     * @return true if AP is started
     */
    public boolean startHotSpot(boolean enable) {
        mWifiManager.setWifiEnabled(false);
        Method[] mMethods = mWifiManager.getClass().getDeclaredMethods();
        for (Method mMethod : mMethods) {
            if (mMethod.getName().equals("setWifiApEnabled")) {
                try {
                    mMethod.invoke(mWifiManager, null, enable);
                    return true;
                } catch (Exception ex) {
                    Log.e(LOG_TAG, "Unknown error during hotspot creation.", ex);
                }
                break;
            }
        }
        return false;
    }

    /**
     * Method to Change SSID  of Device Access Point and Start HotSpot
     *
     * @param enable hotspot
     * @param SSID   a new SSID of your Access Point
     */
    public boolean setAndStartHotSpot(boolean enable, String SSID) {
        //For simple implementation I am creating the open hotspot.
        Method[] mMethods = mWifiManager.getClass().getDeclaredMethods();
        for (Method mMethod : mMethods) {
            {
                if (mMethod.getName().equals("setWifiApEnabled")) {
                    WifiConfiguration netConfig = new WifiConfiguration();
                    netConfig.SSID = SSID;
                    netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                    try {
                        mMethod.invoke(mWifiManager, netConfig, true);
                    } catch (Exception e) {
                        Log.e(LOG_TAG, "Unkown error during setting hotspot.", e);
                        return false;
                    }
                    startHotSpot(enable);
                }
            }
        }
        return enable;
    }

    /**
     * Method to Change SSID and Password of Device Access Point
     *
     * @param SSID     a new SSID of your Access Point
     * @param mode     wireless mode (Open, WEP, WPA, WPA_PSK
     * @param passWord a new password you want for your Access Point
     */
    public boolean setHotSpot(String SSID, String mode, String passWord) {
        /*
         * Before setting the HotSpot with specific Id delete the default AP Name.
    	 */
        String BACKSLASH = "\"";
        List<WifiConfiguration> list = mWifiManager.getConfiguredNetworks();
        if (list != null) {
            for (WifiConfiguration i : list) {
                if (i.SSID != null && i.SSID.equals(SSID)) {
                    mWifiManager.disconnect();
                    mWifiManager.removeNetwork(i.networkId);
                    mWifiManager.saveConfiguration();
                    break;
                }
            }
        }

        if (SSID == null) {
            Log.e(LOG_TAG, "Please provide a SSID");
            return false;
        }
        if (mode == null) {
            Log.e(LOG_TAG, "Please provide the networking mode");
            return false;
        }
        if (passWord == null) {
            Log.e(LOG_TAG, "Please provide the password");
            return false;
        }

        //mWifiManager.acquire();
        Method[] mMethods = mWifiManager.getClass().getDeclaredMethods();

        Log.v(LOG_TAG, "Creating hotspot with mode " + mode);
        for (Method mMethod : mMethods) {

            if (mMethod.getName().equals("setWifiApEnabled")) {
                WifiConfiguration netConfig = new WifiConfiguration();
                if (mode.equalsIgnoreCase("OPEN")) {
                    Log.i(LOG_TAG, "Applying hotspot settings with security: OPEN");
                    netConfig.SSID = SSID;
                    netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                } else if (mode.equalsIgnoreCase("WEP")) {
                    Log.i(LOG_TAG, "Applying hotspot settings with security: WEP");
                    netConfig.SSID = SSID;
                    netConfig.wepKeys[0] = passWord;
                    netConfig.wepTxKeyIndex = 0;
                    netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                    netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                } else {
                    Log.i(LOG_TAG, "Applying hotspot settings with security: WPA");
                    netConfig.SSID = SSID;
                    netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                    netConfig.SSID = SSID;
                    netConfig.preSharedKey = passWord;
                    netConfig.hiddenSSID = false;
                    netConfig.status = WifiConfiguration.Status.ENABLED;
                    netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                    netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        netConfig.allowedKeyManagement.set(4); // WPA2_PSK on Android 4+!
                    } else {
                        netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                    }
                    netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                    netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                    netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                    netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

                }
                try {
                    mMethod.invoke(mWifiManager, netConfig, true);
                    mWifiManager.disconnect();
                    mWifiManager.reconnect();
                    mWifiManager.saveConfiguration();
                    Log.v(LOG_TAG, "Successfully created hotspot");
                    return true;

                } catch (Exception e) {
                    Log.e(LOG_TAG, "Unknown error during saving wifi config.", e);
                }
            }
        }
        return false;
    }

    /**
     * @return true if Wifi Access Point Enabled
     */
    public boolean isWifiApEnabled() {
        try {
            Method method = mWifiManager.getClass().getMethod("isWifiApEnabled");
            return (Boolean) method.invoke(mWifiManager);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Unkown error during checking ap wifi.", e);
        }

        return false;
    }

    /**
     * shred all  Configured wifi Networks
     */

    public boolean sharedAllWifi() {
        Context context = mContext;
        mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (mWifiInfo != null) {
            for (WifiConfiguration conn : mWifiManager.getConfiguredNetworks()) {
                mWifiManager.removeNetwork(conn.networkId);
            }

            mWifiManager.saveConfiguration();
            return true;
        }
        return false;
    }

    /**
     * This gets a list of the wifi profiles from the system and returns them.
     *
     * @return List<WifiConfigurationg> : a list of all the profile names.
     */
    public ArrayList<WifiConfiguration> getProfiles() {
        ArrayList<WifiConfiguration> profileList = new ArrayList<WifiConfiguration>();
        if (mWifiInfo != null) {
            for (WifiConfiguration conn : mWifiManager.getConfiguredNetworks()) {
                profileList.add(conn);
            }
        }
        return profileList;
    }

    /**
     * Method to add Wifi Network
     *
     * @param netSSID of WiFi Network (hotspot)
     * @param netPass put password
     * @param netType Network Security Type   OPEN PSK EAP OR WEP
     */
    public void addWifiNetwork(String netSSID, String netPass, String netType) {
        WifiConfiguration wifiConf = new WifiConfiguration();
        if (netType.equalsIgnoreCase("OPEN")) {
            wifiConf.SSID = "\"" + netSSID + "\"";
            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            mWifiManager.addNetwork(wifiConf);
            mWifiManager.saveConfiguration();
        } else if (netType.equalsIgnoreCase("WEP")) {
            wifiConf.SSID = "\"" + netSSID + "\"";
            wifiConf.wepKeys[0] = "\"" + netPass + "\"";
            wifiConf.wepTxKeyIndex = 0;
            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
            mWifiManager.addNetwork(wifiConf);
            mWifiManager.saveConfiguration();
        } else {
            wifiConf.SSID = "\"" + netSSID + "\"";
            wifiConf.preSharedKey = "\"" + netPass + "\"";
            wifiConf.hiddenSSID = false;
            wifiConf.status = WifiConfiguration.Status.ENABLED;
            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
            wifiConf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
            wifiConf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
            wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
            wifiConf.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
            wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
            wifiConf.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
            mWifiManager.addNetwork(wifiConf);
            mWifiManager.saveConfiguration();
        }
    }

    /**
     * shred  Configured wifi Network By SSID
     *
     * @param ssid of wifi Network
     */
    public void removeWifiNetwork(String ssid) {
        List<WifiConfiguration> configs = mWifiManager.getConfiguredNetworks();
        if (configs != null) {
            for (WifiConfiguration config : configs) {
                if (config.SSID.contains(ssid)) {
                    mWifiManager.disableNetwork(config.networkId);
                    mWifiManager.removeNetwork(config.networkId);
                }
            }
        }
        mWifiManager.saveConfiguration();
    }

    /**
     * get Connection Info
     *
     * @return WifiInfo
     */
    public WifiInfo getConnectionInfo() {
        return mWifiManager.getConnectionInfo();
    }

    public String getWifiPassword(String SSID) {
        File wpaFile = new File(mContext.getCacheDir(), "wpa_supplicant.conf");
        if (!wpaFile.exists()) {
            checkForRoot();
            if (this.gotRoot) {
                final String command = "cat /data/misc/wifi/wpa_supplicant.conf"
                        + " > "
                        + wpaFile.getAbsolutePath()
                        + "\n chmod 666 "
                        + wpaFile.getAbsolutePath();
                if (!runAsRoot(command)) {

                    this.gotRoot = false;
                    return null;
                }
            } else {
                return null;
            }
        }
        wpaFile = new File(wpaFile.getAbsolutePath());
        if (!wpaFile.exists()) {
            Log.e(LOG_TAG, "error read wpa_supplicant.conf file");
            return null;
        }
        BufferedReader bufRead = null;
        try {
            bufRead = new BufferedReader(new InputStreamReader(new FileInputStream(wpaFile), Charset.forName("UTF-8")));
            String line;
            StringBuffer stringBuf = new StringBuffer();
            while ((line = bufRead.readLine()) != null) {
                if (line.startsWith("network=") || line.equals("}")) {
                    String config = stringBuf.toString();
                    if (config.contains("ssid=" + SSID)) {
                        int i = config.indexOf("wep_key0=");
                        int len;
                        if (i < 0) {
                            i = config.indexOf("psk=");
                            len = "psk=".length();
                        } else {
                            len = "wep_key0=".length();
                        }
                        if (i < 0) {
                            return null;
                        }
                        return config.substring(i + len + 1,
                                config.indexOf("\n", i) - 1);

                    }
                    stringBuf = new StringBuffer();
                }
                stringBuf.append(line + "\n");
            }
            bufRead.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Interrupt error during get password.", e);
            return null;
        } finally {
            if (bufRead != null) {
                try {
                    bufRead.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "I/O error during closing reader.", e);
                    return null;
                }
            }
        }
        return null;
    }

    /**
     *
     */

    public void checkForRoot() {
        Process pro;
        DataOutputStream outStr = null;
        try {
            pro = Runtime.getRuntime().exec("su");
            outStr = new DataOutputStream(pro.getOutputStream());

            outStr.writeBytes("echo \"salam alikoum\" >/data/Test.txt\n");
            outStr.writeBytes("exit\n");
            outStr.flush();

            try {
                pro.waitFor();
                if (pro.exitValue() == 0) {
                    this.gotRoot = true;
                } else {
                    this.gotRoot = false;
                }
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, "Interrupt error during check root.", e);
                this.gotRoot = false;
            }
        } catch (IOException ex) {
            Log.e(LOG_TAG, "Unkown IO error during check root", ex);
            this.gotRoot = false;
        } finally {
            if (outStr != null) {
                try {
                    outStr.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "I/O error during closing reader.", e);
                }
            }
        }
    }

    /**
     * Method to Get Ap Capabilities
     *
     * @param mSSID Name of HotSpot
     * @return String contain Ap Capabilities
     */
    public String getApCapabilities(String mSSID) {
        scanNetworks();
        for (ScanResult r : mResults) {
            if (r.SSID.equals(mSSID)) {
                return r.capabilities;
            }
        }

        return null;
    }

    /**
     * Method to Get Ap frequency
     *
     * @param mSSID Name of HotSpot
     * @return int contain Link Speed
     */
    public int getApfrequency(String mSSID) {
        scanNetworks();
        for (ScanResult r : mResults) {
            if (r.SSID.equals(mSSID)) {
                return r.frequency;
            }
        }

        return 0;
    }


    /**
     * Method to Get Ap Signal Level
     *
     * @param mSSID Name of HotSpot
     * @return int contain Link Speed
     */
    public int getApSignalLevel(String mSSID) {
        scanNetworks();
        for (ScanResult r : mResults) {
            if (r.SSID.equals(mSSID)) {
                return r.level;
            }
        }

        return 0;
    }

    /**
     * Method to Get Security Mode By Network SSID
     *
     * @param SSID Name of HotSpot
     * @return OPEN PSK EAP OR WEP
     */
    public String getSecurityModeBySSID(String SSID) {

        List<ScanResult> scanResultList = mWifiManager.getScanResults();

        if (mWifiManager.isWifiEnabled()) {

            for (ScanResult result : scanResultList) {

                if (result.SSID.equals(SSID)) {
                    return getSecurityMode(result);

                }
            }

        }
        return null;
    }

    /**
     * Method to Get Network Security Mode
     *
     * @param scanResult
     * @return OPEN PSK EAP OR WEP
     */
    public String getSecurityMode(ScanResult scanResult) {
        final String cap = scanResult.capabilities;
        final String[] modes = {"WPA", "EAP", "WEP"};
        for (int i = modes.length - 1; i >= 0; i--) {
            if (cap.contains(modes[i])) {
                return modes[i];
            }
        }
        return "OPEN";
    }

    public void startScan(long interval, long duration) {
        twoSecondTimer = new ScanTimerSimple(interval, duration, mContext);
        //Start the timer.
        twoSecondTimer.start();
    }

    public void stopScan() {
        twoSecondTimer.cancel();
    }

    /**
     *
     */


    class WifiReceiver extends BroadcastReceiver {

        public List<ScanResult> getResults() {
            return mResults;
        }

        public WifiManager getManager() {
            return mWifiManager;
        }

        @Override
        public void onReceive(Context c, Intent intent) {
            mResults = mWifiManager.getScanResults();

        }
    }


}
