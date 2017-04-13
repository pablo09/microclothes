(function () {
    'use strict';

    function RestService($http, $cookies) {

        function makeMicroCall(serviceUrl, method, data) {
            if(_.isNil(method)) {
                method = 'GET';
            }
            var request = {
                method: method,
                url: serviceUrl,
                headers: {
                    "Authorization": "Bearer " + $cookies.get('access_token'),
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(data)
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