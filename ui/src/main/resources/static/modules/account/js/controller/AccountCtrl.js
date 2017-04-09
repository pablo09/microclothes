(function () {
    'use strict';
    function AccountCtrl($log, $http, $state, AccountService) {
        var vm = this;
        vm.cart = {};
        vm.favourites = {};
        vm.total = 0;

        vm.removeFromCart = function(itemId) {
            AccountService.removeFromCart(itemId);
            vm.cart = removeFromCart(itemId);
        };

        vm.removeFromFavourites = function(itemId) {
            AccountService.removeFromFavourites();
            vm.favourites = removeFromFavourites(itemId);
        };

        vm.buy = function() {
            AccountService.buy();
        }

        function removeFromCart(itemId) {
            return _.remove(vm.cart, {id: itemId});
        }

        function removeFromFavourites(itemId) {
            return _.remove(vm.favourites, {id: itemId});
        }

        function countTotal() {
            //TODO Refactor using Lodash
            var numberPattern = /\d+/g;
            var sum = 0;

            angular.forEach(vm.cart, function(key, value) {
                    var amount = parseInt(key.price.match(numberPattern));
                    sum += amount;
            });

            return sum;
        }

        (function init() {
            vm.cart = AccountService.getCart();
            vm.favourites = AccountService.getFavourites();
            vm.total = countTotal();
        })();
    }

    AccountCtrl.$inject = ['$log', '$http', '$state', 'AccountService'];
    angular
        .module('uiApp.account')
        .controller('AccountCtrl', AccountCtrl);
})();