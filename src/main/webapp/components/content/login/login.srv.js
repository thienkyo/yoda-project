'use strict';
angular.module('loginModule')
.factory('loginService', ['ajaxService',function(ajaxService) {
		var loginService = {
			getProductByCategory : getProductByCategory
			//  getAllProduct : getAllProduct,
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return loginService;
	
	function getProductByCategory(categoryId){
		console.log('test in 2222 cateId: '+ categoryId);
		var url = "products/categoryId/" + categoryId;
		
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
      
 }]);
