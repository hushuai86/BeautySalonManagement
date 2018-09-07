 
app.controller('hairdressInfoCtr', ['$scope', '$http', 'toaster','$modal','$window', function($scope, $http, toaster,$modal,$window ){
	function get(){
		$http.get('/BeautySalonManagement/hairdresserInfo/getOne.do').success(function(data){
			 $scope.hairdresser=data.data;
			 console.log(data)
			 console.log( $scope.hairdresser)
		})
	}
	get();
	 $scope.save= function(form){
	    		if(form.name.$invalid){
					alert("昵称仅支持2-5位中文或2-10位英文")
					return ;
				}
				if(form.phone.$invalid){
					alert("请输入正确手机号")
					return ;
				}
	    	if (typeof FileReader != 'undefined') {
	    		var file = document.getElementById("file").files[0];
	    		if(file==null){
					var fd = new FormData();
					fd.append('code', $scope.hairdresser.code);
					fd.append('name', $scope.hairdresser.name);
					fd.append('phone', $scope.hairdresser.phone);
					$http({
						method : 'POST',
						url : "/BeautySalonManagement/hairdresserInfo/updateInfo2.do",
						data : fd,
						headers : {
							'Content-Type' : undefined
						},
						transformRequest : angular.identity
					}).success(function(response) {
						toaster.pop("success", "保存成功", "");
						get();
					});
	    	  }else{
	    		  if ((file.type).indexOf("image/") == -1) {
						toaster.pop('error', '请上传图片!', '');
					} else {
						var fd = new FormData();
						var file = document .querySelector('input[type=file]').files[0];
						fd.append('code', $scope.hairdresser.code);
						fd.append('file', file);
						fd.append('name', $scope.hairdresser.name);
						fd.append('phone', $scope.hairdresser.phone);
						$http({
							method : 'POST',
							url : "/BeautySalonManagement/hairdresserInfo/updateInfo2.do",
							data : fd,
							headers : {
								'Content-Type' : undefined
							},
							transformRequest : angular.identity
						}).success(function(response) {
							toaster.pop("success", "保存成功", "");
							$window.location.reload();
//							get();
						});
					}
	    	  }
	    	}
	    	
	 }
	     
 
}])
 