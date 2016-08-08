angular.module('sevenWonder')
    .controller("loginCtrl", ["$scope", "swLogin", function($scope, swLogin) {
        $scope.login = function () {
            var player = {
                name: $scope.user,
                    isAdmin: false
            };
            swLogin.postResponse(player.name);
            swLogin.user = player.name;
        };
    }]);


