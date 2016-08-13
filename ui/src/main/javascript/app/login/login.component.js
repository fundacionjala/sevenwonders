'use strict';

angular.
module('sevenWonder.login').
component('login', {
    templateUrl: 'login/login.tpl.html',
    controller: ['User', '$location', function LoginController(User, $location) {
        this.doLogin = function() {
            console.log('click!!');
            User.login().then(function(data) {
                console.log('Success');
                console.log(data);
                $location.path('/lobby');
            }).catch(
                function(data) {
                    console.log('Fail');
                    console.log(data);
                });
        }
    }]
});