'use strict';
angular.module('accountModule')
.factory('accountService', ['ajaxService',function(ajaxService) {
	var accountService = {
			getMe : getMe
		//	getFirst6Product2 : getFirst6Product2
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return accountService;

   function getMe(){
		console.log('accountsservice: getMe');
		var url = "authenticated/me";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
	      
 }]);
