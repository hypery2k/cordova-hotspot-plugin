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


import android.content.Intent;
import com.mady.wifi.api.WifiHotSpots;
import com.mady.wifi.api.WifiStatus;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class HotSpotPlugin extends CordovaPlugin {


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
        }

        if ("isWifiSupported".equals(action)) {
            if (isWifiSupported()) {
                callback.success();
            } else {
                callback.error("Wifi is not supported.");
            }
        }

        if ("isWifiDirectSupported".equals(action)) {

            if (isWifiDirectSupported()) {
                callback.success();
            } else {
                callback.error("Wifi direct is not supported.");
            }
        }

        if ("connectToHotspot".equals(action)) {
            if (connectToHotspot(args)) {
                callback.success();
            } else {
                callback.error("Connection was not successfull");
            }
        }


        // Returning false results in a "MethodNotFound" error.
        return false;
    }

    // IMPLEMENTATION

    public boolean connectToHotspot(JSONArray args) throws JSONException {
        String ssid = args.getString(0);
        String password = args.getString(1);
        return new WifiHotSpots(this.cordova.getActivity()).connectToHotspot(ssid, password);
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
}
