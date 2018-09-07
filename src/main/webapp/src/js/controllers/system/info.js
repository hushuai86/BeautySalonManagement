app.controller('infoCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		function($scope, $http, toaster, $modal) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 5,
					index: 1
			};
			function get() {
				$http.get('/BeautySalonManagement/storeInfo/selectStoreInfo.do').success(
						function(data) {
							$scope.info = data.data[0];
						})
				$http.get('/BeautySalonManagement/pic/selectPicList.do').success(
						function(data) {
							$scope.items=data.data
							 vm.items = data.data;
						})
			}
			get();
			$scope.save = function(form) {
				if (form.$invalid) {
					console.log("未填写")
					return;
				}
				var data = {
					"id" : $scope.info.id,
					"name" : $scope.info.name,
					"phone" : $scope.info.phone,
					"address" : $scope.info.address,
					"description" : $scope.info.description
				}
				$http.post('/BeautySalonManagement/storeInfo/updateStoreInfo.do', data).success(
						function(data) {
							if (data.code == "200") {
								alert("修改成功！")
								history.go(0)	
							} else {
								toaster.pop('error', '修改失败', '');
							}
							get();
						})
			}
			// 删除
			$scope.deleteOne = function(id) {
				layer.confirm("确定删除？",{
					title:"提示",
					btn:['是','否']
				},function(){
					var data = {
							id : id
						}
						$http.post('/BeautySalonManagement/pic/deletePic.do', data).success(
								function(data) {
									if (data.code == "200") {
										toaster.pop('success', '删除成功', '');
									} else {
										toaster.pop('error', '删除失败', '');
									}
									layer.closeAll("dialog");
									get();
								})
				},function(){})
				
			}
			// 打开模态框
			$scope.open = function(size) {
				var modalInstance = $modal.open({
					templateUrl : 'modal.html',
					controller : 'infoModalCtr',
					size : size,

				});
				modalInstance.result.then(function(data) {
					get()
				}, function() {

				});
			};
		} ])

// 弹框操作
app .controller(
				'infoModalCtr',
				[
						'$scope',
						'$modalInstance',
						'$http',
						'toaster',
						'$state',
						function($scope, $modalInstance, $http, toaster, $state) {
							$scope.savePhoto = function(form) {
								if (form.$invalid) {
									console.log("未填写")
									return;
								}

								if (typeof FileReader != 'undefined') {
									var file = document.getElementById("file").files[0];
									if ((file.type).indexOf("image/") == -1) {
										toaster.pop('error', '请上传图片!', '');
									} else {
										var fd = new FormData();
										var file = document
												.querySelector('input[type=file]').files[0];
										fd.append('file', file);
										$http({
											method : 'POST',
											url : "/BeautySalonManagement/pic/insertPic.do",
											data : fd,
											headers : {
												'Content-Type' : undefined
											},
											transformRequest : angular.identity
										}).success(function(response) {
											toaster.pop("success", "保存成功", "");
											$modalInstance.close();
										});
									}
								} else {
									var fileName = document .getElementById("file").value;
									var suffixIndex = fileName.lastIndexOf(".");
									var suffix = fileName.substring(
											suffixIndex + 1).toUpperCase();
									if (suffix != "BMP" && suffix != "JPG"
											&& suffix != "JPEG"
											&& suffix != "PNG"
											&& suffix != "GIF") {
										toaster .pop( 'error', '请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!', '');
									}
								}
							}
							$scope.ok = function() {
								$modalInstance.dismiss('cancel');
							};
							$scope.close = function() {
								$modalInstance.close();
							}
						} ]);