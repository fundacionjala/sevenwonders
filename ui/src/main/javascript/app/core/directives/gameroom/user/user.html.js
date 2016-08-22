angular.module("sevenWonder.gameroom")
    .directive("user", function () {
        return {
            restrict: 'EA',
            scope: {
                data: '='
            },
            replace: true,
            templateUrl: 'core/directives/gameroom/user/user.html',
        }
    });