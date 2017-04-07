(function () {
    'use strict';

    function ItemService($http, ApiService, RestService, NotificationService) {

        function getClothes() {
            return [{
                id: 1,
                name: 'Clothes'
            }];
        }

        function getShoes() {
            RestService.makeMicroCall(ApiService.getShoesURL()).then(function(response) {
               return response;
            }, function(error) {
                NotificationService.failedOperation();
                return null;
            });
        }

        return {
            getClothes: getClothes,
            getShoes: getShoes
        };
    }

    ItemService.$inject = ['$http', 'ApiService', 'RestService', 'NotificationService'];
    angular.module('uiApp.items').service('ItemService', ItemService);
})();