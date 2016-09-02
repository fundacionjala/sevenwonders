'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', 'Restangular', '$q', 'Game',
        function ($cookies, Restangular, $q, Game) {
            return {
                getGameroom: function () {
                    return Game.getCurrentGame();
                },
                getPlayers: function () {
                    var defer = $q.defer();
                    Restangular.one('games', Game.getCurrentGame().id).getList('players')
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                }
            }
        }
    ]);