'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard', 'Auth',
            function GameBoardController(GameBoard, Auth) {
                var self = this;
                self.resources = [];
                self.storage = [];
                self.gamePlayers = [];
                var currentUser = Auth.getLoggedUser();
                self.players = GameBoard.getGamePlayers();
                // GameBoard.getGamePlayers().then(function (result) {
                //     self.gamePlayers = result;
                //     result.forEach(function (element) {
                //         self.players.push(element);
                //     }, this);
                // });
                GameBoard.getStorage().then(function (result) {
                    self.resources = result;
                    result.forEach(function (element) {
                        self.storage.push(element);
                    }, this);
                });
                self.nearestNeighbors = self.getNearestNeighbors;
                this.getNearestNeighbors = function () {
                    var left1, right1;
                    var locationOfUser = self.locationOfUser1;
                    if ((locationOfUser < self.players.length - 1) && (locationOfUser > 1)) {
                        left1 = self.players[locationOfUser - 1];
                        right1 = self.players[locationOfUser + 1];
                    } else if (locationOfUser == 0) {
                        left1 = self.players[self.players.length - 1];
                        right1 = self.players[locationOfUser + 1];
                    } else if (locationOfUser == self.players.length - 1) {
                        left1 = self.players[locationOfUser - 1];
                        right1 = self.players[0];
                    }
                    var neighbors = { left: left1, right: right1 }; 
                    return neighbors; 
                }
                this.locationOfUser1 = function () {
                    for (var i in self.players) {
                        if (self.players[i].userName == currentUser.userName) {
                            return i;
                        }
                    }
                    return 0;
                }
            }
        ]
    });
