'use strict';

angular.
    module('sevenWonders.core.lobby').
    factory('Lobby', ['$websocket',
        function ($websocket) {
            var lobbySource;
            return {
                connectWs: function (lobby) {
                    if (lobby == undefined) {
                        throw 'Lobby is not defined';
                    } else {
                        lobbySource = lobby;
                        var dataStream = $websocket('ws://localhost:9291/lobby');
                        dataStream.onMessage(function (message) {
                            lobby.validateGame(JSON.parse(message.data));
                        });
                    }
                }
            };
        }
    ]);
