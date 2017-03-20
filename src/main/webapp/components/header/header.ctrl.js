angular.module('app').controller('headerController', ['$http','$rootScope','$scope','$location','memberService',
	function($http,$rootScope,$scope,$location,memberService) {
	console.log('header ctrl');
	var self=this;
	self.currentMember = memberService.getCurrentMember();
	//console.log(self.currentMember);
	self.logout = function() {
		//self.email = '';
		//self.token = null;
		self.currentMember = memberService.setCurrentMember(null);
		console.log('header logout clicked');
		$location.path('#/');
        //$rootScope.authenticated = false;
       // $http.defaults.headers.common.Authorization = '';
    }
	
	$rootScope.$on('authorized', function() {
		self.currentMember = memberService.getCurrentMember();
    });
    $rootScope.$on('unauthorized', function() {
    	console.log('in headerctrl unauthorized event');
        self.currentMember = memberService.setCurrentMember(null);
        $location.path('#/');
    });

}]);