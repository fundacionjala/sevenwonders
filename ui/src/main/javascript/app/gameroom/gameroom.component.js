'use strict';

angular.
    module('sevenWonder.gameroom').
    component('gameroom', {
        templateUrl: 'gameroom/gameroom.tpl.html',
        controller: ['GameRoom',
            function GameRoomController(GameRoom) {
                var self = this;
                var tempGameRoom = GameRoom.getGameRoom();
                var playersJoined = [];
                GameRoom.connectWebsocket(self);
                this.addPlayer = function (player) {
                    playersJoined.push(player);
                };
                GameRoom.getPlayers().then(function (data) {
                    if (data.length < tempGameRoom.numberPlayers) {
                        for (var index = data.length; index < tempGameRoom.numberPlayers; index++) {
                            data.push({
                                userName: ''
                            });
                        }
                    }
                    self.gameroom = {
                        roomName: tempGameRoom.roomName,
                        players: data,
                        numberPlayers: tempGameRoom.numberPlayers
                    };
                });
                this.maxPlayers = 7;
            }
        ]
    });
