angular.module('sevenWonder')
    .controller("loginCtrl", function ($scope, $http) {
            var player = {
                name: $scope.user,
                isAdmin: false
            };
            $http.post('https://demo9730175.mockable.io/user', player.name).
                success(function (data) {
                    console.log(data);
                });
        });


