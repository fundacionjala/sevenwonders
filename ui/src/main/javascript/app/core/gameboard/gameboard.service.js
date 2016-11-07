'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['$cookies', '$websocket', 'Restangular', '$q',
        function ($cookies, $websocket, Restangular, $q) {
            return {
                getStorage: function () {
                    var defer = $q.defer();
                    Restangular.all('storage').getList()
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                getGamePlayers: function () {
                    var defer = $q.defer();
                    Restangular.all('players').getList()
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                getResources: function () {
                    var array = [
                        {
                            "name": "brick",
                            "count": 4
                        },
                        {
                            "name": "papyrus",
                            "count": 5
                        },
                        {
                            "name": "glass",
                            "count": 1
                        },
                        {
                            "name": "wood",
                            "count": 0
                        },
                        {
                            "name": "textile",
                            "count": 3
                        },
                        {
                            "name": "ore",
                            "count": 1
                        },
                        {
                            "name": "stone",
                            "count": 0
                        },
                        {
                            "name": "coin",
                            "count": 12
                        }
                    ];
                    return array;
                },
                getEffects: function () {
                    var array = [
                        {
                            "name": "victoryPoint",
                            "count": 17
                        },
                        {
                            "name": "nut",
                            "count": 1
                        },
                        {
                            "name": "tablet",
                            "count": 0
                        },
                        {
                            "name": "compass",
                            "count": 3
                        }
                    ];
                    return array;
                }
            }
        }
    ]);