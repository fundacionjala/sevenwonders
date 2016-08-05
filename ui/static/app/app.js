angular.module("mainModule", ['ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/")

    $stateProvider
      .state('login', {
        url: "/",
        templateUrl: "app/module/directives/login/login.html",
        controller: 'createGameRoomCtrl'
      })
      .state('game-room', {
        url: "/game-room",
        templateUrl: "app/module/directives/game-room/game-room.html",
        controller: 'gameRoomCtrl'
      })
      .state('create-game-room', {
        url: "/create-game-room",
        templateUrl: "app/module/directives/form-game-room/create-game-room.html",
        controller: 'createGameRoomCtrl'
      })
  });
