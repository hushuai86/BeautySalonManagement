app .controller( 'orderCtr', [ '$scope',
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
								$http .get( '/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=1&appointmentStatus=4') .success(function(data) {
											$scope.orders = data.data;
											 vm.items = $scope.orders;
										})
							}
							get();
							//查询
							$scope.search=function(){
								var data={
										accountFlag:1,
										userReservation:$scope.login_id,
										appointmentStatus:$scope.status,
										hairdresserInfoCode:$scope.code,
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
										accountFlag:1,
										userReservation:$scope.login_id,
										appointmentStatus:$scope.status,
										hairdresserInfoCode:$scope.code,
										codeName:$scope.name
								}
								console.log(data)
								 $http.post("/BeautySalonManagement/hairdressingReservation/userReservation.do",data).success(
											function(data) {
												 vm.items = data.data;
												 $scope.orders = data.data;
											})
							}

							// 删除
							$scope.deleteOne = function(id,status) {
								if(status==7){
								layer.confirm("确定彻底删除？",{
									title:'提示',
									btn: ['是','否' ]
								},function(){
									var data = {
											id : id,
											appointmentStatus : 7
										}
									deleteReservation(data);
								},function(){
									
								})
							}
							if(status==5){
								layer.confirm("确定删除？",{
									title:'提示',
									btn: ['是','否' ]
								},function(){
									var data = {
											id : id,
											appointmentStatus : 5
										}
									deleteReservation(data);
								},function(){
									
								})
							}	
							
							function deleteReservation(data){
								$http .post( '/BeautySalonManagement/hairdressingReservation/updateReservation.do',
										data).success(function(data) {
									toaster.pop('success', '删除成功', '');
									searchData();
									layer.closeAll('dialog');
								})
							 }
							}
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


Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 // 月份
       "d+" : this.getDate(),                    // 日
       "h+" : this.getHours(),                   // 小时
       "m+" : this.getMinutes(),                 // 分
       "s+" : this.getSeconds(),                 // 秒
       "q+" : Math.floor((this.getMonth()+3)/3), // 季度
       "S"  : this.getMilliseconds()             // 毫秒
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}  
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
				}) 
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);