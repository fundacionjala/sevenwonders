'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['Restangular', '$cookies', '$q',
        function (Restangular, $cookies, $q) {
            return {
                gameroom: function (gameroom) {
                    return $q(function (resolve, reject) {
                        Restangular.all('gameroom').post({ gameroom: gameroom })
                            .then(function (data) {
                                var gameroomModel = {
                                    id: 1,
                                    name: "luis",
                                    wonder: "sample",
                                    Side: "A"
                                };
                            })
                            .catch(function (data) {
                                reject(data);
                            });
                    });
                },
                addPlayer: function (gameSetting) {
                    var user = Auth.getLoggedUser();
                    gameSetting.user = user.id;
                    Game.post(gameSetting)
                        .then(function (data) {
                            storeGame(data);
                        });
                }
            }
        }
    ]);
