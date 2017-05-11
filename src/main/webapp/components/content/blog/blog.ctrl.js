'use strict';
angular.module('blogModule').controller('blogController', ['$scope','blogService','memberService','productService',
	function($scope, blogService,memberService,productService) {
		var self = this;
		
		self.isAdmin = memberService.isAdmin();
		blogService.getActiveBlog().then(function (data) {
				self.blogList = data;
		       console.log(self.blogList);
		});
		productService.getRandomProduct()
		.then(function (data) {
			console.log(data);
			self.randomProducts = data;
		});
}]);

