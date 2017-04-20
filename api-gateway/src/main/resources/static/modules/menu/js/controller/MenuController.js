(function () {
    'use strict';
    function MenuCtrl($rootScope, LoginService, UserService) {
        var vm = this;

        vm.loggedUser = null;

        vm.getInfo = function() {
            UserService.getUserInfo().then(function(response) {
                vm.loggedUser = response.data.username;
                $rootScope.loggedUser = vm.loggedUser;
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



    MenuCtrl.$inject = ['$rootScope', 'LoginService', 'UserService'];
    angular
        .module('uiApp.menu')
        .controller('MenuCtrl', MenuCtrl);
})();