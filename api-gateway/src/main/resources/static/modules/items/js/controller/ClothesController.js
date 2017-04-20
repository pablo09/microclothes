(function () {
    'use strict';
    function ClothesCtrl($log, $http, $state, ItemService, NotificationService) {
        var vm = this;
        vm.clothes = [];
        vm.clothesLoaded = null;

        (function init() {
            ItemService.getClothes().then(function(response) {
                vm.clothes = response.data;
                vm.clothesLoaded = true;
            }, function() {
                NotificationService.failedOperation();
            });

        })();
    }

    ClothesCtrl.$inject = ['$log', '$http', '$state', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ClothesCtrl', ClothesCtrl);
})();