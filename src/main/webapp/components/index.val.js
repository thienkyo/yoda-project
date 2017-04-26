'use strict';
var OrderStatusArray=[
	{name : 'đã đặt', value:20 },
	{name : 'nhận tiền', value:21 },
	{name : 'đã chuyển hàng ', value:22 },
	{name : 'xong', value:23 }
];

var CommonStatusArray=[
	{name : 'active', value:1 },
	{name : 'inactive', value:0 }
];

angular
		.module('app') 
		.value('MemberDO', MemberDO)
		.value('OrderDO',OrderDO)
		.value('OrderDetailDO',OrderDetailDO)
		.value('OrderStatusArray',OrderStatusArray)
		.value('CommonStatusArray',CommonStatusArray)
		.value('ProductDO',ProductDO); 

function OrderDO () {
	this.orderId = 0;
	this.shippingAddress = '';
	this.status = 20;
	this.discount = 0;
	this.modDate = (new Date()).getTime();
	this.shipCostFee = 0;
	this.member = null;
	this.orderDetails = [];
}

function OrderDetailDO () {
	this.orderDetailId = 0;
	//this.orderId = 0;
	this.product = null;
	this.priceAtThatTime = 1;
	this.discountAtThatTime = 0;
	this.quantity = 1;
	this.weight = 1;
	this.modDate = (new Date()).getTime();
}

function MemberDO () {
	this.memberId = 0;
	this.fullName = '';
	this.email = '';
	this.pass  = '';
	this.phone = '';
	this.country = '';
	this.city    = '';
	this.district = '';
	this.street   = '';
	this.address  = '';
	this.postCode = '';
	this.status   = 1;
	this.modDate = (new Date()).getTime();
	
		this.clear = function() { 
		this.memberId = 0;
		this.fullName = '';
		this.email = '';
		this.pass  = '';
		this.phone = '';
		this.country = '';
		this.city    = '';
		this.district = '';
		this.street   = '';
		this.address  = '';
		this.postCode = '';
		this.status   = 1;
		this.modDate ='';
		}
}

function ProductDO(){
	this.prodId = 0;
	this.description = '';
	this.discount = 0;
	this.mod_date = (new Date()).getTime();
	this.notification = 'còn hàng';
	this.price = 1000;
	this.prodName = '';
	this.quantity = 1;
	this.status = 1;
	this.weight = 0.1;
	this.category={categoryId:1};
}

