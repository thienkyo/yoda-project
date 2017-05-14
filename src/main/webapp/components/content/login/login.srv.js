'use strict';
angular.module('loginModule')
.factory('loginService', ['ajaxService',function(ajaxService) {
		var loginService = {
			login : login,
			signup : signup
			};
	return loginService;
	
	function login(credentials){
		var url = "members/login";
		return ajaxService.post(url,credentials,null,{}).then(function(response){
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
