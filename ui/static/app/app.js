angular.module("sevenWonder", ['ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/")

    $stateProvider
      .state('login', {
        url: "/",
        templateUrl: "app/module/directives/login/login.html",
        controller: 'loginCtrl'
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
      .state('join-room', {
        url: "/join-room",
        templateUrl: "views/join.html",
        controller: 'joinRoomCtrl'
      })
      .state('choose-wonder', {
        url: "/choose-wonder",
        templateUrl: "views/choose.html",
        controller: 'wonderCtrl'
      })
  });
