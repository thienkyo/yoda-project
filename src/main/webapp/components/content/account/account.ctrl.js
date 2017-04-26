'use strict';
angular.module('accountModule').controller('accountController', ['$scope','$location','accountService','cartService','OrderStatusArray',
	function($scope,$location, accountService,cartService,OrderStatusArray) {
		var self = this;
		self.me = {};
		self.me.shipCostId = 0;
		console.log(OrderStatusArray);
/////get ship list		
		var tempShipCost = {
				distance: "",
				price:"",
				region:"------chọn vùng-------",
				shipCostId : 0
		};
		
		cartService.getShipCost().then(function (response) {
	        self.shipCostList = response;
	        self.shipCostList.push(tempShipCost);
		});
		
		console.log('accountController');
		accountService.getMe().then(function(me){
			self.me = me;
			console.log(me);
			//self.shipCostId = me.shipCostId;
		
			for(var i = 0; i < me.orders.length; i++){
				var total = 0;
				for(var k = 0; k < me.orders[i].orderDetails.length; k++){
					total += me.orders[i].orderDetails[k].priceAtThatTime*me.orders[i].orderDetails[k].quantity;
				}
				total += me.orders[i].shipCostFee;
				me.orders[i].total = total;
				console.log(me.orders[i].status);
				for(var k = 0; k < OrderStatusArray.length; k++){
					if(OrderStatusArray[k].value == me.orders[i].status){
						me.orders[i].status = OrderStatusArray[k].name;
						break;
					}
				}
			}
			self.orderList = me.orders;
		},function(error){
			console.log("accountController.error");
			console.log(error);
			$location.path("#/");
		});
		
		self.updateMe = function(){
			for(var i = 0; i < self.shipCostList.length; i++){
				if(self.shipCostList[i].shipCostId == self.me.shipCostId){
					self.me.shipCost = self.shipCostList[i];
					break;
				}
			}
			accountService.updateMe(self.me).then(function (response) {
		        console.log(response);
			});
		}
		
		self.showOrderDetail = function(order){
			console.log(order);
			self.theOrder = order;
			self.theOrder.subTotal = 0;
			for (var i = 0; i < self.theOrder.orderDetails.length; i++){
				self.theOrder.subTotal += self.theOrder.orderDetails[i].priceAtThatTime*self.theOrder.orderDetails[i].quantity;
			}
			self.theOrder.total = self.theOrder.subTotal + self.theOrder.shipCostFee; 
		}
}]);

