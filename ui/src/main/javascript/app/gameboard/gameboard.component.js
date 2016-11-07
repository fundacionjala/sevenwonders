'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard',
            function GameBoardController(GameBoard) {
                var self = this;
                self.resources = [];
                self.storage = [];
                self.resource = GameBoard.getResources();
                self.effects = GameBoard.getEffects();
                self.gamePlayers = [];

                GameBoard.getGamePlayers().then(function (result) {
                    self.gamePlayers = result;
                    result.forEach(function (element) {
                        self.players.push(element);
                    }, this);
                });
                GameBoard.getStorage().then(function (result) {
                    self.resources = result;
                    result.forEach(function (element) {
                        self.storage.push(element);
                    }, this);
                });
                this.getNearestNeighbors = function () {
                     return 0;
                }
            }
        ]
    });
