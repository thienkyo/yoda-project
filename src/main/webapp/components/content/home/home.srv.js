'use strict';
angular.module('homeModule')
.factory('homeService', ['ajaxService',function(ajaxService) {
	var homeService = {
			getFirst6Product : getFirst6Product,
			getFirst12Product : getFirst12Product,
			getBanner : getBanner
			};
	return homeService;

   function getFirst6Product(){
		var url = "products/first6";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
   
   function getFirst12Product(){
		var url = "products/first12";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
   
   function getBanner(){
		var url = "banner";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
	      
 }]);
