app.controller("empresaEditController", function ($scope, $http, $routeParams) {

	$scope.empresa =  {
			id: null,
			razaoSocial: null,
			cnpj: null,
			status: null,
			filiais: []
	};

	function findEmpresaById (id){
		$http.get("/empresa/findById/"+id).then(
				function(response){
					$scope.empresa = response.data;
				}
				,function(response){
					console.log("fail to get url /empresa/findById");
				});
	}

	function saveEmpresa (empresa) {
		$http({
			url: '/empresa/save',
			method: "POST",
			data: empresa
		})
		.then(function(response) {
			console.log("sucess!");
		}, 
		function(response) { 
			console.log("fail to get url /empresa/save");
		});
	}

	if($routeParams.empresaId){
		findEmpresaById($routeParams.empresaId);
		$routeParams.empresaId = null;
	}
	
	$scope.save = function () {
		$scope.empresa.status = "ativo";
		saveEmpresa($scope.empresa);
	}





});