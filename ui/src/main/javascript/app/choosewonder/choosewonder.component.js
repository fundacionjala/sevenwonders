'use strict';

angular.
    module('sevenWonder.choosewonder').
    component('choosewonder', {
        templateUrl: 'choosewonder/choosewonder.tpl.html',
        controller: ['ChooseWonder',
            function ChooseWonderController(ChooseWonder) {
                var self = this;
                var listOfWonders = [{
                                    'a': "olympia-a"
                                }, {
                                    'a': "babylon-a"
                                }, {
                                    'a': "ephesos-a"
                                }, {
                                    'a': "alexandria-a"
                                }, {
                                    'a': "gizah-a"
                                }, {
                                    'a': "halinarkassus-a"
                                }, {
                                    'a': "rodhos-a"
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
