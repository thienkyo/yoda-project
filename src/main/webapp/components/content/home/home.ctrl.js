'use strict';
angular.module('homeModule').controller('homeController', ['$scope','homeService','cartService',
	function($scope, homeService,cartService) {
		var self = this;
		
		homeService.getHomeProduct()
			.then(function (response) {
				self.homeProducts = response;
			});
		
		homeService.getBanner()
		.then(function (response) {
			self.banners = response;
		});
		
		homeService.gethomeArticle()
		.then(function (response) {
			self.homeArticles = response;
		});
		
		self.addToCart = function(prod){
			if(prod.quantity > 0){
				cartService.addToCart(prod,1);
			}
			self.alertProdId = prod.prodId;
		}

}]);

