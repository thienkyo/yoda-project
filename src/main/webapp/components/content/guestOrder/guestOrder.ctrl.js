'use strict';
angular.module('guestOrderModule').controller('guestOrderController', ['$scope','$location','guestOrderService','cartService','OrderStatusArray','paginationService',
	function($scope,$location, guestOrderService,cartService,OrderStatusArray,paginationService) {
		var self = this;
		self.me = {};
		self.me.shipCostId = 0;
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
		
		function buildPageable(targetPage){
			var pageable ={};
			var size = 4;
			pageable.totalElements = self.orderList.length;
			pageable.totalPages = Math.ceil(pageable.totalElements/size);
			pageable.number = targetPage - 1;
			pageable.content = [];
			var start = size*(targetPage -1);
			var end   = size*targetPage;
			for(var i = start; i< end; i++){
				if(self.orderList[i]){
					pageable.content.push(self.orderList[i]);
				}
			}
			return pageable;
		}
		
		self.getTargetPage = function(targetpage){
			self.orderListPage = buildPageable(targetpage);
			self.pagination = paginationService.builder(self.orderListPage);
		} 
		
		self.getOrders = function(){
		    self.errorMsg = false;
		    const pattern = /^[0-9]{9,14}$/;
		    if(!pattern.test(self.phone)){
		        self.errorMsg = 'Chỉ nhập ít nhất 9 ký số';
		        return;
		    }

			guestOrderService.getGuestOrder(self.phone).then(function (response) {
			    console.log(response);
				for(var i = 0; i < response.length; i++){
                    var total = 0;
                    for(var k = 0; k < response[i].orderDetails.length; k++){
                        total += response[i].orderDetails[k].priceAtThatTime*response[i].orderDetails[k].quantity;
                    }
                    total += response[i].shipCostFee;
                    response[i].total = total;
                    for(var k = 0; k < OrderStatusArray.length; k++){
                        if(OrderStatusArray[k].value == response[i].status){
                            response[i].status = OrderStatusArray[k].name;
                            break;
                        }
                    }
                }
                self.orderList = response.reverse();
                self.orderListPage = buildPageable(1);
                self.pagination = paginationService.builder(self.orderListPage);
			});
		}
		
		self.showOrderDetail = function(order){
			self.theOrder = order;
			self.theOrder.subTotal = 0;
			for (var i = 0; i < self.theOrder.orderDetails.length; i++){
				self.theOrder.subTotal += self.theOrder.orderDetails[i].priceAtThatTime*self.theOrder.orderDetails[i].quantity;
			}
			self.theOrder.total = self.theOrder.subTotal + self.theOrder.shipCostFee; 
		}
}]);

