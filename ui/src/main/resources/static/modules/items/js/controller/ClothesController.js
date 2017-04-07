(function () {
    'use strict';
    function ClothesCtrl($log, $http, $state, ItemService) {
        var vm = this;
        vm.clothes = [];

        (function init() {
            vm.clothes = ItemService.getClothes();
        })();
    }

    ClothesCtrl.$inject = ['$log', '$http', '$state', 'ItemService'];
    angular
        .module('uiApp.items')
        .controller('ClothesCtrl', ClothesCtrl);
})();