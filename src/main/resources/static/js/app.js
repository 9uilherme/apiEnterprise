var app = angular.module("App", ['ngRoute'])


app.config(function($routeProvider, $locationProvider) {

	$routeProvider
	.when("/empresaList", {templateUrl: 'view/empresaList.html', controller: 'empresaListController'})
	.when("/empresaEdit/:empresaId", {templateUrl: '/view/empresaEdit.html', controller: 'empresaEditController'})
	.when("/empresaEdit", {templateUrl: 'view/empresaEdit.html', controller: 'empresaEditController'})
	.otherwise({redirectTo:'/empresaList'});

	$locationProvider.html5Mode(
			{
				enabled: true,
				requireBase: false
			}
	);
	

})

app.controller("indexController", function($scope){

	$scope.empresaChecked =  {
			id: null,
			razaoSocial: null,
			cnpj: null,
			status: null,
			filiais: []
	};
});

