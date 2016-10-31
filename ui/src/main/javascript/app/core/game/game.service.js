'use strict';

angular.
    module('sevenWonders.core.game').
    factory('Game', ['$cookies', 'Restangular', 'Auth', '$q',
        function ($cookies, Restangular, Auth, $q) {
            var Game = Restangular.service('games');

            var storeGame = function (data) {
                var gameModel = {
                    id: data.id,
                    roomName: data.roomName,
                    channel: data.channel,
                    type: data.type,
                    numberPlayers: data.maxPlayers,
                    player: data.owner
                };
                $cookies.putObject('game', gameModel);
            };

            return {
                getAvailableGames: function () {
                    return Game.getList();
                },
                create: function (gameSetting) {
                    return $q(function (resolve, reject) {
                        Restangular.all('games').post(
                            {
                                maxPlayers: gameSetting.players,
                                roomName: gameSetting.name,
                                owner: {
                                    id: $cookies.getObject('user').id,
                                    userName: $cookies.getObject('user').userName,
                                    token: $cookies.getObject('user').token
                                }
                            }
                        )
                            .then(function (data) {
                                storeGame(data);
                                resolve(data);
                            })
                            .catch(function (data) {
                                reject(data);
                            });
                    });
                },
                join: function (game) {
                    var user = Auth.getLoggedUser();
                    var defer = $q.defer();
                    var player = {
                        id: user.id,
                        userName: user.userName,
                        token: user.token
                    }
                    Game.one(game.id).post('players', player)
                        .then(function (data) {
                            var playerTemp = {
                                id: data.id,
                                userName: data.userName,
                                token: data.token
                            }
                            game.owner = playerTemp;
                            storeGame(game);
                            defer.resolve();
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;

                },
                getCurrentGame: function () {
                    return $cookies.getObject('game');
                },

                getUserCurrent: function () {
                    return Auth.getLoggedUser();
                }
            };
        }
    ]);