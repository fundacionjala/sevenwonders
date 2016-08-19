'use strict';

angular.
module('sevenWonder.lobby').
component('lobby', {
    templateUrl: 'lobby/lobby.tpl.html',
    controller: ['Game', '$location', '$cookies',
        function LobbyController(Game, $location, $cookies) {
            var self = this;
            Game.getAvailableGames().then(function(result) {
                self.games = result;
            });
            this.maxPlayers = 7;

            this.createGame = function(game) {
                var gameSettings = {
                    name: game.name,
                    players: game.players
                };
                Game.create(gameSettings);
            }

            this.joinGame = function(game) {
                Game.join(game)
                    .then(function(result) {
                        console.log('join');
                        $location.path('/');
                    });
            }
        }
    ]
});