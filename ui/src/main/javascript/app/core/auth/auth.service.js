'use strict';

angular.
module('sevenWonders.core.auth').
factory('Auth', ['Restangular', '$cookies', '$q', 'UserModel',
    function(Restangular, $cookies, $q, UserModel) {
        return {
            login: function(user) {
                return $q(function(resolve, reject) {
                    var userModel = {
                        userName : user
                    };
                    Restangular.all('login').post(userModel)
                        .then(function(data) {
                            var userModel = new UserModel(data);
                            $cookies.putObject('user', userModel);
                            Restangular.setDefaultHeaders({ Authorization: userModel.token });
                            resolve(data);
                        })
                        .catch(function(data) {
                            reject(data);
                        });
                });
            },
            getLoggedUser: function() {
                return $cookies.getObject('user');
            }
        };
    }
]);