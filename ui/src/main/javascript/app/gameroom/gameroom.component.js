'use strict';

angular.
    module('sevenWonder.gameroom').
    component('gameroom', {
        templateUrl: 'gameroom/gameroom.tpl.html',
        controller: ['GameRoom',
            function GameRoomController(GameRoom) {
                var self = this;
                var tempGameRoom = GameRoom.getGameRoom();           
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
                GameRoom.connectWebsocket(self);
                this.addPlayer = function (player) {
                    if (typeof self.gameroom != 'undefined') {
                        if (!containsUserName(player.userName)) {
                            changeUserName('', player.userName);
                        }
                    }
                };
                var changeUserName = function (oldValue, newValue) {
                    for (var i in self.gameroom.players) {
                        if (self.gameroom.players[i].userName == oldValue) {
                            self.gameroom.players[i].userName = newValue;
                            break;
                        }
                    }
                };
                var containsUserName = function (username) {
                    var found = false;
                    for (var i = 0; i < self.gameroom.players.length; i++) {
                        if (self.gameroom.players[i].userName == username) {
                            found = true;
                            break;
                        }
                    }
                    return found;
                };
                this.maxPlayers = 7;
            }
        ]
    });
