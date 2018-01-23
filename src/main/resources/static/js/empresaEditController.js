app.controller("empresaEditController", function ($scope, $http, $routeParams) {

	$scope.empresa =  {
			id: null,
			razaoSocial: null,
			cnpj: null,
			status: null,
			filiais: []
	};

	if($routeParams.empresaId){
		$http.get("/empresa/findById/"+$routeParams.empresaId).then(

				function(response){
					$scope.empresa = response.data;
					$routeParams.empresaId = null;
				}
				,function(response){
					window.alert("fail to get url /empresa/findById");
				});
	}

});