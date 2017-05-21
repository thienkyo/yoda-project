'use strict';
angular.module('blogListModule')
	.controller('blogListController',['$rootScope','$location','memberService','blogListService','NgTableParams',
	function($rootScope,$location,memberService,blogListService,NgTableParams) {	
	var self = this;
	self.statusStyle = { "width": "100px" };
	if(!memberService.isAdmin() && !memberService.isMod()){
		$location.path('#/');
	}
//	self.currentMember = memberService.getCurrentMember();
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	
	
	blogListService.getBlogsForMgnt(self.amount).then(function (data) {
		self.blogList = data;
		self.tableParams = new NgTableParams({}, { dataset: self.blogList});
	});	
	
	self.getBlogByTerm = function(){
		blogListService.getBlogsForMgnt(self.amount).then(function (data) {
			self.orderList = data;;
			self.tableParams = new NgTableParams({}, { dataset: self.orderList});
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