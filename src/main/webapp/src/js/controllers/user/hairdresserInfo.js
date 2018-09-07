app.controller('hairdresserInfoCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		'$filter',
		'$stateParams',
		'$window',
		function($scope, $http, toaster, $modal,$filter,$stateParams,$window) {
			var code=$stateParams.code;
			var account=angular.fromJson($window.sessionStorage["account"]);
			console.log(account)
			function get() {
				$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?code=' + code)
				.success(function(data) {
					console.log(data);
					$scope.item = data.data[0];
				})
			}
			get();
			$http.get('/BeautySalonManagement/code/selectItem.do').success(function(data) {
				console.log(data)
				$scope.codes = data.data;
			})
			function getFreeTime() {
			$http.get('/BeautySalonManagement/freeTime/selectItem.do?code=' + code).success(function(data) {
				console.log(data)
				 $scope.freetime = data.data;
				 $scope.am8= ($scope.freetime.am8==0);
				 $scope.am10= ($scope.freetime.am10==0);
				 $scope.am12= ($scope.freetime.am12==0);
				 $scope.pm2= ($scope.freetime.pm2==0);
				 $scope.pm4= ($scope.freetime.pm4==0);
				 $scope.pm6= ($scope.freetime.pm6==0);
				 $scope.pm8= ($scope.freetime.pm8==0);
				 $scope.busy = false; 
				 if((!$scope.am8 && !$scope.am10 && !$scope.am12 && !$scope.pm2 && !$scope.pm4 && !$scope.pm6 && !$scope.pm8)){
				 $scope.busy = true;
					}
				 console.log($scope.busy)
			})
			}
			//获取空闲下拉列表
			getFreeTime();
			//预约
			$scope.yy=function(form){
				if($scope.busy){
					toaster.pop('error', '美发师预约已满！', '');
					return ;
				}
				if ($scope.hairdressingProject==null||$scope.hairdressingProject=="") {
					toaster.pop('error', '请选择服务项目！', '');
					return ;
				}
				if($scope.serviceStartTime==""||$scope.serviceStartTime==null){
					toaster.pop('error', '请选择预约时间！', '');
					return ;
				}
				var date = new Date();
				var data={
						serviceStartTime:$scope.serviceStartTime,
						hairdresserInfoCode:code,
						appointmentStatus:1,
						userReservation:account.loginId,
						hairdressingProject:$scope.hairdressingProject
				}
				console.log(data);
				$http.post('/BeautySalonManagement/hairdressingReservation/addReservation.do',data).success(function(data) {
					console.log(data)
					if(data.code!=200){
						toaster.pop('error', data.msg, '');
					}else{
						getFreeTime();
						toaster.pop('success', '预约成功', '');
						$scope.serviceStartTime="";
						$("#serviceStartTime option:first").prop("selected", 'selected'); 
					}
				})
			}
			 
		} ])
 