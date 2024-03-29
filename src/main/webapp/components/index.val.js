'use strict';
var OrderStatusArray=[
	{name : 'init', value:19 },
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
		.value('ProductDO',ProductDO)
		.value('CategoryDO',CategoryDO)
		.value('ArticleDO',ArticleDO)
		.value('BannerDO',BannerDO)
		.value('ShipCostDO',ShipCostDO)
		.value('PaginationItemDO',PaginationItemDO)
		.value('PaginationDO',PaginationDO);

function OrderDO () {
	this.orderId = 0;
	this.shippingAddress = '';
	this.status = 19;
	this.discount = 0;
	this.modDate = (new Date()).getTime();
	this.shipCostFee = 0;
	this.shipCostId = 7;
	this.member = null;
	this.shippingName = '';
	this.shippingPhoneNumber ='';
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
	this.imageNames = '';
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
	this.shipCostId=0;
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
	this.image = '';
	this.extInfo = '{\n\t\"needImage\":false,\n\t\"minNumberOfImage\":0,\n\t\"maxNumberOfImage\":0\n}';
	this.needImage = 0;
	this.minNumberOfImage = 0;
	this.maxNumberOfImage = 0;
}

function CategoryDO(){
	this.categoryId = 0;
	this.categoryName = 'cate name';
	this.status = 1; 
	this.mod_date = (new Date()).getTime();
}

function ArticleDO(){
	this.articleId = 0;
	this.description = '';
	this.mod_date = (new Date()).getTime();
	this.content = '';
	this.articleName = '';
	this.image = '';
	this.status = 1;
	this.author = '';
}

function BannerDO(){
	this.bannerId = 0;
	this.categoryName = 'banner name';
	this.status = 1; 
	this.mod_date = (new Date()).getTime();
	this.image='';
	this.description='';
	this.link='';
}

function ShipCostDO(){
	this.shipCostId = 0;
	this.region = 'region name';
	this.status = 0;
	this.price=0;
	this.distance=0
}

function PaginationItemDO() {
	this.first = false;
	this.last = false;
	this.number = 0;
	this.status = false;
}

function PaginationDO() {
	this.currentNumber = 1;
	this.previousNumber = 1;
	this.nextNumber = 1;
	this.list = [];
	this.clear = function() { 
		this.list = [];
	}
}
