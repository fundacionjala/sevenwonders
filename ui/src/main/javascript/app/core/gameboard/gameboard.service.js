'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['Game', '$cookies', '$websocket', 'Restangular', '$q', 'WsConfig', "$interval",
        function (Game, $cookies, $websocket, Restangular, $q, WsConfig, $interval) {
            var dataStream;
            var self = this;
            var time;
            var GameService = Restangular.service('games');
            var timerComplete = function () {
                document.getElementById($cookies.getObject('chooseCard').nameCard)
                    .src = "http://localhost:3000/resources/images/cards/Age" +
                    $cookies.getObject('chooseCard').age + ".png";
                $interval.cancel(time);
            };
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
                getPlayerCards: function () {
                    var principalGame = $cookies.getObject('PrincipalGame');
                    var loggedUser = $cookies.getObject('user');
                    var cards = [];
                    var defer = $q.defer();
                    Restangular.one('game', principalGame.id).getList('players')
                        .then(function (data) {
                            var currentPlayer;
                            data.forEach(function (data) {
                                if (data.userName == loggedUser.userName) {
                                    currentPlayer = data;
                                    return;
                                }
                            });

                            currentPlayer.deck.cards.forEach(function (data) {
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
                connectWebsocket: function () {
                    dataStream = $websocket(WsConfig.gameUrl + 'chooseCard');
                    dataStream.onMessage(function (message) {
                        var temp = JSON.parse(message.data)
                        time = $interval(timerComplete, 2000);
                    });
                },
                notifiedChooseCard: function (item, event) {
                    var chooseModel = {
                        id: $cookies.getObject("game").id,
                        namePlayer: $cookies.getObject('user').userName,
                        nameCard: item.card,
                        age: ""
                    }
                    GameService.one().post('chooseCard', chooseModel)
                        .then(function (data) {
                            $cookies.putObject('chooseCard', data);
                        }).catch(function () {
                            console.log("Error choosed a card");
                        });
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
                }
            }
        }
    ]);