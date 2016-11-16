'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard',
            function GameBoardController(GameBoard){
                var self = this;
                self.storage = [];
                               
                GameBoard.getStorage().then(function (result) {
                    result.forEach(function (element) {
                        self.storage.push(element);
                    }, this);
                });

                this.calculatePosition = function (cards){
                    var maxCards = 7;
                    var cardsFinal = [];
                    var isPair = cards.length % 2 == 0 
                    var adjustment = isPair ? cards.length + 1 : cards.length;
                    var pos = Math.abs(Math.floor(-(maxCards/adjustment)));

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
                })
            }
        ]
    });
