app.controller('passwordCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		function($scope, $http, toaster, $modal) {
			function get() {
				$http.get('/BeautySalonManagement/account/getSessionAccount.do').success(function(data) {
					$scope.account = data.data;
				})
			}
			get();
			$scope.save = function(form) {
				if (form.$invalid) {
					console.log("未填写")
					return;
				}
				if ($scope.newPassword!==$scope.newPassword2 ) {
					alert("两次输入的密码不一致，请重新输入！");
					return;
				}
				console.log($scope.password)
				var data = {
					"id" : $scope.account.id,
					"password":$scope.newPassword,
					"oldPassword":$scope.password
				}
				$http.post('/BeautySalonManagement/account/updatePassword.do', data).success(
						function(data) {
							if (data.code == "200") {
								toaster.pop('success', '修改成功! 即将跳转重新登录', '');
								var t=setTimeout("window.location.href = '/BeautySalonManagement/src/login.html'",2000);
							}else {
								toaster.pop('error', data.msg, '');
							}
						})
			}

		} ])

