# Cordova HotSpot Plugin


[![Build Status](https://travis-ci.org/hypery2k/cordova-hotspot-plugin.svg?branch=master)](https://travis-ci.org/hypery2k/cordova-hotspot-plugin) [![Build status](https://ci.appveyor.com/api/projects/status/d1g8ygx20or6htpg?svg=true)](https://ci.appveyor.com/project/hypery2k/cordova-hotspot-plugin) [![npm version](https://badge.fury.io/js/cordova-plugin-hotspot.svg)](http://badge.fury.io/js/cordova-plugin-hotspot) [![Dependency Status](https://david-dm.org/hypery2k/cordova-hotspot-plugin.svg)](https://david-dm.org/hypery2k/cordova-hotspot-plugin) [![devDependency Status](https://david-dm.org/hypery2k/cordova-hotspot-plugin/dev-status.svg)](https://david-dm.org/hypery2k/cordova-hotspot-plugin#info=devDependencies) 

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

### Check if Wifi is enabled
   
```javascript
cordova.plugins.hotspot.isWifiOn(
   function () {
       // wifi is on
   },function () {
       // wifi is off
   }
);
```
