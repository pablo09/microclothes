(function () {
    'use strict';

    angular.module('uiApp.common').service('ApiService', function () {
        var vm = this;

        vm.getBaseApiURL = function () {
            return 'http://localhost:8765';
        };

        vm.getTokenAccessURL = function () {
            return vm.getBaseApiURL() + '/oauth/token';
        };

        vm.getShoesURL = function() {
            return vm.getBaseApiURL() + '/api/shoes';
        };

        vm.getClothesURL = function () {
            return vm.getBaseApiURL() + '/api/clothes';
        };

        vm.getAccountURL = function() {
            return vm.getBaseApiURL() + '/api/account';
        };

        vm.getOrderURL = function () {
            return vm.getBaseApiURL() + '/api/order';
        }

    });
})();