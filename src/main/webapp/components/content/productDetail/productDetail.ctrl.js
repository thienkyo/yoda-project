'use strict';
angular.module('productDetailModule').controller('productDetailController',['$scope','$routeParams','productDetailService','cartService',
	function($scope, $routeParams, productDetailService,cartService) {
	
	var self = this;
	self.qty = 1;
	productDetailService.getProductByProdId($routeParams.prodId)
		.then(function (response) {
			//console.log(response.data);
			self.product = response;
	        self.test = response;
	});
	
	self.addToCart = function(prod){
		cartService.addToCart(prod, self.qty);
		self.alertProdId = prod.prodId;
	}

}]);