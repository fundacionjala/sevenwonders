'use strict';
angular.module('sevenWonder.choosewonder')
    .directive('player', function () {
        return {
            restrict: 'E',
            scope: {
                data: '=',
                current: '=',
                state: '=',
            },
            replace: true,
            templateUrl: 'core/directives/choosewonder/player/player.html',
        };
    });