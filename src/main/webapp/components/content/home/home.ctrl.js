'use strict';
angular.module('homeModule').controller('homeController', ['$scope','homeService','cartService',
	function($scope, homeService,cartService) {
		var self = this;
		console.log('homeController');
		console.log('homeController222');
		
		homeService.getFirst12Product()
			.then(function (response) {
				self.first12products = response;
		        console.log(self.first12products);
			});
		
		self.addToCart = function(prod){
			cartService.addToCart(prod,1);
			self.alertProdId = prod.prodId;
		}

}]);

