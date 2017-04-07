(function () {
    'use strict';
    function MenuCtrl($log, $http, $state, RestService, LoginService) {
        var vm = this;

        vm.loggedUser = null;

        vm.getInfo = function() {
            RestService.makeMicroCall('http://localhost:8765/me').then(function(response) {
                vm.loggedUser = response.data.username;
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



    MenuCtrl.$inject = ['$log', '$http', '$state', 'RestService', 'LoginService'];
    angular
        .module('uiApp.menu')
        .controller('MenuCtrl', MenuCtrl);
})();