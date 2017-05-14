'use strict';
angular.module('bannerModule')
	.controller('bannerController',['$rootScope','$location','memberService','bannerService',
									'NgTableParams','CommonStatusArray','BannerDO','Upload','$timeout',
	function($rootScope,$location,memberService,bannerService,NgTableParams,CommonStatusArray,BannerDO,Upload,$timeout) {	
	var self = this;
	self.theBanner = new BannerDO;
	self.statusList = CommonStatusArray;
	if(!memberService.isAdmin()){
		$location.path('#/');
	}
	
	bannerService.getBannerForMgnt().then(function (data) {
		self.BannerList = data;
		self.tableParams = new NgTableParams({}, { dataset: self.BannerList});
	});	
	
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
	
	self.upsert = function(banner){
		if(self.picFile){
			if(self.picFile.result){
				self.theBanner.image = self.picFile.result;
			}
			
		}
		
		self.responseStr = false;
		self.responseStrFail = false;
		bannerService.upsert(banner).then(function (data) {
			self.responseStr = data;
			if(banner.bannerId == 0){
				self.BannerList.push(data);
				self.tableParams = new NgTableParams({}, { dataset: self.BannerList});
			}
		});
	}
	
	self.updateBanner = function(banner){
		self.theBanner = banner;
		self.responseStr = false;
		self.responseStrFail = false;
	}
	
	self.clear = function(){
		self.responseStr = false;
		self.responseStrFail = false;
		self.theBanner = new BannerDO;
	}
}]);