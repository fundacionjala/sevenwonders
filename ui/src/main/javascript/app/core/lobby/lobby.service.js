'use strict';

angular.
    module('sevenWonders.core.lobby').
    factory('Lobby', ['$cookies', '$websocket', 'Restangular', 'Auth', '$q', 'WsConfig',
        function ($cookies, $websocket, Restangular, Auth, $q, WsConfig) {
            var lobbySource;

            return {
                connectWs: function(lobby) {
                    if(lobby === undefined) throw 'Lobby is not defined';
                    lobbySource = lobby;
                    var dataStream = $websocket(WsConfig.lobbyUrl + 'lobby');
                    dataStream.onMessage(function (message) {
                        lobby.validateGame(JSON.parse(message.data));
                    });
                }
            };
        }
    ]);