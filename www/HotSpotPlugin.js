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

/**
 * @namespace
 */
var HotSpotPlugin = function () {

};


/**
 * Callback which provides the error details.
 *
 * @callback errorCallback
 * @param {Object} err - error details.
 */

HotSpotPlugin.prototype = {


    /**
     * Callback which provides the toggle Wifi info
     *
     * @callback isAvailableCallback
     * @param {boolean} available, true or false as response
     */

    /**
     * Check if plugin is available
     * @param {isAvailableCallback} callback
     *      A callback function which is invoked on success.
     */
    isAvailable: function (callback) {
        if (cordova.platformId == 'android') {
            callback(true);
        } else {
            callback(false);
        }
    },

    /**
     * @callback getWriteSettingsCallback
     * @param {boolean} settings false if no write settings, true if write settings are permitted
     */

    /**
     * get the current write settings of the app
     * @param  {getWriteSettingsCallback} successCB
     *
     * @param  {errorCallback} errorCB
     */
    getWriteSettings: function(successCB,errorCB) {
        cordova.exec(
            function(val){
                if(val === 1)
                    successCB(true);
                else
                    successCB(false);
            },
            errorCB,
            'HotSpotPlugin',
            'getWriteSettings',
            []
        );
    },

    /**
     * get the current write settings of the app
     * @param  {requestWriteSettingsCallback} callback
     *      A callback function which is invoked on success.     *
     * @param  {errorCB} error callback
     *      A callback function to be called when errors occurr
     */
    requestWriteSettings: function(successCB,errorCB) {
        cordova.exec(
            successCB,
            errorCB,
            'HotSpotPlugin',
            'requestWriteSettings',
            []
        );
    },


    /**
     * Callback which provides the toggle Wifi info
     *
     * @callback toggleWifiCallback
     * @param {boolean} wifiOn, true or false as response,true if wifi ON and false if wifi OFF
     */

    /**
     * Method to Toggle wifi ON/OFF
     *
     * @param {toggleWifiCallback} callback
     *      A callback function which is invoked on success.
     * @param {errorCallback} errorCB
     *      A callback function to be called when errors occurr
     */
    toggleWifi: function (callback, errorCB) {
        cordova.exec(function (code) {
            if (code === 1) {
                callback(true);
            } else {
                callback(false);
            }
        }, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "toggleWifi", []);
    },


    /**
     * A callback function to be called when start was successful
     *
     * @callback createHotspotCallback
     */
    /**
     * Create a WiFi Hotspot
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {string} mode
     *      wireless mode (Open, WEP, WPA, WPA_PSK)
     * @param {string} password
     *      password to use
     * @param {createHotspotCallback} successCB
     *      A callback function to be called when start was successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when connection was not successful
     */
    createHotspot: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "createHotspot", [ssid, mode, password]);
    },

    /**
     * A callback function to be called when start was successful
     *
     * @callback startHotspotCallback
     */
    /**
     * Start a default WiFi Hotspot
     *
     * @param {startHotspotCallback} successCB
     *      A callback function to be called when start was successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when start was not successful
     */
    startHotspot: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "startHotspot", []);
    },

    /**
     * A callback function to be called when stop was successful
     *
     * @callback stopHotspotCallback
     */
    /**
     * Stop a running default WiFi Hotspot
     *
     * @param {stopHotspotCallback} successCB
     *      A callback function to be called when stop was successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when stop was not successful
     */
    stopHotspot: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "stopHotspot", []);
    },
    /**
     * A callback function to be called when stop was successful
     *
     * @callback isHotspotEnabledCallback
     * @param {boolean} enabled, true  if hotspot is off or false as response
     */
    /**
     * Checks if hot spot is enabled
     * @param {isHotspotEnabledCallback} successCB
     *      A callback function with the result
     * @param {errorCallback} errorCB
     *      A error callback function
     */
    isHotspotEnabled: function (successCB, errorCallback) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, "HotSpotPlugin", "isHotspotEnabled", []);
    },
    /**
     * A callback function to be called when connected successful and is
     * called with an array of JSON objects.
     *
     * @callback getAllHotspotDevicesCallback
     * @param {Array} of objects:
     *      [{
     *        ip,
     *        mac
     *      }]
     */
    /**
     * Get all connected devices
     * @param {getAllHotspotDevicesCallback}
     *      A callback function when hotspot connection is active and info was rad.
     * @param {errorCallback} errorCB
     *      An error callback
     */
    getAllHotspotDevices: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "getAllHotspotDevices", []);
    },

    /**
     * A callback function to be called when connected successful
     *
     * @callback connectToWifiCallback
     */
    /**
     * Connect to a WiFi network
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {string} password
     *      password to use
     * @param {connectToWifiCallback} successCB
     *      A callback function to be called when connected successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when connection was not successful
     */
    connectToWifi: function (ssid, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "connectToWifi", [ssid, password]);
    },
    /**
     * A callback function to be called when connected successful
     *
     * @callback connectToWifiAuthEncryptCallback
     */
    /**
     * configure current WiFi Hotspot
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {string} password
     *      password to use
     * @param {string} authentication
     *      mode use (LEAP, SHARED, OPEN)
     * @param {string[]} encryption
     *      mode use (CCMP, TKIP, WEP104, WEP40)
     * @param {connectToWifiAuthEncryptCallback} successCB
     *      A callback function to be called when connected successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when connection was not successful
     */
    connectToWifiAuthEncrypt: function (ssid, password, authentication, encryption, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "connectToWifiAuthEncrypt", [ssid, password, authentication, encryption]);
    },
    /**
     * A callback function to be called when connected successful
     *
     * @callback configureHotspotCallback
     */
    /**
     * configure current WiFi Hotspot
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {string} mode
     *      mode use (Open, WEP, WPA, WPA_PSK)
     * @param {string} password
     *      password to use
     * @param {configureHotspotCallback} successCB
     *      A callback function to be called when connected successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when configuration was not successful
     */
    configureHotspot: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "configureHotspot", [ssid, mode, password]);
    },
    /**
     * A callback function to be called when connected successful
     *
     * @callback addWifiNetworkCallback
     */
    /**
     * add a WiFi network
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {string} mode
     *      mode use (Open, WEP, WPA, WPA_PSK)
     * @param {string} password
     *      password to use
     * @param {function} successCB
     *      A callback function to be called when adding successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when adding was not successful
     */
    addWifiNetwork: function (ssid, mode, password, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "addWifiNetwork", [ssid, password, mode]);
    },
    /**
     * A callback function function to be called when removal was successful
     *
     * @callback removeWifiNetworkCallback
     */
    /**
     * Delete a WiFi network
     *
     * @param {string} ssid
     *      SSID to connect
     * @param {function} successCB
     *      A callback function function to be called when removal was successful
     * @param {errorCallback} errorCB
     *      A callback function to be called when removal was not successful
     */
    removeWifiNetwork: function (ssid, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, "HotSpotPlugin", "removeWifiNetwork", [ssid]);
    },
    /**
     * A callback function to be called with the result
     *
     * @callback isConnectedToInternetCallback
     * @param {boolean} connected, true  if device is connected to internet, otherwise false
     */
    /**
     * Check if connection to internet is active
     *
     * @param {isConnectedToInternetCallback} successCB
     *      A callback function to be called with the result
     * @param {errorCallback} errorCB
     *      An error callback
     */
    isConnectedToInternet: function (successCB, errorCB) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, 'HotSpotPlugin', 'isConnectedToInternet', []);
    },
    /**
     * A callback function to be called when connection is done via wifi
     *
     * @callback isConnectedToInternetViaWifiCallback
     * @param {boolean} connected, true  if device is connected to internet via WiFi, otherwise false
     */
    /**
     * Check if connection to internet is active via WiFi
     *
     * @param {isConnectedToInternetViaWifiCallback} successCB
     *      A callback function to be called with the result
     * @param {errorCallback} errorCB
     *      An error callback
     */
    isConnectedToInternetViaWifi: function (successCB, errorCB) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, 'HotSpotPlugin', 'isConnectedToInternetViaWifi', []);
    },
    /**
     * A callback function to be called when WiFi is enabled
     *
     * @callback isWifiOnCallback
     * @param {boolean} connected, true  if device if WiFi is on, otherwise false
     */
    /**
     * Check if WiFi is enabled
     *
     * @param {isWifiOnCallback} successCB
     *      A callback function with the result
     * @param {errorCallback} errorCB
     *      An error callback
     */
    isWifiOn: function (successCB, errorCB) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, 'HotSpotPlugin', 'isWifiOn', []);
    },
    /**
     * A callback function to be called when WiFi is supported
     *
     * @callback isWifiSupportedCallback
     * @param {boolean} wifiSupported, true  if  WiFi is supported, otherwise false
     */
    /**
     * Check if WiFi is supported
     *
     * @param {isWifiSupportedCallback} successCB
     *      A callback function with the result
     * @param {errorCallback} errorCB
     *      An error callback
     */
    isWifiSupported: function (successCB, errorCB) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, 'HotSpotPlugin', 'isWifiSupported', []);
    },
    /**
     * A callback function to be called when WiFi is supported
     *
     * @callback isWifiDirectSupportedCallback
     * @param {boolean} wifiSupported, true  if  WiFi Direct is supported, otherwise false
     */
    /**
     * Check if WiFi Direct is supported
     *
     * @param {isWifiDirectSupportedCallback} successCB
     *      A callback function with the result
     * @param {errorCallback} errorCB
     *      An error callback
     */
    isWifiDirectSupported: function (successCB, errorCB) {
        cordova.exec(function () {
            successCB(true);
        }, function () {
            successCB(false);
        }, 'HotSpotPlugin', 'isWifiDirectSupported', []);
    },
    /**
     * A callback function to be called when scan is started
     *
     * @callback scanWifiCallback
     * @param {Array} info - An array of JSON objects with the following information:
     *  [{
     *      SSID,
     *      BSSID,
     *      frequency,
     *      level,
     *      timestamp,
     *      capabilities
     *  }]
     */
    /**
     * Scan wifi
     *
     * @param {scanWifiCallback} successCB
     *      A callback function to be called when scan is started
     * @param {errorCallback} errorCB
     *      An error callback
     */
    scanWifi: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'scanWifi', []);
    },
    /**
     * A callback function to be called when scan is started
     *
     * @callback scanWifiCallback
     * @param {Array} info - An array of JSON objects with the following information:
     *  [{
     *      SSID,
     *      BSSID,
     *      frequency,
     *      level,
     *      timestamp,
     *      capabilities
     *  }]
     */
    /**
     * Scan wifi by level
     *
     * @param {scanWifiByLevelCallback} successCB
     *      A callback function to be called when scan is started
     * @param {errorCallback} errorCB
     *      An error callback
     */
    scanWifiByLevel: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'scanWifiByLevel', []);
    },
    /**
     * A callback function to be called when scan is started
     *
     * @callback startWifiPeriodicallyScanCallback
     */
    /**
     * Start a periodically scan wifi
     *
     * @param {long} interval
     *      interval to use
     * @param {long} duration
     *      duration to use
     * @param {startWifiPeriodicallyScanCallback} successCB
     *      A callback function to be called when scan is started
     * @param {errorCallback} errorCB
     *      An error callback
     */
    startWifiPeriodicallyScan: function (interval, duration, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'startWifiPeriodicallyScan', [interval, duration]);
    },
    /**
     * A callback function to be called when scan is stopped
     *
     * @callback stopWifiPeriodicallyScanCallback
     */
    /**
     * Stop a periodically scan wifi
     *
     * @param {stopWifiPeriodicallyScanCallback} successCB
     *      A callback function to be called when scan is stopped
     * @param {errorCallback} errorCB
     *      An error callback
     */
    stopWifiPeriodicallyScan: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'stopWifiPeriodicallyScan', []);
    },
    /**
     * A callback function to be called when scan is started
     *
     * @callback getNetConfigCallback
     * @param {Object} info - An JSON object with the following information:
     *  {
     *      deviceIPAddress,
     *      deviceMacAddress,
     *      gatewayIPAddress,
     *      gatewayMacAddress
     *  }
     */
    /**
     * Get network information, e.g Gateway Ip/Mac Device Mac/Ip etc
     *
     * @param {getNetConfigCallback} successCB
     *      A callback function to be called with all information
     * @param {errorCallback} errorCB
     *      An error callback
     */
    getNetConfig: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'getNetConfig', []);
    },
    /**
     * Callback which provides the connection information.
     *
     * @callback getConnectionInfoCallback
     * @param {Object} info - An JSON object with the following information:
     * {
     *      SSID,
     *      linkSpeed,
     *      IPAddress
     * }
     */
    /**
     * Get current connection information, e.g SSID, linkSpeed,IPAddress etc
     *
     * @param {getConnectionInfoCallback} successCB
     *      A callback function with the connection details.
     * @param {errorCallback} errorCB
     *      An error callback
     */
    getConnectionInfo: function (successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'getConnectionInfo', []);
    },
    /**
     * Callback which provides the ping information.
     *
     * @callback pingHostCallback
     * @param {Object} info - An JSON object with the following information:
     * {
     *      requestTimeout,
     *      stat: {
     *          requestTimeout,
     *          time,
     *          min,
     *          max,
     *          stddev
     *      }
     * }
     */
    /**
     * Ping a host
     *
     * @param {string} ip
     *      host IP
     * @param {pingHostCallback} successCB
     *      A callback function to be called with all information
     * @param {errorCallback} errorCB
     *      An error callback
     */
    pingHost: function (ip, successCB, errorCB) {
        var statsPattern = /\s=\s(\d+\.\d+)\/(\d+\.\d+)\/(\d+\.\d+)\/(\d+\.\d+)/;

        function parseResponse(response) {
            var stats = response.substring(response.indexOf('min/avg/max/mdev') + 'min/avg/max/mdev'.length);

            var result = {
                requestTimeout: response.indexOf('Request timeout') !== -1
            };
            result.stat = {
                packetLoss: response.match(/\sreceived,\s(\d+(\.\d+)?)%\spacket\sloss/)[1],
                time: response.match(/,\stime\s(\d+)ms/)[1]
            };
            if (!!stats && stats.match(statsPattern)) {
                result.stat.min = stats.match(statsPattern)[1];
                result.stat.max = stats.match(statsPattern)[3];
                result.stat.avg = stats.match(statsPattern)[2];
                result.stat.stddev = stats.match(statsPattern)[4];
            }

            return result;
        }

        cordova.exec(function (response) {
            if (response && response.length > 0) {
                successCB(parseResponse(response));
            } else {
                errorCB(parseResponse(response));
            }
        }, function (err) {
            errorCB(parseResponse(err));
        }, 'HotSpotPlugin', 'pingHost', [ip]);
    },
    /**
     * A callback function to be called with all information
     *
     * @callback getMacAddressOfHostCallback
     * @param {String} info - the MAC address
     */
    /**
     * Get MAC address of host
     *
     * @param {string} ip
     *      host IP
     * @param {getMacAddressOfHost} successCB
     *      A callback function to be called with all information
     * @param {errorCallback} errorCB
     *      An error callback
     */
    getMacAddressOfHost: function (ip, successCB, errorCB) {
        cordova.exec(successCB, function (err) {
            errorCB(err);
        }, 'HotSpotPlugin', 'getMacAddressOfHost', [ip]);
    },
    /**
     * A callback function to be called with DNS details
     *
     * @callback isDnsLiveCallback
     * @param {boolean} isDnsLive, true  if device is DNS is live, otherwise false
     */
    /**
     * dnsLive
     *
     * @param {string} ip
     *      host IP
     * @param {function} successCB
     *      A callback function to be called with the result
     * @param {errorCallback} errorCB
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
     * A callback function to be called with DNS details
     *
     * @callback isPortLiveCallback
     * @param {boolean} isPortLive, true  if device is port is available, otherwise false
     */
    /**
     * is port available
     *
     * @param {string} ip
     *      host IP
     * @param {isPortLiveCallback} successCB
     *      A callback function to be called with all information
     * @param {errorCallback} errorCB
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
     * A callback function to be called with Rooted information
     *
     * @callback isRootedCallback
     * @param {boolean} isRooted, true  if device is port is available, otherwise false
     */
    /**
     * Check Device is Rooted
     *
     * @param {isRootedCallback} successCB
     *      A callback function to be called with all information
     * @param {errorCallback} errorCB
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
};
module.exports = new HotSpotPlugin();
