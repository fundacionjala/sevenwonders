'use strict';

angular.
    module('sevenWonders.core.gameboard').
    factory('GameBoard', ['$cookies', '$websocket', 'Restangular', '$q',
        function ($cookies, $websocket, Restangular, $q) {
                return {
                  getStorage: function () {
                      var defer = $q.defer();
                      Restangular.allUrl('storage', 'http://demo5549833.mockable.io/storage').getList()
                                 .then(function (data) {
                                       defer.resolve(data);
                                 }).catch(function () {
                                       defer.reject();
                                 });
                         return defer.promise;
                   }
                }
        }
    ]);