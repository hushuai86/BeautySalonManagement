app.controller('orderCtr', [
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
				$http .get( '/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=2') .success(function(data) {
					$scope.orders = data.data;
					 vm.items = data.data;
				})
			}
			get();
			//查询
			$scope.search=function(){
				var data={
						accountFlag:2,
						userReservation:$scope.user_reservation,
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
				var data={
						accountFlag:2,
						userReservation:$scope.user_reservation,
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
			
			// 预约操作
			$scope.update = function(item,status) {
				console.log(item)
				var statusCase = status; 
				if(statusCase==4){//完成预约
					var date= new Date(); //当前时间 
					console.log(date.getTime())
					var appointmentTime= new Date(item.appointment_time); //预约服务时间
					console.log(appointmentTime.getTime())
					var serviceTime = item.service_time*1000*60; //服务项目所需时间
					console.log(date.getTime()-appointmentTime.getTime())
					console.log(serviceTime)
					if(date.getTime()-appointmentTime.getTime()<serviceTime){
						toaster.pop('error', '服务未完成，无法完成预约！', '');
						return;
					}else{
						item.remarks="";
						item.price="";
						updateReservation();
					}
				}
				if(statusCase==3){//客户违约，强行扣费
					var date= new Date(); //当前时间 
					var appointmentTime= new Date(item.appointment_time); //预约服务时间
					var serviceTime = item.service_time*1000*60; //服务项目所需时间
					console.log(serviceTime)
					if(date.getTime()-appointmentTime.getTime()<serviceTime){
						toaster.pop('error', '未到违约时间，无法违约扣费！', '');
						return;
					}else{
						item.remarks="违约扣费";
						updateReservation();
					}
				}
					if(statusCase==2){//接受预约
						var date= new Date(); //当前时间
						console.log(date)
						var appointmentTime= new Date(item.appointment_time); //预约服务时间
						console.log(appointmentTime)
						if(date.getTime()-appointmentTime.getTime()>0){
							layer.confirm("预约已过期 ！是否拒绝？",{
								title : '警告',
								btn : [ '确定','取消']
							},function(){
								status=3;
								statusCase=1;
								item.remarks="预约已过期";
								item.price="";
								updateReservation();
								layer.closeAll('dialog');
							},function(){
								return;
							})
						}else{
							updateReservation();
						}
						
					}
					if(statusCase==8){//开始服务
						var date= new Date(); //当前时间 
						console.log(date.getTime())
						var appointmentTime= new Date(item.appointment_time); //预约服务时间
						console.log(appointmentTime.getTime())
						var serviceTime = item.service_time*1000*60; //服务项目所需时间
						console.log(date.getTime()-appointmentTime.getTime())
						if(date.getTime()<appointmentTime.getTime()){
							toaster.pop('error', '未到服务时间，无法开始服务！', '');
							return;
						}else{
							item.remarks="";
							item.price="";
							updateReservation();
						}
					}
					if(status==3){//拒绝订单
						satusCase=1;
					}
			function updateReservation(){
				var data = {
					id : item.id,
					remarks:item.remarks,
					penalty:item.price,
					appointmentTime:item.appointment_time,
					userReservation:item.user_reservation,
					appointmentStatus:status	
				}
				$http .post( '/BeautySalonManagement/hairdressingReservation/updateReservation.do',
						data).success(function(data) {
							if(statusCase==1){
								toaster.pop('success', '预约已拒绝', '');
							}
							if(statusCase==2){
								toaster.pop('success', '预约已接受', '');
							}else if(statusCase==4){
								toaster.pop('success', '预约已完成', '请到订单管理查看');
							}else if(statusCase==3){
								toaster.pop('success', '违约账单已扣费');
							}else if(statusCase==8){
								toaster.pop('success', '服务已开始');
							}
							searchData();
				})
			}
			}
			// 打开拒绝预约态框
			$scope.open = function(size,tx) {
				var modalInstance = $modal.open({
					templateUrl : 'modal.html',
					controller : 'orderModalCtr',
					size : size,
					resolve : {
						tx : function() {
							return tx;
						}
					}

				});
				modalInstance.result.then(function(data) {
					searchData()
				}, function() {

				});
			};
			// 打开用户信息模态框
			$scope.userInfo = function(size, loginId) {
				$http.get('/BeautySalonManagement/userInfo/selectUserInfo.do?loginId=' + loginId).success(function(data) {
				 if(data.code!=200){
					 alert(data.msg);
				 }
				 else{
					 var modalInstance = $modal.open({
							templateUrl : 'userInfo.html',
							controller : 'userInfoModalCtr',
							size : size,
							resolve : {
								loginId : function() {
									return loginId;
								}
							}

						});
						modalInstance.result.then(function(data) {
						}, function() {

						});
				 }
				}) 
				
			}
			// 打开美发师信息模态框
			$scope.hairdresserInfo = function(size, code) {
				$http.get('/BeautySalonManagement/hairdresserInfo/selectHairdresserInfo.do?code=' + code).success(function(data) {
					if(data.code!=200){
						 alert(data.msg);
					}else{
						var modalInstance = $modal.open({
							templateUrl : 'hairdresserInfo.html',
							controller : 'hairdresserInfoModalCtr',
							size : size,
							resolve : {
								code : function() {
									return code;
								}
							}

						});
						modalInstance.result.then(function(data) {
						}, function() {

						});
					}
				}) 
				
			}
		} ])

// 拒绝预约
app.controller('orderModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state','tx',
		function($scope, $modalInstance, $http, toaster, $state,tx) {
	$scope.item={};
	 $scope.save = function(form) {
			if (form.$invalid) {
				console.log("未填写")
				return;
			}
			var data={
					id:tx.id,
					appointmentTime:tx.appointment_time,
					userReservation:tx.user_reservation,
					remarks:$scope.item.remarks,
					appointmentStatus:3,//预约失败状态
			}
			$http.post('/BeautySalonManagement/hairdressingReservation/updateReservation.do', data).success(
					function(data) {
							toaster.pop('success', '预约已拒绝', '');
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
//用户信息
app.controller('userInfoModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state', 'loginId',
		function($scope, $modalInstance, $http, toaster, $state, loginId) {
			console.log(loginId)    
			$scope.item = {};
				$http.get('/BeautySalonManagement/userInfo/selectUserInfo.do?loginId=' + loginId).success(function(data) {
					console.log(data);
					$scope.item = data.data;
					console.log($scope.item);
				}) 
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);
//美发师信息
app.controller('hairdresserInfoModalCtr', [ '$scope', '$modalInstance', '$http',
		'toaster', '$state', 'code',
		function($scope, $modalInstance, $http, toaster, $state, code) {
			console.log(code)    
			$scope.haireresser = {};
				$http.get('/BeautySalonManagement/hairdresserInfo/selectHairdresserInfo.do?code=' + code).success(function(data) {
					console.log(data);
					$scope.haireresser = data.data;
					console.log($scope.haireresser);
					if(data.code!=200){
						alert("1111111111111")
						return;
					}
				}) 
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);