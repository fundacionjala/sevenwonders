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
                    var player = new UserModel(Auth.getLoggedUser());
                    var gameModel = new GameModel(game);
                    var defer = $q.defer();
                    Game.one(game.id).post('players', player.getRequestModel())
                        .then(function (data) {
                            var playerAdded = new UserModel(data);
                            gameModel.addPlayer(playerAdded);
                            storeGame(gameModel);
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