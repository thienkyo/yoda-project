'use strict';
angular.module('cartModule')
.factory('cartService', ['$rootScope','ajaxService','cartStoreService',function($rootScope,ajaxService,cartStoreService) {
	var cartService = {
			getFirst6Product : getFirst6Product,
			getProductForCart : getProductForCart,
			addToCart : addToCart,
			placeOrder : placeOrder 
		};
	return cartService;
	
   function getFirst6Product(){
		console.log('service: getFirst6Product2');
		var url = "products/first6";
		return ajaxService.get(url,null,{}).then(function(data){
			return data.data;
		});
   }
	      
   function getProductForCart(prodIds){
		console.log('cart service: getProductForCart');
		console.log(prodIds);
		var url = "products/getProductForCart";
		return ajaxService.post(url,prodIds,{}).then(function(response){
			return response.data;
		});
   }
  
   function addToCart(prod,qty){
	   var currentItem={prod:prod,quantity:qty};
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
   }
   
   function placeOrder(cart){
	   console.log('cart service placeOrder');
	   console.log(cartStoreService.getCurrentCart());
   }
 }]);
