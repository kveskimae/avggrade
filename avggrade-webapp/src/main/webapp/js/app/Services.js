(function () {
    angular.module('AvgGradeApp').factory('ExtractionService', function ExtractionServiceFactory($rootScope, events, appData, Upload) {
        return {
            submit: function (files) {
                if (files && files.length) {
                    for (var i = 0; i < files.length; i++) {
                        var file = files[i];
                        Upload.upload({
                            url: 'rest/upload',
                            fields: {
                                // e.g. 'username': "abcd"
                            },
                            file: file
                        }).progress(function (evt) {
                            var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                            appData.progress = progressPercentage;
                            if (angular.equals(evt.loaded, evt.total)) {
                                // console.log("equals");
                                appData.progress = null;
                                $rootScope.$broadcast(events.message._FILE_UPLOADED_, []);
                            }
                        }).success(function (data, status, headers, config) {
                            $rootScope.$broadcast(events.message._SEND_TO_PROCESS_COMPLETE_, [data]);
                        }).error(function (data, status, headers, config) {
                        	var errorAll = "";
                        	if (!!data.errorMsg) {
                        		errorAll = data.errorMsg;
                        	}
                        	errorAll += " ("+status+")";
                            $rootScope.$broadcast(events.message._SEND_TO_PROCESS_FAILED_, [errorAll]);
                        });
                    }
                }
            }
        };
    });
})();


