(function () {
    'use strict';

    function ItemService($http, ApiService, RestService, NotificationService) {

        function getClothes() {
          return RestService.makeMicroCall(ApiService.getClothesURL());
        }

        function getShoes() {
            return RestService.makeMicroCall(ApiService.getShoesURL());
        }

        function getShoesDetails(shoeId) {
            return RestService.makeMicroCall(ApiService.getShoesURL() + "/" + shoeId);
        }



        return {
            getClothes: getClothes,
            getShoes: getShoes,
            getShoesDetails: getShoesDetails
        };
    }

    ItemService.$inject = ['$http', 'ApiService', 'RestService', 'NotificationService'];
    angular.module('uiApp.items').service('ItemService', ItemService);
})();