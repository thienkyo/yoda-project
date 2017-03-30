'use strict';

angular
		.module('app') 
		.value('MemberDO', MemberDO)
		.value('ItemDO',ItemDO); 

function ItemDO () {
	this.prodID = 0;
	this.quantity = 1;
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