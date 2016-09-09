'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', '$websocket', 'Game', 'Restangular',  '$q',
        function ($cookies, $websocket, Game, Restangular, $q) {
            return {
                getGameRoom: function () {
                    return Game.getCurrentGame();
                },
                getPlayers: function () {
                    var defer = $q.defer();
                    Restangular.one('games', Game.getCurrentGame().id).getList('players')
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                connectWebsocket: function (game) {
                    var dataStream = $websocket('ws://localhost:9295/game');
                    dataStream.onMessage(function (message) {
                        game.addPlayer(JSON.parse(message.data));
                        console.log('joined');
                    });
                }
            }
        }
    ]);