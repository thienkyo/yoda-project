'use strict';
angular.module('blogUpsertModule')
	.controller('blogUpsertController',['$rootScope','$routeParams','$location',
										   'blogUpsertService','memberService',
										   'CommonStatusArray',
										   'ArticleDO','Upload','$timeout',
	function($rootScope, $routeParams,$location,
			blogUpsertService,memberService,
			CommonStatusArray,
			ArticleDO,Upload,$timeout) {	
		
	var self = this;
	self.statusList = CommonStatusArray;
	
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	self.currentMember = memberService.getCurrentMember();
	
	if($routeParams.articleId > 0){
		blogUpsertService.getArticleById($routeParams.articleId)
			.then(function (data) {
				self.article = data;
		});

	}else{
		self.article = new ArticleDO();
	}
	
	self.clear = function(){
		self.responseStr = false;
		self.article = new ArticleDO();
	}
	
	self.upsert = function(){
		if(self.picFile){
			if(self.picFile.result){
				self.article.image = self.picFile.result;
			}
			
		}
		
		blogUpsertService.upsert(self.article)
		.then(function (data) {
				self.responseStr = data.replyStr;
		});
	}
	
	self.uploadPic = function(file,url) {
		    file.upload = Upload.upload({
		      url: url,
		      data: {username: 'testkyo', file: file},
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