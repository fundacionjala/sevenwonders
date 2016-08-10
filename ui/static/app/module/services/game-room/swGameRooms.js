angular.module("sevenWonder")
  .service('swGameRooms', function ($http) {
    this.getGameRoom = function () {
      return $http.get('http://demo9730175.mockable.io/game-room/7');
    }
  });