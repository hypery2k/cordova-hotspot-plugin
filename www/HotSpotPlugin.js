/*
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Martin Reinhardt
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */


var HotSpotPlugin = function () {

};

HotSpotPlugin.prototype = {

    /**
     * Check if plugin is available
     *
     * @param {Function} callback
     *      A callback function with true or false as response
     */
    isAvailable: function (callback) {
        if (cordova.platformId == 'android') {
            callback(true);
        } else {
            callback(false);
        }
    },
    /**
     * Create a WiFi Hotspot
     *
     * @param {String} ssid
     *      SSID to connect
     * @param {String} mode
     *      wireless mode
     * @param {String} password
     *      password to use
     * @param {Function} successCB
     *      A callback function to be called when connected successful
     * @param {Object} errorCB
     *      A callback function to be called when connection was not successful
     */
    createHotspot: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "createHotspot", [ssid, password, mode]);
    },
    stopHotspot: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "stopHotspot", []);
    },


    isHotspotEnabled: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "isHotspotEnabled", []);
    },


    getAllHotspotDevices: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "getAllHotspotDevices", []);
    },

    /**
     * Connect to a WiFi Hotspot
     *
     * @param {String} ssid
     *      SSID to connect
     * @param {String} password
     *      password to use
     * @param {Function} successCB
     *      A callback function to be called when connected successful
     * @param {Object} errorCB
     *      A callback function to be called when connection was not successful
     */
    connectToHotspot: function (ssid, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "connectToHotspot", [ssid, password]);
    },
    /**
     * Check if WiFi is enabled
     *
     * @param {Function} successCB
     *      A callback function to be called when WiFi is enabled
     * @param {Object} errorCB
     *      A callback function to be called when WiFi is disabled
     */
    isWifiOn: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'isWifiOn', []);
    },
    /**
     * Check if WiFi is supported
     *
     * @param {Function} successCB
     *      A callback function to be called when WiFi is supported
     * @param {Object} errorCB
     *      A callback function to be called when WiFi is not supported
     */
    isWifiSupported: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'isWifiSupported', []);
    },
    /**
     * Check if WiFi Direct is supported
     *
     * @param {Function} successCB
     *      A callback function to be called when WiFi Direct is supported
     * @param {Object} errorCB
     *      A callback function to be called when WiFi Direct is not supported
     */
    isWifiDirectSupported: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'isWifiDirectSupported', []);
    }
}
;

module.exports = new HotSpotPlugin();