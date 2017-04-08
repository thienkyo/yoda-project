'use strict';
angular.module('cartModule')
	.controller('cartController', ['$rootScope','cartService','cartStoreService','memberService','accountService','OrderDO','OrderDetailDO',
	function($rootScope, cartService,cartStoreService,memberService,accountService,OrderDO,OrderDetailDO) {
		var self = this;
		console.log('cartController');
		
		self.isShow = false;
		self.currentMember = memberService.getCurrentMember();
		self.currentCart = cartStoreService.getCurrentCart();
		console.log(self.currentCart);
		self.subTotal = 0;
		self.total = 0;
		self.order = new OrderDO;
		self.orderdetail = [];
		//self.order.shipCostId = 0;
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
	        console.log(self.shipCostList);
		});
		
//////get full user data from db		
		accountService.getMe().then(function(me){
			self.me = me;
			//self.order.shipCostId = self.me.shipCostId;
			console.log(me);
			self.order.member = me;
			self.isShow = false;
			self.updateTotal();
		},
		function(error){
			console.log("no authorized header");
			console.log(error);
			self.isShow = true;
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
			self.updateShippingFee ();
		}
		
		self.placeOrder = function(){
			console.log('placeOrder()');
		//	console.log(self.order_return_status);
			
		//	console.log(self.order.shipCostId);
			self.isShow = !memberService.isLogin();
			if(!self.isShow && self.order_one_time_trigger && self.currentCart.length > 0){
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
				
				if(self.me.address &&  self.order.shipCostId != 0){
					cartService.placeOrder(self.order).then(function (response) {
				       // console.log(response);
				        self.order_return_status = response;
				        console.log(self.order_return_status);
				        self.order_one_time_trigger = false;
				        cartStoreService.clearCart();
				        self.currentCart = [];
					});
				}
				
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
			//	console.log(self.currentCart[i].quantity);
				w += self.currentCart[i].prod.weight*self.currentCart[i].quantity;
			}
			w = Math.round(w*100)/100;
			//console.log(w);
			self.order.shipCostFee = w*shipBaseFee;
			self.order.shipCostFee = (self.order.shipCostFee < 20000) ?  25000 : self.order.shipCostFee ;
			self.total = self.order.shipCostFee + self.subTotal;
		}
		
}]);

