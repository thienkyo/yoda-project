'use strict';
angular.module('productUpsertModule')
.factory('productUpsertService', ['ajaxService',function(ajaxService) {
		var productUpsertService = {
			upsert : upsert
		//	get50Products : get50Products,
	//		getAllProduct : getAllProduct
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return productUpsertService;
	
	function upsert(){
		console.log('upsert');
		var url = "mgnt/upsert";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
      
 }]);