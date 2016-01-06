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
     *      wireless mode (Open, WEP, WPA, WPA_PSK)
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
     * configure current WiFi Hotspot
     *
     * @param {String} ssid
     *      SSID to connect
     * @param {String} mode
     *      mode use (Open, WEP, WPA, WPA_PSK)
     * @param {String} password
     *      password to use
     * @param {Function} successCB
     *      A callback function to be called when connected successful
     * @param {Object} errorCB
     *      A callback function to be called when connection was not successful
     */
    configureHotspot: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "configureHotspot", [ssid, password, mode]);
    },

    /**
     * add a WiFi network
     *
     * @param {String} ssid
     *      SSID to connect
     * @param {String} mode
     *      mode use (Open, WEP, WPA, WPA_PSK)
     * @param {String} password
     *      password to use
     * @param {Function} successCB
     *      A callback function to be called when connected successful
     * @param {Object} errorCB
     *      A callback function to be called when connection was not successful
     */
    addWifiNetwork: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "addWifiNetwork", [ssid, password, mode]);
    },

    /**
     * Delete a WiFi network
     *
     * @param {String} ssid
     *      SSID to connect
     * @param {Function} successCB
     *      A callback function to be called when connected successful
     * @param {Object} errorCB
     *      A callback function to be called when connection was not successful
     */
    removeWifiNetwork: function (ssid, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "removeWifiNetwork", [ssid]);
    },
    /**
     * Check if connection to internet is active
     *
     * @param {Function} successCB
     *      A callback function to be called when connection is active
     * @param {Object} errorCB
     *      A callback function to be called when not
     */
    isConnectedToInternet: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'isConnectedToInternet', []);
    },
    /**
     * Check if connection to internet is active via WiFi
     *
     * @param {Function} successCB
     *      A callback function to be called when connection is done via wifi
     * @param {Object} errorCB
     *      A callback function to be called when not
     */
    isConnectedToInternetViaWifi: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'isConnectedToInternetViaWifi', []);
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
    },
    /**
     * Scan wifi
     *
     * @param {Function} successCB
     *      A callback function to be called when scan is done
     * @param {Object} errorCB
     *      An error callback
     */
    scanWifi: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'scanWifi', []);
    },
    /**
     * Scan wifi by level
     *
     * @param {Function} successCB
     *      A callback function to be called when scan is done
     * @param {Object} errorCB
     *      An error callback
     */
    scanWifiByLevel: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'scanWifiByLevel', []);
    },
    /**
     * Start a perodically scan wifi
     *
     * @param {long} interval
     *      interval to use
     * @param {long} duration
     *      duration to use
     * @param {Function} successCB
     *      A callback function to be called when scan is started
     * @param {Object} errorCB
     *      An error callback
     */
    startPeriodicallyScan: function (interval, duration, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'startPeriodicallyScan', [interval, duration]);
    },
    /**
     * Stop a perodically scan wifi
     *
     * @param {Function} successCB
     *      A callback function to be called when scan is stopped
     * @param {Object} errorCB
     *      An error callback
     */
    stopPeriodicallyScan: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'stopPeriodicallyScan', []);
    },
    /**
     * Get network information, e.g Gateway Ip/Mac Device Mac/Ip etc
     *
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    getNetConfig: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'getNetConfig', []);
    },
    /**
     * Ping a host
     *
     * @param {String} ip
     *      host IP
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    pingHost: function (ip, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'pingHost', [ip]);
    },
    /**
     * Get MAC address of host
     *
     * @param {String} ip
     *      host IP
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    getMacAddressOfHost: function (ip, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'getMacAddressOfHost', [ip]);
    },
    /**
     * dnsLive
     *
     * @param {String} ip
     *      host IP
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    isDnsLive: function (ip, successCB, errorCB) {
        cordova.exec(function (code) {
            if (code === 1) {
                successCB(true);
            } else {
                successCB(false);
            }
        }, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'dnsLive', [ip]);
    },
    /**
     * portLive
     *
     * @param {String} ip
     *      host IP
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    isPortLive: function (ip, successCB, errorCB) {
        cordova.exec(function (code) {
            if (code === 1) {
                successCB(true);
            } else {
                successCB(false);
            }
        }, errorCB, 'HotSpotPlugin', 'portLive', [ip]);
    },
    /**
     * Check Device is Rooted
     *
     * @param {Function} successCB
     *      A callback function to be called with all information
     * @param {Object} errorCB
     *      An error callback
     */
    isRooted: function (successCB, errorCB) {
        cordova.exec(function (code) {
            if (code === 1) {
                successCB(true);
            } else {
                successCB(false);
            }
        }, errorCB, 'HotSpotPlugin', 'checkRoot', []);
    }
}
;

module.exports = new HotSpotPlugin();