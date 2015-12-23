(function () {
    angular.module('AvgGradeApp').controller('BaseCtrl',
        function ($scope, appData) {
            $scope.appData = appData;
            $scope.resetMsg = function () {
                $scope.appData.msg = "";
            };
            $scope.resetResult = function () {
                $scope.appData.result = null;
            };
            $scope.resetProgress = function () {
                $scope.appData.progress = 0;
            };
            $scope.resetExtractionInProgress = function () {
                $scope.appData.extractionInProgress = false;
            };
            $scope.resetLink = function () {
                $scope.appData.link = null;
            };
            $scope.reset = function () {
                $scope.resetMsg();
                $scope.resetResult();
                $scope.resetProgress();
                $scope.resetLink();
                // $scope.resetExtractionInProgress();
            };
        });
})();