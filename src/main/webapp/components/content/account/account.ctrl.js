'use strict';
angular.module('accountModule').controller('accountController', ['$scope','$location','accountService',
	function($scope,$location, accountService) {
		var self = this;
		console.log('accountController');
		accountService.getMe().then(function(me){
			self.me = me;
			console.log(me);
		},
		function(error){
			console.log("accountController.error");
			console.log(error);
			$location.path("#/");
		});
		
		
}]);

