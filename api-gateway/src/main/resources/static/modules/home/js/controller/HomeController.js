(function () {
    'use strict';
    function HomeCtrl() {
        //TODO
    }

    HomeCtrl.$inject = ['$log', '$http', '$state', 'RestService'];
    angular
        .module('uiApp.home')
        .controller('HomeCtrl', HomeCtrl);
})();