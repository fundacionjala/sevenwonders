'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder', '$cookies',
            function ChooseWonderController(ChooseWonder, $cookies) {
                var self = this;
                self.owner = $cookies.getObject("user");
                self.wonderPlayers = [];
                self.buttonDisable = true;

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
                    self.isOwner();
                };

                this.isOwner = function(){
                    self.buttonDisable = self.owner.userName !== self.wonders[1].userName
                }

                this.sendSelection = function (){
                    ChooseWonder.setWonderPlayer(self.wonders[1]).then(function (result){
                        buttonDisable = true;
                    });
                }
                
                this.change = function(data){
                    if ( data.wonderModel.currentSide == 'b') {
                        data.wonderModel.currentSide = 'a'
                    } else {
                        data.wonderModel.currentSide = 'b'
                    }
                }
            }
        ]
    });
