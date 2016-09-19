'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder',
            function ChooseWonderController(ChooseWonder) {
                var self = this;
                var listOfWonders = [{
                    'name': "olympia"
                }, {
                        'name': "babylon"
                    }, {
                        'name': "ephesos"
                    }, {
                        'name': "alexandria"
                    }, {
                        'name': "gizah"
                    }, {
                        'name': "halinarkassus"
                    }, {
                        'name': "rodhos"
                    }];
                self.wonders = listOfWonders;
                this.rotate = function (data) {
                    if (data == 'next') {
                        listOfWonders.slice(0, 3);
                        self.wonders.push(listOfWonders.shift());
                    } else if (data == 'previous') {
                        listOfWonders.slice(0, 3);
                        self.wonders.unshift(listOfWonders.pop());
                    }
                };
            }
        ]
    });
