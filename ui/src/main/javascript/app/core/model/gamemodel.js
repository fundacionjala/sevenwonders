'use strict';

angular
    .module('sevenWonders.core.model')
    .factory('GameModel', ['UserModel', function(UserModel){
        function GameModel(id, roomName, channel, type, maxPlayers, owner){
                    this.id = id;
                    this.roomName = roomName;
                    this.channel = channel;
                    this.type = type;
                    this.maxPlayers = maxPlayers;
                    this.owner = player;
        }  
        function GameModel(data){
                    this.id = data.id;
                    this.roomName = data.roomName;
                    this.channel = data.channel;
                    this.type = data.type;
                    this.maxPlayers = data.maxPlayers;
                    this.owner = new UserModel(data.owner);
        }  

        return (GameModel);

    }]);