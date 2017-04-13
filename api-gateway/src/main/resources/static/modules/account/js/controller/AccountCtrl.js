(function () {
    'use strict';
    function AccountCtrl($log, $http, $state, AccountService, NotificationService, OrderService) {
        var vm = this;
        vm.cart = {};
        vm.cart.items = [];
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

        vm.finalizeOrder = function() {
            OrderService.finalizeOrder(vm.cart.items).then(function(response) {
                $state.reload();
                NotificationService.successfulOperation();
            }, function(error) {
                NotificationService.failedOperation();
            })
        };

        function removeFromCart(itemId) {
            return _.remove(vm.cart.items, {specimenId: itemId});
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
                vm.total = countTotal();

            }, function(error) {});


        })();
    }

    AccountCtrl.$inject = ['$log', '$http', '$state', 'AccountService', 'NotificationService', 'OrderService'];
    angular
        .module('uiApp.account')
        .controller('AccountCtrl', AccountCtrl);
})();