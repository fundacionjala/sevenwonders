angular.module('sevenWonder')
    .controller("gameRoomCtrl", ["$scope", "swGameRooms", function ($scope, swGameRooms) {
       swGameRooms.getGameRoom().success(function (result) {
             $scope.gameroom = result;
        });
    }])
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