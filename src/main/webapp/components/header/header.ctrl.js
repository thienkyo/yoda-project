angular.module('app')
.controller('headerController', ['$rootScope','$location','memberService',
								 'cartStoreService','categoryService','ajaxService',
	function($rootScope,$location,memberService,cartStoreService,categoryService,ajaxService) {
	var self=this;
	self.cart=[];
	self.currentMember = memberService.getCurrentMember();
	
	self.currentCart = cartStoreService.getCurrentCart();
	self.itemQuantity = cartStoreService.getQuantity();
	self.isAdmin = memberService.isAdmin();
	self.isMod = memberService.isMod();
	
	categoryService.getActiveCategories().then(function(data){
		self.cateList = data;
	});
	
	self.logout = function() {
		self.currentMember = memberService.setCurrentMember(null);
		self.isAdmin = false;
		self.isMod = false;
		$location.path('#/');
    }
	
	self.querySearch = function(searchText){
		if(searchText){
			var url = "search/"+searchText;
			return ajaxService.get(url,null,{}).then(function(response){
				return response.data;
			});

		}else{
			return {id:0,name:'no result',type:1,image:''};
		}
	}
	
	self.searchTextChange =function(text) {
	    //console.log('Text changed to ' + text);
	}

	self.selectedItemChange = function(item) {
		var url = '';
		if(item){
			if(item.type == 1){
				url = 'productDetail/'+item.id;
			}else{
				url = 'blogDetail/'+item.id;
			}
			$location.path(url);
		}
	}
	
///////////////////////////Receiver/////////////////////////////////////////////	
	
	$rootScope.$on('authorized', function() {
		self.currentMember = memberService.getCurrentMember();
		self.isAdmin = self.currentMember.roles.indexOf("ADMIN") != -1;
		self.isMod = self.currentMember.roles.indexOf("MOD") != -1;
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