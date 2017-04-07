(function () {
    'use strict';

    angular.module('uiApp.login').config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

          //  $urlRouterProvider.otherwise('/login');

            $stateProvider
                .state('login', {
                    url: '/login',
                    parent: 'site',
                    views: {
                        'content@': {
                            templateUrl: 'modules/login/templates/login.html'
                        }
                    },
                    resolve: {
                    }
                })
                .state('forbidden', {
                    url: '/forbidden',
                    parent: 'site',
                    views: {
                        'content@': {
                            templateUrl: 'modules/login/templates/forbidden.html'
                        }
                    }
                });
        }
    ]);
})();
