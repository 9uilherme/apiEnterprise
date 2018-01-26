app.controller("empresaEditController", function ($scope, $http, $routeParams) {


	$scope.resetForm = function () {
		$scope.empresa =  {
				id: null,
				razaoSocial: null,
				cnpj: null,
				status: null,
				filiais: [
					{id: null,
						cnpj: null,
						municipio: null,
						status: null,
						uf: null}
					]
		};

	}
	function findEmpresaById (id){
		$http.get("/empresa/findById/"+id).then(
				function(response){
					$scope.empresa = response.data;
				}
				,function(response){
					console.log("fail to get url /empresa/findById");
				});
	}

	$scope.addFilial = function () {
		$scope.empresa.filiais.push(
				{id: null,
					cnpj: null});
	}
	$scope.removerFilial = function (filial) {
		$scope.empresa.filiais.splice($scope.empresa.filiais.indexOf(filial), 1);

	}
	function saveEmpresa (empresa) {
		$http({
			url: '/empresa/save',
			method: "POST",
			data: empresa
		})
		.then(function(response) {
			$scope.success = 'sucesso ao salvar!';
			$scope.erro = null;
		}, 
		function(response) { 
			$scope.erro = 'falha ao salvar!';
			$scope.success = null;
		});
	}

	if($routeParams.empresaId){
		findEmpresaById($routeParams.empresaId);

		$routeParams.empresaId = null;
	}

	$scope.save = function () {
		if($scope.formEmpresa.$valid){
			if($scope.empresa.filiais.length > 0){
				saveEmpresa($scope.empresa);
			}
		}
	}


	$scope.resetForm();



});