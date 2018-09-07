 
app.controller('userInfoCtr', ['$scope', '$http', 'toaster','$modal','$window', function($scope, $http, toaster,$modal,$window ){
	function get(){
		$http.get('/BeautySalonManagement/userInfo/getUserInfo.do').success(function(data){
			 $scope.user=data.data;
			 console.log(data)
			 console.log($scope.user)
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
					var fd = new FormData();
					fd.append('id', $scope.user.id);
					fd.append('name', $scope.user.name);
					fd.append('phone', $scope.user.phone);
					$http({
						method : 'POST',
						url : "/BeautySalonManagement/userInfo/updateUserInfo.do",
						data : fd,
						headers : {
							'Content-Type' : undefined
						},
						transformRequest : angular.identity
					}).success(function(response) {
						toaster.pop("success", "保存成功", "");
						get();
					});
	    	}
}])
 