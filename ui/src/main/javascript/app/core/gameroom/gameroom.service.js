'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', '$websocket', 'Game', '$q',
        function ($cookies, $websocket, Game, $q) {
            var dataStream = $websocket('wss://echo.websocket.org');

            var collection = [];

            dataStream.onMessage(function (message) {
                collection.push(JSON.parse(message.data));
            });

            var methods = {
                collection: collection,
                get: function () {
                    dataStream.send(JSON.stringify({ action: 'get' }));
                }
            };
             return {
                getGame: function () {
                    return methods;
                }
            };

            // var game = {
            //     'id': 7,
            //     'name': 'Stalingrado',
            //     'numberPlayers': 7,
            //     'players': [{
            //         'id': 1,
            //         'username': 'Diego',
            //         'avatar': 'user'
            //     }, {
            //             'id': 2,
            //             'username': 'JOhx',
            //             'avatar': 'user'
            //         }, {
            //             'id': 3,
            //             'username': 'Gumu',
            //             'avatar': 'user'
            //         }, {
            //             'id': 4,
            //             'username': '',
            //             'avatar': ''
            //         }, {
            //             'id': 5,
            //             'username': '',
            //             'avatar': ''
            //         }, {
            //             'id': 6,
            //             'username': '',
            //             'avatar': ''
            //         }, {
            //             'id': 7,
            //             'username': '',
            //             'avatar': ''
            //         }]
            // };


            // var game1 = Game.getCurrentGame();
            // return {
            //     getGame: function () {
            //         return game;
            //     }
            // };
        }
    ]);