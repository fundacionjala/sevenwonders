'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder',
            function ChooseWonderController(ChooseWonder) {
                var self = this;
                self.wonderPlayers = [];
                ChooseWonder.getWonderPlayers().then(function (result) {
                    self.wonderPlayers = result;
                    self.wonders = self.wonderPlayers;
                });
                this.rotate = function (data) {
                    if (data == 'next') {
                        self.wonderPlayers.slice(0, 3);
                        self.wonders.push(self.wonderPlayers.shift());
                    } else if (data == 'previous') {
                        self.wonderPlayers.slice(0, 3);
                        self.wonders.unshift(self.wonderPlayers.pop());
                    }
                };
            }
        ]
    });
