'use strict';
angular.module('app')
.factory('memberService',['store', function(store) {
	var currentMember = null;
	var memberService = {
		setCurrentMember : setCurrentMember,
		getCurrentMember : getCurrentMember,
		isLogin : isLogin
		};
	return memberService;
	
	function setCurrentMember(member){
		currentMember = member;
        store.set('member', member);
        return currentMember;
	}
	
	function getCurrentMember(){
		if (!currentMember) {
            currentMember = store.get('member');
        }
        return currentMember;
	}
	
	function isLogin(){ 
		if (!currentMember) {
            currentMember = store.get('member');
        }
		if(currentMember){
			return true;
		}
		return false;
	}
      
}])
.factory('cartStoreService',['store', function(store) {
	var currentCart = [];
	var cartStoreService = {
		setCurrentCart : setCurrentCart,
		getCurrentCart : getCurrentCart,
		getQuantity : getQuantity,
		clearCart : clearCart
		};
	return cartStoreService;
	
	function setCurrentCart(cart){
		currentCart = cart;
        store.set('cart', cart);
        return currentCart;
	}
	
	function getCurrentCart(){
		if (store.get('cart')) {
			currentCart = store.get('cart')
        }else{
        	store.set('cart', currentCart);
        }
        return currentCart;
	}
	
	function getQuantity(){
		if(getCurrentCart()){
			return getCurrentCart().length;
		}
		return 0;
	}
	
	function clearCart(){
		currentCart = [];
		store.set('cart', currentCart);
	}

}])
;