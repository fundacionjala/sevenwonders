'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard', 'Auth',
            function GameBoardController(GameBoard, Auth) {
                var self = this;
                var currentUser = Auth.getLoggedUser();
                self.resources = [];
                self.storage = [];
                self.nearestNeighbors = [];
                self.wonder = {};

                GameBoard.getStorage().then(function (result) {
                    result.forEach(function (element) {
                        self.storage.push(element);
                    }, this);
                });

                GameBoard.getGamePlayers().then(function (result) {
                    result.forEach(function (element) {
                        self.players.push(element);
                    }, this);
                    self.nearestNeighbors = getNearestNeighbors();
                });

                GameBoard.getWonder().then(function (result) {
                     self.wonder = result;
                });

                var getNearestNeighbors = function () {
                    var neighborLeft, neighborRight;
                    var indexOfUser = parseInt(locationOfUser());
                    if ((indexOfUser < self.players.length - 1) && (indexOfUser > 0)) {
                        neighborLeft = self.players[indexOfUser - 1];
                        neighborRight = self.players[indexOfUser + 1];
                    } else if (indexOfUser == 0) {
                        neighborLeft = self.players[self.players.length - 1];
                        neighborRight = self.players[1];
                    } else if (indexOfUser == self.players.length - 1) {
                        neighborLeft = self.players[indexOfUser - 1];
                        neighborRight = self.players[0];
                    }
                    return { left: neighborLeft, right: neighborRight };
                };
                var locationOfUser = function () {
                    for (var i in self.players) {
                        if (self.players[i].id == currentUser.id) {
                            return i;
                        }
                    }
                    return -1;
                };

                self.getRequirements = function(requirement){
                     return new Array(requirement.quantity);
                };

                self.CountQuantityOfRequirements = function(requirements){
                var result = 0;
                    requirements.forEach(function(element){
                        result += element.quantity;
                    }, this);
                    console.log(result);
                    return result;
                }
            }
        ]
    });
