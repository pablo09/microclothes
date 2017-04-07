(function () {
    'use strict';

    angular.module('uiApp.items').config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

            //  $urlRouterProvider.otherwise('/login');

            $stateProvider
                .state('clothes', {
                    url: '/clothes',
                    parent: 'site',
                    views: {
                        'content@': {
                            templateUrl: 'modules/items/templates/clothes.html'
                        }
                    },
                    resolve: {
                    }
                })
                .state('shoes', {
                    url: '/shoes',
                    parent: 'site',
                    views: {
                        'content@': {
                            templateUrl: 'modules/items/templates/shoes.html'
                        }
                    }
                })
                .state('shoesDetails', {
                    url: '/details/:itemId',
                    parent: 'shoes',
                    views: {
                        'content@': {
                            templateUrl: 'modules/items/templates/shoesDetails.html'
                        }
                    },
                    params: {
                        itemId: undefined
                    },
                });
        }
    ]);
})();
