'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder', '$cookies','Auth',
            function ChooseWonderController(ChooseWonder, $cookies, Auth) {
                var self = this;
                self.owner = $cookies.getObject("user");
                self.owner = $cookies.getObject("user");
                self.size = 0;
                self.players = [];
                self.wonderPlayers = [];
                self.buttonDisable = true;
                self.states = new Map();
                self.playerId = Auth.getLoggedUser().id;
                ChooseWonder.getWonderPlayers().then(function (result) {
                    self.wonderPlayers = result;
                    result.forEach(function (element) {
                        self.players.push(element);
                        self.states.set(element.id, 'unselected');
                    }, this);
                    self.wonders = self.wonderPlayers;
                    self.size = self.wonders.length;
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
                this.clickPlayer = function (data) {
                    var middleNumber = (Math.round(self.wonders.length / 2) - 1);
                    while (self.wonders[middleNumber].id !== data.id) {
                        self.rotate('next');
                    }
                    cleanStates();
                    self.states.set(data.id, 'selected');
                };
                this.checkPlayer = function (data) {
                    if (data.id == self.playerId) {
                        return 'current';
                    }
                    return 'no-current';
                };
                this.getState = function (data) {
                    return self.states.get(data.id);
                };
                var cleanStates = function () {
                    self.states.forEach(function (state, playerId) {
                        self.states.set(playerId, 'unselected');
                    });
                };



                this.isOwner = function(){
                    self.buttonDisable = self.owner.userName !== self.wonders[1].userName
                }

                this.sendSelection = function (){
                    ChooseWonder.setWonderPlayer(self.wonders[1]).then(function (result){
                        self.buttonDisable = true;
                    });
                }

                this.change = function(data){
                    if ( data.wonderModel.currentSide == 'b') {
                        data.wonderModel.currentSide = 'a'
                    } else {
                        data.wonderModel.currentSide = 'b'
                    }
                }

                this.isOwner = function(){
                        self.buttonDisable = self.owner.userName !== self.wonders[1].userName
                }

                this.sendSelection = function (){

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
