describe('ChooseWonder service', function () {
    var ChooseWonder;
    var $q;
    var Restangular;
    var $httpBackend;


    beforeEach(angular.mock.module('sevenWonders.core.choosewonder'));

    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));

    beforeEach(inject(function (_ChooseWonder_, _$q_, _Game_,_$exceptionHandler_) {
        ChooseWonder = _ChooseWonder_;
        $q = _$q_;
        deferred = $q.defer();
        Game = _Game_;
        $exceptionHandler = _$exceptionHandler_;
    }));

    it('should exist', function () {
        expect(ChooseWonder).toBeDefined();
    });

    describe('.setWonderPlayer()', function () {
        var mockedPlayer = {
            id: 1
        };

        it('should exist', function () {
            expect(ChooseWonder.setWonderPlayer).toBeDefined();
        });

        it('Should return exception when trying to set a undefinded player', function () {
            expect($exceptionHandler.errors).toEqual([]);
            try {
                ChooseWonder.setWonderPlayer(undefined);
            } catch (e) {
                $exceptionHandler(e);
            }
            expect($exceptionHandler.errors).toEqual(['player is not defined.']);
        });

        it('accept to set a valid player', function () {
            spyOn(ChooseWonder, 'setWonderPlayer').and.returnValue(deferred.promise);
            deferred.resolve([{ id: 1 }]);
            expect(ChooseWonder.setWonderPlayer(mockedPlayer).$$state.value[0]).toEqual(mockedPlayer);
        });

        it('reject to set a player', function () {
            spyOn(ChooseWonder, 'setWonderPlayer').and.returnValue(deferred.promise);
            deferred.reject();
            expect(ChooseWonder.setWonderPlayer(mockedPlayer).$$state.value).toBe(undefined);
        });

    });

    describe('.getWonderPlayers()', function () {
        it('should exist', function () {
            expect(ChooseWonder.getWonderPlayers).toBeDefined();
        });
    });
});