(function () {
    'use strict';

    angular.module('uiApp.account').config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {

            $stateProvider
                .state('account', {
                    url: '/account',
                    parent: 'site', //TODO Co sie stanie jak to opuszcze?
                    views: {
                        'content@': {
                            templateUrl: 'modules/account/templates/account.html'
                        }
                    },
                    resolve: {
                    }
                })
                .state('history', {
                    url: '/history',
                    parent: 'account',
                    views: {
                        'content@': {
                            templateUrl: 'modules/account/templates/orderHistory.html'
                        }
                    },
                    resolve: {
                    }
                });
        }
    ]);
})();
