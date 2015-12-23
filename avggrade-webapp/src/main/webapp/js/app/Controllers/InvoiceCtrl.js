﻿(function () {
    angular.module('InvoiceApp').controller('InvoiceCtrl', function ($http, $timeout, $rootScope, $scope, $controller, $cookies, $cookieStore, events) {
        angular.extend(this, $controller('BaseCtrl', {$scope: $scope}));
        Array.prototype.move = function (old_index, new_index) {
            if (new_index >= this.length) {
                var k = new_index - this.length;
                while ((k--) + 1) {
                    this.push(undefined);
                }
            }
            this.splice(new_index, 0, this.splice(old_index, 1)[0]);
            return this; // for testing purposes
        };
        $scope.upload = function (files) {
            $scope.reset();
            $scope.appData.extractionInProgress = true;
            $rootScope.$broadcast(events.message._SEND_TO_PROCESS_, [files]);
        };
        $scope.selectedBank = {selectedValue: null};
        $scope.updateAccountOrdering = function() {
        	var orderedBanks = $cookieStore.get('orderedBanks');
        	var selectedBankselectedBank = $scope.selectedBank.selectedValue.bankCorpororation;
        	var selectedIdx = orderedBanks.indexOf(selectedBankselectedBank);
        	var minIdx = selectedIdx;
        	angular.forEach($scope.appData.result.accountNr, function(i, idx) {
        		var curIdx = orderedBanks.indexOf(i.bankCorpororation);
        		if (curIdx < minIdx) {
        			minIdx = idx;
        		}
        	});
        	orderedBanks.move(selectedIdx, minIdx);
        	$cookieStore.put('orderedBanks', orderedBanks);
        };
        $rootScope.$on(events.message._SEND_TO_PROCESS_COMPLETE_, function (event, data) {

        });
    });
})();