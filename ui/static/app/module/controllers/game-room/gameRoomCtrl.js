angular.module('sevenWonder')
    .controller('gameRoomCtrl', function ($scope, $http) {
        $http.get('https://demo9730175.mockable.io/game-room/1/players').
            success(function (data) {
                $scope.players = data;
            });
        $http.get('https://demo9730175.mockable.io/game-room/1').
            success(function (data) {
                $scope.gameroom = data;
            });
    })
    .controller("createGameRoomCtrl", function ($scope, $http, swLogin, swCreateGameRoom) {

        $scope.maxPlayers = ["3", "4", "5", "6", "7"];
        $scope.user = swLogin.user;

        $scope.createGameRoom = function () {
            var roomCreated = {
                name: $scope.room,
                numberPlayers: $scope.player
            };

         swCreateGameRoom.saveGameRoom(roomCreated);

        };
    });
