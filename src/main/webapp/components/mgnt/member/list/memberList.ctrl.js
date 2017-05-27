'use strict';
angular.module('memberListModule')
	.controller('memberListController',['$rootScope','$location','memberService',
										 'memberListService','NgTableParams','CommonStatusArray',
	function($rootScope,$location,memberService,memberListService,NgTableParams,CommonStatusArray) {	
	var self = this;
	self.statusList = CommonStatusArray;
	self.statusStyle = { "width": "100px" };
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	
	memberListService.getMembersForMgnt(self.amount).then(function (data) {
		self.members = data;
		self.tableParams = new NgTableParams({}, { dataset: self.members});
	});
	
	self.getMemberbyTerm = function(){
		memberListService.getMembersForMgnt(self.amount).then(function (data) {
			self.members = data;
			self.tableParams = new NgTableParams({}, { dataset: self.members});
		});
	}
	
	self.updateMember = function(mem){
		self.theMember = mem;
		self.responseStr = false;
		self.responseStrFail = false;
	}
	
	self.upsert = function(mem){
		self.responseStr = false;
		self.responseStrFail = false;
		memberListService.upsert(mem).then(function (data) {
			self.responseStr = data;
		});
	}
	
	self.clear = function(){
		self.responseStr = false;
		self.responseStrFail = false;
		self.theMember = {};
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