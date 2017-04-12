(function () {
    'use strict';
    function OrderHistoryCtrl($log, $http, $state, OrderService, NotificationService) {
        var vm = this;

        vm.history = {};
        vm.currency = 'PLN';

        vm.getTotalPrice = function(history) {
            var sum = 0;

            _.forEach(history.items, function(item) {
               sum += item.price.amount;
            });

            return sum;
        };

        (function init() {
             OrderService.orderHistory().then(function(response) {
                 console.log(response);
                 vm.history = response.data;
             }, function(error) {
                 NotificationService.failedOperation();
             })
        })();
    }

    OrderHistoryCtrl.$inject = ['$log', '$http', '$state', 'OrderService', 'NotificationService'];
    angular
        .module('uiApp.account')
        .controller('OrderHistoryCtrl', OrderHistoryCtrl);
})();