(function () {
    'use strict';
    function ClothesDetailsCtrl($log, $http, $state, $stateParams, ItemService, NotificationService) {
        var vm = this;
        vm.clothesDetails = {};
        vm.clothesLoaded = false;

        (function init() {
            ItemService.getClothesDetails($stateParams.itemId).then(function(response) {
                vm.clothesDetails = response.data;
                vm.clothesLoaded = true;
            }, function() {
                NotificationService.failedOperation();
            });


        })();
    }

    ClothesDetailsCtrl.$inject = ['$log', '$http', '$state', '$stateParams', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ClothesDetailsCtrl', ClothesDetailsCtrl);
})();