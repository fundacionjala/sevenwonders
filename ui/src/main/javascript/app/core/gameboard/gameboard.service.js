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
                    var players = [
                        {
                            id: 1,
                            userName: 'JOhx',
                            token: '12312-dfgdfgd',
                            city: {
                                resources: [
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
                                ],
                                effects: [
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
                                ]
                            }
                        },
                        {
                            id: 2,
                            userName: 'Diego',
                            token: '123-dgfg',
                            city: {
                                resources: [
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
                                ],
                                effects: [
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
                                ]
                            }
                        },
                        {
                            id: 3,
                            userName: 'Juan',
                            token: '123546-dgfhgfg',
                            city: {
                                resources: [
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
                                ],
                                effects: [
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
                                ]
                            }
                        }
                    ];
                    return players;
                }
                }
        }
    ]);
