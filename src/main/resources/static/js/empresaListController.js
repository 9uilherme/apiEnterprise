app.controller("empresaListController", function ($scope, $http){

	$scope.isFiltros = false;
	$scope.filtro = {};
	$scope.empresaChecked =  {
			id: null,
			razaoSocial: null,
			cnpj: null,
			status: null,
			filiais: []
	};
	
	$scope.findAllEmpresas = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:9090/empresa/findAll'
		}).then(function successCallback(response) {
			$scope.empresas = response.data;
		}, function errorCallback(response) {
			window.alert("Fail to get url /empresa/findAll");
		});
	};
	
	$scope.findAllEmpresas();
	

	

	$scope.filtroControle = function(){
		$scope.isFiltros = !$scope.isFiltros;
	};
	
	$scope.buscarFiltros = function (){
		var urlToGet = 'http://localhost:9090/empresa/';
		if($scope.filtro.razaoSocial){ 
			urlToGet = urlToGet + 'findByRazaoSocial/' + $scope.filtro.razaoSocial;
			$http({
				method: 'GET',
				url: urlToGet
			}).then(function successCallback(response) {
				$scope.empresas = [];
				$scope.empresas.push(response.data);
			}, function errorCallback(response) {
				window.alert("Fail to get url /empresa/findByRazaoSocial");
			});
		}
	};
	
	$scope.limparFiltros = function () {
		$scope.filtro = {};
		$scope.findAllEmpresas();
	};
	
	
});