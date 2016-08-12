angular.module("sevenWonder")
.config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/login', {
          template: '<login></login>'
        })
        .otherwise('/login');
    }
  ]);

/*  .config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/")

    $stateProvider
      .state('login', {
        url: "/",
        templateUrl: "<login></login>",
      })
     *//* .state('game-room', {
        url: "/game-room",
        templateUrl: "app/module/directives/game-room/game-room.html",
        controller: 'gameRoomCtrl'
      })
      .state('create-game-room', {
        url: "/create-game-room",
        templateUrl: "app/module/directives/create-game-room/create-game-room.html",
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
      })*//*
  });*/
