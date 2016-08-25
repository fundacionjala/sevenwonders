'use strict';
angular.module('sevenWonder.gameroom')
    .directive('players', function () {
        return {
            restrict: 'E',
            scope: {
                data: '='
            },
            replace: true,
            templateUrl: 'core/directives/gameroom/players/players.html',
        };
    });