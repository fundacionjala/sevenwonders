'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['Game', '$cookies', '$websocket', 'Restangular', '$q',
        function (Game, $cookies, $websocket, Restangular, $q) {
                return {
                  getStorage: function () {
                      var defer = $q.defer();
                      Restangular.allUrl('storage', 'http://demo5549833.mockable.io/storage').getList()
                                 .then(function (data) {
                                       defer.resolve(data);
                                 }).catch(function () {
                                       defer.reject();
                                 });
                         return defer.promise;
                 },
                 getPlayerCards: function() {
                     var principalGame = $cookies.getObject('PrincipalGame');
                     var loggedUser = $cookies.getObject('user');
                     var cards = [];
                     var defer = $q.defer();
                     Restangular.one('game', principalGame.id).getList('players')
                                .then(function (data) {
                                    var currentPlayer;
                                    data.forEach(function(data){
                                        if(data.userName == loggedUser.userName){
                                            currentPlayer = data;
                                            return;
                                        }
                                    });

                                    currentPlayer.deck.cards.forEach(function(data){
                                        cards.push(data.name);
                                    })

                                    defer.resolve(cards);

                                }).catch(function () {
                                    defer.reject();
                                });
                    return defer.promise;
                 },
                 getGamePlayers: function () {
                     var defer = $q.defer();
                     Restangular.allUrl('players', 'http://demo9730175.mockable.io/players').getList()
                         .then(function (data) {
                            defer.resolve(data);
                         }).catch(function () {
                            defer.reject();
                        });
                     return defer.promise;
                    },
                getWonder: function () {
                       var defer = $q.defer();
                       Restangular.oneUrl('wonder', ' http://demo5549833.mockable.io/player/1/game/1/wonder').get()
                           .then(function (data) {
                               defer.resolve(data);
                           }).catch(function () {
                               defer.reject();
                           });
                       return defer.promise;
                },
                getCardsPlayed: function (){
                       var defer = $q.defer();
                           Restangular.oneUrl('cards', '  http://demo5549833.mockable.io/player/1/game/1/cards').get()
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
