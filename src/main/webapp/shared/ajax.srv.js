angular.module('networkServices',[])
	.factory('ajaxService',['$http','$cookies',function($http,$cookies){
		
		//var urlbase = "http://54.255.250.109:8080/yoda/";
		//var urlbase = "http://kyoshop.org/";
		var urlbase = "http://localhost:8080/";
		var sessionid = $cookies.get('JSESSIONID');
		
		var config = {
				headers:{
					'Accept': 'application/json',
					'Content-Type': 'application/json' ,
					'requestType':'angularJS',
					'Cache-Control': 'no-cach, no-store, must-revalidate',
					'Pragame':'no-catch',
					'X-Testing': sessionid,
					'Expries': 0,
					action: ''
				},
				params:{}
		};

		var ajaxService = {
			get : get,
			post: post
		};

		return ajaxService;

		function get(url,action,params){
			close.params = params || {};
			config.params["timeStamp"] = (new Date()).getTime();
			config.headers.action = action || '';
            url = urlbase + url;
			return $http.get(url,config);
		}

		function post(url,data,action,params) {
			close.params = params || {};
			config.params["timeStamp"] = (new Date()).getTime();
			config.headers.action = action || '';
			url = urlbase + url;
			return $http.post(url,data,config);
		}
}]);