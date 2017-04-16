'use strict';
angular.module('productUpsertModule')
	.controller('productUpsertController',['$rootScope','$routeParams','$location','productUpsertService','memberService','productDetailService',
	function($rootScope, $routeParams,$location,productUpsertService,memberService,productDetailService) {	
	console.log('productUpsertController');
	var self = this;
	//$routeParams.prodId
	self.currentMember = memberService.getCurrentMember();
	
	if(!self.currentMember || self.currentMember.roles.indexOf("ADMIN") == -1){
		$location.path('#/');
	}
	
	console.log($routeParams.prodId);
	if($routeParams.prodId != 0){
		productDetailService.getProductByProdId($routeParams.prodId)
			.then(function (data) {
				console.log(data);
				self.product = data;
		});

	}else{
		//self.product = 
	}
	
	
}]);