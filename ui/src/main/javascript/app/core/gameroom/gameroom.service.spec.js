
describe('getGameRoom', function(){
    var gameRoom;
    var game;

    beforeEach(function() {
        module('sevenWonders.core.gameroom');
        inject(function($injector){
            gameRoom = $injector.get('GameRoom');
            game = $injector.get('Game');
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
        
    });
         
});
