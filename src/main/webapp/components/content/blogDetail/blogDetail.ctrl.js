'use strict';
angular.module('blogDetailModule')
.controller('blogDetailController', ['$scope','$routeParams','blogDetailService','$sce','memberService','productService',
	function($scope,$routeParams, blogDetailService,$sce,memberService,productService) {
		var self = this;
		console.log('blogDetailController');
		self.isAdmin = memberService.isAdmin();
		
		blogDetailService.getOneActiveBlog($routeParams.blogId)
		.then(function (data) {
			self.theBlog = data;
			self.theBlog.content=$sce.trustAsHtml(self.theBlog.content);
		});
		
		productService.getRandomProduct()
		.then(function (data) {
			console.log(data);
			self.randomProducts = data;
		});

}]);

