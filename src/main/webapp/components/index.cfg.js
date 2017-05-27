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
		.when('/blog', {
			templateUrl: 'components/content/blog/blog.html',
			controller: 'blogController',
			controllerAs:'ctrl'
		})
		.when('/blogDetail/:blogId', {
			templateUrl: 'components/content/blogDetail/blogDetail.html',
			controller: 'blogDetailController',
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
        .when('/mgnt/blogList',{
            templateUrl: 'components/mgnt/blog/list/blogList.html',
            controller: 'blogListController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/blogUpsert/:articleId',{
            templateUrl: 'components/mgnt/blog/upsert/blogUpsert.html',
            controller: 'blogUpsertController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/category',{
            templateUrl: 'components/mgnt/category/category.html',
            controller: 'categoryController',
            controllerAs:'ctrl'
        })
        .when('/mgnt/banner',{
            templateUrl: 'components/mgnt/banner/banner.html',
            controller: 'bannerController',
            controllerAs:'ctrl'
        })
         .when('/mgnt/member',{
            templateUrl: 'components/mgnt/member/list/memberList.html',
            controller: 'memberListController',
            controllerAs:'ctrl'
        })
        .otherwise(
            { redirectTo: '/'}
        );
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.interceptors.push('APIInterceptor');
});