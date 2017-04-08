'use strict';
angular.module('accountModule')
.factory('accountService', ['ajaxService',function(ajaxService) {
	var accountService = {
			getMe : getMe,
			updateMe : updateMe
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return accountService;

   function getMe(){
	//	console.log('accountsservice: getMe');
		var url = "authenticated/me";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
   }
   
   function updateMe(me){
	   console.log('updateMe');
	   console.log(me);
	   var url = "authenticated/updateMe";
		return ajaxService.post(url,me,{}).then(function(response){
			return response.data;
		});
   }
	      
 }]);
