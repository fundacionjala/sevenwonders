angular.module("sevenWonder")
	.directive("swStage", function () {
		return {
			restrict: 'EA',
			scope: {
				stage: '='
			},
			replace: true,
			templateUrl: 'app/module/directives/stage/swStage.html',
			controller: function ($scope) {
			},
			link: function () {
			}
		}
	});