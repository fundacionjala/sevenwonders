'use strict';

angular.
module('sevenWonder.lobby').
component('lobby', {
    templateUrl: 'lobby/lobby.tpl.html',
    controller: ['Game', 'Lobby', '$location', '$cookies',
        function LobbyController(Game, Lobby, $location, $cookies) {
            var self = this;
            self.games = [];
            Game.getAvailableGames().then(function(result) {
                self.games = result;
            });

            Lobby.connectWs(self); 

            this.maxPlayers = ['3', '4', '5', '6', '7'];

            this.addGame = function(game){
                self.games.push(game);
            }

            this.createGame = function(game) {
                var gameSettings = {
                    name: game.name,
                    players: game.player
                };
                Game.create(gameSettings);
                console.log('create');
                $location.path('/gameroom');
            };

            this.joinGame = function(game) {
                Game.join(game)
                    .then(function(result) {
                        console.log('join');
                        $location.path('/gameroom');
                    });
            };
        }
    ]
});