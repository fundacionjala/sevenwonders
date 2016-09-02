'use strict';

angular.
module('sevenWonders.core.auth').
factory('Auth', ['Restangular', '$cookies', '$q',
    function(Restangular, $cookies, $q) {
        return {
            login: function(user) {
                return $q(function(resolve, reject) {
                    var userModel = {
                        userName : user
                    };
                    Restangular.all('login').post(userModel)
                        .then(function(data) {
                            var userModel = {
                                id: data.id,
                                userName: data.userName,
                                isLoggedIn: true,
                                token: 'Bearer ' + data.token
                            };
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