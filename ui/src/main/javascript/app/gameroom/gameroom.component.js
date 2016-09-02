'use strict';

angular.
    module('sevenWonder.gameroom').
    component('gameroom', {
        templateUrl: 'gameroom/gameroom.tpl.html',
        controller: ['GameRoom',
            function GameRoomController(GameRoom) {
                var game = this;
                var playersJoined = [];
                
                GameRoom.connectWebsocket(game);

                this.addPlayer = function (player) {
                     playersJoined.push(player);
                };

                this.gameroom = {
                    id: GameRoom.getGameRoom().id,
                    name: GameRoom.getGameRoom().name,
                    numberPlayers: GameRoom.getGameRoom().numberPlayers,
                    players: playersJoined
                };
            }
        ]
    });
