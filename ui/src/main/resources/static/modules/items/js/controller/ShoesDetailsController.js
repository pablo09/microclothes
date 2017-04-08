(function () {
    'use strict';
    function ShoesDetailsCtrl($log, $http, $state, $stateParams, ItemService, NotificationService) {
        var vm = this;
        vm.shoeDetails = {};
        vm.selectedColor = null;
        vm.selectedSize = null;

        vm.chooseColor = function(color) {
            vm.selectedColor = color;
            vm.availableSizes = getAvailableSizes(vm.shoeDetails);
        };

        function getAvailableColors(data) {
           return Object.keys(data.specimens);
        }

        function getAvailableSizes(data) {
            return Object.keys(data.specimens[vm.selectedColor]);
        }


        (function init() {
            ItemService.getShoesDetails($stateParams.itemId).then(function(response) {
                vm.shoeDetails = response.data;
                vm.availableColors = getAvailableColors(response.data);
                vm.selectedColor = vm.availableColors[0];
                vm.availableSizes = getAvailableSizes(response.data);
                vm.selectedSize = vm.availableSizes[0];
            }, function(error) {
                NotificationService.failedOperation();
            });


        })();
    }

    ShoesDetailsCtrl.$inject = ['$log', '$http', '$state', '$stateParams', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ShoesDetailsCtrl', ShoesDetailsCtrl);
})();