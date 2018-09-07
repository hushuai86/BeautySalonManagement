app .controller( 'order2Ctr', [ '$scope',
						'$http',
						'toaster',
						'$modal',
						function($scope, $http, toaster, $modal) {
							/** 分页 */
							var vm = $scope.vm = {};
							vm.column=vm.page = {
									size: 10,
									index: 1
							};
							function get() {
								$http .get( '/BeautySalonManagement/hairdressingReservation/userReservation.do?accountFlag=5') .success(function(data) {
											$scope.orders = data.data;
											 vm.items = data.data;
										})
							}
							get();
							//查询
							$scope.search=function(){
								var data={
										accountFlag:5,
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
 										accountFlag:5,
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
							// 删除
							$scope.deleteOne = function(id) {
								layer.confirm("确定删除？",
										{title:"提示",btn:['是','否']},function(){
											var data = {
													id : id,
													appointmentStatus : 5
												}
												$http .post( '/BeautySalonManagement/hairdressingReservation/updateReservation.do',
																data).success(function(data) {
															toaster.pop('success', '删除成功', '');
															searchData();
														})
												layer.closeAll('dialog');
										},function(){}
								)
								
							}
							// 打开线下订单模态框
							$scope.open = function(size, tx) {
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
							// 完成订单模态框
							$scope.complete = function(size,item) {
								console.log(item)
								var date= new Date(); //当前时间 
								console.log(date.getTime())
								var appointmentTime= new Date(item.appointment_time); //服务时间
								console.log(appointmentTime.getTime())
								var serviceTime = item.service_time*1000*60; //服务项目所需时间
								console.log(date.getTime()-appointmentTime.getTime())
								console.log(serviceTime)
								if(date.getTime()-appointmentTime.getTime()<serviceTime){
									toaster.pop('error', '服务未完成，无法完成订单！', '');
									return;
								}else{
									var modalInstance = $modal.open({
										templateUrl : 'complete_modal.html',
										controller : 'completeModalCtr',
										size : size,
										resolve : {
											item : function() {
												return item;
											}
										}

									});
									modalInstance.result.then(function(data) {
									}, function() {

									});
								}
								
							}
						} ])

// 线下订单弹框操作
app.controller('orderModalCtr', [
		'$scope',
		'$modalInstance',
		'$http',
		'toaster',
		'$state',
		'tx',
		function($scope, $modalInstance, $http, toaster, $state, tx) {
			$scope.item={};
			var date = new Date();
			$scope.item.appointmentTime=date;
			// 获取所有服务项目
			$http.get('/BeautySalonManagement/code/selectItem.do').success(function(data) {
				console.log(data)
				$scope.codes = data.data;
			})
			// 所有空闲理发师
			$http.get('/BeautySalonManagement/hairdresserInfo/hairdresserList.do?type=2') .success(function(data) {
							$scope.hairs = data.data;
						})
			
		 $scope.save = function(form) {
				if (form.$invalid) {
					console.log("未填写")
					return;
				}   
					var data={
							hairdressingProject:$scope.item.hairdressingProject,
							hairdresserInfoCode:$scope.item.hairdresserInfoCode,
							appointmentTime:date,
							submitAppointmentTime:date,
							acceptAppointmentTime:date,
							appointmentStatus:6,//待支付状态
							userType:$scope.item.userType,
							userReservation:$scope.item.userReservation,
							type:'offline'
					}
					console.log(data)
				$http.post('/BeautySalonManagement/hairdressingReservation/addOne.do', data).success(
						function(data) {
							console.log(data)
							if(data.code==200){
								toaster.pop('success', '添加成功', '');
								$modalInstance.close();
							}else{
								alert(data.msg);
							}
						})
			}
			$scope.ok = function() {
				$modalInstance.dismiss('cancel');
			};
			$scope.close = function() {
				$modalInstance.close();
			}
		} ]);
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
//会员信息
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
//完成模态框操作
app.controller('completeModalCtr', [ '$scope', '$modalInstance', '$http',
                                    		'toaster', '$state', 'item',
                                    		function($scope, $modalInstance, $http, toaster, $state, item) {
                                    			console.log(item) 
                                    			$scope.item =item;
                                    			$scope.save =function(){
                                    				var data = {
                                    						id :item.id,
                                    						userReservation:item.user_reservation,
                                    						payType:item.pay_type,
                                    						appointmentStatus:4	
                                    					}
                                    					$http .post( '/BeautySalonManagement/hairdressingReservation/updateReservation.do',
                                    							data).success(function(data) {
                                    								if(data.code==200){
                                    									alert('订单已完成');
                                    									$modalInstance.close();
                                    									history.go(0)
                                    								}else{
                                    									toaster.pop('error', data.msg, '');
                                    								}
                                    									
                                    					})
                                    			}
                                    			$scope.ok = function() {
                                    				$modalInstance.dismiss('cancel');
                                    			};
                                    			$scope.close = function() {
                                    				$modalInstance.close();
                                    			}
                                    		} ]);