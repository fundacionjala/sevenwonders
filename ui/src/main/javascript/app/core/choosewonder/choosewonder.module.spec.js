describe('ChooseWonder factory', function () {
    var ChooseWonder;
    var $q;
    var Restangular;

    beforeEach(angular.mock.module('sevenWonders.core.choosewonder'));

    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));

    beforeEach(inject(function (_ChooseWonder_, _$q_, _Restangular_) {
        ChooseWonder = _ChooseWonder_;
        q = _$q_;
        Restangular = _Restangular_;
    }));

    beforeEach(inject(function (_$exceptionHandler_) {
        $exceptionHandler = _$exceptionHandler_;
    }));

    it('should exist', function () {
        expect(ChooseWonder).toBeDefined();
    });

    describe('.setWonderPlayer()', function () {

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

        it('set a valid player', function () {

            var thePlayer = {
                id: '1'
            };
            var gameModel = {
                id: '2',
                roomName: 'test',
                channel: 'shannel',
                type: 'typo',
                numberPlayers: 7,
                player: thePlayer
            };

            expect(ChooseWonder.setWonderPlayer(thePlayer)).toBeDefined();
            spyOn(ChooseWonder, "setWonderPlayer").and.callThrough();
        });

    });

    describe('.getWonderPlayers()', function () {
        it('should exist', function () {
            expect(ChooseWonder.getWonderPlayers).toBeDefined();
        });
    });
});