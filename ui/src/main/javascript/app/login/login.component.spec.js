'use strict';

describe('Tests Login component', function () {
    var userData = {
        userName: "Johx"
    };
    var $exceptionHandler;
    // Load the module that contains the `sevenWonder.login` component before each test
    beforeEach(module('sevenWonder.login'));
    beforeEach(function () {
        module(function ($provide) {
            $provide.service('$cookies', function () {
                this.getObject = function (user) {
                    return userData;
                };
            });
        });
        module('sevenWonders.core.auth');
    });
    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));
    // Test the controller
    describe('.doLogin(user)', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_, _$exceptionHandler_) {
            $httpBackend = _$httpBackend_;
            ctrl = $componentController('login');
            $exceptionHandler = _$exceptionHandler_;
        }));

        it('should exist', function () {
            jasmine.addCustomEqualityTester(angular.equals);
            expect(ctrl.doLogin).toBeDefined();
        });

        it('should login a user', function () {
            expect(ctrl.doLogin(userData)).toBe(undefined);
        });

        it('should return exception when trying to login a undefinded user', function () {
            expect($exceptionHandler.errors).toEqual([]);
            try {
                ctrl.doLogin(undefined);
            } catch (e) {
                $exceptionHandler(e);
            }
            expect($exceptionHandler.errors).toEqual(['User is not defined.']);
        });
    });
});
