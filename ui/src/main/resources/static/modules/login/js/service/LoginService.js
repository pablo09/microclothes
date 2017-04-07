(function () {
    'use strict';

    function LoginService($http, ApiService, $httpParamSerializer, $cookies, $state, NotificationService) {

        function login(username, password) {
            getAccessToken(username, password).then(function(response) {
                $cookies.put('access_token', response.data.access_token);
                $state.go("home", {}, {reload: true});
                NotificationService.successfulOperation();
            }, function(error) {
                NotificationService.failedOperation();
            });
        }

        function logout() {
            $cookies.put('access_token', null);
            $state.go("home", {}, {reload: true});
            NotificationService.successfulOperation();
        }

        function getAccessToken(username, password) {
            var data = {
                grant_type:"password",
                username: username,
                password: password,
                client_id: "acme"
            };
            var request = {
                method: 'POST',
                url: ApiService.getTokenAccessURL(),
                headers: {
                    "Authorization": "Basic " + btoa("acme:acmesecret"),
                    "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: $httpParamSerializer(data)
            };
            return $http(request);
        }

        return {
            login: login,
            logout: logout
        };
    }

    LoginService.$inject = ['$http', 'ApiService', '$httpParamSerializer', '$cookies', '$state', 'NotificationService'];
    angular.module('uiApp.login').service('LoginService', LoginService);
})();