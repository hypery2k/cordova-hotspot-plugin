/* 
 * Copyright (C) 2013-2014 www.Andbrain.com 
 * Faster and more easily to create android apps
 * 
 * */
package com.mady.wifi.api;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;


public class ScanTimerSimple extends ScanTimer {
    List<ScanResult> mResults;
    WifiManager mWifiManager;

    public ScanTimerSimple(Context c) {
        super(c);
        mWifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);

    }

    public ScanTimerSimple(long interval, long duration, Context c) {
        super(interval, duration, c);
        mWifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);

    }

    /**
     * This method is called periodically with the interval set as the delay between subsequent calls.
     */

    @Override
    protected void onTick() {
        scanNetworks();
    }

    @Override
    protected void onFinish() {

    }

    public void scanNetworks() {
        boolean scan = mWifiManager.startScan();

        if (scan) {
            mResults = mWifiManager.getScanResults();
        }

    }
}
