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
		console.log('memberservice');
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
		console.log('cartStoreService');
		currentCart = cart;
        store.set('cart', cart);
        return currentCart;
	}
	
	function getCurrentCart(){
	//	if (!currentCart) {
            currentCart = store.get('cart');
    //    }
        return currentCart;
	}
	
	function getQuantity(){
		return getCurrentCart().length;
	}
	
	function clearCart(){
		currentCart = [];
		store.set('cart', currentCart);
	}

}])
;