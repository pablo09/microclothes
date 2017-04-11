(function () {
    'use strict';
    function AccountCtrl($log, $http, $state, AccountService) {
        var vm = this;
        vm.cart = {};
        vm.cart.items = [];
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
            var sum = 0;

            angular.forEach(vm.cart.items, function(key, value) {
                    sum += key.price.amount;
            });

            return sum;
        }

        (function init() {
            AccountService.getCart().then(function(response) {
                vm.cart = response.data;
                vm.favourites = AccountService.getFavourites();
                vm.total = countTotal();

            }, function(error) {});


        })();
    }

    AccountCtrl.$inject = ['$log', '$http', '$state', 'AccountService'];
    angular
        .module('uiApp.account')
        .controller('AccountCtrl', AccountCtrl);
})();