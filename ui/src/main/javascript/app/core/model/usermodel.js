'use strict';

angular.
    module('sevenWonders.core.model').factory('UserModel', [function(){
        function UserModel(id, userName, isLoggedIn, token){
                    this.id = id;
                    this.userName = userName;
                    this.isLoggedIn = isLoggedIn;
                    this.token = token;
        }  
        function UserModel(data){
                    this.id = data.id;
                    this.userName = data.userName;
                    this.isLoggedIn = true;
                    this.token = 'Bearer ' + data.token;
        }  

        return (UserModel);
    }]);