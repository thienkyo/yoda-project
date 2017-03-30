'use strict';
angular.module('loginModule')
.factory('loginService', ['ajaxService',function(ajaxService) {
		var loginService = {
			login : login,
			signup : signup
			//  createProduct : createProduct,
			//  updateProduct : updateProduct,
			//  deleteProduct : deleteProduct
			};
	return loginService;
	
	function login(credentials){
		console.log('login method in login srv: ');
		console.log(credentials);
		var url = "members/login";
		//return ajaxService.post(url,credentials,null,{});
		
		return ajaxService.post(url,credentials,null,{}).then(function(response){
			return response.data;
		});
	}
	
	function signup(newMember){
		console.log('signup method in login srv: ');
		console.log(newMember);
		
		var url = "members/add";
		return ajaxService.post(url,newMember,null,{}).then(function(response){
			return response.data.replyStr;
		});

	}
      
 }]);
