(function () {
    angular.module('InvoiceApp', 
    		['ngResource', 'ngRoute', 'ngFileUpload', 'ngCookies', 'ngSanitize', 'ui.bootstrap', 'ui.router', 'ui.select', 'angularSpinner'])
        .config(function ($stateProvider) {
            $stateProvider
                .state('invoice', {
                    templateUrl: 'js/app/templates/invoice.html',
                    controller: 'InvoiceCtrl',
                    url: '/invoice'
                })
            ;
        })
        .run(function ($rootScope, $state, modelTransformer, events, ExtractionService, ExtractionSuccessResult, appData) {
            $rootScope.$on(events.message._SEND_TO_PROCESS_, function (event, data) {
                var files = data[0];
                if (files.length > 1) {
                    $rootScope.$broadcast(events.message._SEND_TO_PROCESS_FAILED_, ["Ainult 1 fail korraga palun"]);
                } else {
                    ExtractionService.submit(files);
                }
            });
            $rootScope.$on(events.message._SEND_TO_PROCESS_COMPLETE_, function (event, data) {
                var transformedResult = modelTransformer.transform(data[0], ExtractionSuccessResult);
                appData.result = transformedResult;
                appData.extractionInProgress = false;
            });
            $rootScope.$on(events.message._SEND_TO_PROCESS_FAILED_, function (event, data) {
                appData.msg = data[0];
                appData.extractionInProgress = false;
            });
            $rootScope.$on(events.message._FILE_UPLOADED_, function (event, data) {
                appData.extractionInProgress = true;
            });
            $state.go('invoice');
        });
})();