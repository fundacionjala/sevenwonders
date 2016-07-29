angular.module("mainModule", ['ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    // For any unmatched url, send to /game-room
    $urlRouterProvider.otherwise("/game-room")

    $stateProvider
      .state('game-room', {
        url: "/game-room",
        templateUrl: "views/game-room.html",
        controller: function ($scope) {
          $scope.gameroom = {
            id: 1,
            name: 'Battle Blood',
            numberPlayers: 8
          };
          $scope.players = [{
            id: 1,
            username: 'VaniMar',
            user: 'user'
          }, {
              id: 2,
              username: 'JOhx',
              user: 'user'
            }, {
              id: 3,
              username: '',
              user: 'playerdisabled'
            }, {
              id: 4,
              username: '',
              user: 'playerdisabled'
            }, {
              id: 5,
              username: '',
              user: 'playerdisabled'
            }, {
              id: 6,
              username: '',
              user: 'playerdisabled'
            }, {
              id: 6,
              username: '',
              user: 'playerdisabled'
            }];
        }
      })
  })
  .controller("mainController", function ($scope) {
    $scope.myScopeVar = "the scope variable value";
  })
  // 1. Directive with a template specified inline
  .directive("nghTemplateDir", function () {
    return {
      template: 'This is <strong>nghTemplateDir</strong> directive printing <em>{{myScopeVar}}</em>'
    };
  })
  // 2. Directive with a template loaded from a URL
  .directive("nghTemplateUrlDir", function () {
    return {
      templateUrl: 'templates/nghTemplateUrlDirBody.html'
    };
  });