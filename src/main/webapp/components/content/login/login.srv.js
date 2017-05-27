'use strict';
angular.module('loginModule')
.factory('loginService', ['ajaxService',function(ajaxService) {
		var loginService = {
			login : login,
			login2 : login2,
			signup : signup
			};
	return loginService;
	
	function login(credentials){
		var url = "members/login";
		return ajaxService.post(url,credentials,null,{}).then(function(response){
			return response.data.token;
		});
	}
	
	function login2(loginRequest){
		var url = "members/login";
		return ajaxService.post(url,loginRequest,null,{}).then(function(response){
			return response.data.token;
		});
	}
	
	function signup(newMember){
		var url = "members/add";
		return ajaxService.post(url,newMember,null,{}).then(function(response){
			return response.data.replyStr;
		});

	}
      
 }]);
