# Cordova HotSpot Plugin


[![Build Status](https://travis-ci.org/hypery2k/cordova-hotspot-plugin.svg?branch=master)](https://travis-ci.org/hypery2k/cordova-hotspot-plugin) [![npm version](https://badge.fury.io/js/cordova-plugin-hotspot.svg)](http://badge.fury.io/js/cordova-plugin-hotspot) [![Dependency Status](https://david-dm.org/hypery2k/cordova-hotspot-plugin.svg)](https://david-dm.org/hypery2k/cordova-hotspot-plugin) [![devDependency Status](https://david-dm.org/hypery2k/cordova-hotspot-plugin/dev-status.svg)](https://david-dm.org/hypery2k/cordova-hotspot-plugin#info=devDependencies) 

> A Cordova plugin for managing HotSpot networks on Android with Cordova 3.4.0 / API Level 19
 
[![NPM](https://nodei.co/npm/cordova-plugin-hotspot.png)](https://nodei.co/npm/cordova-plugin-hotspot/)

## Installation

```bash
$ cordova plugin add cordova-plugin-hotspot
```
## Usage

### Check if Plugin is available

```javascript
cordova.plugins.hotspot.isAvailable(
    function (isAvailable) {
        // alert('Service is not available') unless isAvailable;
    }
);
```

### Check if device is connected to internet
   
```javascript
cordova.plugins.hotspot.isConnectedToInternet(
   function () {
       // is connected
   },function () {
       // is not connected
   }
);
```

### Hotspot functionality

#### Create Hotspot

```javascript
cordova.plugins.hotspot.createHotspot(ssid, mode, password, 
    function () {
        // Hotspot is created
    },function () {
        // Error
    }
);
```

#### Stop Hotspot

```javascript
cordova.plugins.hotspot.stopHotspot(
    function () {
        // Hotspot is disabled
    },function () {
        // Error
    }
);
```

#### Check if Hotspot is enabled

```javascript
cordova.plugins.hotspot.isHotspotEnabled(
    function () {
        // Hotspot is on
    },function () {
        // Hotspot is off
    }
);
```

#### Get all connected devices

```javascript
cordova.plugins.hotspot.getAllHotspotDevices(
    function (devices) {
        // array of JSON objects:
        // -> ip
        // -> mac
    },function (err) {
        // error
    }
);
```

### Wifi

### Check if Wifi is supported
   
```javascript
cordova.plugins.hotspot.isWifiSupported(
   function () {
       // wifi is on
   },function () {
       // wifi is off
   }
);
```

#### Check if Wifi is enabled
   
```javascript
cordova.plugins.hotspot.isWifiOn(
   function () {
       // wifi is on
   },function () {
       // wifi is off
   }
);
```

### Check if device is connected to internet via Wifi
   
```javascript
cordova.plugins.hotspot.isConnectedToInternetViaWifi(
   function () {
       // is connected
   },function () {
       // is not connected
   }
);
```

### Check if Wifi Direct is supported

```javascript
cordova.plugins.hotspot.isWifiDirectSupported(
    function () {
        // wifi is on
    },function () {
        // wifi is off
    }
);
```

#### Add a network config
     
  ```javascript
  cordova.plugins.hotspot.addWifiNetwork(ssid, mode, password,
     function () {
         // wifi is added
     },function (err) {
         // error
     }
  );
```

#### Delete a network config
     
  ```javascript
  cordova.plugins.hotspot.removeWifiNetwork(ssid, 
     function () {
         // wifi is deleted
     },function (err) {
         // error
     }
  );
```

#### Start a periodically scan
     
  ```javascript
  cordova.plugins.hotspot.startPeriodicallyScan(interval, duration,
     function () {
         // success
     },function (err) {
         // error
     }
  );
```

#### Scan network
     
  ```javascript
  cordova.plugins.hotspot.scanWifi( 
     function (listOfNetworks) {
         // array of results
     },function (err) {
         // error
     }
  );
```

#### Scan network by level
     
  ```javascript
  cordova.plugins.hotspot.scanWifiByLevel(
     function (listOfNetworks) {
         // array of results
     },function (err) {
         // error
     }
  );
```

#### Stop a periodically scan
     
  ```javascript
  cordova.plugins.hotspot.stopPeriodicallyScan( 
     function () {
         // success
     },function (err) {
         // error
     }
  );
```

