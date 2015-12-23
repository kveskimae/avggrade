(function () {
    angular.module('InvoiceApp').factory('modelTransformer', function modelTransformerFactory() {

        var transformObject = function (jsonResult, constructor) {
            var model = new constructor();
            angular.extend(model, jsonResult);
            return model;
        };

        var transformResult = function (jsonResult, constructor) {
            if (angular.isArray(jsonResult)) {
                var models = [];
                angular.forEach(jsonResult, function (object) {
                    models.push(transformObject(object, constructor));
                });
                return models;
            } else {
                return transformObject(jsonResult, constructor);
            }
        };

        var service = {
            transform: transformResult
        };

        return service;
    });
})();