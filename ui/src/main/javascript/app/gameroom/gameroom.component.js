'use strict';

angular.
module('sevenWonder.gameroom').
component('gameroom', {
    templateUrl: 'gameroom/gameroom.tpl.html',
    controller: ['Game',
        function GameRoomController(Game) {
            var self = this;
            Game.getAvailableGames().$promise.then(function(result) {
                self.games = result.games;
            });
            this.maxPlayers = 7;
            this.createGame = function() {

            }
            this.joinGame = function() {

            }
        }
    ]
});