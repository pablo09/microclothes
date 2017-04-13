(function () {
    'use strict';

    function OrderService(ApiService, RestService) {

        function finalizeOrder(items) {
            var data = {};
            data.specimenIds = _.map(items, 'specimenId');

            return RestService.makeMicroCall(ApiService.getOrderURL() + '/finalize', 'POST', data);
        }

        function orderHistory() {
            return RestService.makeMicroCall(ApiService.getOrderURL());
        }

        return {
            finalizeOrder: finalizeOrder,
            orderHistory: orderHistory
        };
    }

    OrderService.$inject = ['ApiService', 'RestService'];
    angular.module('uiApp.account').service('OrderService', OrderService);
})();