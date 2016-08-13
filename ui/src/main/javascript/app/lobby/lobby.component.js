'use strict';

angular.
module('sevenWonder.lobby').
component('lobby', {
    templateUrl: 'lobby/lobby.tpl.html',
    controller: ['Game',
        function LobbyController(Game) {
            this.games = Game.getAvailableGames();
            debugger;
            this.maxPlayers = 7;
            this.createRoomGame = function() {

            }
        }
    ]
});