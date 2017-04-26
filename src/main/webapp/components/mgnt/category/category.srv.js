'use strict';
angular.module('categoryModule')
.factory('categoryService', ['ajaxService',function(ajaxService) {
		var categoryService = {
				getAllCategories : getAllCategories,
				getActiveCategories : getActiveCategories
			};
	return categoryService;
	
	function getAllCategories(){
		var url = "mgnt/getAllCategories";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
	
	function getActiveCategories(){
		var url = "categories/1";
		return ajaxService.get(url,null,{}).then(function(response){
			return response.data;
		});
	}
      
 }]);