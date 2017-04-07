(function () {
    'use strict';
    function ShoesCtrl($log, $http, $state, ItemService, NotificationService) {
        var vm = this;
        vm.shoes = [];
        vm.shoesLoaded = false;

        (function init() {
            ItemService.getShoes().then(function(response) {
                vm.shoes = response.data;
                vm.shoesLoaded = true;
            }, function(error) {
                NotificationService.failedOperation();
            });
        })();
    }

    ShoesCtrl.$inject = ['$log', '$http', '$state', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ShoesCtrl', ShoesCtrl);
})();