angular.module("sevenWonder")
  .service('swWonders', function ($http) {
    this.getWonder = function () {
       return $http.get('http://demo3473547.mockable.io/swonders');
    }
  });