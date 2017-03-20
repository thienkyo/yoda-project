'use strict';

angular
		.module('app') // load the student module
		.value('MemberDO', MemberDO); // add a value to the student module
		
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