## Objects

<dl>
<dt><a href="#cordova.plugins.hotspot">cordova.plugins.hotspot</a> : <code>object</code></dt>
<dd></dd>
</dl>

## Typedefs

<dl>
<dt><a href="#errorCallback">errorCallback</a> : <code>function</code></dt>
<dd><p>Callback which provides the error details.</p>
</dd>
<dt><a href="#isAvailableCallback">isAvailableCallback</a> : <code>function</code></dt>
<dd><p>Callback which provides the toggle Wifi info</p>
</dd>
<dt><a href="#getWriteSettingsCallback">getWriteSettingsCallback</a> : <code>function</code></dt>
<dd></dd>
<dt><a href="#toggleWifiCallback">toggleWifiCallback</a> : <code>function</code></dt>
<dd><p>Callback which provides the toggle Wifi info</p>
</dd>
<dt><a href="#createHotspotCallback">createHotspotCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when start was successful</p>
</dd>
<dt><a href="#startHotspotCallback">startHotspotCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when start was successful</p>
</dd>
<dt><a href="#stopHotspotCallback">stopHotspotCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when stop was successful</p>
</dd>
<dt><a href="#isHotspotEnabledCallback">isHotspotEnabledCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when stop was successful</p>
</dd>
<dt><a href="#getAllHotspotDevicesCallback">getAllHotspotDevicesCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connected successful and is
called with an array of JSON objects.</p>
</dd>
<dt><a href="#connectToWifiCallback">connectToWifiCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connected successful</p>
</dd>
<dt><a href="#connectToWifiAuthEncryptCallback">connectToWifiAuthEncryptCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connected successful</p>
</dd>
<dt><a href="#configureHotspotCallback">configureHotspotCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connected successful</p>
</dd>
<dt><a href="#addWifiNetworkCallback">addWifiNetworkCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connected successful</p>
</dd>
<dt><a href="#removeWifiNetworkCallback">removeWifiNetworkCallback</a> : <code>function</code></dt>
<dd><p>A callback function function to be called when removal was successful</p>
</dd>
<dt><a href="#isConnectedToInternetCallback">isConnectedToInternetCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called with the result</p>
</dd>
<dt><a href="#isConnectedToInternetViaWifiCallback">isConnectedToInternetViaWifiCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when connection is done via wifi</p>
</dd>
<dt><a href="#isWifiOnCallback">isWifiOnCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when WiFi is enabled</p>
</dd>
<dt><a href="#isWifiSupportedCallback">isWifiSupportedCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when WiFi is supported</p>
</dd>
<dt><a href="#isWifiDirectSupportedCallback">isWifiDirectSupportedCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when WiFi is supported</p>
</dd>
<dt><a href="#scanWifiCallback">scanWifiCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when scan is started</p>
</dd>
<dt><a href="#scanWifiCallback">scanWifiCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when scan is started</p>
</dd>
<dt><a href="#startWifiPeriodicallyScanCallback">startWifiPeriodicallyScanCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when scan is started</p>
</dd>
<dt><a href="#stopWifiPeriodicallyScanCallback">stopWifiPeriodicallyScanCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when scan is stopped</p>
</dd>
<dt><a href="#getNetConfigCallback">getNetConfigCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called when scan is started</p>
</dd>
<dt><a href="#getConnectionInfoCallback">getConnectionInfoCallback</a> : <code>function</code></dt>
<dd><p>Callback which provides the connection information.</p>
</dd>
<dt><a href="#pingHostCallback">pingHostCallback</a> : <code>function</code></dt>
<dd><p>Callback which provides the ping information.</p>
</dd>
<dt><a href="#getMacAddressOfHostCallback">getMacAddressOfHostCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called with all information</p>
</dd>
<dt><a href="#isDnsLiveCallback">isDnsLiveCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called with DNS details</p>
</dd>
<dt><a href="#isPortLiveCallback">isPortLiveCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called with DNS details</p>
</dd>
<dt><a href="#isRootedCallback">isRootedCallback</a> : <code>function</code></dt>
<dd><p>A callback function to be called with Rooted information</p>
</dd>
</dl>

<a name="cordova.plugins.hotspot"></a>

## cordova.plugins.hotspot : <code>object</code>
**Kind**: global namespace  

