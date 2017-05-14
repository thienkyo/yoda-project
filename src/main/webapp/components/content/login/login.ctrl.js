'use strict';
angular.module('loginModule')
.controller('loginController',['$rootScope','loginService', 
							   '$location','MemberDO','memberService','$mdDialog',
	function($rootScope, loginService,$location,MemberDO,memberService,$mdDialog) {
	
	var self = this;
	self.newMember = new MemberDO();
    self.member = {};
    self.credential ={};
    
	self.login = function() {
		if(self.credential.email && self.credential.pass){
            loginService.login({email:self.credential.email,pass:btoa(self.credential.pass)}).then(function(token) {	
            	self.member.token =  'sheep ' + token;
            	var arr = token.split('.');
            	var decodedString = atob(arr[1]);
                var a = angular.fromJson(decodedString);
                self.member.roles = a.roles;
                self.member.name = a.name;
               
                memberService.setCurrentMember(self.member);
                $rootScope.$broadcast('authorized');
                $location.path('#/');
            },
            function(error){
            	self.loginError = error.data.message;
            });
		}else{
			self.loginError = 'email và pass không được để trống';
		}     
    }
	
	
	self.signup = function(){
		if(self.newMember.email && self.newMember.fullName && self.newMember.pass && self.newMember.phone){
			self.newMember.pass = btoa(self.newMember.pass);
			loginService.signup(self.newMember).then(function(replyStr) {	
				self.reset();
				self.showAlert();
            },
            function(error){
            	if(error.data.message == 'emailexists'){
            		self.signUpError = 'email đã tồn tại';
            	}
            });
		}else{
			self.signUpError = 'Cả 4 không để trống';
		}
	};
	
	self.showAlert = function() {
	    $mdDialog.show(
	      $mdDialog.alert()
	        .parent(angular.element(document.body))
	        .clickOutsideToClose(true)
	        .title('Bạn đã đăng ký thành công')
	        .textContent('Hãy đăng nhập ngay và bắt đầu mua sắm.')
	        .ariaLabel('Alert Dialog Demo')
	        .ok('OK')
	    );
	};
	
	self.reset = function(){
		self.loginError = null;
		self.signUpError = null;
		self.newMember.email = '';
		self.newMember.fullName = '';  
		self.newMember.pass = '';
		self.newMember.phone ='';
	}

}]);