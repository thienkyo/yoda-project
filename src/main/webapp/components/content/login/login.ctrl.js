'use strict';
angular.module('loginModule').controller('loginController',['$rootScope','$scope','$http','$routeParams','loginService', '$location','MemberDO',
	function($rootScope, $scope,$http,$routeParams, loginService,$location,MemberDO) {
	
	console.log('login controller');
	var self = this;
	
	var authenticate = function(credentials, callback) {
		console.log(credentials);
		
		var mem = credentials ? credentials : new MemberDO();
	/*	
		if(credentials){
			credentials.pass = btoa(credentials.pass);
		}
	  */  
	    loginService.login(credentials).success(function(data) {
	    	console.log(data);
	      if (data.email) {
	        $rootScope.authenticated = true;
	      } else {
	        $rootScope.authenticated = false;
	      }
	      callback && callback();
	    }).error(function() {
	      $rootScope.authenticated = false;
	      callback && callback();
	    });

	  }
	
	  authenticate();
	 // self.credentials = {};
	  self.member = new MemberDO();
	  self.login = function() {
		  console.log('click login');
	      authenticate(self.member, function() {
	        if ($rootScope.authenticated) {
	          $location.path("/");
	          $scope.error = false;
	        } else {
	          $location.path("/login");
	          $scope.error = true;
	        }
	      });
	  };

}]);