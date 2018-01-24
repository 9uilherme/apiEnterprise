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
	$scope.empresas = [];

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
			console.log($scope.empresas.indexof(response.data));
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
				console.log("Fail to get url /empresa/findByRazaoSocial");
			});
		}
	};

	$scope.limparFiltros = function () {
		$scope.filtro = {};
		findAllEmpresas();
	};


});