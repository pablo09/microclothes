(function () {
    'use strict';

    angular.module('uiApp.common').config([
        '$stateProvider',
        function ($stateProvider) {

            $stateProvider
                .state('site', {
                    'abstract': true,
                    views: {
                        'navbar@': {
                            templateUrl: 'modules/menu/templates/navbar.html'
                        }
                    }
                });
        }
    ]);
})();
