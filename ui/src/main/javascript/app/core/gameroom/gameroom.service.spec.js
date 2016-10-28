
describe('getGameRoom', function(){
    var gameRoom;
    var game;
    var httpBackend;
    var restangular;
    var websocket;

    beforeEach(function() {
        module('sevenWonders.core.gameroom');
        inject(function($injector, _$httpBackend_){
            websocket = $injector.get('$websocket');
            gameRoom = $injector.get('GameRoom');
            game = $injector.get('Game');
            httpBackend = _$httpBackend_;
            restangular = $injector.get('Restangular');
            spyOn(game, 'getCurrentGame').and.returnValue({
                    id: 1,
                    roomName: "Tu",
                    channel: 2,
                    type: "Normal",
                    numberPlayers: 5,
                    player: {
                        name: "Juan"
                    }
            });
        });
    });

    it('should return the current game', () => {
        var room = gameRoom.getGameRoom();
        expect(game.getCurrentGame).toHaveBeenCalled();
        expect(room).toEqual(jasmine.objectContaining({
                    id: 1,
                    roomName: "Tu",
                    channel: 2,
                    type: "Normal",
                    numberPlayers: 5,
                    player: {
                        name: "Juan"
                    }
        }));
    });    

    
    it('should return the list of players', () => {

        var gameModel = {
            id: 1
        };
     
        var mockPlayers = [
                {
                name:"juan",
                token:"asdasdasd"
                },
                {
                name:"dwtis",
                token:"asd"
                }
            ];

        spyOn(restangular, 'one').and.callThrough();

        httpBackend.expectGET('/games/1/players').respond(mockPlayers);

        var players = gameRoom.getPlayers();
        expect(restangular.one).toHaveBeenCalledWith('games', 1);
        httpBackend.flush();    

        players.then(function(value){
            expect(value).toEqual(mockPlayers);
        });
    });

    
    it('should addPlayer onMessage', () => {
       
    });
        
         
});
