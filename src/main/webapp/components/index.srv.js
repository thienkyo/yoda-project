'use strict';
angular.module('app')
.factory('memberService',['store', function(store) {
	var currentMember = null;
	var memberService = {
		setCurrentMember : setCurrentMember,
		getCurrentMember : getCurrentMember,
		isLogin : isLogin,
		isAdmin :isAdmin
		};
	return memberService;
	
	function setCurrentMember(member){
		var te = new BannerDO;
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
	
	function isAdmin(){
		if(isLogin() && currentMember.roles.indexOf("ADMIN") != -1){
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
			currentCart = store.get('cart');
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
.factory('shipStoreService',['store', function(store) {
	var shipList = [];
	var shipStoreService = {
		setShipList : setShipList,
		getShipList : getShipList,
		isAvailable : isAvailable 
		};
	return shipStoreService;
	
	function setShipList(list){
		shipList = list;
        store.set('shipList', list);
        return shipList;
	}
	
	function getShipList(){
		if (store.get('shipList')) {
			shipList = store.get('shipList');
        }else{
        	store.set('cart', shipList);
        }
        return shipList;
	}
	
	function isAvailable(){ 
		if (!shipList) {
			shipList = store.get('shipList');
        }
		if(shipList){
			return true;
		}
		return false;
	}
}])

.factory('paginationService',['store','PaginationItemDO','PaginationDO', function(store,PaginationItemDO,PaginationDO) {
	var pagination = new PaginationDO;
	var paginationService = {
		builder : builder
		};
	return paginationService;
	
	function builder(pageable){
		pagination.clear();
	   for(var i=1 ; i <= pageable.totalPages ; i++){ 
		   var temp = new PaginationItemDO();
		   temp.first = i == 1 ? true : false;
		   temp.last  = i == pageable.totalPages ? true : false;
		   temp.number = i ;
		   temp.status = i == (pageable.number + 1) ? true : false;
		   pagination.list.push(temp);
	   }
	   pagination.currentNumber = parseInt(pageable.number) +1;
	   pagination.nextNumber = parseInt(pagination.currentNumber) + 1;
	   pagination.previousNumber = pagination.currentNumber == 1 ? 1 : parseInt(pagination.currentNumber) - 1;
	   
	   return pagination;
	}
	
}])

;