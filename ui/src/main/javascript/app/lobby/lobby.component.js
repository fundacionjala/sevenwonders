'use strict';

angular.
    module('sevenWonder.lobby').
    component('lobby', {
        templateUrl: 'lobby/lobby.tpl.html',
        controller: ['Game', 'Lobby', '$location', '$cookies',
            function LobbyController(Game, Lobby, $location, $cookies) {
                var self = this;
                self.games = [];
                Game.getAvailableGames().then(function (result) {
                    self.games = result;
                });

                Lobby.connectWs(self);

                this.maxPlayers = ['3', '4', '5', '6', '7'];

                this.addGame = function (game) {
                    if(game == undefined) {
                        throw 'game is not defined.'
                    } else {
                        self.games.push(game);
                    }
                }

                this.createGame = function (game) {
                    if(game == undefined) {
                        throw 'game is not defined.'
                    } else {
                        var gameSettings = {
                            name: game.name,
                            players: game.player
                        };
                        Game.create(gameSettings)
                            .then(function (result) {
                                $location.path('/gameroom');
                            });
                    }
                };

                this.joinGame = function (game) {
                    if(game == undefined) {
                        throw 'game is undefined'
                    } else {
                        Game.join(game)
                            .then(function (result) {
                                 $location.path('/gameroom');
                             });
                    }
                };

                this.validateGame = function (game) {
                    if(game == undefined) {
                        throw 'game is undefined'
                    } else {
                        var indexGame = self.games.findIndex(function(b){
                            return b.roomName === game.roomName;
                         });
                        if (indexGame !== -1) {
                            self.games.splice(indexGame, 1);
                        } else {
                            self.games.push(game);
                        }
                    }
                }
            }
        ]
    });