'use strict';
angular.module('sevenWonder')
    .config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            $routeProvider
                .when('/login', {
                    template: '<login></login>'
                })
                .when('/lobby', {
                    template: '<lobby></lobby>',
                    resolve:{
                            "check":function(Auth, $location){
                                if(Auth.getLoggedUser().userName == null){
                                    $location.path('/login');
                                    alert("You must't login first");
                                }
                            }
                        }
                })
                .when('/gameroom', {
                    template: '<gameroom></gameroom>'
//                    ,
//                    resolve:{
//                            "check":function($location){
//                                if(){
//                                }else{
//                                    $location.path('/login');
//                                    alert("You must't login first");
//                                }
//                            }
//                        }
                })
                .when('/choosewonder', {
                    template: '<choosewonder></choosewonder>'
                })

                .otherwise('/login');
        }
    ])
    .config(['RestangularProvider', function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://localhost:9999');
    }]);