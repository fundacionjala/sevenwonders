'use strict';

angular.
    module('sevenWonders.core.game').
    factory('Game', ['$cookies', 'Restangular', 'Auth', '$q', 'GameModel', 'UserModel',
        function ($cookies, Restangular, Auth, $q, GameModel, UserModel) {
            var Game = Restangular.service('games');

            var storeGame = function (data) {
                var gameModel = new GameModel(data);
                $cookies.putObject('game', gameModel);
            };

            return {
                getAvailableGames: function () {
                    return Game.getList();
                },
                create: function (gameSetting) {
                    return $q(function (resolve, reject) {
                        var player = new UserModel(Auth.getLoggedUser());
                        Restangular.all('games').post({
                            maxPlayers: gameSetting.players,
                            roomName: gameSetting.name,
                            owner: player.getRequestModel()
                            })
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