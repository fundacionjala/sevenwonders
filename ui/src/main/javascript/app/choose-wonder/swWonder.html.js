angular.module("sevenWonder")
	.directive("swWonder", function () {
		return {
			restrict: 'EA',
			scope: {
				wonder: '='
			},
			replace: true,
			templateUrl: 'app/module/directives/choose-wonder/swWonder.html',
			controller: function ($scope) {
				$scope.count = 0;
				$scope.setCount = function (val) {
					$scope.count = val;
				}
			}
		}
	});