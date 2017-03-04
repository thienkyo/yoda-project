angular.module('app').controller('appcontroller',['$scope','cfpLoadingBar', function($scope,cfpLoadingBar) {
	console.log('app controller');
    $scope.headingTitle = "app controller";
}]);