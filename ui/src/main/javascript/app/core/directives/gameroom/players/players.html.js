angular.module("sevenWonder.gameroom")
    .directive("players", function () {
        return {
            restrict: 'EA',
            scope: {
                data: '='
            },
            replace: true,
            templateUrl: 'core/directives/gameroom/players/players.html',
        }
    });