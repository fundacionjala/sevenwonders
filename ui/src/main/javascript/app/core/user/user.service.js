'use strict';

angular.
module('sevenWonders.core.user').
factory('User', ['$resource',
    function($resource) {
        var Login = $resource("http://demo8039957.mockable.io/login");
        return {
            login: function(user) {
                return Login.save().$promise;
            }
        }
    }
]);