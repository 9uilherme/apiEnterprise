var app = angular.module("App", ['ngRoute'])


app.config(function($routeProvider, $locationProvider) {

	$routeProvider
	.when("/empresaList", {templateUrl: 'view/empresaList.html', controller: 'empresaListController'})
	.when("/empresaEdit/:empresaId", {templateUrl: '/view/empresaEdit.html', controller: 'empresaEditController'})
	.when("/empresaEdit", {templateUrl: 'view/empresaEdit.html', controller: 'empresaEditController'})
	.when("/importacao", {templateUrl: 'view/importacao.html', controller: 'importacaoController'})
	.otherwise({redirectTo:'/empresaList'});

	$locationProvider.html5Mode(
			{
				enabled: true,
				requireBase: false
			}
	);
	

})

