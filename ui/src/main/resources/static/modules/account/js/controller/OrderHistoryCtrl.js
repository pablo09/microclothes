(function () {
    'use strict';
    function OrderHistoryCtrl($log, $http, $state, AccountService) {
        var vm = this;

        (function init() {

        })();
    }

    OrderHistoryCtrl.$inject = ['$log', '$http', '$state', 'OrderHistoryService'];
    angular
        .module('uiApp.account')
        .controller('OrderHistoryCtrl', OrderHistoryCtrl);
})();