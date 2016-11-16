'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['$cookies', '$websocket', 'Restangular', '$q',
        function ($cookies, $websocket, Restangular, $q) {
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
                 }
               }
        }
    ]);