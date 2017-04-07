(function () {
    'use strict';
    function ShoesDetailsCtrl($log, $http, $state, $stateParams, ItemService, NotificationService) {
        var vm = this;
        vm.shoeDetails = {};

        (function init() {
            ItemService.getShoesDetails($stateParams.itemId).then(function(response) {
                vm.shoeDetails = response.data;
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