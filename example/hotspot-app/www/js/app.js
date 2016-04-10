// Ionic Starter App

// bootstrap manual

function onDeviceReady() {
  // bootstrap app:
  angular.bootstrap(document, ['starter']);
}

if (window.cordova) {
    document.addEventListener('deviceready', onDeviceReady, false);
} else {
  onDeviceReady();
}

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers'])

    .run(function ($ionicPlatform) {
        $ionicPlatform.ready(function () {
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
                cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
                cordova.plugins.Keyboard.disableScroll(true);

            }
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleDefault();
            }
        });
    })

    .constant('$ionicLoadingConfig', {
      template: '<ion-spinner></ion-spinner> <br> Loading '
    })

    .config(function ($stateProvider, $urlRouterProvider, $ionicConfigProvider) {

        // places them at the bottom for all OS
        $ionicConfigProvider.tabs.position('bottom');

        // Ionic uses AngularUI Router which uses the concept of states
        // Learn more here: https://github.com/angular-ui/ui-router
        // Set up the various states which the app can be in.
        // Each state's controller can be found in controllers.js
        $stateProvider

            // setup an abstract state for the tabs directive
            .state('tab', {
                url: '/tab',
                abstract: true,
                templateUrl: 'templates/tabs.html'
            })

            // Each tab has its own nav history stack:

            .state('tab.hotspot', {
                url: '/hotspot',
                views: {
                    'tab-hotspot': {
                        templateUrl: 'templates/tab-hotspot.html',
                        controller: 'HotSpotCtrl'
                    }
                }
            })

            .state('tab.devices', {
                url: '/devices',
                views: {
                    'tab-devices': {
                        templateUrl: 'templates/tab-devices.html',
                        controller: 'DevicesCtrl'
                    }
                }
            });

        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/tab/hotspot');

    });
