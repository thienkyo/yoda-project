'use strict';
angular.module('productDetailModule')
	.controller('productDetailController',['$scope','$routeParams','productDetailService','cartService','memberService','$sce',
	function($scope, $routeParams, productDetailService,cartService,memberService,$sce) {
	
	var self = this;
	self.qty = 1;
	self.isAdmin = memberService.isAdmin();
	
	productDetailService.getProductByProdId($routeParams.prodId)
		.then(function (response) {
			self.product = response;
			self.product.description=$sce.trustAsHtml(self.product.description);
	        self.test = response;
	});
	
	self.addToCart = function(prod){
		if(prod.quantity > 0){
			cartService.addToCart(prod, self.qty);
		}
		self.alertProdId = prod.prodId;
	}

}]);