'use strict';
angular.module('sevenWonder')
    .config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            $routeProvider
                .when('/login', {
                    template: '<login></login>'
                })
                .when('/lobby', {
                    template: '<lobby></lobby>'
                })
                .when('/gameroom', {
                    template: '<gameroom></gameroom>'
                })
                .when('/choosewonder', {
                    template: '<choosewonder></choosewonder>'
                })
                .when('/gameboard', {
                    template: '<gameboard></gameboard>'
                })

                .otherwise('/login');
        }
    ])
    .run(function (Auth, $rootScope, $location) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (Auth.getLoggedUser() == null) {
                $location.path("/login");
            }
        })
    })
    .config(['RestangularProvider', function (RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:9999');
    }])
    .constant('WsConfig', {
        baseUrl: 'ws://localhost/',
        gameUrl: 'ws://localhost:9295/',
        chooseWonderUrl: 'ws://localhost:9298/',
        lobbyUrl:'ws://localhost:9291/',
        gameReady: 'ws://localhost:9090/'
    });