(function () {
    'use strict';

    function AccountService($http, ApiService, NotificationService) {

        function addToCart(itemId) {
            NotificationService.successfulOperation('account.cart.added');
        }

        function addToFavourites(itemId) {
            NotificationService.successfulOperation('account.favourites.added');
        }

        function removeFromCart(itemId) {
            NotificationService.successfulOperation('account.cart.removed');
        }

        function removeFromFavourites(itemId) {
            NotificationService.successfulOperation('account.favourites.removed');
        }



        function getCart() {
            return [
                {
                    id: 1,
                    name: 'Some shoes',
                    size: '44.5',
                    price: '240 PLN'
                },
                {
                    id: 2,
                    name: 'Some clothes',
                    size: 'L',
                    price: '310 PLN'
                }
            ];
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
            removeFromFavourites: removeFromFavourites
        };
    }

    AccountService.$inject = ['$http', 'ApiService','NotificationService'];
    angular.module('uiApp.account').service('AccountService', AccountService);
})();