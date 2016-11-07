'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['Auth', '$cookies', 'UserModel', '$websocket', 'Game', 'Restangular', '$q', '$location', '$timeout', 'WsConfig',
        function (Auth, $cookies, UserModel, $websocket, Game, Restangular, $q, $location, $timeout, WsConfig) {
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
                    var dataStream = $websocket(WsConfig.baseUrl + ':9295/game');
                    var gameRoom = Game.getCurrentGame(); 
                    var loggedPlayer = new UserModel(Auth.getLoggedUser());
                    dataStream.send(JSON.stringify(loggedPlayer.getAddPlayerModel(gameRoom.id)));
                    dataStream.onMessage(function (message) {
                        game.addPlayer(JSON.parse(message.data));
                        console.log('joined');
                    });
                },

                connectRoomWebsocket: function(game) {
                    var dataRoom = $websocket(WsConfig.baseUrl + ':9298/choosewonder');
                    dataRoom.onOpen(function () {
                        console.log('open connection at choosewonder');
                    });

                   dataRoom.onMessage(function (message) {
                       var room = JSON.parse(message.data);
                           $timeout(function() {
                                $location.path('/choosewonder');
                                console.log('room is complete');
                           }, 2000);
                   });
                }
            }
        }
    ]);