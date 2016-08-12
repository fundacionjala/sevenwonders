'use strict';

angular.
  module('sevenWonders.core.user').
  factory('User', ['$resource',
    function($resource) {
      var Login = $resource("http://demo1976057.mockable.io/login");
      //var Login = $resource("login");
      return {
        login: function(user) {
            return Login.save().$promise;
        }
      }
    }
  ]);
