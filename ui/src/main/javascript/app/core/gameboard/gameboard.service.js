'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['Game', '$cookies', '$websocket', 'Restangular', '$q',
        function (Game, $cookies, $websocket, Restangular, $q) {
            return {
                getStorage: function () {
                    var defer = $q.defer();
                    Restangular.allUrl('storage', 'http://demo5549833.mockable.io/storage').getList()
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                getGamePlayers: function () {
                    var defer = $q.defer();
                    Restangular.allUrl('players', 'http://demo9730175.mockable.io/players').getList()
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                getWonder: function () {
                       var defer = $q.defer();
                       Restangular.oneUrl('wonder', ' http://demo5549833.mockable.io/player/1/game/1/wonder').get()
                           .then(function (data) {
                               defer.resolve(data);
                           }).catch(function () {
                               defer.reject();
                           });
                       return defer.promise;
                }
            }
        }
    ]);
