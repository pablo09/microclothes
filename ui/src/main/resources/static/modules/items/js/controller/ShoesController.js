(function () {
    'use strict';
    function ShoesCtrl($log, $http, $state, ItemService, NotificationService) {
        var vm = this;
        vm.shoes = [];

        (function init() {
            vm.shoes = ItemService.getShoes();
        })();
    }

    ShoesCtrl.$inject = ['$log', '$http', '$state', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ShoesCtrl', ShoesCtrl);
})();