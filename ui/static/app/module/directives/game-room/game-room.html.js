angular.module("sevenWonder")
	.directive("swGameRoom", function () {
		return {
			restrict: 'EA',
			scope: {
				gameroom: '='
			},
			replace: true,
			templateUrl: 'app/module/directives/game-room/game-room.html',
			controller: function ($scope) {
				$scope.count = 0;
				$scope.setCount = function (val) {
					$scope.count = val;
				}
			}
		}
	});