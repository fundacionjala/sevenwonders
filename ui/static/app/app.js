angular.module("mainModule", ['ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    // For any unmatched url, send to /
    $urlRouterProvider.otherwise("/")

    $stateProvider
      .state('login', {
        url: "/",
        templateUrl: "views/login.html",
        controller: function ($scope) { }
      })
      .state('game-room', {
        url: "/game-room",
        templateUrl: "views/game-room.html",
        controller: function ($scope, $http) {
          $http.get('https://demo9730175.mockable.io/game-room/1/players').
            success(function (data) {
              $scope.players = data;
            });
          $http.get('https://demo9730175.mockable.io/game-room/1').
            success(function (data) {
              $scope.gameroom = data;
            });
        }
      })
      .state('create-game-room', {
        url: "/create-game-room",
        templateUrl: "views/create-game-room.html",
        controller: function ($scope, $http) {
          $scope.players = ["3", "4", "5", "6", "7","8"];

          $scope.createRoomGame = function() {
          roomcreated = { name: $scope.room,
                          numberPlayers: $scope.player
                        };
            $http.post('http://demo9056918.mockable.io/gameroom/1', roomCreated).
                success(function(data) {
                console.log(data);
            });
          };
        }
      })
 });
