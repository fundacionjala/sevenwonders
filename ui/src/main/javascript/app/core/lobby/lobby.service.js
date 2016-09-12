'use strict';

angular.
    module('sevenWonders.core.lobby').
    factory('Lobby', ['$cookies', '$websocket', 'Restangular', 'Auth', '$q',
        function ($cookies, $websocket, Restangular, Auth, $q) {
            var lobbySource;

            return {
                connectWs: function(lobby) {
                    lobbySource = lobby;
                    var dataStream = $websocket('ws://localhost:9291/lobby');
                    dataStream.onMessage(function (message) {
                        lobby.validateGame(JSON.parse(message.data));
                    });
                }
            };
        }
    ]);