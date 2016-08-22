'use strict';

angular.
module('sevenWonders.core.user').
factory('User', ['Restangular',
    function(Restangular) {
        var Login = Restangular.service('user');
        return {
            login: function(user) {

                return Login.save().$promise;
            }
        };
    }
]);