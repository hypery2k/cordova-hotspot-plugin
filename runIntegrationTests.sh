#!/bin/sh
cwd=$(pwd)
cd ..  
rm -rf cordova-app-hello-world && git clone https://github.com/apache/cordova-app-hello-world.git && cd cordova-app-hello-world && cordova platform add android@4
cordova plugin add ../cordova-hotspot-plugin/
cordova build
echo "Changing back to plugin directly: "$cwd
cd $cwd