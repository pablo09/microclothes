(function () {
    'use strict';
    angular.module('uiApp.items').directive('itemDetails', ['$state',
        itemDetails]);

    function itemDetails($state) {
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
            vm.shoeDetails = {};
            vm.selectedColor = null;
            vm.selectedSize = null;

            vm.chooseColor = function(color) {
                vm.selectedColor = color;
                vm.availableSizes = getAvailableSizes(vm.shoeDetails);
            };

            function getAvailableColors(data) {
                //TODO obsluga w przypadku braku towaru
                return Object.keys(data.specimens);
            }

            function getAvailableSizes(data) {
                return Object.keys(data.specimens[vm.selectedColor]);
            }


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