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
    .controller("createGameRoomCtrl", function ($scope, $http, swLogin ) {

        $scope.maxPlayers = ["3", "4", "5", "6", "7", "8"];
        $scope.user = swLogin.user;

        $scope.createRoomGame = function () {
            var roomCreated = {
                name: $scope.room,
                numberPlayers: $scope.player
            };
            $http.post('https://demo9730175.mockable.io/game-room/1', roomCreated).
                success(function (data) {
                    console.log(data);
                });
        };
    });
