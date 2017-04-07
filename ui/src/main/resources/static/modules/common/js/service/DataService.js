(function () {
    'use strict';

    function DataService($http, RestService, ApiService) {


        function getOne(dictId) {
            return $http.get(ApiService.getAppSelectionRuleURL() + "/" + dictId);
        }

        function getAllByAppPoolType(appPoolType) {
            return $http.get(ApiService.getAppSelectionRuleURL() + "/appPoolType/" + appPoolType);
        }

        function getAllCriterions() {
            return $http.get(ApiService.getAppSelectionRuleURL() + "/criterions");
        }

        function save(data, dictId) {
            return (angular.isDefined(dictId)) ? update(data) : create(data);
        }

        function create(data) {
            return $http.post(ApiService.getAppSelectionRuleURL(), data);
        }

        function update(data) {
            return $http.put(ApiService.getAppSelectionRuleURL(), data);
        }

        function acceptRule(item, trustee) {
            var dto = {
                ruleId: item.id,
                poolTypeCode: item.poolType.code,
                validFrom: item.validFrom,
                trustee: trustee
            };

            return $http.post(ApiService.getAppSelectionRuleURL() + '/accept', dto);
        }

        function rejectRule(item) {
            return $http.post(ApiService.getAppSelectionRuleURL() + '/reject', {ruleId: item.id});
        }

        return {
            getAll: getAll,
            getOne: getOne,
            getAllCriterions: getAllCriterions,
            getAllByAppPoolType: getAllByAppPoolType,
            save: save,
            create: create,
            update: update,
            acceptRule: acceptRule,
            rejectRule: rejectRule
        };
    }

    DataService.$inject = ['$http', 'ApiService', 'RestService'];
    angular.module('uiApp.common').service('DataService', DataService);
})();