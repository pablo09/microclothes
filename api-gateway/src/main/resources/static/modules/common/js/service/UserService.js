(function () {
    'use strict';

    function UserService(ApiService, RestService) {

        function getUserInfo() {
            return RestService.makeMicroCall(ApiService.getUserInfoURL());
        }

        return {
            getUserInfo: getUserInfo
        };
    }

    UserService.$inject = ['ApiService', 'RestService'];
    angular.module('uiApp.common').service('UserService', UserService);
})();