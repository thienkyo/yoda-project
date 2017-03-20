angular.module('app')
.service('APIInterceptor', function($q,$rootScope, memberService) { 
	var service = this;
    service.request = function(config) {
        var currentMember = memberService.getCurrentMember();
        var	authen_token = currentMember ? currentMember.authen_token : null;
        if (authen_token) {
            config.headers.authorization = authen_token;
        }
        return config;
    };
    service.responseError = function(response) {
    	console.log('response');
    	//response.status = 401;
    	 if (response.status === 401) {
             $rootScope.$broadcast('unauthorized');
            // console.log('401');
         }
         return $q.reject(response);;
    };
    
});