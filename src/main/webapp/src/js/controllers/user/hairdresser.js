app.controller('hairdresserCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		'$filter',
		function($scope, $http, toaster, $modal,$filter) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 10,
					index: 1
			};
			function get() {
				$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?type=2')
				.success(function(data) {
					console.log(data)
					$scope.temp=$filter('filter')(data.data, { status: 1});
					$scope.dates=$filter('filter')(data.data, { status: 1})
					vm.items =$scope.dates;		 
				})
 
			}
			get();
			//查询 
			$scope.code='';
			$scope.name='';
//			$scope.search=function(){
//				$scope.dates=$filter('filter')($scope.temp, { code: $scope.code,name:$scope.name})
//				vm.items =$scope.dates;	
//			}
			//排序
			$scope.sort='good';
			$scope.desc=true;
			$scope.hp=function(){
				$scope.sort='good';
				$scope.desc=true;
			}
			$scope.sj=function(){
				$scope.sort='date_of_entry';
				$scope.desc=true;
			}
		} ])
 