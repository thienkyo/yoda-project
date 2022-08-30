'use strict';
angular.module('shipCostModule')
.factory('shipCostService', ['ajaxService',function(ajaxService) {
	var shipCostService = {
			getShipCostForMgnt : getShipCostForMgnt,
			upsert : upsert
			};
	return shipCostService;

	function getShipCostForMgnt(){
		var url = "mgnt/getShipCostForMgnt";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}   
    
	function upsert(shipCost){
		var url = "mgnt/upsertShipCost";
		return ajaxService.post(url,shipCost,{}).then(function(response){
			return response.data;
		});
	}
	
 }]);
