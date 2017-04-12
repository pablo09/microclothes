(function () {
    'use strict';

    function AccountService($http, $rootScope, ApiService, NotificationService, RestService) {

        function addToCart(itemId) {
            return RestService.makeMicroCall(ApiService.getAccountURL() + '/' + itemId, 'PUT');
        }

        function removeFromCart(itemId) {
            return RestService.makeMicroCall(ApiService.getAccountURL() + '/' + itemId, 'DELETE');
        }

        function getCart() {
            return RestService.makeMicroCall( ApiService.getAccountURL());
        }

        return {
            addToCart: addToCart,
            getCart: getCart,
            removeFromCart: removeFromCart
        };
    }

    AccountService.$inject = ['$http', '$rootScope', 'ApiService','NotificationService', 'RestService'];
    angular.module('uiApp.account').service('AccountService', AccountService);
})();