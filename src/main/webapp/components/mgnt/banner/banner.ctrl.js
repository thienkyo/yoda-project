'use strict';
angular.module('bannerModule')
	.controller('bannerController',['$rootScope','$location','memberService','bannerService','NgTableParams',
	function($rootScope,$location,memberService,bannerService,NgTableParams) {	
	//console.log('productListController');
	var self = this;
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	
	bannerService.getBannerForMgnt().then(function (data) {
		console.log(data);
		//self.blogList = data;
		self.tableParams = new NgTableParams({}, { dataset: data});
	});	
	
	
}]);