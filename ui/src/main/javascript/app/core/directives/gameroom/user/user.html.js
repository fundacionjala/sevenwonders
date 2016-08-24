'use strict';
angular.module('sevenWonder.gameroom')
    .directive('user', function () {
        return {
            restrict: 'E',
            scope: {
                data: '='
            },
            replace: true,
            templateUrl: 'core/directives/gameroom/user/user.html',
        };
    });