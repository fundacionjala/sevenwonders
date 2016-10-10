'use strict';
angular.module('sevenWonder.gameboard')
    .directive('containerWonders', function () {
        return {
            restrict: 'E',
            scope: {
                data: '=',
                action: '&'
            },
            replace: true,
            templateUrl: 'core/directives/gameboard/container-wonders/container-wonders.html',
        };
    });
    