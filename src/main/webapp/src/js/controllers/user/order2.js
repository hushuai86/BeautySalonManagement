app.controller('order2Ctr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		'$window',
		function($scope, $http, toaster, $modal,$window) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size:10,
					index: 1
			};
			function get() {
				var account=angular.fromJson($window.sessionStorage["account"]);
				console.log(account)
		$http.get( '/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=7&userReservation='+account.loginId) .success(function(data) {
				$scope.orders = data.data;
				 vm.items = data.data;
			})
				
			}
			get();
			//查询
			$scope.search=function(){
				var account=angular.fromJson($window.sessionStorage["account"]);
				var data={
						accountFlag:7,
						userReservation:account.loginId,
						hairdresserInfoCode:$scope.code,
						appointmentStatus:$scope.status,
						codeName:$scope.name
				}
				console.log(data)
				 $http.post("/BeautySalonManagement/hairdressingReservation/userReservation.do",data).success(
							function(data) {
								 vm.items = data.data;
								 $scope.orders = data.data;
							})
				 
			 }
			function searchData(){
				var account=angular.fromJson($window.sessionStorage["account"]);
				var data={
						accountFlag:7,
						userReservation:account.loginId,
						hairdresserInfoCode:$scope.code,
						appointmentStatus:$scope.status,
						codeName:$scope.name
				}
				console.log(data)
				 $http.post("/BeautySalonManagement/hairdressingReservation/userReservation.do",data).success(
							function(data) {
								 vm.items = data.data;
								 $scope.orders = data.data;
							})
			}
			
		 
			//评价订单
			$scope.evaluate = function(id,evaluate) {
				var data ={
						id:id,
						evaluate:evaluate
				}
				$http.post('/BeautySalonManagement/hairdressingReservation/updateReservation.do', data).success(
						function(data) {
							toaster.pop('success', '评价成功', '');
							searchData();
						})
				
			};
		  
		} ])
	