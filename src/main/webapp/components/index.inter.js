angular.module('app')
.service('APIInterceptor', function($q,$rootScope, memberService, shipStoreService) { 
	var service = this;
    service.request = function(config) {
        var currentMember = memberService.getCurrentMember();
        var	authen_token = currentMember ? currentMember.token : null;
        if (authen_token) {
            config.headers.authorization = authen_token;
        }
        return config;
    };
    service.responseError = function(response) {
    	 if (response.status === 401) {
             $rootScope.$broadcast('unauthorized');
         }
    	 
    	 if (response.status === 500) {
    		 if(response.data.exception == 'io.jsonwebtoken.ExpiredJwtException'){
    			 $rootScope.$broadcast('ExpiredJwt');
    		 }
         }
    	 
         return $q.reject(response);;
    };
    
});