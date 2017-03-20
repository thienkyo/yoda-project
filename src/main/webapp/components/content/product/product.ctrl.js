'use strict';
angular.module('productModule').controller('productController',['$scope','$routeParams','productService', 
	function($scope, $routeParams, productService) {	
	var sefl = this;

	productService.getProductByCategory($routeParams.categoryId)
		.then(function (response) {
			//console.log(response.data);
	        $scope.products = response;
	        //console.log($scope.products);
	    });

}]);