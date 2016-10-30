'use strict';

angular.
    module('sevenWonders.core.choosewonder').
    factory('ChooseWonder', ['$cookies', '$websocket', 'Game', 'Restangular', '$q', '$location',
        function ($cookies, $websocket, Game, Restangular, $q, $location) {
                return {
                    setWonderPlayer : function(player){
                        return $q(function(resolve, reject){
                        Restangular.all('games/' + Game.getCurrentGame().id + '/player').customPUT(player)
                                   .then(function(data) {
                                         resolve(data);
                                   }).catch(function(data){
                                         reject(data);
                                   });
                       });
                    },

                    getWonderPlayers : function () {
                       return $q(function(resolve, reject){
                        Restangular.one('games', Game.getCurrentGame().id).getList('players')
                                   .then(function(data) {
                                        $cookies.putObject('wonder', data);
                                         resolve(data);
                                   }).catch(function(data){
                                         reject(data);
                                   });
                       });
                    }
                }
        }
    ]);