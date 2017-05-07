'use strict';
angular.module('orderListModule')
	.controller('orderListController',['$rootScope','$routeParams','$location',
										 'memberService','orderListService',
										 'NgTableParams','OrderStatusArray',
	function($rootScope, $routeParams,$location,memberService,orderListService,NgTableParams,OrderStatusArray) {	
//	console.log('orderListController');
	var self = this;
	self.orderList = [];
	self.OrderStatusArray=OrderStatusArray;
	self.statusStyle = { "width": "120px" };
	self.statusNumber = {};
	self.statusNumber.ordered = 0;
	self.statusNumber.paid = 0;
	self.statusNumber.shipped = 0;
	self.statusNumber.done = 0;
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	self.currentMember = memberService.getCurrentMember();
	
	self.amountList=[
		{name : '20', value:20 },
		{name : 'all', value:0 }
	];
	
	self.amount = 20;
	self.categoryId = 0;
	
	
	orderListService.getOrdersForMgnt(self.amount).then(function (data) {
		//console.log(data);
		self.orderList = data;
	/*  self.statusNumber.ordered = 0;
		self.statusNumber.paid = 0;
		self.statusNumber.shipped = 0;
		self.statusNumber.done = 0;
		
		for(var i = 0; i < data.length; i++){
			var total = 0;
			for(var k = 0; k < data[i].orderDetails.length; k++){
				total += data[i].orderDetails[k].priceAtThatTime*data[i].orderDetails[k].quantity;
			}
			total += data[i].shipCostFee;
			data[i].total = total;
			if(data[i].status == 20){
				self.statusNumber.ordered += 1;
			}
			if(data[i].status == 21){
				self.statusNumber.paid += 1;
			}
			if(data[i].status == 22){
				self.statusNumber.shipped += 1;
			}
			if(data[i].status == 23){
				self.statusNumber.done += 1;
			}
		}
	*/	engineerOrderList();
		self.tableParams = new NgTableParams({}, { dataset: self.orderList});
	});
	
	 function calculateTotal(order){
		var total = 0;
		for(var k = 0; k < order.orderDetails.length; k++){
			total += order.orderDetails[k].priceAtThatTime*order.orderDetails[k].quantity;
		}
		total += order.shipCostFee;
		return total;
	}
	 
	self.getOrderStatusName = function(value){
		for(var k = 0; k < OrderStatusArray.length; k++){
			if(OrderStatusArray[k].value == value){
				return OrderStatusArray[k].name;
				break;
			}
		}
	}
	
	self.updateOrderStatus = function(){
		orderListService.updateOrderStatus(self.theOrder.orderId,self.newOrderStatus).then(function(data){
			self.responseStr = data.replyStr;
			self.theOrder.status = self.newOrderStatus;
			engineerOrderList();
		});
		
	}
	
	self.getOrderbyTerm = function(){
		orderListService.getOrdersForMgnt(self.amount).then(function (data) {
			self.orderList = data;;
			engineerOrderList();
			self.tableParams = new NgTableParams({}, { dataset: self.orderList});
		});
	}
	
	self.setStyle = function(status){
		if(status==20){
			self.statusStyle.color = "limegreen";
		}else if(status==21){
			self.statusStyle.color = "blue";
		}
		else{
			self.statusStyle = { "width": "120px" }
		}
		return self.statusStyle;
	}

	self.showOrderDetail = function(order){
		if(self.responseStr || self.responseStrFail){
			self.responseStr = false;
		}
		self.newOrderStatus = order.status;
		self.theOrder = order;
		
		self.theOrder.subTotal = 0;
		for (var i = 0; i < self.theOrder.orderDetails.length; i++){
			self.theOrder.subTotal += self.theOrder.orderDetails[i].priceAtThatTime*self.theOrder.orderDetails[i].quantity;
		}
		//self.theOrder.total = self.theOrder.subTotal + self.theOrder.shipCostFee; 
	}
	
	self.deleteOrder = function(order){
		console.log(order);
		orderListService.deleteOrder(order).then(function (data) {
			self.responseStr = data;
			console.log(self.responseStr);
		});
	}
	
	function engineerOrderList(){
		self.statusNumber.ordered = 0;
		self.statusNumber.paid = 0;
		self.statusNumber.shipped = 0;
		self.statusNumber.done = 0;
		
		for(var i = 0; i < self.orderList.length; i++){
			var total = 0;
			for(var k = 0; k < self.orderList[i].orderDetails.length; k++){
				total += self.orderList[i].orderDetails[k].priceAtThatTime*self.orderList[i].orderDetails[k].quantity;
			}
			total += self.orderList[i].shipCostFee;
			self.orderList[i].total = total;
			if(self.orderList[i].status == 20){
				self.statusNumber.ordered += 1;
			}
			if(self.orderList[i].status == 21){
				self.statusNumber.paid += 1;
			}
			if(self.orderList[i].status == 22){
				self.statusNumber.shipped += 1;
			}
			if(self.orderList[i].status == 23){
				self.statusNumber.done += 1;
			}
		
		}
	}
	
}]);