'use strict';

angular.
    module('sevenWonders.core.choosewonder').
    factory('ChooseWonder', ['$cookies', 'Game', 'Restangular', '$q',
        function ($cookies, Game, Restangular, $q) {
            return {
                setWonderPlayer: function (player) {
                    return $q(function (resolve, reject) {
                        Restangular.all('games/' + Game.getCurrentGame().id + '/player').customPUT(player)
                            .then(function (data) {
                                resolve(data);
                            }).catch(function (data) {
                                reject(data);
                            });
                    });
                },

                getWonderPlayers: function () {
                    return $q(function (resolve, reject) {
                        Restangular.one('games', Game.getCurrentGame().id).getList('players')
                            .then(function (data) {
                                $cookies.putObject('wonder', data);
                                resolve(data);
                            }).catch(function (data) {
                                reject(data);
                            });
                    });
                }
            }
        }
    ]);
