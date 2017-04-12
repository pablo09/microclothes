(function () {
    'use strict';

    function OrderService($http, $rootScope, ApiService, NotificationService, RestService) {

        function finalizeOrder(items) {
            var ids = _.map(items, 'specimenId');
            var data = {};
            data.specimenIds = ids;

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

    OrderService.$inject = ['$http', '$rootScope', 'ApiService', 'NotificationService', 'RestService'];
    angular.module('uiApp.account').service('OrderService', OrderService);
})();