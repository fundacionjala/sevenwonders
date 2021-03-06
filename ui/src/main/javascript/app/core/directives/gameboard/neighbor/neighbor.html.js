'use strict';
angular.module('sevenWonder.gameboard')
    .directive('neighbor', function () {
        return {
            restrict: 'E',
            scope: {
                side: '=',
                action: '&'
            },
            replace: true,
            templateUrl: 'core/directives/gameboard/neighbor/neighbor.html',
        };
    });
    