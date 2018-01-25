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
			console.log("Fail to get url /empresa/findAll");
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
			var path = response.data.path.split('/');
			$scope.pathDownload = path[5] + '/'+  path[6];
			$window.open( response.data.path );
		}, function errorCallback(response) {
			console.log("Fail to get url /allToXml");
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
		}, function(rejection) {
			console.log("Fail to delete empresa/delete");
		});
		/*
		$http.delete("empresa/delete/" + $scope.empresaChecked , 
		).then(function(response){
			window.alert("Sucess!")
		},function(response){
			window.alert("Fail to delete empresa/delete");
		})
		*/
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