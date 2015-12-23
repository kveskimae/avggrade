(function () {
    angular.module('AvgGradeApp').controller('HomeCtrl', function ($rootScope, $scope, $controller) {
        angular.extend(this, $controller('BaseCtrl', {$scope: $scope}));
    });
})();