* [cordova.plugins.hotspot](#cordova.plugins.hotspot) : <code>object</code>
    * [.isAvailable(callback)](#cordova.plugins.hotspot+isAvailable)
    * [.getWriteSettings(successCB, errorCB)](#cordova.plugins.hotspot+getWriteSettings)
    * [.requestWriteSettings(callback, error)](#cordova.plugins.hotspot+requestWriteSettings)
    * [.toggleWifi(callback, errorCB)](#cordova.plugins.hotspot+toggleWifi)
    * [.createHotspot(ssid, mode, password, successCB, errorCB)](#cordova.plugins.hotspot+createHotspot)
    * [.startHotspot(successCB, errorCB)](#cordova.plugins.hotspot+startHotspot)
    * [.stopHotspot(successCB, errorCB)](#cordova.plugins.hotspot+stopHotspot)
    * [.isHotspotEnabled(successCB, errorCB)](#cordova.plugins.hotspot+isHotspotEnabled)
    * [.getAllHotspotDevices(A, errorCB)](#cordova.plugins.hotspot+getAllHotspotDevices)
    * [.connectToWifi(ssid, password, successCB, errorCB)](#cordova.plugins.hotspot+connectToWifi)
    * [.connectToWifiAuthEncrypt(ssid, password, authentication, encryption, successCB, errorCB)](#cordova.plugins.hotspot+connectToWifiAuthEncrypt)
    * [.configureHotspot(ssid, mode, password, successCB, errorCB)](#cordova.plugins.hotspot+configureHotspot)
    * [.addWifiNetwork(ssid, mode, password, successCB, errorCB)](#cordova.plugins.hotspot+addWifiNetwork)
    * [.removeWifiNetwork(ssid, successCB, errorCB)](#cordova.plugins.hotspot+removeWifiNetwork)
    * [.isConnectedToInternet(successCB, errorCB)](#cordova.plugins.hotspot+isConnectedToInternet)
    * [.isConnectedToInternetViaWifi(successCB, errorCB)](#cordova.plugins.hotspot+isConnectedToInternetViaWifi)
    * [.isWifiOn(successCB, errorCB)](#cordova.plugins.hotspot+isWifiOn)
    * [.isWifiSupported(successCB, errorCB)](#cordova.plugins.hotspot+isWifiSupported)
    * [.isWifiDirectSupported(successCB, errorCB)](#cordova.plugins.hotspot+isWifiDirectSupported)
    * [.scanWifi(successCB, errorCB)](#cordova.plugins.hotspot+scanWifi)
    * [.scanWifiByLevel(successCB, errorCB)](#cordova.plugins.hotspot+scanWifiByLevel)
    * [.startWifiPeriodicallyScan(interval, duration, successCB, errorCB)](#cordova.plugins.hotspot+startWifiPeriodicallyScan)
    * [.stopWifiPeriodicallyScan(successCB, errorCB)](#cordova.plugins.hotspot+stopWifiPeriodicallyScan)
    * [.getNetConfig(successCB, errorCB)](#cordova.plugins.hotspot+getNetConfig)
    * [.getConnectionInfo(successCB, errorCB)](#cordova.plugins.hotspot+getConnectionInfo)
    * [.pingHost(ip, successCB, errorCB)](#cordova.plugins.hotspot+pingHost)
    * [.getMacAddressOfHost(ip, successCB, errorCB)](#cordova.plugins.hotspot+getMacAddressOfHost)
    * [.isDnsLive(ip, successCB, errorCB)](#cordova.plugins.hotspot+isDnsLive)
    * [.isPortLive(ip, successCB, errorCB)](#cordova.plugins.hotspot+isPortLive)
    * [.isRooted(successCB, errorCB)](#cordova.plugins.hotspot+isRooted)

<a name="cordova.plugins.hotspot+isAvailable"></a>

### cordova.plugins.hotspot.isAvailable(callback)
Check if plugin is available

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| callback | <code>[isAvailableCallback](#isAvailableCallback)</code> | A callback function which is invoked on success. |

<a name="cordova.plugins.hotspot+getWriteSettings"></a>

### cordova.plugins.hotspot.getWriteSettings(successCB, errorCB)
get the current write settings of the app

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type |
| --- | --- |
| successCB | <code>[getWriteSettingsCallback](#getWriteSettingsCallback)</code> | 
| errorCB | <code>[errorCallback](#errorCallback)</code> | 

<a name="cordova.plugins.hotspot+requestWriteSettings"></a>

### cordova.plugins.hotspot.requestWriteSettings(callback, error)
get the current write settings of the app

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| callback | <code>requestWriteSettingsCallback</code> | A callback function which is invoked on success.     * |
| error | <code>errorCB</code> | callback      A callback function to be called when errors occurr |

<a name="cordova.plugins.hotspot+toggleWifi"></a>

### cordova.plugins.hotspot.toggleWifi(callback, errorCB)
Method to Toggle wifi ON/OFF

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| callback | <code>[toggleWifiCallback](#toggleWifiCallback)</code> | A callback function which is invoked on success. |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when errors occurr |

<a name="cordova.plugins.hotspot+createHotspot"></a>

### cordova.plugins.hotspot.createHotspot(ssid, mode, password, successCB, errorCB)
Create a WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | wireless mode (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>[createHotspotCallback](#createHotspotCallback)</code> | A callback function to be called when start was successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+startHotspot"></a>

### cordova.plugins.hotspot.startHotspot(successCB, errorCB)
Start a default WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[startHotspotCallback](#startHotspotCallback)</code> | A callback function to be called when start was successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when start was not successful |

<a name="cordova.plugins.hotspot+stopHotspot"></a>

### cordova.plugins.hotspot.stopHotspot(successCB, errorCB)
Stop a running default WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[stopHotspotCallback](#stopHotspotCallback)</code> | A callback function to be called when stop was successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when stop was not successful |

<a name="cordova.plugins.hotspot+isHotspotEnabled"></a>

### cordova.plugins.hotspot.isHotspotEnabled(successCB, errorCB)
Checks if hot spot is enabled

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isHotspotEnabledCallback](#isHotspotEnabledCallback)</code> | A callback function with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A error callback function |

<a name="cordova.plugins.hotspot+getAllHotspotDevices"></a>

### cordova.plugins.hotspot.getAllHotspotDevices(A, errorCB)
Get all connected devices

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| A | <code>[getAllHotspotDevicesCallback](#getAllHotspotDevicesCallback)</code> | callback function when hotspot connection is active and info was rad. |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+connectToWifi"></a>

### cordova.plugins.hotspot.connectToWifi(ssid, password, successCB, errorCB)
Connect to a WiFi network

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| password | <code>string</code> | password to use |
| successCB | <code>[connectToWifiCallback](#connectToWifiCallback)</code> | A callback function to be called when connected successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+connectToWifiAuthEncrypt"></a>

### cordova.plugins.hotspot.connectToWifiAuthEncrypt(ssid, password, authentication, encryption, successCB, errorCB)
configure current WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| password | <code>string</code> | password to use |
| authentication | <code>string</code> | mode use (LEAP, SHARED, OPEN) |
| encryption | <code>Array.&lt;string&gt;</code> | mode use (CCMP, TKIP, WEP104, WEP40) |
| successCB | <code>[connectToWifiAuthEncryptCallback](#connectToWifiAuthEncryptCallback)</code> | A callback function to be called when connected successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+configureHotspot"></a>

### cordova.plugins.hotspot.configureHotspot(ssid, mode, password, successCB, errorCB)
configure current WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | mode use (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>[configureHotspotCallback](#configureHotspotCallback)</code> | A callback function to be called when connected successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when configuration was not successful |

<a name="cordova.plugins.hotspot+addWifiNetwork"></a>

### cordova.plugins.hotspot.addWifiNetwork(ssid, mode, password, successCB, errorCB)
add a WiFi network

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | mode use (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when adding successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when adding was not successful |

<a name="cordova.plugins.hotspot+removeWifiNetwork"></a>

### cordova.plugins.hotspot.removeWifiNetwork(ssid, successCB, errorCB)
Delete a WiFi network

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| successCB | <code>function</code> | A callback function function to be called when removal was successful |
| errorCB | <code>[errorCallback](#errorCallback)</code> | A callback function to be called when removal was not successful |

<a name="cordova.plugins.hotspot+isConnectedToInternet"></a>

### cordova.plugins.hotspot.isConnectedToInternet(successCB, errorCB)
Check if connection to internet is active

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isConnectedToInternetCallback](#isConnectedToInternetCallback)</code> | A callback function to be called with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isConnectedToInternetViaWifi"></a>

### cordova.plugins.hotspot.isConnectedToInternetViaWifi(successCB, errorCB)
Check if connection to internet is active via WiFi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isConnectedToInternetViaWifiCallback](#isConnectedToInternetViaWifiCallback)</code> | A callback function to be called with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isWifiOn"></a>

### cordova.plugins.hotspot.isWifiOn(successCB, errorCB)
Check if WiFi is enabled

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isWifiOnCallback](#isWifiOnCallback)</code> | A callback function with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isWifiSupported"></a>

### cordova.plugins.hotspot.isWifiSupported(successCB, errorCB)
Check if WiFi is supported

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isWifiSupportedCallback](#isWifiSupportedCallback)</code> | A callback function with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isWifiDirectSupported"></a>

### cordova.plugins.hotspot.isWifiDirectSupported(successCB, errorCB)
Check if WiFi Direct is supported

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isWifiDirectSupportedCallback](#isWifiDirectSupportedCallback)</code> | A callback function with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+scanWifi"></a>

### cordova.plugins.hotspot.scanWifi(successCB, errorCB)
Scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[scanWifiCallback](#scanWifiCallback)</code> | A callback function to be called when scan is started |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+scanWifiByLevel"></a>

### cordova.plugins.hotspot.scanWifiByLevel(successCB, errorCB)
Scan wifi by level

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>scanWifiByLevelCallback</code> | A callback function to be called when scan is started |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+startWifiPeriodicallyScan"></a>

### cordova.plugins.hotspot.startWifiPeriodicallyScan(interval, duration, successCB, errorCB)
Start a periodically scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| interval | <code>long</code> | interval to use |
| duration | <code>long</code> | duration to use |
| successCB | <code>[startWifiPeriodicallyScanCallback](#startWifiPeriodicallyScanCallback)</code> | A callback function to be called when scan is started |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+stopWifiPeriodicallyScan"></a>

### cordova.plugins.hotspot.stopWifiPeriodicallyScan(successCB, errorCB)
Stop a periodically scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[stopWifiPeriodicallyScanCallback](#stopWifiPeriodicallyScanCallback)</code> | A callback function to be called when scan is stopped |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+getNetConfig"></a>

### cordova.plugins.hotspot.getNetConfig(successCB, errorCB)
Get network information, e.g Gateway Ip/Mac Device Mac/Ip etc

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[getNetConfigCallback](#getNetConfigCallback)</code> | A callback function to be called with all information |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+getConnectionInfo"></a>

### cordova.plugins.hotspot.getConnectionInfo(successCB, errorCB)
Get current connection information, e.g SSID, linkSpeed,IPAddress etc

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[getConnectionInfoCallback](#getConnectionInfoCallback)</code> | A callback function with the connection details. |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+pingHost"></a>

### cordova.plugins.hotspot.pingHost(ip, successCB, errorCB)
Ping a host

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>[pingHostCallback](#pingHostCallback)</code> | A callback function to be called with all information |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+getMacAddressOfHost"></a>

### cordova.plugins.hotspot.getMacAddressOfHost(ip, successCB, errorCB)
Get MAC address of host

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>getMacAddressOfHost</code> | A callback function to be called with all information |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isDnsLive"></a>

### cordova.plugins.hotspot.isDnsLive(ip, successCB, errorCB)
dnsLive

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>function</code> | A callback function to be called with the result |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isPortLive"></a>

### cordova.plugins.hotspot.isPortLive(ip, successCB, errorCB)
is port available

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>[isPortLiveCallback](#isPortLiveCallback)</code> | A callback function to be called with all information |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="cordova.plugins.hotspot+isRooted"></a>

### cordova.plugins.hotspot.isRooted(successCB, errorCB)
Check Device is Rooted

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>[isRootedCallback](#isRootedCallback)</code> | A callback function to be called with all information |
| errorCB | <code>[errorCallback](#errorCallback)</code> | An error callback |

<a name="errorCallback"></a>

## errorCallback : <code>function</code>
Callback which provides the error details.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| err | <code>Object</code> | error details. |

<a name="isAvailableCallback"></a>

## isAvailableCallback : <code>function</code>
Callback which provides the toggle Wifi info

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| available, | <code>boolean</code> | true or false as response |

<a name="getWriteSettingsCallback"></a>

## getWriteSettingsCallback : <code>function</code>
**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| settings | <code>boolean</code> | false if no write settings, true if write settings are permitted |

<a name="toggleWifiCallback"></a>

## toggleWifiCallback : <code>function</code>
Callback which provides the toggle Wifi info

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| wifiOn, | <code>boolean</code> | true or false as response,true if wifi ON and false if wifi OFF |

<a name="createHotspotCallback"></a>

## createHotspotCallback : <code>function</code>
A callback function to be called when start was successful

**Kind**: global typedef  
<a name="startHotspotCallback"></a>

## startHotspotCallback : <code>function</code>
A callback function to be called when start was successful

**Kind**: global typedef  
<a name="stopHotspotCallback"></a>

## stopHotspotCallback : <code>function</code>
A callback function to be called when stop was successful

**Kind**: global typedef  
<a name="isHotspotEnabledCallback"></a>

## isHotspotEnabledCallback : <code>function</code>
A callback function to be called when stop was successful

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| enabled, | <code>boolean</code> | true  if hotspot is off or false as response |

<a name="getAllHotspotDevicesCallback"></a>

## getAllHotspotDevicesCallback : <code>function</code>
A callback function to be called when connected successful and is
called with an array of JSON objects.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| of | <code>Array</code> | objects:      [{        ip,        mac      }] |

<a name="connectToWifiCallback"></a>

## connectToWifiCallback : <code>function</code>
A callback function to be called when connected successful

**Kind**: global typedef  
<a name="connectToWifiAuthEncryptCallback"></a>

## connectToWifiAuthEncryptCallback : <code>function</code>
A callback function to be called when connected successful

**Kind**: global typedef  
<a name="configureHotspotCallback"></a>

## configureHotspotCallback : <code>function</code>
A callback function to be called when connected successful

**Kind**: global typedef  
<a name="addWifiNetworkCallback"></a>

## addWifiNetworkCallback : <code>function</code>
A callback function to be called when connected successful

**Kind**: global typedef  
<a name="removeWifiNetworkCallback"></a>

## removeWifiNetworkCallback : <code>function</code>
A callback function function to be called when removal was successful

**Kind**: global typedef  
<a name="isConnectedToInternetCallback"></a>

## isConnectedToInternetCallback : <code>function</code>
A callback function to be called with the result

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| connected, | <code>boolean</code> | true  if device is connected to internet, otherwise false |

<a name="isConnectedToInternetViaWifiCallback"></a>

## isConnectedToInternetViaWifiCallback : <code>function</code>
A callback function to be called when connection is done via wifi

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| connected, | <code>boolean</code> | true  if device is connected to internet via WiFi, otherwise false |

<a name="isWifiOnCallback"></a>

## isWifiOnCallback : <code>function</code>
A callback function to be called when WiFi is enabled

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| connected, | <code>boolean</code> | true  if device if WiFi is on, otherwise false |

<a name="isWifiSupportedCallback"></a>

## isWifiSupportedCallback : <code>function</code>
A callback function to be called when WiFi is supported

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| wifiSupported, | <code>boolean</code> | true  if  WiFi is supported, otherwise false |

<a name="isWifiDirectSupportedCallback"></a>

## isWifiDirectSupportedCallback : <code>function</code>
A callback function to be called when WiFi is supported

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| wifiSupported, | <code>boolean</code> | true  if  WiFi Direct is supported, otherwise false |

<a name="scanWifiCallback"></a>

## scanWifiCallback : <code>function</code>
A callback function to be called when scan is started

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>Array</code> | An array of JSON objects with the following information:  [{      SSID,      BSSID,      frequency,      level,      timestamp,      capabilities  }] |

<a name="scanWifiCallback"></a>

## scanWifiCallback : <code>function</code>
A callback function to be called when scan is started

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>Array</code> | An array of JSON objects with the following information:  [{      SSID,      BSSID,      frequency,      level,      timestamp,      capabilities  }] |

<a name="startWifiPeriodicallyScanCallback"></a>

## startWifiPeriodicallyScanCallback : <code>function</code>
A callback function to be called when scan is started

**Kind**: global typedef  
<a name="stopWifiPeriodicallyScanCallback"></a>

## stopWifiPeriodicallyScanCallback : <code>function</code>
A callback function to be called when scan is stopped

**Kind**: global typedef  
<a name="getNetConfigCallback"></a>

## getNetConfigCallback : <code>function</code>
A callback function to be called when scan is started

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>Object</code> | An JSON object with the following information:  {      deviceIPAddress,      deviceMacAddress,      gatewayIPAddress,      gatewayMacAddress  } |

<a name="getConnectionInfoCallback"></a>

## getConnectionInfoCallback : <code>function</code>
Callback which provides the connection information.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>Object</code> | An JSON object with the following information: {      SSID,      linkSpeed,      IPAddress } |

<a name="pingHostCallback"></a>

## pingHostCallback : <code>function</code>
Callback which provides the ping information.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>Object</code> | An JSON object with the following information: {      requestTimeout,      stat: {          requestTimeout,          time,          min,          max,          stddev      } } |

<a name="getMacAddressOfHostCallback"></a>

## getMacAddressOfHostCallback : <code>function</code>
A callback function to be called with all information

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| info | <code>String</code> | the MAC address |

<a name="isDnsLiveCallback"></a>

## isDnsLiveCallback : <code>function</code>
A callback function to be called with DNS details

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| isDnsLive, | <code>boolean</code> | true  if device is DNS is live, otherwise false |

<a name="isPortLiveCallback"></a>

## isPortLiveCallback : <code>function</code>
A callback function to be called with DNS details

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| isPortLive, | <code>boolean</code> | true  if device is port is available, otherwise false |

<a name="isRootedCallback"></a>

## isRootedCallback : <code>function</code>
A callback function to be called with Rooted information

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| isRooted, | <code>boolean</code> | true  if device is port is available, otherwise false |

