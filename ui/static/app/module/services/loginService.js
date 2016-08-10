angular.module('sevenWonder')
    .service('swLogin', function($http) {
        this.postResponse = function (name){
            return $http.post('https://demo9730175.mockable.io/user', name)
                .then(function (response){
                        response.statusText = "Succes in Callback";
                        console.log(response.statusText);
                    }, function (data){
                        response.statusText = "Error in Callback";
                        console.log(data);
                });
        };
    });
