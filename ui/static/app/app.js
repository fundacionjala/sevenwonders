angular.module("mainModule", ['ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    // For any unmatched url, send to /
    $urlRouterProvider.otherwise("/")

    $stateProvider
      .state('login', {
        url: "/",
        templateUrl: "views/login.html",
        controller: 'createGameRoomCtrl'
      })
      .state('game-room', {
        url: "/game-room",
        templateUrl: "views/game-room.html",
        controller: 'gameRoomCtrl'
      })
      .state('create-game-room', {
        url: "/create-game-room",
        templateUrl: "views/create-game-room.html",
        controller: 'createGameRoomCtrl'
      })
      .state('join-room', {
        url: "/join-room",
        templateUrl: "views/join.html",
        controller: 'ExampleController'
      })
  });
