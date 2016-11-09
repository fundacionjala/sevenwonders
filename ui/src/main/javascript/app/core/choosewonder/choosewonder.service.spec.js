describe('choose wonder tests', function () {
    var chooseWonder;
    var game;
    var httpBackend;
    var restangular;
    var websocket;
    var cookies;

    beforeEach(function() {
        module('sevenWonders.core.choosewonder');
        inject(function($injector, _$httpBackend_){
            websocket = $injector.get('$websocket');
            chooseWonder = $injector.get('ChooseWonder');
            game = $injector.get('Game');
            httpBackend = _$httpBackend_;
            cookies = $injector.get('$cookies');
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

    
    it('should set wonder giving a player', () => {

        var mockPlayer = {
            name : "Juan",
            token : "ASDCG554",
            wonder: ""
        }

        spyOn(restangular, 'all').and.callThrough();
        
        httpBackend.expectPUT('/games/1/player', mockPlayer)
        .respond(mockPlayer);
        
        var chooseWonderRun = chooseWonder.setWonderPlayer(mockPlayer);
        expect(restangular.all).toHaveBeenCalledWith('games/1/player');
        httpBackend.flush(); 

        chooseWonderRun.then(function(value){
            expect(value).toEqual(mockPlayer);
        });
    });
    
    
    it('should get wonder players', () => {
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
        spyOn(cookies, 'putObject').and.callThrough();      
        httpBackend.expectPUT('games/1/players').respond(mockPlayers);
        
        var chooseWonderRun = chooseWonder.getWonderPlayers();
        expect(restangular.one).toHaveBeenCalledWith('games', 1);

        chooseWonderRun.then(function(value){
            expect(cookies.putObject).toHaveBeenCalled();
            expect(value).toEqual(mockPlayers);
        });


    });
        
});