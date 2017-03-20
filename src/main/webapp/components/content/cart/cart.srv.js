'use strict';
angular.module('cartModule')
.factory('cartService', ['ajaxService',function(ajaxService) {
	var cartService = {
			getFirst6Product : getFirst6Product
		
			};
	return cartService;
	
   function getFirst6Product(){
		console.log('service: getFirst6Product2');
		var url = "products/first6";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
	      
 }]);
