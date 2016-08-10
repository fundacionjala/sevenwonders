angular.module("sevenWonder")
  .service('swCreateGameRoom', function ($http) {
    this.saveGameRoom = function (roomCreated) {
       $http.post('https://demo9730175.mockable.io/game-room/1', roomCreated).
                       success(function (data) {
                           console.log(data);
                       });
    }
  });