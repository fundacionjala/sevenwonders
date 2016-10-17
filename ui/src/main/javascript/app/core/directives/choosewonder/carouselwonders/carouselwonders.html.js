'use strict';
angular.module('sevenWonder.choosewonder')
    .directive('carouselWonders', function () {
        return {
            restrict: 'E',
            scope: {
                wonders: '=',
                move: '&',
                change: '&'
            },
            replace: true,
            templateUrl: 'core/directives/choosewonder/carouselwonders/carouselwonders.html',
        };
    });
    