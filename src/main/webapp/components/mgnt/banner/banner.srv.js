'use strict';
angular.module('bannerModule')
.factory('bannerService', ['ajaxService',function(ajaxService) {
	var bannerService = {
			getBannerForMgnt : getBannerForMgnt
			};
	return bannerService;

	function getBannerForMgnt(){
		var url = "mgnt/getBannerForMgnt";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}   
    
 }]);
