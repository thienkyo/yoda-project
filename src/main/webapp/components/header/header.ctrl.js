angular.module('app')
.controller('headerController', ['$http','$rootScope','$location','memberService',
								 'cartStoreService','categoryService',
	function($http,$rootScope,$location,memberService,cartStoreService,categoryService) {
	var self=this;
	self.cart=[];
	self.currentMember = memberService.getCurrentMember();
	
	self.currentCart = cartStoreService.getCurrentCart();
	self.itemQuantity = cartStoreService.getQuantity();
	self.isAdmin = memberService.isAdmin();
	
	categoryService.getActiveCategories().then(function(data){
		self.cateList = data;
	});
	
	self.logout = function() {
		self.currentMember = memberService.setCurrentMember(null);
		self.isAdmin = false;
		$location.path('#/');
    }
	
	$rootScope.$on('authorized', function() {
		self.currentMember = memberService.getCurrentMember();
		self.isAdmin = self.currentMember.roles.indexOf("ADMIN") != -1;
    });
	
    $rootScope.$on('unauthorized', function() {
        self.currentMember = memberService.setCurrentMember(null);
        $location.path('#/');
    });
    
    $rootScope.$on('addToCart', function() {
    	self.itemQuantity = cartStoreService.getQuantity();
		self.currentCart = cartStoreService.getCurrentCart();
    });
    
    $rootScope.$on('removeItemCart', function() {
    	self.itemQuantity = cartStoreService.getQuantity();
    });
    
    $rootScope.$on('clearCart', function() {
    	self.itemQuantity = 0;
    });
    
    $rootScope.$on('ExpiredJwt', function() {
    	self.currentMember = memberService.setCurrentMember(null);
		self.isAdmin = false;
		$location.path('#/');
    });

}]);