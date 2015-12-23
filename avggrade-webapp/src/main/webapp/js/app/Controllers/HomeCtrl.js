(function () {
    angular.module('InvoiceApp').controller('HomeCtrl', function ($rootScope, $scope, $controller) {
        angular.extend(this, $controller('BaseCtrl', {$scope: $scope}));
    });
})();