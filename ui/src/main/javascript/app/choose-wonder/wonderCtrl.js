angular.module("sevenWonder")
    .controller("wonderCtrl", ["$scope", "swWonders", function ($scope, swWonders) {
       swWonders.getWonder().success(function (result) {
             $scope.wonder = result;
        });
    }]);

