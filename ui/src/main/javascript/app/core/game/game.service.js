'use strict';

angular.
module('sevenWonders.core.game').
factory('Game', ['$resource',
    function($resource) {
        var Game = $resource("http://demo8039957.mockable.io/game");
        return {
            getAvailableGames: function() {
                return Game.get();
            }
        }
    }
]);