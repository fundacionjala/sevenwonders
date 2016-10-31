describe('LobbyController', function(){
	beforeEach(angular.mock.module('sevenWonder.lobby'));

    var game;
    var controller;
    var $exceptionHandler;

    beforeEach(module(function($exceptionHandlerProvider) {
            $exceptionHandlerProvider.mode('log');
     }));

    beforeEach(inject(function(_Game_, _$exceptionHandler_, $componentController) {
        game = _Game_;
        $exceptionHandler = _$exceptionHandler_;
        controller = $componentController('lobby');
    }));

    it('controller of lobby exist', function() {
        expect(controller).toBeDefined();
    });

    it('controller of lobby exist', function() {
        expect($exceptionHandler).toBeDefined();
    });

    describe('addGame()', function() {
      var gameModel = {
                        id: 1,
                        roomName: 'vania',
                        channel: 2,
                        type: 'Babilon',
                        numberPlayers: 3
                    };

        it('should added a game', function() {
            controller.addGame(gameModel);
            expect(controller.games.length).toBe(1);
        });

        it('added undefined game then exception', function() {
            expect($exceptionHandler.errors).toEqual([]);
                  try {
                        controller.addGame(undefined);
                    } catch (e) {
                        $exceptionHandler(e);
                    }
                    expect($exceptionHandler.errors).toEqual(['game is not defined.']);
        });
    });

        describe('createGame', function() {
          it('should create a game', function() {
          var gameSettings = {
                                  name: 'blood',
                                  players: 3
                              };
              controller.createGame(gameSettings);
              expect(game.getCurrentGame()).toBeDefined();
              expect(game.getCurrentGame().roomName).toBe('blood');
          });

          it('failed create with undefined game then exception', function() {
              expect($exceptionHandler.errors).toEqual([]);
              try {
                 controller.createGame(undefined);
              } catch (e) {
                 $exceptionHandler(e);
              }
              expect($exceptionHandler.errors).toEqual(['game is not defined.']);
          });
        });

        describe('validateGame()', function() {
           it('should verify ', function() {
               var gameModel = {
                                id: 1,
                                roomName: 'blood2',
                                channel: 2,
                                type: 'Babilon',
                                numberPlayers: 3
                                };
                controller.addGame(gameModel);
                controller.validateGame(gameModel);
                expect(controller.games.length).toBe(0);
           });

            it('should verify game not find in the list', function() {
                var gameModel2 = {
                                 id: -1,
                                 roomName: 'blood4',
                                 channel: 2,
                                 type: 'Babilon',
                                 numberPlayers: 3
                                 };
                 controller.validateGame(gameModel2);
                 expect(controller.games.length).toBe(1);
            });

            it('should verify game undefined', function() {
                expect($exceptionHandler.errors).toEqual([]);
                          try {
                                controller.validateGame(undefined);
                            } catch (e) {
                                $exceptionHandler(e);
                            }
                            expect($exceptionHandler.errors).toEqual(['game is undefined']);

            });

        });
 });


