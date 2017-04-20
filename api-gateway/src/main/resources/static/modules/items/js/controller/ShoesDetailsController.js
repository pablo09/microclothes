(function () {
    'use strict';
    function ShoesDetailsCtrl($log, $http, $state, $stateParams, ItemService, NotificationService) {
        var vm = this;
        vm.shoesDetails = {};
        vm.shoesLoaded = false;

        (function init() {
            ItemService.getShoesDetails($stateParams.itemId).then(function(response) {
                vm.shoesDetails = response.data;
                vm.shoesLoaded = true;
            }, function() {
                NotificationService.failedOperation();
            });


        })();
    }

    ShoesDetailsCtrl.$inject = ['$log', '$http', '$state', '$stateParams', 'ItemService', 'NotificationService'];
    angular
        .module('uiApp.items')
        .controller('ShoesDetailsCtrl', ShoesDetailsCtrl);
})();