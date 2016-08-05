angular.module('mainModule')
    .controller("loginController", function ($scope, $http) {
            var player = {
                name: $scope.user,
                isAdmin: false
            };
            $http.post('http://demo9586009.mockable.io/user', player.name).
                success(function (data) {
                    console.log(data);
                });
        });


