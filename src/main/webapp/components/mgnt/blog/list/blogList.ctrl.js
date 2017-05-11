'use strict';
angular.module('blogListModule')
	.controller('blogListController',['$rootScope','$location','memberService','blogListService','NgTableParams',
	function($rootScope,$location,memberService,blogListService,NgTableParams) {	
	console.log('productListController');
	var self = this;
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	self.currentMember = memberService.getCurrentMember();
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	
	
	blogListService.getBlogsForMgnt(self.amount).then(function (data) {
		console.log(data);
		self.blogList = data;
		self.tableParams = new NgTableParams({}, { dataset: self.blogList});
	});	
	
	self.getBlogByTerm = function(){
		blogListService.getBlogsForMgnt(self.amount).then(function (data) {
			console.log(data);
			self.orderList = data;;
			self.tableParams = new NgTableParams({}, { dataset: self.orderList});
		});
	}
	
}]);