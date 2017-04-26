'use strict';
angular.module('productListModule')
.factory('productListService', ['ajaxService',function(ajaxService) {
		var productListService = {
			get20Products : get20Products,
			getProductsForMgnt : getProductsForMgnt
		//	get50Products : get50Products,
	//		getAllProduct : getAllProduct
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return productListService;
	
	function get20Products(){
		console.log('get20Products');
		var url = "mgnt/get20Products";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
	
	function getProductsForMgnt(cateId,amount){
		console.log('getProductsForMgnt');
		var url = "mgnt/getProductsForMgnt/"+cateId+"/"+amount;
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
      
 }]);