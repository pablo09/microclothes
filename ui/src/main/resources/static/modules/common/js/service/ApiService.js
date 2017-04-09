(function () {
    'use strict';

    angular.module('uiApp.common').service('ApiService', function () {
        var vm = this;

        vm.getBaseApiURL = function () {
            return '/api';
        };

        vm.getTokenAccessURL = function () {
            return 'http://localhost:8765/uaa/oauth/token';
        };

        vm.getShoesURL = function() {
            return 'http://localhost:8765/api/shoes';
        };

        vm.getClothesURL = function () {
            return 'http://localhost:8765/api/clothes';
        }


    });
})();