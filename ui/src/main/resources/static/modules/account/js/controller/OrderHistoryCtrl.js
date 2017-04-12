(function () {
    'use strict';
    function OrderHistoryCtrl($log, $http, $state, OrderService) {
        var vm = this;

        (function init() {
            OrderService.orderHistory();
        })();
    }

    OrderHistoryCtrl.$inject = ['$log', '$http', '$state', 'OrderService'];
    angular
        .module('uiApp.account')
        .controller('OrderHistoryCtrl', OrderHistoryCtrl);
})();