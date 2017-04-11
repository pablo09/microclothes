(function () {
    'use strict';

    function AccountService($http, $rootScope, ApiService, NotificationService, RestService) {

        function addToCart(itemId) {
            return RestService.makeMicroCall(ApiService.getAccountURL() + '/' + itemId, 'PUT');
        }

        function removeFromCart(itemId) {
            return RestService.makeMicroCall(ApiService.getAccountURL() + '/' + itemId, 'DELETE');
        }

        function addToFavourites(itemId) {
            NotificationService.successfulOperation('account.favourites.added');
        }

        function removeFromFavourites(itemId) {
            NotificationService.successfulOperation('account.favourites.removed');
        }

        function buy() {
            NotificationService.successfulOperation('account.cart.forwarded');
        }

        function getCart() {
            return RestService.makeMicroCall( ApiService.getAccountURL());
        }

        function getFavourites() {
            return [
                {
                    id: 3,
                    name: 'Some Favourites shoes'
                },
                {
                    id: 4,
                    name: 'Some favourite clothes'
                }
            ];
        }

        return {
            addToCart: addToCart,
            addToFavourites: addToFavourites,
            getCart: getCart,
            getFavourites: getFavourites,
            removeFromCart: removeFromCart,
            removeFromFavourites: removeFromFavourites,
            buy: buy
        };
    }

    AccountService.$inject = ['$http', '$rootScope', 'ApiService','NotificationService', 'RestService'];
    angular.module('uiApp.account').service('AccountService', AccountService);
})();