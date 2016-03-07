<a name="cordova.plugins.hotspot"></a>
## cordova.plugins.hotspot : <code>object</code>
**Kind**: global namespace  

* [cordova.plugins.hotspot](#cordova.plugins.hotspot) : <code>object</code>
    * [.isAvailable(callback)](#cordova.plugins.hotspot+isAvailable)
    * [.toggleWifi(callback, errorCB)](#cordova.plugins.hotspot+toggleWifi)
    * [.createHotspot(ssid, mode, password, successCB, errorCB)](#cordova.plugins.hotspot+createHotspot)
    * [.startHotspot(successCB, errorCB)](#cordova.plugins.hotspot+startHotspot)
    * [.configureHotspot(ssid, mode, password, successCB, errorCB)](#cordova.plugins.hotspot+configureHotspot)
    * [.stopHotspot(successCB, errorCB)](#cordova.plugins.hotspot+stopHotspot)
    * [.isHotspotEnabled(enabledCB, disabledCB)](#cordova.plugins.hotspot+isHotspotEnabled)
    * [.getAllHotspotDevices(successCB, errorCB)](#cordova.plugins.hotspot+getAllHotspotDevices)
    * [.connectToHotspot(ssid, password, successCB, errorCB)](#cordova.plugins.hotspot+connectToHotspot)
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
    * [.startPeriodicallyScan(interval, duration, successCB, errorCB)](#cordova.plugins.hotspot+startPeriodicallyScan)
    * [.stopPeriodicallyScan(successCB, errorCB)](#cordova.plugins.hotspot+stopPeriodicallyScan)
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
| callback | <code>function</code> | A callback function with true or false as response |

<a name="cordova.plugins.hotspot+toggleWifi"></a>
### cordova.plugins.hotspot.toggleWifi(callback, errorCB)
Method to Toggle wifi ON/OFF

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| callback | <code>function</code> | A callback function with true or false as response,true if wifi ON and false if wifi OFF |
| errorCB | <code>Object</code> | A callback function to be called when errors occurr |

<a name="cordova.plugins.hotspot+createHotspot"></a>
### cordova.plugins.hotspot.createHotspot(ssid, mode, password, successCB, errorCB)
Create a WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | wireless mode (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when start was successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+startHotspot"></a>
### cordova.plugins.hotspot.startHotspot(successCB, errorCB)
Start a default WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when start was successful |
| errorCB | <code>Object</code> | A callback function to be called when start was not successful |

<a name="cordova.plugins.hotspot+configureHotspot"></a>
### cordova.plugins.hotspot.configureHotspot(ssid, mode, password, successCB, errorCB)
Configure a running WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | wireless mode (Open, WEP, WPA, WPA_PSK, WPA2_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when configuration was successful |
| errorCB | <code>Object</code> | A callback function to be called when configuration was not successful |

<a name="cordova.plugins.hotspot+stopHotspot"></a>
### cordova.plugins.hotspot.stopHotspot(successCB, errorCB)
Stop a running default WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when stop was successful |
| errorCB | <code>Object</code> | A callback function to be called when stop was not successful |

<a name="cordova.plugins.hotspot+isHotspotEnabled"></a>
### cordova.plugins.hotspot.isHotspotEnabled(enabledCB, disabledCB)
Checks if hot spot is enabled

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Description |
| --- | --- |
| enabledCB | is called if hotspot is on |
| disabledCB | called if hotspot is off |

<a name="cordova.plugins.hotspot+getAllHotspotDevices"></a>
### cordova.plugins.hotspot.getAllHotspotDevices(successCB, errorCB)
Get all connected devices

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>ip</code> &#124; <code>mac</code> | A callback function to be called when connected successful and is called with an array of JSON objects: |
| errorCB |  | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+connectToHotspot"></a>
### cordova.plugins.hotspot.connectToHotspot(ssid, password, successCB, errorCB)
Connect to a WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when connected successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

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
| successCB | <code>function</code> | A callback function to be called when connected successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+configureHotspot"></a>
### cordova.plugins.hotspot.configureHotspot(ssid, mode, password, successCB, errorCB)
configure current WiFi Hotspot

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | mode use (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when connected successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+addWifiNetwork"></a>
### cordova.plugins.hotspot.addWifiNetwork(ssid, mode, password, successCB, errorCB)
add a WiFi network

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| mode | <code>string</code> | mode use (Open, WEP, WPA, WPA_PSK) |
| password | <code>string</code> | password to use |
| successCB | <code>function</code> | A callback function to be called when connected successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+removeWifiNetwork"></a>
### cordova.plugins.hotspot.removeWifiNetwork(ssid, successCB, errorCB)
Delete a WiFi network

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ssid | <code>string</code> | SSID to connect |
| successCB | <code>function</code> | A callback function to be called when connected successful |
| errorCB | <code>Object</code> | A callback function to be called when connection was not successful |

<a name="cordova.plugins.hotspot+isConnectedToInternet"></a>
### cordova.plugins.hotspot.isConnectedToInternet(successCB, errorCB)
Check if connection to internet is active

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when connection is active |
| errorCB | <code>Object</code> | A callback function to be called when not |

<a name="cordova.plugins.hotspot+isConnectedToInternetViaWifi"></a>
### cordova.plugins.hotspot.isConnectedToInternetViaWifi(successCB, errorCB)
Check if connection to internet is active via WiFi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when connection is done via wifi |
| errorCB | <code>Object</code> | A callback function to be called when not |

<a name="cordova.plugins.hotspot+isWifiOn"></a>
### cordova.plugins.hotspot.isWifiOn(successCB, errorCB)
Check if WiFi is enabled

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when WiFi is enabled |
| errorCB | <code>Object</code> | A callback function to be called when WiFi is disabled |

<a name="cordova.plugins.hotspot+isWifiSupported"></a>
### cordova.plugins.hotspot.isWifiSupported(successCB, errorCB)
Check if WiFi is supported

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when WiFi is supported |
| errorCB | <code>Object</code> | A callback function to be called when WiFi is not supported |

<a name="cordova.plugins.hotspot+isWifiDirectSupported"></a>
### cordova.plugins.hotspot.isWifiDirectSupported(successCB, errorCB)
Check if WiFi Direct is supported

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when WiFi Direct is supported |
| errorCB | <code>Object</code> | A callback function to be called when WiFi Direct is not supported |

<a name="cordova.plugins.hotspot+scanWifi"></a>
### cordova.plugins.hotspot.scanWifi(successCB, errorCB)
Scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when scan is done |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+scanWifiByLevel"></a>
### cordova.plugins.hotspot.scanWifiByLevel(successCB, errorCB)
Scan wifi by level

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when scan is done |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+startPeriodicallyScan"></a>
### cordova.plugins.hotspot.startPeriodicallyScan(interval, duration, successCB, errorCB)
Start a perodically scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| interval | <code>long</code> | interval to use |
| duration | <code>long</code> | duration to use |
| successCB | <code>function</code> | A callback function to be called when scan is started |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+stopPeriodicallyScan"></a>
### cordova.plugins.hotspot.stopPeriodicallyScan(successCB, errorCB)
Stop a perodically scan wifi

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called when scan is stopped |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+getNetConfig"></a>
### cordova.plugins.hotspot.getNetConfig(successCB, errorCB)
Get network information, e.g Gateway Ip/Mac Device Mac/Ip etc

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+getConnectionInfo"></a>
### cordova.plugins.hotspot.getConnectionInfo(successCB, errorCB)
Get current connection information, e.g SSID, linkSpeed,IPAddress etc

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+pingHost"></a>
### cordova.plugins.hotspot.pingHost(ip, successCB, errorCB)
Ping a host

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+getMacAddressOfHost"></a>
### cordova.plugins.hotspot.getMacAddressOfHost(ip, successCB, errorCB)
Get MAC address of host

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+isDnsLive"></a>
### cordova.plugins.hotspot.isDnsLive(ip, successCB, errorCB)
dnsLive

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+isPortLive"></a>
### cordova.plugins.hotspot.isPortLive(ip, successCB, errorCB)
portLive

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| ip | <code>string</code> | host IP |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

<a name="cordova.plugins.hotspot+isRooted"></a>
### cordova.plugins.hotspot.isRooted(successCB, errorCB)
Check Device is Rooted

**Kind**: instance method of <code>[cordova.plugins.hotspot](#cordova.plugins.hotspot)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCB | <code>function</code> | A callback function to be called with all information |
| errorCB | <code>Object</code> | An error callback |

