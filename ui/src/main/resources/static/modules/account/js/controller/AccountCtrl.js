(function () {
    'use strict';
    function AccountCtrl($log, $http, $state, AccountService, NotificationService) {
        var vm = this;
        vm.cart = {};
        vm.cart.items = [];
        vm.favourites = {};
        vm.total = 0;

        vm.removeFromCart = function(itemId) {
            AccountService.removeFromCart(itemId).then(function(response) {
                removeFromCart(itemId);
                NotificationService.successfulOperation();
            }, function(error) {
                NotificationService.operationFailed();
            });
            ;
        };

        vm.removeFromFavourites = function(itemId) {
            AccountService.removeFromFavourites();
            vm.favourites = removeFromFavourites(itemId);
        };

        vm.buy = function() {
            AccountService.buy();
        }

        function removeFromCart(itemId) {
            console.log(vm.cart.items);
            return _.remove(vm.cart.items, {specimenId: itemId});
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

    AccountCtrl.$inject = ['$log', '$http', '$state', 'AccountService', 'NotificationService'];
    angular
        .module('uiApp.account')
        .controller('AccountCtrl', AccountCtrl);
})();