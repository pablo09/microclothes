(function () {
    'use strict';

    angular.module('uiApp.home').config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

            $urlRouterProvider.otherwise('/home');

            $stateProvider
                .state('home', {
                    parent: 'site',
                    url: '/home',
                    views: {
                        'content@': {
                            templateUrl: 'modules/home/templates/home.html'
                        }
                    },
                    resolve: {
                    }
                });
        }
    ]);
})();
