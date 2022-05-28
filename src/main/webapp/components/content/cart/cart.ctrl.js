'use strict';
angular.module('cartModule')
	.controller('cartController', ['$rootScope','cartService','cartStoreService',
								   'memberService','accountService','OrderDO',
								   'OrderDetailDO','MemberDO',
	function($rootScope, cartService,cartStoreService,memberService,accountService,OrderDO,OrderDetailDO) {
		var self = this;
		self.isShow = false;
		self.currentMember = memberService.getCurrentMember();
		self.currentCart = cartStoreService.getCurrentCart();
		self.subTotal = 0;
		self.total = 0;
		self.order = new OrderDO;
		self.guest = new MemberDO;
		self.orderdetail = [];
		self.order_one_time_trigger = true;
/////get ship list		
		var tempShipCost = {
				distance : "",
				price : 0,
				region : "----chọn vùng----",
				shipCostId : 0
		};
		
		cartService.getShipCost().then(function (response) {
	        self.shipCostList = response;
	        self.shipCostList.push(tempShipCost);
		});
		
//////get full user data from db		
		accountService.getMe().then(function(me){
			self.me = me;
			//self.order.shipCostId = self.me.shipCostId;
			self.order.member = me;
			self.isShow = false;
			self.updateTotal();
		},
		function(error){
			//self.isShow = true;
			self.isShow = false;
			self.me = self.guest;
            self.order.member = self.guest;
		});
		
		if(self.currentCart.length > 0){
			for (var i = 0; i < self.currentCart.length; i++){
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
			cartStoreService.setCurrentCart(self.currentCart);;
			self.updateShippingFee();
		}
		
		self.placeOrder = function(){
			self.isShow = !memberService.isLogin();
			if(self.order_one_time_trigger && self.currentCart.length > 0){
				var OrderDetailList = [];
				
				for (var i = 0; i < self.currentCart.length; i++){
					var tempOrderDetail = new OrderDetailDO();
					tempOrderDetail.product = self.currentCart[i].prod;
					tempOrderDetail.priceAtThatTime = self.currentCart[i].prod.price;
					tempOrderDetail.discountAtThatTime = self.currentCart[i].prod.discount;
					tempOrderDetail.weight = self.currentCart[i].prod.weight;
					tempOrderDetail.quantity = self.currentCart[i].quantity;
					OrderDetailList.push(tempOrderDetail);
				}
				self.order.orderDetails = OrderDetailList;
				self.order.shippingAddress = self.me.address;
				self.order.shipCostId = self.me.shipCostId;
				self.isShow = false;
				self.isAddress = false;
				if(self.me.address &&  self.order.shipCostId != 0){
					cartService.placeOrder(self.order).then(function (response) {
				        self.order_return_status = response;
				        self.order_one_time_trigger = false;
				        cartStoreService.clearCart();
				        self.currentCart = [];
				        $rootScope.$broadcast('clearCart');
					});
				}else{
					self.isAddress ='Cần nhập địa chỉ và chọn vùng.';
				}
				
			}else{
				console.log('NOT logined');
			}
		}
		
		self.updateShippingFee = function(){
			var shipBaseFee = 0;
			for (var i = 0; i < self.shipCostList.length; i++){
				if(self.shipCostList[i].shipCostId === self.me.shipCostId){
					shipBaseFee = self.shipCostList[i].price;
					break;
				}
			}
			var w=0;
			for (var i = 0; i < self.currentCart.length; i++){
				w += self.currentCart[i].prod.weight*self.currentCart[i].quantity;
			}
			self.order.shipCostFee = w*shipBaseFee;
			self.order.shipCostFee = (self.order.shipCostFee < 20000 && self.me.shipCostId != 7) ?  25000 : self.order.shipCostFee ;
			self.total = self.order.shipCostFee + self.subTotal;
		}
		
}]);

