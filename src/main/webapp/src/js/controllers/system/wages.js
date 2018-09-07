app.controller('wagesCtr', [
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

			// 打开模态框
			$scope.open = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'modal.html',
					controller : 'wagesModalCtr',
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

// 弹框操作
app.controller('wagesModalCtr', [
		'$scope',
		'$modalInstance',
		'$http',
		'toaster',
		'$state',
		'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
			$scope.item={}
			$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?code=' + tx)
					.success(function(data) {
						console.log(data);
						$scope.item = data.data[0];
					})
			$scope.save = function(form) {
				if (form.$invalid) {
					console.log("未填写")
					return;
				}
				var commission= $scope.item.commission;
				console.log(commission)
				if(commission!=$scope.item.commission.toFixed(2)){
					toaster.pop('error', '请输入不超过两位的小数', '');
					return;
				}
				
				var data = {
					code : tx,
					baseCharge : $scope.item.base_charge,
					commission : $scope.item.commission,
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