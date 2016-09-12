'use strict';

angular.
    module('sevenWonders.core.choosewonder').
    factory('ChooseWonder', ['$cookies', '$websocket', 'Game', 'Restangular',  '$q', '$location',
        function ($cookies, $websocket, Game, Restangular, $q, $location) {
            return {
                    connectWSWonder: function (game) {
                          var dataStream = $websocket('ws://localhost:9298/choosewonder');
                          dataStream.onOpen(function () {

                          });

                          dataStream.onMessage(function (message) {

                          });
                    }
        }
        }
    ]);