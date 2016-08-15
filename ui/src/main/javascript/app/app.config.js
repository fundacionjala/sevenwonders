angular.module("sevenWonder")
    .config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            //      $locationProvider.hashPrefix('!');

            $routeProvider
                .when('/login', {
                    template: '<login></login>'
                })
                .when('/lobby', {
                    template: '<lobby></lobby>'
                })
                .otherwise('/login');
        }
    ])
    .config(['RestangularProvider', function(RestangularProvider) {
        RestangularProvider.setBaseUrl('http://demo1701066.mockable.io');
    }]);