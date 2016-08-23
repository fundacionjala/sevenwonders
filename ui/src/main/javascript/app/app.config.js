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
                        .otherwise('/login');
            }
        ])
        .config(['RestangularProvider', function (RestangularProvider) {
                RestangularProvider.setBaseUrl('http://localhost:9999/gameRoom');
                RestangularProvider.setDefaultHeaders({
                    'Content-Type': 'application/json',
                    'X-Requested-With': 'XMLHttpRequest'
                });
            }]);