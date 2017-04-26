(function () {
    'use strict';

    function UserService(ApiService, RestService, $cookies) {

        function getUserInfo() {
            var data = $cookies.get('access_token');
            return RestService.makeMicroCall(ApiService.getUserInfoURL(), 'POST', data);
        }

        return {
            getUserInfo: getUserInfo
        };
    }

    UserService.$inject = ['ApiService', 'RestService', '$cookies'];
    angular.module('uiApp.common').service('UserService', UserService);
})();