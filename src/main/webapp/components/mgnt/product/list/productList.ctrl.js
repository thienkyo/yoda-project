'use strict';
angular.module('productListModule')
	.controller('productListController',['$rootScope','$location',
										 'memberService','productListService',
										 'NgTableParams','categoryService',
	function($rootScope,$location,memberService,productListService,NgTableParams,categoryService) {	
	var self = this;
	self.statusStyle = { "width": "100px" };
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
//	self.currentMember = memberService.getCurrentMember();
	
	self.categories =[];
	var cateTemp = {categoryId:0,categoryName:'all'}
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	self.categoryId = 0;
	
	productListService.getProductsForMgnt(self.categoryId,self.amount).then(function (data) {
		self.products = data;
		self.tableParams = new NgTableParams({}, { dataset: self.products});
	});
	
	categoryService.getAllCategories().then(function (data) {
		self.categories = data;
		self.categories.push(cateTemp);
	});
	
	self.getProductbyTerm = function(){
		productListService.getProductsForMgnt(self.categoryId,self.amount).then(function (data) {
			self.products = data;
			self.tableParams = new NgTableParams({}, { dataset: self.products});
		});
	}
	
	self.setStyle = function(status){
		if(status==0){
			self.statusStyle.color = "crimson";
		}else if(status==1){
			self.statusStyle.color = "blue";
		}
		else{
			self.statusStyle = { "width": "100px" }
		}
		return self.statusStyle;
	}
	
}]);