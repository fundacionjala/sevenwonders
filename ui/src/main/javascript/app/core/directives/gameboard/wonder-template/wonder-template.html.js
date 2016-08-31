'use strict';
angular.module('sevenWonder.gameboard')
    .directive('wonderTemplate', function () {
        return {
            restrict: 'E',
            scope: {
                data: '=',
                action: '&'
            },
            replace: true,
            templateUrl: 'core/directives/gameboard/wonder-template/wonder-template.html',
        };
    });
    