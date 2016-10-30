'use strict';

angular.
    module('sevenWonder.login').
    component('login', {
        templateUrl: 'login/login.tpl.html',
        controller: ['Auth', '$location', '$cookies', function LoginController(Auth, $location, $cookies) {
            this.doLogin = function (user) {
                if (user == undefined) {
                    throw 'User is not defined.';
                } else {
                    Auth.login(user)
                        .then(function (data) {
                            console.log('Success:' + data);
                            $location.path('/lobby');
                        })
                        .catch(function (data) {
                            console.log('Fail');
                        });
                }
            };
        }]
    });