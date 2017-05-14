'use strict';
angular.module('productModule')
.controller('productController',['$rootScope','$routeParams','productService','cartService','paginationService',
	function($rootScope, $routeParams, productService,cartService,paginationService) {	
	var self = this;
/*
	productService.getProductByCategory($routeParams.categoryId)
		.then(function (response) {
	        self.products = response;
	    });*/
	 self.pagination = {
			 currentNumber : 1,
			 previousNumber :  1,
			 nextNumber :  1,
			 cateId : 1,
			 list : []
	 };
	
	function item() {
		this.first = false;
		this.last = false;
		this.number = 0;
		this.status = false;
	}

	productService.getProductPage($routeParams.categoryId,$routeParams.pageNumber)
	.then(function (response) {
		self.currentPage = response;
		self.pagination = paginationService.builder(response, $routeParams.categoryId);
    });
	
	self.addToCart = function(prod){
		cartService.addToCart(prod,1);
		self.alertProdId = prod.prodId;
	}
}]);