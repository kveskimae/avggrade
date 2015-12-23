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
        $scope.presentPaymentLink = function () {
        	var accountNr = window.encodeURIComponent($scope.selectedBank.selectedValue.accountNr);
        	var total = window.encodeURIComponent($scope.appData.result.total);
        	var name = window.encodeURIComponent($scope.appData.result.name);
        	var referenceNr = window.encodeURIComponent($scope.appData.result.referenceNr);
        	var description = window.encodeURIComponent($scope.appData.result.description);
        	var link = 
        		"http://www.mybank.ee/banklink?"+
        			"VK_ACC="+accountNr+
        			"&VK_AMOUNT=" + total +
        			"&VK_NAME=" +  name +
        			"&VK_REF=" + referenceNr +
        			"&VK_MSG=" + description;
            $scope.reset();
            $scope.appData.link = link;
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
        	if ($scope.appData.result.accountNr.length > 0) {
            	var orderedBanks = $cookieStore.get('orderedBanks');
                if (!orderedBanks) {
                	orderedBanks = [];
                	angular.forEach($scope.appData.result.accountNr, function(i) {
                	  this.push(i.bankCorpororation);
                	}, orderedBanks);
                } else {
                	angular.forEach($scope.appData.result.accountNr, function(i) {
                		if (this.indexOf(i.bankCorpororation) == -1) {
                      	  this.push(i.bankCorpororation);	
                		}
                	}, orderedBanks);
                }
            	$cookieStore.put('orderedBanks', orderedBanks);
            	var lastIdx = -1;
            	angular.forEach(orderedBanks, function(val, idx) {
            		lastIdx = idx + 1;
                	angular.forEach($scope.appData.result.accountNr, function(i) {
                		if (i.bankCorpororation == val) {
                      	  i.sortIdx = idx;	
                		}
                	});
            	});
            	angular.forEach($scope.appData.result.accountNr, function(i) {
            		if (i.sortIdx === null) {
            			i.sortIdx = lastIdx;	
            		}
            	});
            	$scope.appData.result.accountNr.sort(function(a, b){return a.sortIdx - b.sortIdx});
            	$scope.selectedBank.selectedValue = $scope.appData.result.accountNr[0];
        	} else {
        		$scope.selectedBank.selectedValue = null;
        	}
        });
    });
})();