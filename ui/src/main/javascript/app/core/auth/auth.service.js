'use strict';

angular.
    module('sevenWonders.core.auth').
    factory('Auth', ['Restangular', '$cookies', '$q',
        function (Restangular, $cookies, $q) {
            return {
                login: function (user) {
                    return $q(function (resolve, reject) {
                        Restangular.all('login').post({ userName: user })
                            .then(function (data) {
                                var userModel = {
                                    id: data.id,
                                    userName: data.userName,
                                    isLoggedIn: true,
                                    token: data.token
                                };
                                $cookies.putObject('user', userModel);
                                resolve(data);
                            })
                            .catch(function (data) {
                                reject(data);
                            });
                    });
                },
                getLoggedUser: function () {
                    return $cookies.getObject('user');
                }
            };
        }
    ]);