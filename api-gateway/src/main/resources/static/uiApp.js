(function () {
    'use strict';
    angular
        .module('uiApp', [
            'ui.router',
            'pascalprecht.translate',
            'ngSanitize',
            'ngResource',
            'ngAnimate',
            'ngCookies',
            'toastr',
            'uiApp.home',
            'uiApp.login',
            'uiApp.common',
            'uiApp.menu',
            'uiApp.items',
            'uiApp.account'
        ]).config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.interceptors.push('XSRFInterceptor');
    }]);

})();