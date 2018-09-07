app.controller('orderCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal',
		function($scope, $http, toaster, $modal) {
			/**分页*/
			var vm = $scope.vm = {};
			vm.column=vm.page = {
					size: 20,
					index: 1
			};
			function get() {
				$http.get('/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=3').success(
						function(data) {
							 vm.items = data.data;
 					$scope.orders = data.data;
						})
 
			}
			get();
		 $scope.appointmentStatus='';
		 $scope.appointmentTime='';
		 $scope.search=function(){
			 if($scope.appointmentTime==''||$scope.appointmentTime==null){
				 var data={
						 appointmentStatus :$scope.appointmentStatus,
				 }
			 }else {
				 var data={
						 appointmentStatus :$scope.appointmentStatus,
						 appointmentTime:$scope.appointmentTime.format("yyyy-MM-dd"),
				}
					
			 }
			if($scope.appointmentStatus==''){
				var url="/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=3"
			}else{
				var url="/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=6"
			}
			 $http.post(url,data).success(
						function(data) {
							 vm.items = data.data;
					$scope.orders = data.data;
						})
			 
		 } 
		 
			// 打开用户信息模态框
			$scope.userInfo = function(size, loginId) {
				$http.get('/BeautySalonManagement/userInfo/selectUserInfo.do?loginId=' + loginId).success(function(data) {
					 if(data.code!=200){
						 alert(data.msg);
					 }else{
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
//弹框操作
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