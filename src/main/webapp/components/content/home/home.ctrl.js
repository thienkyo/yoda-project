'use strict';
angular.module('homeModule').controller('homeController', ['$scope','homeService',
	function($scope, homeService) {
		var self = this;
		console.log('homeController');
		
		homeService.getFirst6Product()
			.then(function (response) {
				$scope.first6products = response;
		        //console.log($scope.first6products);
			});
/*	
		homeService.getFirst6Product2()
		.then(function (response) {
			//console.log(response.data);
			$scope.first6products2 = response;
	        console.log($scope.first6products2);
		});
	//	ajaxService.test();
		homeService.getFirst6Product3();
	
		ajaxService.get("products/first6",null,{}).then(function(data){
			console.log(data.data);
			return data.data;
		});*/
}]);

