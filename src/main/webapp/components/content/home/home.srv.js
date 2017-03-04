'use strict';
angular.module('homeModule')
.factory('homeService', ['ajaxService',function(ajaxService) {
	var homeService = {
			getFirst6Product : getFirst6Product
		//	getFirst6Product2 : getFirst6Product2
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return homeService;
/*	
	function getFirst6Product(){
		console.log('first 6 product service');
		var url = "http://localhost:8080/products/first6";
		return $http.get(url);
	}
*/	
   function getFirst6Product(){
		console.log('service: getFirst6Product2');
		var url = "products/first6";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
	      
 }]);
