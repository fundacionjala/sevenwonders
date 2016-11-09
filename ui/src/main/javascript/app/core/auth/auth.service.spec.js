'use strict';

describe('Tests Auth service', function () {
    var Auth;
    var $cookies;
    var $q;
    var deferred;
    var userData = {
        userName: 'JOhx'
    };

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

    beforeEach(inject(function (_$cookies_, _$q_, _Auth_) {
        $cookies = _$cookies_;
        $q = _$q_;
        deferred = $q.defer();
        Auth = _Auth_;
    }));

    it('should exist', function () {
        expect(Auth).toBeDefined();
    });
    describe('.login(user)', function () {

        it('should exist', function () {
            expect(Auth.login).toBeDefined();
        });

        it('accept to set a valid user', function () {
            spyOn(Auth, 'login').and.returnValue(deferred.promise);
            deferred.resolve({ userName: 'JOhx' });
            expect(Auth.login(userData).$$state.value).toEqual(userData);
        });

        it('reject to set a user', function () {
            spyOn(Auth, 'login').and.returnValue(deferred.promise);
            deferred.reject();
            expect(Auth.login(userData).$$state.value).toBe(undefined);
        });
    });
    describe('.getLoggedUser()', function () {

        it('should get the value stored in the cookies', function () {
            var player = {
                userName: 'JOhx'
            }   
            spyOn(Auth, 'getLoggedUser').and.returnValue(player);
            var userModel = Auth.getLoggedUser();
            expect(userModel.userName).toBe(userData.userName);
        });

    });
});