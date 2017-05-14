'use strict';
angular.module('productDetailModule')
.factory('productDetailService', ['ajaxService',function(ajaxService) {
		var productDetailService = {
			getProductByProdId : getProductByProdId
			};
	return productDetailService;
	
	function getProductByProdId(prodId){
		var url = "products/activeId/" + prodId;
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
	}
      
 }]);
