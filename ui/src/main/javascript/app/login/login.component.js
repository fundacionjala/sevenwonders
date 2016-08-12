'use strict';

angular.
  module('sevenWonder.login').
  component('login', {
    templateUrl: 'login/login.tpl.html',
    controller: ['User',
      function LoginController(User) {
        User.login().then(function(data){
            console.log('Success');
            console.log(data);
        }).catch(
        function(data){
            console.log('Fail');
            console.log(data);
        });
      }
    ]
  });