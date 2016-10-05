'use strict';
angular.module('sevenWonder.choosewonder')
    .directive('carouselWonders', function () {
        return {
            restrict: 'E',
            scope: {
                wonders: '=',
                move: '&'
            },
            replace: true,
            templateUrl: 'core/directives/choosewonder/carouselwonders/carouselwonders.html',
        };
    });
    