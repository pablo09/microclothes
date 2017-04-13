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
        ]);

})();