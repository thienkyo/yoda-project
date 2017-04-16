angular.module('app').controller('headerController', ['$http','$rootScope','$location','memberService','cartStoreService',
	function($http,$rootScope,$location,memberService,cartStoreService) {
	console.log('header ctrl');
	var self=this;
	self.cart=[];
	self.currentMember = memberService.getCurrentMember();
	
	self.currentCart = cartStoreService.getCurrentCart();
	self.itemQuantity = cartStoreService.getQuantity();
	
	self.isAdmin = false;
	if(self.currentMember){
		self.isAdmin = self.currentMember.roles.indexOf("ADMIN") != -1;
	}
	
	
	self.logout = function() {
		self.currentMember = memberService.setCurrentMember(null);
		self.isAdmin = false;
		console.log('header logout clicked');
		$location.path('#/');
        //$rootScope.authenticated = false;
       // $http.defaults.headers.common.Authorization = '';
    }
	
	$rootScope.$on('authorized', function() {
		self.currentMember = memberService.getCurrentMember();
		self.isAdmin = self.currentMember.roles.indexOf("ADMIN") != -1;
    });
	
    $rootScope.$on('unauthorized', function() {
    	console.log('in headerctrl unauthorized event');
        self.currentMember = memberService.setCurrentMember(null);
        $location.path('#/');
    });
    
    $rootScope.$on('addToCart', function() {
		//self.itemQuantity = self.itemQuantity + 1;
    	self.itemQuantity = cartStoreService.getQuantity();
		self.currentCart = cartStoreService.getCurrentCart();
		console.log('rootScope.on addToCart');
		console.log(self.currentCart);
    });
    
    $rootScope.$on('removeItemCart', function() {
    	self.itemQuantity = cartStoreService.getQuantity();
    });
    
    $rootScope.$on('ExpiredJwt', function() {
    	self.currentMember = memberService.setCurrentMember(null);
		self.isAdmin = false;
		$location.path('#/');
    });

}]);