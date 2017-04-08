'use strict';
angular.module('loginModule')
.controller('loginController',['$rootScope','$scope','$http','$routeParams','loginService', 
							   '$location','MemberDO','memberService','$mdDialog',
	function($rootScope,$scope,$http,$routeParams, loginService,$location,MemberDO,memberService,$mdDialog) {
	
	console.log('login controller');
	var self = this;
	
	
    self.member = {};
    self.credential ={};
	self.login = function() {
		console.log('loginctrl.login');
            //self.error = null;
		//console.log(self.member.email);
		if(self.credential.email && self.credential.pass){
            loginService.login({email:self.credential.email,pass:btoa(self.credential.pass)}).then(function(token) {	
            	console.log(token);
            	
            	self.member.token =  'sheep ' + token;
            	var arr = token.split('.');
            	var decodedString = decodeURIComponent(atob(arr[1]));
                console.log(decodedString);
                var a = angular.fromJson(decodeURIComponent(decodedString));
                console.log(a);
                self.member.roles = a.roles;
             //   self.member.shipAddress = a.shipAddress;
               
                memberService.setCurrentMember(self.member);
                $rootScope.$broadcast('authorized');
                $location.path('#/');
            },
            function(error){
            	self.loginError = error.data.message;
            	//$rootScope.authenticated = false;
               // $scope.error = error;
               // $scope.userName = '';
            });
		}else{
			self.loginError = 'email và pass không được để trống';
		}     
    }
	
	
	self.signup = function(){
		self.newMember = new MemberDO();
		console.log(self.newMember);
		if(self.newMember.email && self.newMember.fullName && self.newMember.pass && self.newMember.phone){
			
			self.newMember.pass = btoa(self.newMember.pass);
			loginService.signup(self.newMember).then(function(replyStr) {	
				//$location.path('#/authenticated/me');
				console.log(replyStr);
				self.reset();
				self.showAlert();
            },
            function(error){
            	console.log(error);
            	if(error.data.message == 'emailexists'){
            		self.signUpError = 'email đã tồn tại';
            	}
            });
		}else{
			console.log('not add');
			self.signUpError = 'Cả 4 không để trống';
		}
	};
	
	self.showAlert = function() {
	    // Appending dialog to document.body to cover sidenav in docs app
	    // Modal dialogs should fully cover application
	    // to prevent interaction outside of dialog
	    $mdDialog.show(
	      $mdDialog.alert()
	        //.parent(angular.element(document.querySelector('#popupContainer')))
	        .parent(angular.element(document.body))
	        .clickOutsideToClose(true)
	        .title('Bạn đã đăng ký thành công')
	        .textContent('Hãy đăng nhập ngay và bắt đầu mua sắm.')
	        .ariaLabel('Alert Dialog Demo')
	        .ok('OK')
	       // .targetEvent(ev)
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