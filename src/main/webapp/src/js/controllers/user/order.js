app.controller('orderCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		'$window',
		function($scope, $http, toaster, $modal,$window) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 10,
					index: 1
			};
			function get() {
				var account=angular.fromJson($window.sessionStorage["account"]);
		$http.get( '/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=2&userReservation='+account.loginId) .success(function(data) {
				$scope.orders = data.data;
				 vm.items = data.data;
			})
			}
			get();
			
			//查询
			$scope.search=function(){
				var account=angular.fromJson($window.sessionStorage["account"]);
				var data={
						accountFlag:2,
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
							accountFlag:2,
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
		 
			//取消预约
			$scope.deleteOne = function(item) {
				 //预约成功后取消预约 扣除违约金
				if(item.appointment_status==2){
					//违约金计算
					console.log(item.appointment_time)
					console.log(item.submit_appointment_time)
					var appointmentTime =new Date(item.appointment_time);
					var acceptAppointmentTime =new Date(item.accept_appointment_time);
					var appointmentTime =new Date(item.appointment_time);
					var hour = (appointmentTime-acceptAppointmentTime)/1000/(60*60);
					console.log(hour)
					var mony = item.price/hour; //每小时违约金
					console.log(mony)
					var date = new Date();
					var penalty = ((date-acceptAppointmentTime)/1000/(60*60))*mony;
					if(date-acceptAppointmentTime>=0){ //如果当前时间过了预订的服务时间，扣除所有服务费
						penalty=item.price;
					}
					
					console.log(penalty)
					layer.confirm(
							'现在取消预约将扣除'+ Math.ceil(penalty)+'元违约金，确定取消？',
							{
								title : '警告',
								btn : [ '确定', '取消' ]
							},
							function() { // 确定
								cancelReservation(item,penalty);
								layer.closeAll('dialog');
								alert("取消成功!")	
								history.go(0);
							}, function() { // 取消
							});
				}else{
					layer.confirm(
							'确定取消？',
							{
								title : '警告',
								btn : [ '确定', '取消' ]
							},function(){
								cancelReservation(item,null);
								layer.closeAll('dialog');
							},function(){});
				}
				
			}
			function cancelReservation(item,penalty){
				var data = {
						id : item.id,
						hairdresserInfoCode:item.hairdresser_info_code,
						appointmentTime:item.appointment_time,
						appointmentStatus:0,
						penalty:Math.ceil(penalty)
					}
					$http .post( '/BeautySalonManagement/hairdressingReservation/updateReservation.do',
							data).success(function(data) {
						alert("取消成功!")	
						searchData();
					})
			}
		
		} ])
 