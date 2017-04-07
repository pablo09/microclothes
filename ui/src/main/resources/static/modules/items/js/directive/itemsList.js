(function () {
    'use strict';
    angular.module('uiApp.items').directive('itemsList',
        itemsList);

    function itemsList() {
        return {
            restrict : 'E',
            templateUrl : 'modules/items/templates/items.html',
            scope: {
                data: "="
            },
            controller : controller,
            controllerAs : 'vm'
        };

        function controller($scope) {
            var vm = this;

            vm.maxItemsInRow = 3;
            vm.totalItemsRange = [];

            function getNumberOfRows(items) {
                return Math.ceil(items.length / 3);
            }

            function createTotalItemsRange(items) {
                var range = [];
                var rows = getNumberOfRows(items);

                for(var i=0;i<rows;i++) {
                    range.push(i);
                }

                vm.totalItemsRange = range;
            };

            vm.getItemsInRow = function(row) {
                var items = [];

                for(var i = vm.maxItemsInRow * row; i < vm.maxItemsInRow * (row + 1); i++) {
                    var item = vm.items[i];
                    if(item !== undefined) {
                        items.push(vm.items[i]);
                    }
                }

                return items;
            };

            (function init() {
                vm.items = $scope.data;
                createTotalItemsRange(vm.items);
            })();

        }
    }

})();