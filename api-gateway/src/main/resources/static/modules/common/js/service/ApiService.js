(function () {
    'use strict';

    angular.module('uiApp.common').service('ApiService', ['$location', function ($location) {
        var vm = this;

        vm.getBaseURL = function () {
            return $location.protocol() + "://" + $location.host() + ":" + $location.port();
        };

        vm.getTokenAccessURL = function () {
            return vm.getBaseURL() + '/uaa/oauth/token';
        };

        vm.getShoesURL = function() {
            return vm.getBaseURL() + '/api/shoes';
        };

        vm.getClothesURL = function () {
            return vm.getBaseURL() + '/api/clothes';
        };

        vm.getAccountURL = function() {
            return vm.getBaseURL() + '/api/account';
        };

        vm.getOrderURL = function () {
            return vm.getBaseURL() + '/api/order';
        };
        
        vm.getUserInfoURL = function() {
            return vm.getBaseURL() + '/me';
        };

    }]);
})();