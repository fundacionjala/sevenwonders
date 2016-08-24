'use strict';

angular.
    module('sevenWonder.gameroom').
    component('gameroom', {
        templateUrl: 'gameroom/gameroom.tpl.html',
        controller: ['Game',
            function GameRoomController(Game) {
                var self = this;
                // Game.getAvailableGames().$promise.then(function (result) {
                //     self.games = result.games;
                // });
                this.gameroom = {
                    'id': 7,
                    'name': 'Stalingrado',
                    'numberPlayers': 7,
                    'players': [{
                        'id': 1,
                        'username': 'Diego',
                        'avatar': 'user'
                    }, {
                            'id': 2,
                            'username': 'JOhx',
                            'avatar': 'user'
                        }, {
                            'id': 3,
                            'username': 'Gumu',
                            'avatar': 'user'
                        }, {
                            'id': 4,
                            'username': '',
                            'avatar': ''
                        }, {
                            'id': 5,
                            'username': '',
                            'avatar': ''
                        }, {
                            'id': 6,
                            'username': '',
                            'avatar': ''
                        }, {
                            'id': 7,
                            'username': '',
                            'avatar': ''
                        }]
                };
                this.maxPlayers = 7;
                this.createGame = function () {

                };
                this.joinGame = function () {

                };
            }
        ]
    });
