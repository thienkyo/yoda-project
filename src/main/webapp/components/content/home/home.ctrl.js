'use strict';
angular.module('homeModule').controller('homeController', ['$scope','homeService','cartService',
	function($scope, homeService,cartService) {
		var self = this;
		
		homeService.getFirst12Product()
			.then(function (response) {
				self.first12products = response;
			});
		
		homeService.getBanner()
		.then(function (response) {
			self.banners = response;
		});
		
		self.addToCart = function(prod){
			cartService.addToCart(prod,1);
			self.alertProdId = prod.prodId;
		}

}]);

