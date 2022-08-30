'use strict';
angular.module('guestOrderModule')
.factory('guestOrderService', ['ajaxService',function(ajaxService) {
	var guestOrderService = {
			getGuestOrder : getGuestOrder,
			updateMe : updateMe
			};
	return guestOrderService;

   function getGuestOrder(phone){
		var url = "guest/getGuestOrder/"+phone;
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
   }
   
   function updateMe(me){
	   var url = "authenticated/updateMe";
		return ajaxService.post(url,me,{}).then(function(response){
			return response.data;
		});
   }
	      
 }]);
