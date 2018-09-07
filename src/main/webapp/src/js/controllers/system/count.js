app.controller('countCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		function($scope, $http, toaster, $modal) {
			$scope.date='';
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 10,
					index: 1
			};
			function get() {
				$http.get('/BeautySalonManagement/hairdressingReservation/statisticalReport.do?excel=0').success(function(data) {
					console.log(data)
					$scope.items=data.data.list;
					$scope.allWages=data.data.allWages;
					$scope.turnover=data.data.turnover;
					$scope.profit=data.data.profit;
					 vm.items = data.data.list;
				})
			}
			get();
			$scope.search=function(){
				if($scope.date==''){
					get()
				}else{
					var data={
							startDate:$scope.date.substring(0,10),
							endDate:$scope.date.substring(13,23)
					}
					$http.post('/BeautySalonManagement/hairdressingReservation/statisticalReport.do?excel=0',data).success(function(data) {
						$scope.items=data.data.list;
						$scope.allWages=data.data.allWages;
						$scope.turnover=data.data.turnover;
						$scope.profit=data.data.profit;
						 vm.items = data.data.list;
					})
				}
				
				
			}
			$scope.down=function(){
				window.location.href="/BeautySalonManagement/hairdressingReservation/statisticalReport.do?excel=1" 
			}
			 
		} ])
 