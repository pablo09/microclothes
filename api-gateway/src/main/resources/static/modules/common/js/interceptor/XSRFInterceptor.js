angular.module('uiApp.common')
    .factory('XSRFInterceptor', ['$cookies', function ($cookies) {

        var XSRFInterceptor = {

            request: function(config) {

                var token = $cookies.get('XSRF-TOKEN');
                if (token) {
                    config.headers['X-XSRF-TOKEN'] = token;
                }

                return config;
            }
        };
        return XSRFInterceptor;
    }]);
