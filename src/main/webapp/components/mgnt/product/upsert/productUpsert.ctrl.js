'use strict';
angular.module('productUpsertModule')
	.controller('productUpsertController',['$rootScope','$routeParams','$location',
										   'productUpsertService','memberService',
										   'productDetailService','CommonStatusArray',
										   'ProductDO','Upload','$timeout','categoryService',
	function($rootScope, $routeParams,$location,
			productUpsertService,memberService,
			productDetailService,CommonStatusArray,
			ProductDO,Upload,$timeout,categoryService) {	
		
	var self = this;
	self.statusList = CommonStatusArray;
	
	categoryService.getActiveCategories()
	.then(function (data) {
			self.categories = data;
	});
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
//	self.currentMember = memberService.getCurrentMember();
/*	
	if(!self.currentMember || self.currentMember.roles.indexOf("ADMIN") == -1){
		$location.path('#/');
	}
*/	
	
	
	if($routeParams.prodId > 0){
		productUpsertService.getProductById($routeParams.prodId)
			.then(function (data) {
				self.product = data;
		});

	}else{
		self.product = new ProductDO();
	}
	
	self.upsert = function(){
		if(self.picFile){
			if(self.picFile.result){
				self.product.image = self.picFile.result;
			}
		}
		
		productUpsertService.upsert(self.product)
		.then(function (data) {
				self.responseStr = data.replyStr;
		});
	}
	
	self.uploadPic = function(file,url) {
		    file.upload = Upload.upload({
		      url: url,
		      data: {oldName: url == 'mgnt/uploadfile2' ? self.product.image : '', file: file},
		      headers:{'Content-Type':'application/x-www-form-urlencoded;charset=utf-8'}
		    });

		    file.upload.then(function (response) {
		      $timeout(function () {
		        file.result = response.data;
		      });
		    }, function (response) {
		      if (response.status > 0)
		        self.errorMsg = response.status + ': ' + response.data;
		    }, function (evt) {
		      // Math.min is to fix IE which reports 200% sometimes
		      file.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
		    });
	}
	/////////////////////////tinyMCE/////////////////////
	self.tinymceOptions = { 
		      height: 800,
			  theme: 'modern',
			  plugins: [
			    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
			    'searchreplace wordcount visualblocks visualchars code fullscreen',
			    'insertdatetime media nonbreaking save table contextmenu directionality',
			    'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc'
			  ],
			  toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
			  toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',
			  image_advtab: true,
			  templates: [
			    { title: 'Test template 1', content: 'Test 1' },
			    { title: 'Test template 2', content: 'Test 2' }
			  ]
		  };
	
}]);