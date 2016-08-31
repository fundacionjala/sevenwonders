'use strict';

angular.
    module('sevenWonder.gameroom').
    component('gameroom', {
        templateUrl: 'gameroom/gameroom.tpl.html',
        controller: ['GameRoom',
            function GameRoomController(GameRoom) {
                var self = this;
                // Game.getAvailableGames().$promise.then(function (result) {
                //     self.games = result.games;
                // });
                this.gameroom = GameRoom.getGame();
                this.maxPlayers = 7;
                this.createGame = function () {

                };
                this.joinGame = function () {

                };
            }
        ]
    });
