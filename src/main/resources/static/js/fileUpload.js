app.directive('fileUpload', function ($http) {
	return {
		templateUrl:'view/uploadFileView.html',
		link: function (scope, element) {

			scope.fileName = 'Escolha um arquivo...';

			element.bind('change', function () {
				scope.$apply(function () {
					scope.fileName = document.getElementById('uploadFileInput').files[0].name;
				});
			});

			scope.uploadFile = function(){
				var formData = new FormData();

				formData.append('file', document.getElementById('uploadFileInput').files[0]);

				$http({ 
					method: 'POST',
					url: 'http://localhost:9090/xmlToObj',
					data: document.getElementById('uploadFileInput').files[0],
					headers: { "Content-Type": 'text/xml' }
				}).then(function success(result){
					console.log(result);
					scope.success = 'sucesso na importação!';
					scope.erro = null;
				}, function fail(error){
					console.log(error);
					scope.erro = 'falha na importação!';
					scope.success = null;
				});

			};
		}
	};
});