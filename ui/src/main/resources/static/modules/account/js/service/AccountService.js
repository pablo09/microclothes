(function () {
    'use strict';

    function AccountService (ApiService, RestService) {

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

    AccountService.$inject = ['ApiService', 'RestService'];
    angular.module('uiApp.account').service('AccountService', AccountService);
})();