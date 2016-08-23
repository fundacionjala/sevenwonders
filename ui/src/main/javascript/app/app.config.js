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
                .otherwise('/login');
        }
    ])
    .config(['RestangularProvider', function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://demo1701066.mockable.io');
    }]);