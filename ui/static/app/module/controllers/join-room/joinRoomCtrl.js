angular.module('sevenWonder')
 	.controller('joinRoomCtrl', function($scope, $http) {
 		$scope.roomselected = null;
 		$http.get('https://demo9730175.mockable.io/rooms').
            success(function (data) {
                $scope.rooms = data;
            });
	});

