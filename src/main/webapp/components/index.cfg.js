angular.module('app')
.config(function(cfpLoadingBarProvider){
	cfpLoadingBarProvider.includeSpinner = true;
})
.config(function($routeProvider,$httpProvider){
    $routeProvider
	    .when('/',{
	        templateUrl: 'components/content/home/home.html',
	        controller: 'homeController',
	        controllerAs:'ctrl'
	    })
        .when('/home',{
            templateUrl: 'components/content/home/home.html',
            controller: 'homeController',
            controllerAs:'ctrl'
        })
        .when('/category/:categoryId',{
            templateUrl: 'components/content/product/product.html',
            controller: 'productController',
            controllerAs:'ctrl'
        })
        .when('/productDetail/:prodId',{
            templateUrl: 'components/content/productDetail/productDetail.html',
            controller: 'productDetailController',
            controllerAs:'ctrl'
        })
        .when('/cart',{
            templateUrl: 'components/content/cart/cart.html',
            controller: 'cartController',
            controllerAs:'ctrl'
        })
        .when('/login', {
			templateUrl: 'components/content/login/login.html',
			controller: 'loginController',
			controllerAs:'ctrl'
		})
		.when('/account', {
			templateUrl: 'components/content/account/account.html',
			controller: 'accountController',
			controllerAs:'ctrl'
		})
		.when('/readme', {
			templateUrl: 'components/content/readme/readme.html'
		})
		.when('/contact', {
			templateUrl: 'components/content/contact/contact.html'
		})
		.when('/mgnt/productList',{
            templateUrl: 'components/mgnt/product/list/productList.html',
            controller: 'productListController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/productUpsert/:prodId',{
            templateUrl: 'components/mgnt/product/upsert/productUpsert.html',
            controller: 'productUpsertController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/orderList',{
            templateUrl: 'components/mgnt/order/list/orderList.html',
            controller: 'orderListController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/category',{
            templateUrl: 'components/mgnt/category/category.html',
            controller: 'categoryController',
            controllerAs:'ctrl'
        })
        .otherwise(
            { redirectTo: '/'}
        );
  //  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.interceptors.push('APIInterceptor');
});