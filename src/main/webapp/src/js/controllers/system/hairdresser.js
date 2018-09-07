app.controller('hairdresserCtr', [
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
			function get() {
				$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?type=2')
						.success(function(data) {
							 vm.items = data.data;
							$scope.hairs = data.data;
						})
			}
			get();
			$scope.save = function(form) {
				if (form.$invalid) {
					console.log("未填写")
					return;
				}
				var data = {
					"id" : $scope.hairdresser.id,
					"name" : $scope.hairdresser.name,
					"phone" : $scope.hairdresser.phone,
					"address" : $scope.hairdresser.address
				}
				console.log(data)
				$http.post('/BeautySalonManagement/storehairdresser/updateStorehairdresser.do',
						data).success(function(data) {
					if (data.code == "200") {
						toaster.pop('success', '修改成功', '');
					} else {
						toaster.pop('error', '修改失败', '');
					}
					get();
				})
			}
			// 更新状态
			$scope.update = function(item, status) {
				if(status==1){
					layer.confirm("确定激活？",{
						title:'提示',
						btn:['是','否']
					},function(){
						updateInfo();
						layer.closeAll('dialog');
					},function(){})
				}
				if(status==2){
					layer.confirm("确定删除？",{
						title:'提示',
						btn:['是','否']
					},function(){
						updateInfo();
						layer.closeAll('dialog');
					})
				}
				if(status==3){
					layer.confirm("删除后数据无法恢复，确定删除？",{
						title:'警告',
						btn:['是','否']
					},function(){
						var data = {
								accountId : item.id
							}
						$http.post('/BeautySalonManagement/account/deleteHairdresser.do', data).success(
								function(data) {
								toaster.pop('success', '删除成功', '');
								layer.closeAll('dialog');
								get();
								})
					},function(){})
				}
				function updateInfo(){
				var data = {
					id : item.id,
					status : status
				}
				console.log(data);
				$http.post('/BeautySalonManagement/account/updateInfo.do', data).success(
						function(data) {
							if(status==2){
								toaster.pop('success', '删除成功', '');
							}else if(status==1){
								toaster.pop('success', '激活成功', '');
							}
							
							get();
						})
				}
			}
				
			// 打开模态框
			$scope.open = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'modal.html',
					controller : 'hairdresserModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}
				});
				modalInstance.result.then(function(data) {
					get()
				}, function() {

				});
			};
		} ])

// 修改美发师信息
app.controller('hairdresserModalCtr', [
		'$scope',
		'$modalInstance',
		'$http',
		'toaster',
		'$state',
		'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
			$scope.item = {};
			$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?code=' + tx)
					.success(function(data) {
						console.log(data);
						$scope.item = data.data[0];
					})
					  $scope.save= function(form){
		    	if(form.$invalid){
		    		console.log("未填写")
		    		return;
		    	}
		    	var data = {
						code : tx,
						dateOfEntry : $scope.item.date_of_entry,
						name : $scope.item.name,
						phone:$scope.item.phone,
					}
					console.log(data);
		    	$http.post('/BeautySalonManagement/account/updateInfo2.do', data).success(
						function(data) {
							toaster.pop('success', '修改成功', '');
							$modalInstance.close();
						})
			}
			

			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);