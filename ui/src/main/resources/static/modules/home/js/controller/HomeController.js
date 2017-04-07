(function () {
    'use strict';
    function HomeCtrl($log, $http, $state, RestService) {
        var vm = this;
    }



    HomeCtrl.$inject = ['$log', '$http', '$state', 'RestService'];
    angular
        .module('uiApp.home')
        .controller('HomeCtrl', HomeCtrl);
})();