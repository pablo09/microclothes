(function () {
    'use strict';

    function ItemService($http, ApiService, RestService) {

        function getClothes() {
          return RestService.makeMicroCall(ApiService.getClothesURL());
        }

        function getShoes() {
            return RestService.makeMicroCall(ApiService.getShoesURL());
        }

        function getShoesDetails(shoeId) {
            return RestService.makeMicroCall(ApiService.getShoesURL() + "/" + shoeId);
        }

        function getClothesDetails(clothesId) {
            return RestService.makeMicroCall(ApiService.getClothesURL() + "/" + clothesId);
        }

        return {
            getClothes: getClothes,
            getShoes: getShoes,
            getShoesDetails: getShoesDetails,
            getClothesDetails: getClothesDetails
        };
    }

    ItemService.$inject = ['$http', 'ApiService'];
    angular.module('uiApp.items').service('ItemService', ItemService);
})();