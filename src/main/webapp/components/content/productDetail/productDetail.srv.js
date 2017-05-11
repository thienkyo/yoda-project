'use strict';
angular.module('productDetailModule')
.factory('productDetailService', ['ajaxService',function(ajaxService) {
		var productDetailService = {
			getProductByProdId : getProductByProdId
			//  getAllProduct : getAllProduct,
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return productDetailService;
	
	function getProductByProdId(prodId){
		console.log('productDetailService prodId: '+ prodId);
		var url = "products/activeId/" + prodId;
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
      
 }]);
