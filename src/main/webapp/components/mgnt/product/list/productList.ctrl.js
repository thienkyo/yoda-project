'use strict';
angular.module('productListModule')
	.controller('productListController',['$rootScope','$routeParams','$location',
										 'memberService','productListService',
										 'NgTableParams','categoryService',
	function($rootScope, $routeParams,$location,memberService,productListService,NgTableParams,categoryService) {	
	console.log('productListController');
	var self = this;
	
	self.currentMember = memberService.getCurrentMember();
	if(!self.currentMember || self.currentMember.roles.indexOf("ADMIN") == -1){
		$location.path('#/');
	}
	
	self.categories =[];
	var cateTemp = {categoryId:0,categoryName:'all'}
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	self.categoryId = 0;
	
/*	
	productListService.get20Products().then(function (data) {
		console.log(data);
		self.products = data;
		self.tableParams = new NgTableParams({}, { dataset: self.products});
	});*/
	
	
	productListService.getProductsForMgnt(self.categoryId,self.amount).then(function (data) {
		console.log(data);
		self.products = data;
		self.tableParams = new NgTableParams({}, { dataset: self.products});
	});
	
	categoryService.getAllCategories().then(function (data) {
		console.log(data);
		self.categories = data;
		self.categories.push(cateTemp);
	});
	
	self.getProductbyTerm = function(){
		productListService.getProductsForMgnt(self.categoryId,self.amount).then(function (data) {
			console.log(data);
			self.products = data;
			self.tableParams = new NgTableParams({}, { dataset: self.products});
		});
	}
	
	
}]);