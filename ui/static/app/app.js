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
  });
