'use strict';
angular.module('blogModule').controller('blogController', ['$routeParams','blogService','memberService','productService','paginationService',
	function($routeParams, blogService,memberService,productService,paginationService) {
		var self = this;
		
		self.isAdmin = memberService.isAdmin();
		
		blogService.getBlogPage($routeParams.pageNumber)
		.then(function (response) {
			self.currentPage = response;
			self.pagination = paginationService.builder(response, 0);
	    });
		
		
		productService.getRandomProduct()
		.then(function (data) {
			self.randomProducts = data;
		});
}]);

