angular.module('sevenWonderTest')
    .controller('wonderCtrl', function ($scope, $http) {
        var getWonder = function () {
            $http.get('http://demo3473547.mockable.io/swonders').success(function (data, status, headers, config) {
                $scope.wonder = data;
            }).error(function (data, status, headers, config) {
                $scope.wonder = [];
            });
        };
        /* On Load */
        $scope.wonder = [];
        getWonder();
    })

/* Tests */
describe('choose wonder tests', function () {
    beforeEach(module('sevenWonderTest'));
    var $controller;
    var $httpBackend;
    var $scope;

    describe('test response service', function () {

        beforeEach(angular.mock.http.init);
        afterEach(angular.mock.http.reset);

        beforeEach(inject(function (_$controller_, _$httpBackend_) {
            $controller = _$controller_;
            $scope = {};
            $httpBackend = _$httpBackend_;

            $httpBackend.whenGET('http://demo3473547.mockable.io/swonders').passThrough();
        }));
        it('no equals wonder', function (done) {
            $controller('wonderCtrl', { $scope: $scope });

            setTimeout(function () {
                expect($scope.wonder).not.toEqual([]);
                done();
            }, 1000);

        });
        it('is Equals wonder', function (done) {
            $controller('wonderCtrl', { $scope: $scope });
            setTimeout(function () {
                var actual = {
                    "name": "halinarkassus",
                    "sides": [
                        {
                            "type": "a",
                            "stages": [
                                {
                                    "resources": [
                                        {
                                            "name": "papyrus"
                                        },
                                        {
                                            "name": "papyrus"
                                        }],
                                    "effects": [
                                        {
                                            "name": "papyrus",
                                            "typeEffect": "resource"
                                        }],
                                    "order": 1
                                }, {
                                    "resources": [
                                        {
                                            "name": "papyrus"
                                        },
                                        {
                                            "name": "papyrus"
                                        }],
                                    "effects": [
                                        {
                                            "name": "papyrus",
                                            "typeEffect": "resource"
                                        }],
                                    "order": 2
                                }
                            ]
                        },
                        {
                            "type": "b",
                            "stages": [
                                {
                                    "resources": [
                                        {
                                            "name": "papyrus"
                                        }],
                                    "effects": [
                                        {
                                            "name": "papyrus",
                                            "typeEffect": "resource"
                                        }],
                                    "order": 1
                                }]
                        }
                    ],
                    "description": "hello world",
                    "effects": [
                        {
                            "name": "papyrus",
                            "typeEffect": "resource"
                        }]
                };
                expect($scope.wonder).toEqual(actual);
                done();
            }, 1000);

        });
    });
})
