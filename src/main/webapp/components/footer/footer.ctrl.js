angular.module('app').controller('footerController', function ( $scope ,$location ){
	 console.log('footer') ;
	 $scope.showtech = function(){
		 console.log('footer : clicked') ;
	 };
});
