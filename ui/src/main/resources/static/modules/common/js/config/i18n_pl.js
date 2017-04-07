/* global angular */
(function () {
'use strict';

    angular.module('uiApp.common').config(['$translateProvider', function ($translateProvider) {

        $translateProvider.translations('pl', {

            menu: {
                home: "Strona główna",
                clothes: "Odzież",
                shoes: "Obuwie"
            },
            home: {
                welcome: 'Witaj na MicroClothes!',
                info: 'Możesz tutaj zamówić najnowsze i najmodniejsze ubrania i buty.'
            },
            footer: {
                info: 'MicroClothes 2017 Paweł Żeszko'
            },
            login: {
                title: 'Zaloguj się',
                username: 'Nazwa użytkownika',
                password: 'Haslo'
            },
            common: {
                yes: 'Tak',
                no: 'Nie',
                login: 'Zaloguj',
                register: 'Załóż konto',
                logout: 'Wyloguj',
                cancel: 'Anuluj',
                hello: 'Witaj',
                message: {
                    error: 'Wystąpił błąd podczas próby wykonania operacji',
                    success: 'Operacja zakończona sukcesem',
                    fail: 'Operacja zakończona niepowodzeniem'
                }
            }
        });

        $translateProvider.preferredLanguage('pl');
        $translateProvider.useSanitizeValueStrategy('escape');
 }]);
})();
