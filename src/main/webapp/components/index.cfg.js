angular.module('app')
.config(function(cfpLoadingBarProvider){
	cfpLoadingBarProvider.includeSpinner = true;
})
.config(function($routeProvider){
    $routeProvider
	    .when('/',{
	        templateUrl: 'components/content/home/home.html',
	        controller: 'homeController'
	    })
        .when('/home',{
            templateUrl: 'components/content/home/home.html',
            controller: 'homeController'	
        })
        .when('/category/:categoryId',{
            templateUrl: 'components/content/product/product.html',
            controller: 'productController'
        })
        .when('/productDetail/:prodId',{
            templateUrl: 'components/content/productDetail/productDetail.html',
            controller: 'productDetailController',
            controllerAs:'prodDtlCtrl'
        })
        .when('/login', {
			templateUrl: 'components/content/login/login.html',
			controller: 'loginController'
		})
		.when('/subpage', {
			templateUrl: 'subpage.html',
			controller: 'subpageController'
		})
        .otherwise(
            { redirectTo: '/'}
        );
});