'use strict';
angular.module('sevenWonder.gameroom')
    .directive('wonder', function () {
        return {
            restrict: 'E',
            scope: {
                data: '='
            },
            replace: true,
            templateUrl: 'core/directives/gameroom/wonder/wonder.html',
        };
    });