app.controller('wagesCtr', [
		'$scope',
		'$http',
		'toaster',
		'$modal','$window',
		function($scope, $http, toaster, $modal,$window) {
			function get() {
				$http.get('/BeautySalonManagement/hairdresserInfo/getOne.do').success(
						function(data) {
							var hairdresser=data.data;
							console.log(hairdresser)
							$http.get('/BeautySalonManagement/hairdresserInfo/selectCharge.do?code='+hairdresser.code).success(
									function(data) {
									console.log(data);
									 $scope.hairdresser=data.data; 
									 var itemChargeAll=data.data.itemChargeAll;
									 var tc=0;
									 var charts=[];
									 var j=0;
									 for(var i=0;i<itemChargeAll.length;i++)
										 {
										     var chartq={};
										     tc+=itemChargeAll[i].value;
										     if(itemChargeAll[i].value>0){
										    	 chartq.name=itemChargeAll[i].name,
										    	 chartq.value=itemChargeAll[i].value*$scope.hairdresser.commission,
										    	 charts[j]=chartq;
										    	 j++;
										     }
										 } 
									
									 
									 $scope.tc=tc;
									 var myChart = echarts.init(document.getElementById('echartu'));
									 var scale = 1;
									 var echartData = charts
									 var rich = {
									     yellow: {
									         color: "#ffc72b",
									         fontSize: 30 * scale,
									         padding: [5, 4],
									         align: 'center'
									     },
									     total: {
									         color: "#ffc72b",
									         fontSize: 40 * scale,
									         align: 'center'
									     },
									     white: {
									         color: "#fff",
									         align: 'center',
									         fontSize: 14 * scale,
									         padding: [21, 0]
									     },
									     blue: {
									         color: '#49dff0',
									         fontSize: 16 * scale,
									         align: 'center'
									     },
									     hr: {
									         borderColor: '#0b5263',
									         width: '100%',
									         borderWidth: 1,
									         height: 0,
									     }
									 }
									 option = {
									     backgroundColor: '#031f2d',
									     title: {
									         text:'总提成',
									         left:'center',
									         top:'53%',
									         padding:[24,0],
									         textStyle:{
									             color:'#fff',
									             fontSize:18*scale,
									             align:'center'
									         }
									     },
									     legend: {
									         selectedMode:false,
									         formatter: function(name) {
									             var total = 0; 
									             var averagePercent; 
									             echartData.forEach(function(value, index, array) {
									                 total += value.value;
									             });
									             return '{total|' + total + '}';
									         },
									         data: [echartData[0].name],
									         left: 'center',
									         top: 'center',
									         icon: 'none',
									         align:'center',
									         textStyle: {
									             color: "#fff",
									             fontSize: 16 * scale,
									             rich: rich
									         },
									     },
									     series: [{
									         name: '总额',
									         type: 'pie',
									         radius: ['42%', '50%'],
									         hoverAnimation: false,
									         color: ['#c487ee', '#deb140', '#49dff0', '#034079', '#6f81da', '#00ffb4'],
									         label: {
									             normal: {
									                 formatter: function(params, ticket, callback) {
									                     var total = 0; 
									                     var percent = 0;
									                     echartData.forEach(function(value, index, array) {
									                         total += value.value;
									                     });
									                     percent = ((params.value / total) * 100).toFixed(1);
									                     return '{white|' + params.name + '}\n{hr|}\n{yellow|' + params.value + '}\n{blue|' + percent + '%}';
									                 },
									                 rich: rich
									             },
									         },
									         labelLine: {
									             normal: {
									                 length: 55 * scale,
									                 length2: 0,
									                 lineStyle: {
									                     color: '#0b5263'
									                 }
									             }
									         },
									         data: echartData
									     }]
									 };
									 myChart.setOption(option);
									})
						})
		 
			}
			get();
		 
			
		 
		} ])
 