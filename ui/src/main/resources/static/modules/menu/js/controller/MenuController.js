(function () {
    'use strict';
    function MenuCtrl($log, $http, $state, $rootScope, RestService, LoginService) {
        var vm = this;

        vm.loggedUser = null;

        //TODO Wyniesc do serwisu
        vm.getInfo = function() {
            RestService.makeMicroCall('http://localhost:8765/me').then(function(response) {
                vm.loggedUser = response.data.username;

                $rootScope.loggedUser = vm.loggedUser;
                console.log($rootScope.loggedUser);
            }, function(error) {
                console.log(error);
            });
        };

        vm.isLoggedUser = function() {
            return vm.loggedUser !== null;
        };

        vm.logout = function() {
            LoginService.logout();
            vm.loggedUser = null;
        };

        (function init() {
            vm.getInfo();
        })();

    }



    MenuCtrl.$inject = ['$log', '$http', '$state', '$rootScope', 'RestService', 'LoginService'];
    angular
        .module('uiApp.menu')
        .controller('MenuCtrl', MenuCtrl);
})();