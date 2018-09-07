app
		.controller(
				'mainCtr',
				[
						'$scope',
						'$http',
						'toaster',
						'$modal',
						'$window',
						function($scope, $http, toaster, $modal, $window) {

							// 获取图片列表
							$http.get('/BeautySalonManagement/storeInfo/selectStoreInfo.do')
									.success(function(data) {
										$scope.info = data.data[0];
									})
							$http.get('/BeautySalonManagement/pic/selectPicList.do').success(
									function(data) {
										
										$scope.items = data.data
										console.log(data)
								 
									})
 

						} ])
