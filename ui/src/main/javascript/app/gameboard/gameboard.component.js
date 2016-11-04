'use strict';

angular.
    module('sevenWonder.gameboard').
    component('gameboard', {
        templateUrl: 'gameboard/gameboard.tpl.html',
        controller: ['GameBoard',
            function GameBoardController(GameBoard){
                var self = this;
                self.storage = [];
                self.victoryPoint = [];
                self.resources = [];
                GameBoard.getStorage().then(function (result) {
                                        result.forEach(function (element) {
                                            if(element.name === "victoryPoint") {
                                                self.victoryPoint.push(element);
                                            } else {
                                                self.storage.push(element);
                                            }
                                              }, this);
                                      });
            }
        ]
    });
