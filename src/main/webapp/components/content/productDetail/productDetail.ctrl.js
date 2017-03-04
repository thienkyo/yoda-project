'use strict';
angular.module('productDetailModule').controller('productDetailController',['$scope','$routeParams','productDetailService', 
	function($scope, $routeParams, productDetailService) {
	
	var self = this;
	//$scope.prod=[];
	//$scope.categoryId = $routeParams.categoryId;
	//console.log('productDetailController');
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
	productDetailService.getProductByProdId($routeParams.prodId)
		.then(function (response) {
			//console.log(response.data);
			self.product = response;
	        self.test = response;
	    });

}]);