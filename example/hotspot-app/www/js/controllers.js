angular.module('starter.controllers', [])
    .controller('HotSpotCtrl', function ($scope, $ionicLoading, $timeout) {

        var init = function () {
            $ionicLoading.show();
            // default to WPA2PSK
            $scope.config = {
              mode: 'WPA2PSK'
            };
            cordova.plugins.hotspot.isHotspotEnabled(
                function () {
                    $ionicLoading.hide();
                    $scope.isHotSpotActive = true;
                }, function () {
                    $ionicLoading.hide();
                    $scope.isHotSpotActive = false;
                }
            );
        };

        // init controllers
        init();

        // API
        $scope.start = function () {
          $ionicLoading.show();
            cordova.plugins.hotspot.createHotspot(
                $scope.config.ssid, $scope.config.mode, $scope.config.password,
                function () {
                  // delay UI refresh
                  $timeout(function () {
                    $ionicLoading.hide();
                    init();
                  }, 500);
                }, function () {
                    $ionicLoading.hide();
                    alert('Hotspot creation failed');
                }
            );
        };

        $scope.stop = function () {
          $ionicLoading.show();
          cordova.plugins.hotspot.stopHotspot(
              function () {
                  $ionicLoading.hide();
                  $scope.isHotSpotActive = false;
                  init();
              }, function () {
                  alert('AP disabling failed');
              }
          );
        };
    })
    .controller('DevicesCtrl', function ($scope, $ionicLoading) {
        var init = function () {
            $ionicLoading.show();
            cordova.plugins.hotspot.isHotspotEnabled(
                function () {
                    cordova.plugins.hotspot.getAllHotspotDevices(
                        function (response) {
                            $ionicLoading.hide();
                            $scope.devices = response;
                        }, function () {
                            $ionicLoading.hide();
                            alert('Device listing failed.');
                        }
                    );
                }, function () {
                    $ionicLoading.hide();
                    $scope.isHotSpotActive = false;
                }
            );
        };

        // init controllers
        init();

        // API
        $scope.refresh = function () {
          init()
        };
    });
