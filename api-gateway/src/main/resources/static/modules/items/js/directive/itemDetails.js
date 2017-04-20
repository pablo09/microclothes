(function () {
    'use strict';
    angular.module('uiApp.items').directive('itemDetails', ['$state', 'AccountService', 'NotificationService',
        itemDetails]);

    function itemDetails($state, AccountService, NotificationService) {
        return {
            restrict : 'E',
            templateUrl : 'modules/items/templates/itemDetails.html',
            scope: {
                data: "=",
                type: '@type'
            },
            controller : controller,
            controllerAs : 'vm'
        };

        function controller($scope) {
            var vm = this;
            vm.itemDetails = {};
            vm.selectedColor = null;
            vm.selectedSize = {};

            vm.chooseColor = function(color) {
                vm.selectedColor = color;
                vm.availableSizes = getAvailableSizes(vm.itemDetails);
                console.log(vm.availableSizes);
                vm.selectedSize = vm.availableSizes[0];
            };

            vm.isAvailableForPurchase = function() {
                return vm.itemDetails.specimens[vm.selectedColor][vm.selectedSize.size]['amount'] > 0;
            };


            function getAvailableColors(data) {
                //TODO obsluga w przypadku braku towaru
                return Object.keys(data.specimens);
            }

            function getAvailableSizes(data) {
                var sizes = [];
                angular.forEach(data.specimens[vm.selectedColor], function(key, value) {
                   sizes.push({
                       id: key.id,
                       size: value
                   });
                });
                return sizes;
            }

            vm.addToCart = function() {
                AccountService.addToCart(vm.selectedSize.id).then(function() {
                    NotificationService.successfulOperation();
                }, function() {
                    NotificationService.failedOperation();
                });
            };

            vm.addToFavourites = function() {
                AccountService.addToFavourites(vm.selectedSize.id);
            };

            (function init() {
                vm.itemDetails = $scope.data;
                vm.type = $scope.type;
                vm.availableColors = getAvailableColors(vm.itemDetails);
                vm.selectedColor = vm.availableColors[0];
                vm.availableSizes = getAvailableSizes(vm.itemDetails);
                vm.selectedSize = vm.availableSizes[0];
            })();
        }
    }

})();