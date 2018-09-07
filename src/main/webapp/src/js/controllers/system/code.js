app.controller('codeCtr', [
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
				$http.get('/BeautySalonManagement/code/selectItem.do').success(function(data) {
					$scope.codes = data.data;
					 vm.items = data.data;
				})
			}
			function getDiscount() {
				$http.get('/BeautySalonManagement/code/getDiscount.do').success(function(data) {
					$scope.discount = data.data;
				})
			}
			get();
			getDiscount();

			// 删除
			$scope.deleteOne = function(id) {
				layer.confirm("删除后数据无法找回，确定删除？",{
					title:'警告',
					btn:['是','否']
				},function(){
					var data = {
							accountId : id
						}
						$http.post('/BeautySalonManagement/code/deleteItem.do', data).success(
								function(data) {
									toaster.pop('success', '删除成功', '');
									get();
									layer.closeAll('dialog');
								})
				},function(){})
			}
			// 打开增加 修改服务项目模态框
			$scope.open = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'modal.html',
					controller : 'codeModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}

				});
				modalInstance.result.then(function(data) {
					get();
				}, function() {

				});
			}
			
			// 打开discount模态框
			$scope.openDiscount = function(size, tx) {
				var modalInstance = $modal.open({
					templateUrl : 'discount_modal.html',
					controller : 'discountModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}

				});
				modalInstance.result.then(function(data) {
					getDiscount();
				}, function() {

				});
			};
		} ])

// 增加 修改服务项目操作
app.controller('codeModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state', 'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
			$scope.item = {};
			if(tx!=0){//增加
				$http.get('/BeautySalonManagement/code/getOne.do?id=' + tx).success(function(data) {
					console.log(data);
					$scope.item = data.data;
				}) 
			} 
			
			 $scope.save= function(form){
		    	if(form.$invalid){
		    		console.log("未填写")
		    		return;
		    	}if($scope.item.price<0){
		    		toaster.pop('error', '价格不能小于0元', '');
		    	}if($scope.item.time<10){
		    		toaster.pop('error', '时间不能小于10分钟', '');
		    	}if($scope.item.time>120){
		    		toaster.pop('error', '时间不能大于120分钟', '');
		    	}
		    	if(tx==0){//增加
		    		var data={
							name:$scope.item.name,
							description:$scope.item.description,
							price:$scope.item.price,
							time:$scope.item.time
						}
						$http.post('/BeautySalonManagement/code/insertItem.do', data).success(function(data) {
							console.log(data)
							if(data.code==200){toaster.pop('success', '保存成功', '');}
							else{
								toaster.pop('error', data.msg, '');
							}
							
							$modalInstance.close( );
						})
		    	}else{
		    		var data={
		    				name:$scope.item.name,
							description:$scope.item.description,
							price:$scope.item.price,
							time:$scope.item.time,
							id:tx
						}
						$http.post('/BeautySalonManagement/code/updateItem.do', data).success(function(data) {
							console.log(data)
							toaster.pop('success', '保存成功', '');
							$modalInstance.close( );

						})
		    	}
		    	
			}
			
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);
//弹框操作
app.controller('discountModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state', 'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
			$scope.item = {discount:tx};
			 $scope.saveDiscount= function(form){
				 console.log(form.discount.$invalid);
		    	if(form.discount.$invalid){
		    		return;
		    	}
		    	var data={
		    		discount:$scope.item.discount
		    	}
		    	if(data.discount<=0||data.discount>=10){
		    		toaster.pop('error', '折扣需在0-10之间', '');
		    		return;
		    	}
		    	var discount = data.discount;
		    	if(discount.toFixed(1)!=data.discount){
		    		toaster.pop('error', '折扣只允许一位小数', '');
		    		return;
		    	}
		    	
						$http.post('/BeautySalonManagement/code/updateDiscount.do',data).success(function(data) {
							console.log(data)
							toaster.pop('success', '保存成功', '');
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

