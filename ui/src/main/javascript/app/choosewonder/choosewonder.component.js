'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder',
            function ChooseWonderController(ChooseWonder) {
              var self = this;
              self.wonders= [];
              ChooseWonder.getWonderPlayers().then(function (result) {
                       self.wonders = result;
                       console.log(self.wonders[0]);
              });
            }
        ]
    });
