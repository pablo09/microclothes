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

        function makeMicroPostCall(serviceUrl, data) {
            var request = {
                method: 'POST',
                url: serviceUrl,
                headers: {
                    "Authorization": "Bearer " + $cookies.get('access_token'),
                    "Content-Type": "application/json"
                },
                data: data
            };
            return $http(request);
        }

        return {
            makeMicroCall: makeMicroCall,
            makeMicroPostCall: makeMicroPostCall
        };
    }

    RestService.$inject = ['$http', '$cookies'];
    angular.module('uiApp.common').service('RestService', RestService);
})();