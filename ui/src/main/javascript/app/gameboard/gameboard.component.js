'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard', 'Auth',
            function GameBoardController(GameBoard, Auth){
                var self = this;
                var currentUser = Auth.getLoggedUser();
                self.resources = [];
                self.storage = [];
                self.players = [];
                self.nearestNeighbors = [];
                self.wonder = {};
                self.cardsPlayed = {};

                GameBoard.getStorage().then(function (result){
                    result.forEach(function (element) {
                        self.storage.push(element);
                    }, this);
                });

                this.calculatePosition = function (cards){
                    var maxCards = 7;
                    var cardsFinal = [];
                    var isPair = cards.length % 2 == 0;
                    var adjustment = isPair ? cards.length + 1 : cards.length;
                    var pos = Math.ceil(maxCards/adjustment);

                    for(var i = 0; i< cards.length; i++){
                        var cardWithPos = {
                            card : cards[i],
                            position : pos
                        }
                        cardsFinal.push(cardWithPos);
                        pos = isPair && pos == 3 ? pos + 2 : pos + 1;
                    }
                    return cardsFinal;
                }

                GameBoard.getPlayerCards().then(function(result){
                    self.cards = self.calculatePosition(result);
                });

                GameBoard.getGamePlayers().then(function (result){
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
                            console.log(i);
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
                     requirements.forEach(function(element) {
                        result += element.quantity;
                     }, this);
                     return result;
                }

               GameBoard.getCardsPlayed().then(function (result) {
                      self.cardsPlayed = result;
               });
           }
        ]
    });
