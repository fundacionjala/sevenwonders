describe('Game service', function () {
    beforeEach(angular.mock.module('sevenWonders.core.game'));
    beforeEach(angular.mock.module('sevenWonders.core.model'));

    beforeEach(module(function ($exceptionHandlerProvider) {
        $exceptionHandlerProvider.mode('log');
    }));

    beforeEach(inject(function (_UserModel_,_Game_, _Restangular_, _$q_, _$exceptionHandler_, _$httpBackend_, _Auth_) {
        $q = _$q_;
        deferred = $q.defer();
        Restangular = _Restangular_;
        $exceptionHandler = _$exceptionHandler_;
        exception = _$exceptionHandler_;
        $httpBackend = _$httpBackend_;
        Auth = _Auth_;
        Game = _Game_;
        UserModel = _UserModel_;
    }));
    it('Should exist', function () {
        expect(Game).toBeDefined();
    });

    describe('.getAvailableGames', function () {
        var mockedgameModel = {
            id: 1,
            roomName: 'Jhon',
            channel: 4,
            type: 'Babilon',
            numberPlayers: 7
        };
        var mockedgameModel2 = {
            id: 2,
            roomName: 'Snow',
            channel: 4,
            type: 'Ephesos',
            numberPlayers: 3
        };
        var mockedGames = [mockedgameModel, mockedgameModel2];

        it('should exist', function () {
            expect(Game.getAvailableGames).toBeDefined();
        });

        it('should return an empty list of games', function () {
            spyOn(Game, 'getAvailableGames').and.returnValue(deferred.promise);
            deferred.resolve([]);
            var expected = Game.getAvailableGames().$$state.value;
            expect(expected).toEqual([]);
        });

        it('should not let return an empty list of games', function () {
            spyOn(Game, 'getAvailableGames').and.returnValue(deferred.promise);
            deferred.reject();
            var expected = Game.getAvailableGames().$$state.value;
            expect(expected).toBe(undefined);
        });


    });
    describe('.create', function () {
        var mockGame = {
            gameSetting: mockGameSetting,
            token: 'bla bla bla',
            id: '8569235'
        }
        var mockGameSetting = {
            maxPlayers: 5,
            roomName: 'Light',
            owner: 'Gonzalo'
        }

        it('Should exist', function () {
            expect(Game.create).toBeDefined();
        });
        it('should return exception when trying to create an undefinde gameSetting', function () {
            expect($exceptionHandler.errors).toEqual([]);
            try {
                Game.create(undefined);
            } catch (e) {
                $exceptionHandler(e);
            }
            expect($exceptionHandler.errors).toEqual(['gameSetting is not defined!!.']);
        });

        it('Should accept to create a Game', function () {

            spyOn(Game, 'create').and.returnValue(deferred.promise);
            deferred.resolve(mockGame);
            expect(Game.create(mockGameSetting).$$state.value).toEqual(mockGame);
        });

        it('Should reject to create a Game', function () {

            spyOn(Game, 'create').and.returnValue(deferred.promise);
            deferred.reject();
            expect(Game.create(mockGameSetting).$$state.value).toBe(undefined);
        });
    });
    describe('.join', function () {
        var mockedPlayer = {
            id: 1
        };
        var player = {
            id: 4,
            userName: 'fpfo',
            token: 896
        }
        it('should exist', function () {
            expect(Game.join).toBeDefined();
        });

        it('should return exception when trying to create an undefinde gameSetting', function () {
            expect(exception.errors).toEqual([]);
            try {
                Game.join(undefined);
            } catch (e) {
                exception(e);
            }
            expect(exception.errors).toEqual(['game is not defined!!.']);
        });
        it('Should reject to create a Game', function () {

            spyOn(Restangular.service('games'), 'one').and.callThrough();
            spyOn(Auth, 'getLoggedUser').and.returnValue(player);
            $httpBackend.expectPOST('/games/1/players', mockedPlayer).respond(undefined);

            var runGame = Game.join(mockedPlayer);
            expect(runGame.$$state.status).toBe(0);
        });
    });
});