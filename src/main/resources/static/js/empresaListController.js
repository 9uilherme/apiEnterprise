app.controller("empresaListController", function ($scope, $http, $window){

	$scope.isFiltros = false;
	$scope.filtro = {};
	$scope.pathDownload = "";
	$scope.empresaChecked =  {
			id: null,
			razaoSocial: null,
			cnpj: null,
			status: null,
			filiais: []
	};
	$scope.empresas = [];
	var urlMin = 'http://localhost:9090/';
	var urlToGet = urlMin + 'empresa/';
	
	function findAllEmpresas(){
		$http({
			method: 'GET',
			url: 'http://localhost:9090/empresa/findAll'
		}).then(function successCallback(response) {
			$scope.empresas = response.data;
		}, function errorCallback(response) {
		});
	};

	function findEmpresasByRazaoSocial (razaoSocial) {
		var url = urlToGet + 'findByRazaoSocial/' + razaoSocial;
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			$scope.empresas = [];
			angular.forEach(response.data, function(empresa){
				$scope.empresas.push(empresa);
			});
		}, function errorCallback(response) {
			console.log("Fail to get url /empresa/findByRazaoSocial");
		});
	}
	function findEmpresasByCnpj (cnpj){
		var url = urlToGet + 'findByCnpj/' + cnpj;
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			$scope.empresas = [];
			angular.forEach(response.data, function(empresa){
				$scope.empresas.push(empresa);
			});
		}, function errorCallback(response) {
			console.log("Fail to get url /empresa/findByCnpj");
		});
	}
	function findEmpresasByStatus (status){
		var url = urlToGet + 'findByStatus/' + status;
		$http({
			method: 'GET',
			url: url
		}).then(function successCallback(response) {
			$scope.empresas = [];
			angular.forEach(response.data, function(empresa){
				$scope.empresas.push(empresa);
			});
		}, function errorCallback(response) {
			console.log("Fail to get url /empresa/findByStatus");
		});
	}
	$scope.downloadXml = function () {
		var url = urlMin + 'allToXml';
		$http({
			method: 'GET',
			url: url

		}).then(function successCallback(response) {
			
			$scope.pathDownload = response.data;
			$scope.success = 'sucesso ao exportar, clique em download!';
			$scope.erro = null;
		}, function errorCallback(response) {

			$scope.erro = 'falha ao exportar xml!';
			$scope.success = null;
		});
	
		
	}
	findAllEmpresas();


	$scope.deleteEmpresa = function(){
		$http({
		    method: 'DELETE',
		    url: "empresa/delete/" + $scope.empresaChecked.id,
		    headers: {
		        'Content-type': 'application/json;charset=utf-8'
		    }
		})
		.then(function(response) {
			console.log(response);
			$scope.success = 'sucesso ao deletar empresa!';
			$scope.erro = null;
		}, function(rejection) {
			$scope.erro = 'falha ao deletar empresa!';
			$scope.success = null;
		});
	}


	$scope.filtroControle = function(){
		$scope.isFiltros = !$scope.isFiltros;
	};

	$scope.buscarFiltros = function (){
		if($scope.filtro.razaoSocial){ 
			findEmpresasByRazaoSocial($scope.filtro.razaoSocial);
		}else if($scope.filtro.cnpj){
			findEmpresasByCnpj($scope.filtro.cnpj);
		}else if($scope.filtro.status){
			findEmpresasByStatus($scope.filtro.status);
		}
	};

	$scope.limparFiltros = function () {
		$scope.filtro = {};
		findAllEmpresas();
	};


});