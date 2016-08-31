'use strict';

angular.
    module('sevenWonders.core.game').
    factory('Game', ['$cookies', '$websocket', 'Restangular', 'Auth', '$q',
        function ($cookies, $websocket, Restangular, Auth, $q) {
            var Game = Restangular.service('games');
            var lobby;

            var storeGame = function (data) {
                var gameModel = {
                    id: data.id,
                    channel: data.channel,
                    players: data.players,
                    type: data.type
                };
                $cookies.putObject('game', gameModel);
            };

            return {
                getAvailableGames: function () {
                    return Game.getList();
                },
                connectWs: function(lobby) {
                    lobby = this.lobby;
                    var dataStream = $websocket('ws://localhost:9291/lobby');
                    dataStream.onMessage(function (message) {
                        lobby.addGame(message.data);
                    });
                },

                create: function (gameSetting) {
                    var user = Auth.getLoggedUser();
                    gameSetting.user = user.id;
                    Game.post(gameSetting)
                        .then(function (data) {
                            storeGame(data);
                        });
                },
                join: function (game) {
                    var user = Auth.getLoggedUser();
                    var defer = $q.defer();

                    var gameRest = Game.one(game.id);
                    gameRest.user = user.id;
                    gameRest.put()
                        .then(function (data) {
                            storeGame(data);
                            defer.resolve();
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;

                },
                getCurrentGame: function () {
                    return $cookies.getObject('game');
                }
            };
        }
    ]);