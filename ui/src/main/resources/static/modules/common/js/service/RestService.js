(function () {
    'use strict';

    function RestService($http, $cookies) {

        function makeMicroCall(serviceUrl) {
            var request = {
                method: 'GET',
                url: serviceUrl,
                headers: {
                    "Authorization": "Bearer " + $cookies.get('access_token'),
                    "Content-Type": "application/json"
                }
            };
            return $http(request);
        }

        return {
            makeMicroCall: makeMicroCall
        };
    }

    RestService.$inject = ['$http', '$cookies'];
    angular.module('uiApp.common').service('RestService', RestService);
})();