(function () {
    'use strict';

    function RestService($http, $cookies) {

        function makeMicroCall(serviceUrl, method) {
            if(_.isNil(method)) {
                method = 'GET';
            }
            var request = {
                method: method,
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