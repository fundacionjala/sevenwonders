'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard', 'Auth'
            function GameBoardController(GameBoard, Auth){
                var self = this;
                self.resources = [];
                self.storage = [];
                GameBoard.getStorage().then(function (result) {
                                        result.forEach(function (element) {
                                                self.storage.push(element);
                                              }, this);
                                      });
                self.gamePlayers = [];
                self.nearestNeighbors = [];
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
                    self.nearestNeighbors = getNearestNeighbors();
                });
                var getNearestNeighbors = function () {
                    var neighborLeft, neighborRight;
                    var indexOfUser = locationOfUser();
                    if ((indexOfUser < self.players.length - 1) && (indexOfUser > 1)) {
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
                        if (self.players[i].userName == currentUser.userName) {
                            console.log(i);
                            return i;
                        }
                    }
                    return 0;
                };
	    }
        ]
    });
