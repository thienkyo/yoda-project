'use strict';
angular.module('productModule')
.factory('productService', ['ajaxService',function(ajaxService) {
		var productService = {
			getProductByCategory : getProductByCategory,
			  getRandomProduct : getRandomProduct,
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return productService;
	
	function getProductByCategory(categoryId){
		console.log('test in 2222 cateId: '+ categoryId);
		var url = "products/categoryId/" + categoryId;
		
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
	
	function getRandomProduct(){
		var url = "products/getRandomProduct";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
      
 }]);
