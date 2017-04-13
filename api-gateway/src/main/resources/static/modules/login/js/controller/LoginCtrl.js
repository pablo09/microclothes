(function () {
    'use strict';
    function LoginCtrl($log, $http, $state, LoginService) {
        var vm = this;

        vm.username = '';
        vm.password ='';

        vm.login = function() {
            LoginService.login(vm.username, vm.password);
        }

    }

    LoginCtrl.$inject = ['$log', '$http', '$state', 'LoginService'];
    angular
        .module('uiApp.login')
        .controller('LoginCtrl', LoginCtrl);
})();