'use strict';
angular.module('productUpsertModule')
.factory('productUpsertService', ['ajaxService',function(ajaxService) {
		var productUpsertService = {
			upsert : upsert,
			//getCategories : getCategories,
		//	get50Products : get50Products,
	//		getAllProduct : getAllProduct
			//  createProduct : createProduct,
			upsert : upsert
			//  deleteProduct : deleteProduct
			};
	return productUpsertService;
	
	function upsert(product){
		console.log('upsert');
		var url = "mgnt/upsertProduct";
		return ajaxService.post(url,product,{}).then(function(response){
			return response.data;
		});
	}
/*	
	function getCategories(){
		var url = "categories/1";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
	*/
	
      
 }]);