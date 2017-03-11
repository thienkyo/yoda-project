'use strict';
angular.module('loginModule')
.factory('loginService', ['ajaxService',function(ajaxService) {
		var loginService = {
			login : login
			//  getAllProduct : getAllProduct,
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return loginService;
	
	function login(member){
		console.log('test in login srv: ');
		console.log(member);
		
		var url = "members/login";
		
		return ajaxService.post(url,member,null,{});
		
	/*	return ajaxService.post(url,member,null,{}).then(function(data){
			return data.data;
		});
*/
	}
      
 }]);
