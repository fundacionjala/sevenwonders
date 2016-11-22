'use strict';

angular.
    module('sevenWonders.core.choosewonder').
    factory('ChooseWonder', ['$cookies', '$websocket', 'Game', 'Restangular', '$q', '$location', '$timeout', 'WsConfig',
        function ($cookies, $websocket, Game, Restangular, $q, $location, $timeout, WsConfig) {
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

                    ConnectWsReady: function(){
                        var dataStream = $websocket(WsConfig.gameReady + 'gameReady');
                        dataStream.onMessage(function (message) {
                            Restangular.one('game').one('last').get()
                                .then(function(data) {
                                    $cookies.putObject('PrincipalGame', data);
                                    $timeout(function() {
                                        $location.path('/gameboard');
                                        console.log('game is created');
                                    }, 2000);  
                                }).catch(function(){

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