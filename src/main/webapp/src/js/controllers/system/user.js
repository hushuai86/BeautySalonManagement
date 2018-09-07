app.controller('userCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		function($scope, $http, toaster, $modal) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 10,
					index: 1
			};
			function getUserInfoList() {
				$http.get('/BeautySalonManagement/userInfo/selectUserInfoList.do?').success(
						function(data) {
							$scope.accounts = data.data;
							vm.items = data.data;
						})
			}
			getUserInfoList();

			// 删除
			$scope.update = function(id, status) {
				console.log(status)
				if(status==2){
					layer.confirm("确定删除？",{
						title : '提示',
						btn : [ '确定']
					},function(){updateUser()},function(){});
				}
				else if(status==1){
					layer.confirm("确定激活？",{
						title : '提示',
						btn : [ '确定']
					},function(){updateUser()},function(){});
				}
				else if(status==3){
					console.log(id);
					layer.confirm("删除后数据无法恢复，确定删除？",{
						title:'警告',
						btn:['是','否']
					},function(){
						var data = {
								accountId: id
							}
						$http.post('/BeautySalonManagement/account/deleteUser.do', data).success(
								function(data) {
								toaster.pop('success', '删除成功', '');
								layer.closeAll('dialog');
								getUserInfoList();
								})
					},function(){})
				}
				
				function updateUser(){
					var data = {
							id : id,
							status : status
						}
						$http.post('/BeautySalonManagement/account/updateInfo.do', data).success(
								function(data) {
									if(status==2){ 
										toaster.pop('success', '删除成功', '');
										}
									else if(status==1){  
										toaster.pop('success', '激活成功', '');
									}
									 getUserInfoList();
								})
								layer.closeAll('dialog');
				}
			}
			
			// 打开充值模态框 
			$scope.openRecharge = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'recharge.html',
					controller : 'rechargeModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}

				});
				modalInstance.result.then(function(data) {
					getUserInfoList();
				}, function() {

				});
			}
			// 打开添加会员模态框 
			$scope.addUser = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'user_modal.html',
					controller : 'addUserModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}

				});
				modalInstance.result.then(function(data) {
					getUserInfoList();
				}, function() {

				});
			}
			
		} ])
		
		//充值操作
app.controller('rechargeModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state', 'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
				$scope.item = {recharge:""};
			 $scope.saveRecharge= function(form){
		    	if(form.$invalid){
		    		return;
		    	}
		    	if($scope.item.recharge<50){
		    		toaster.pop('error', '充值不能少于50元', '');
		    		return;
		    	}
		    	if($scope.item.recharge%50!=0){
		    		toaster.pop('error', '充值金额需为50元的倍数', '');
		    		return;
		    	}
		    	var data={
		    		id:tx,
		    		recharge:$scope.item.recharge
		    	}
		    	console.log(data);
						$http.post('/BeautySalonManagement/userInfo/recharge.do',data).success(function(data) {
							toaster.pop('success', '充值成功', '');
							$modalInstance.close( );
						})
		    	
			}
			
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.closeDiscount = function(){
				$modalInstance.close();
			}
		} ]);

//添加会员操作
app.controller('addUserModalCtr', [ '$scope', '$modalInstance', '$http',
                             		'toaster', '$state', 'tx',
                             		function($scope, $modalInstance, $http, toaster, $state, tx) {
	 	$scope.item={}	
		$scope.save= function(form){
				 if (form.loginId.$invalid) {
						alert("账号仅支持6-10位英文字母加数字，首位必须是字母！")
						return;
					}
					if (form.name.$invalid) {
						alert("昵称仅支持2-5位中文或2-10位英文")
						return;
					}
					if (form.phone.$invalid) {
						alert("请输入正确手机号")
						return;
					}
		    	var data={
		    		loginId:$scope.item.loginId,
		    		name:$scope.item.name,
		    		sex:$scope.item.sex,
		    		password:'shaxuan',
		    		phone:$scope.item.phone
		    	}
		    	console.log(data);
						$http.post('/BeautySalonManagement/account/registerUser.do',data).success(function(data) {
							alert("添加成功！初始密码为 :shaxuan");
							$modalInstance.close( );
						})
		    	
			}
			
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close= function(){
				$modalInstance.close();
			}
		} ]);
