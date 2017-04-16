'use strict';
angular.module('productListModule')
	.controller('productListController',['$rootScope','$routeParams','$location','memberService','productListService','NgTableParams',
	function($rootScope, $routeParams,$location,memberService,productListService,NgTableParams) {	
	console.log('productListController');
	var self = this;
	
	self.currentMember = memberService.getCurrentMember();
	
	if(!self.currentMember || self.currentMember.roles.indexOf("ADMIN") == -1){
		$location.path('#/');
	}
	
	productListService.get20Products().then(function (data) {
		console.log(data);
		self.products = data;
		self.tableParams = new NgTableParams({}, { dataset: self.products});
	});
	
	
	
}]);