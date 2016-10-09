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
    }]);