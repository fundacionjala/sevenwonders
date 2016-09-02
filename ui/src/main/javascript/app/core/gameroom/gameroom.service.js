'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', '$websocket', 'Game',
        function ($cookies, $websocket, Game) {
            return {
                connectWebsocket: function (game) {
                    var dataStream = $websocket('ws://localhost:9295/game');
                    dataStream.onOpen(function () {
                        console.log('connection open');
                        var dataGame = {
                            id: Game.getCurrentGame().id,
                            player: Game.getCurrentGame().player
                        };
                        dataStream.send(JSON.stringify(dataGame));
                    });

                    dataStream.onMessage(function (message) {
                        game.addPlayer(JSON.parse(message.data));
                        console.log('joined');
                    });
                },
                getGameRoom: function () {
                    var game = {
                        id: Game.getCurrentGame().id,
                        name: Game.getCurrentGame().name,
                        numberPlayers: Game.getCurrentGame().numberPlayers
                    };
                    return game;
                }
            };
        }
    ]);