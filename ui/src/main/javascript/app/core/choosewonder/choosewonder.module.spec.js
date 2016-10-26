describe('ChooseWonder factory', function () {
    var ChooseWonder;
    var $q;
    var Restangular;
    var $httpBackend;

    beforeEach(angular.mock.module('sevenWonders.core.choosewonder'));

    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));

    beforeEach(inject(function (_ChooseWonder_, _$q_, _Restangular_, _$httpBackend_, _Game_) {
        ChooseWonder = _ChooseWonder_;
        $q = _$q_;
        deferred = $q.defer();
        Restangular = _Restangular_;
        $httpBackend = _$httpBackend_;
        Game = _Game_;
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

            spyOn(Game, ['getCurrentGame']).and.returnValue({ gameModel });

            spyOn(Game, 'search').and.returnValue(deferred.promise);

            var res;
            expect(ChooseWonder.setWonderPlayer(thePlayer).promise).toBeDefined();

            deferred.resolve([{ id: 1 }, { id: 2 }]);

            // We have to call apply for this to work
            $scope.$apply();

            // Since we called apply, not we can perform our assertions
            expect($scope.results).not.toBe(undefined);
            expect($scope.error).toBe(undefined);
            // $httpBackend.whenJSONP('games/' + Game.getCurrentGame().id + '/player').respond(accountsModel);
            // $httpBackend.whenPOST(API + search).respond(200, $q.when(RESPONSE_SUCCESS));


            ChooseWonder.setWonderPlayer(thePlayer);


            // .then(function (res) {
            //     result = res;
            // });


        });

    });

    describe('.getWonderPlayers()', function () {
        it('should exist', function () {
            expect(ChooseWonder.getWonderPlayers).toBeDefined();
        });
    });
});