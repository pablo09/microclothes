(function () {
    'use strict';

    function NotificationService(toastr, $translate) {
        var ERROR_MSG = 'common.message.error';
        var SUCCESS_MSG = 'common.message.success';
        var FAILED_MSG = 'common.message.fail';


        function successfulOperation(msg) {
            if(msg != null && msg != undefined) {
                toastr.success(getMessage(msg));
            } else {
                toastr.success(getMessage(SUCCESS_MSG));
            }
        }

        function failedOperation() {
            toastr.warning(getMessage(FAILED_MSG));
        }

        function failedOperationMsg(msg){
            toastr.warning(getMessage(msg));
        }

        function error(errorCode) {
            toastr.error(getErrorMessage(errorCode));
        }

        function getErrorMessage(errorCode) {
            return getMessage(ERROR_MSG);
        }

        function getMessage(key) {
            return $translate.instant(key);
        }

        return({
            successfulOperation: successfulOperation,
            failedOperation: failedOperation,
            error: error,
            failedOperationMsg: failedOperationMsg
        });
    }

    NotificationService.$inject = ['toastr', '$translate'];

    angular
        .module('uiApp.common')
        .factory('NotificationService', NotificationService);
})();