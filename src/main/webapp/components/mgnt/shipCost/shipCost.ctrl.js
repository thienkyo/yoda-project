'use strict';
angular.module('shipCostModule')
	.controller('shipCostController',['$rootScope','$location','memberService','shipCostService',
									'NgTableParams','CommonStatusArray','ShipCostDO','Upload','$timeout',
	function($rootScope,$location,memberService,shipCostService,NgTableParams,CommonStatusArray,ShipCostDO,Upload,$timeout) {	
	var self = this;
	self.theShipCost = new ShipCostDO;
	self.statusList = CommonStatusArray;
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	
	shipCostService.getShipCostForMgnt().then(function (data) {
		self.shipCostList = data;
		self.tableParams = new NgTableParams({}, { dataset: self.shipCostList});
	});	

	
	self.upsert = function(shipCost){
		
		self.responseStr = false;
		self.responseStrFail = false;
		shipCostService.upsert(shipCost).then(function (data) {
			self.responseStr = data;
			if(shipCost.shipCostId == 0){
				self.shipCostList.push(data);
				self.tableParams = new NgTableParams({}, { dataset: self.shipCostList});
			}
		});
	}
	
	self.updateShipCost = function(shipCost){
		self.theShipCost = shipCost;
		self.responseStr = false;
		self.responseStrFail = false;
	}
	
	self.clear = function(){
		self.responseStr = false;
		self.responseStrFail = false;
		self.theShipCost = new ShipCostDO;
	}
}]);