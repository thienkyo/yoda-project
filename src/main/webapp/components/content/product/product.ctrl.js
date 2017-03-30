'use strict';
angular.module('productModule').controller('productController',['$rootScope','$routeParams','productService','cartService',
	function($rootScope, $routeParams, productService,cartService) {	
	var self = this;

	productService.getProductByCategory($routeParams.categoryId)
		.then(function (response) {
	        self.products = response;
	    });

	self.addToCart = function(prod){
		cartService.addToCart(prod,1);
		self.alertProdId = prod.prodId;
	/*	
		var currentItem={prod:prod,quantity:1};
		var currentCart=cartStoreService.getCurrentCart();
		
		if(cartStoreService.getQuantity() == 0){
			currentCart.push(currentItem);
		}else{
			var flag = false;
			
			for (var i = 0; i < currentCart.length; i++){
				console.log(currentCart[i]);
			    if(currentCart[i].prod.prodId == prod.prodId){
			    	currentCart[i].quantity = currentCart[i].quantity + 1;
			    	flag = false;
			    	break;
			    }else{
			    	flag = true;
			    }
			}
			if(flag){
				currentCart.push(currentItem);
			}
		}
		
		//console.log(prodId);
		cartStoreService.setCurrentCart(currentCart);
		$rootScope.$broadcast('addToCart');
		*/
	}
}]);