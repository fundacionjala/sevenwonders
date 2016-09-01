'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', '$websocket', 'Game', 'Auth',
        function ($cookies, $websocket, Game, Auth) {
            
            var gameroom = Game.getCurrentGame();
            var user = Auth.getLoggedUser();

            var dataStream = $websocket('ws://localhost:9291/gameChannel');
            var playersJoined = [];

            var dataGame = {
                id: gameroom.id,
                player: gameroom.player
            };

            dataStream.onOpen(function () {
                console.log('connection open');
                dataStream.send(JSON.stringify(dataGame));
            });

            dataStream.onMessage(function (data) {
                playersJoined.push(data);
                console.log('joined');
            });

            dataStream.onClose(function (event) {
                console.log('connection closed', event);
            });

            var game = {
                id: gameroom.id,
                name: gameroom.name,
                numberPlayers: gameroom.numberPlayers,
                players: playersJoined
            };
            return {
                getGame: function () {
                    return game;
                }
            };
        }
    ]);