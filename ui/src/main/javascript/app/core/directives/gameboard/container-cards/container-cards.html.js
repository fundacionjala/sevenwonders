'use strict';
angular.module('sevenWonder.gameboard')
    .directive('containerCards', function () {
        return {
            restrict: 'E',
            scope: {
                data: '=',
                action: '&'
            },
            replace: true,
            templateUrl: 'core/directives/gameboard/container-cards/container-cards.html',
        };
    });
    