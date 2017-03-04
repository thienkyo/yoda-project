'use strict';
angular.module('productModule').controller('productController',['$scope','$routeParams','productService', 
	function($scope, $routeParams, productService) {
	
	var sefl = this;
	//$scope.prod=[];
	//$scope.categoryId = $routeParams.categoryId;
	//console.log('productController.categoryId: '+ $scope.categoryId);
/*	productService.getProductByCategory($scope.categoryId)
		.then(function (response) {
			//console.log(response.data);
	        $scope.prod2 = response;
	        console.log('test33');
	        console.log($scope.prod2);
	    }, function (error) {
	        $scope.status = 'Unable to load customer data: ' + error.message;
	    });
*/	
	productService.getProductByCategory($routeParams.categoryId)
		.then(function (response) {
			//console.log(response.data);
	        $scope.products = response;
	        console.log($scope.products);
	    });

}]);