(function () {
    var AppModel = function () {
        self.msg = "";
        self.result = null; // Payment data extraction result
        self.progress = null;
        self.extractionInProgress = true;
        self.link = null;
    };

    AppModel.prototype = {
        reset: function () {
            this.result = [];
        }
    };

    var appDataInstance = new AppModel();

    angular.module('AvgGradeApp')
        .value('appData', appDataInstance);

    angular.module('AvgGradeApp').factory('ExtractionSuccessResult', function () {
        var ExtractionSuccessResult = function () {
            var self = this;
            self.accountNr = null;
            self.total = null;
            self.name = null;
            self.referenceNr = null;
            self.description = null;
        };
        ExtractionSuccessResult.prototype = {};
        return ExtractionSuccessResult;
    });
})();