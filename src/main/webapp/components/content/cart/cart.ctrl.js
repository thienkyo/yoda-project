'use strict';
angular.module('cartModule').controller('cartController', ['$rootScope','cartService','cartStoreService','memberService',
	function($rootScope, cartService,cartStoreService,memberService) {
		var self = this;
		console.log('cartController');
		
		self.isShow = false;
		self.currentMember = memberService.getCurrentMember();

		self.currentCart = cartStoreService.getCurrentCart();;
		console.log(self.currentCart);
		self.subTotal = 0;
		//self.updateTotal();
		
		
		if(self.currentCart.length > 0){
			//self.currentCart = cartStoreService.getCurrentCart();
			//var prodIds = [];
			for (var i = 0; i < self.currentCart.length; i++){
				//prodIds.push(self.currentCart[i].id);
				self.subTotal += self.currentCart[i].prod.price*self.currentCart[i].quantity;
			}
		}	
		
		self.removeItem = function(index){
			self.currentCart.splice(index, 1);
			cartStoreService.setCurrentCart(self.currentCart);
			self.updateTotal();
			$rootScope.$broadcast('removeItemCart');
		}
		
		self.updateTotal = function(){
			self.subTotal = 0;
			for (var i = 0; i < self.currentCart.length; i++){
				//prodIds.push(self.currentCart[i].id);
				self.subTotal += self.currentCart[i].prod.price*self.currentCart[i].quantity;
			}
		}
		
		self.placeOrder = function(){
			self.isShow = !memberService.isLogin();
			console.log(self.isShow);
			if(!self.isShow){
				console.log('logined');
				cartService.placeOrder();
			}else{
				
				console.log('NOT logined');
			}
		}
		/*	
			cartService.getProductForCart(prodIds).then(function (response) {
				//$scope.first6products = response;
		        console.log(response);
		        self.fullCart=response;
			});
			*/
		
}]);

