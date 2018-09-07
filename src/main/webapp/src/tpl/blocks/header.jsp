<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- navbar header -->
<c:choose>
	<c:when test="${account.type==1}">
		<div class="navbar-header bg-dark dker">
			<button class="pull-right visible-xs dk" ui-toggle-class="show"
				data-target=".navbar-collapse">
				<i class="glyphicon glyphicon-cog"></i>
			</button>
			<button class="pull-right visible-xs" ui-toggle-class="off-screen"
				data-target=".app-aside" ui-scroll-to="app">
				<i class="glyphicon glyphicon-align-justify"></i>
			</button>
			<!-- brand -->
			<a href="#/" class="navbar-brand text-lt"> <i class="fa fa-btc"></i>
				<span class="hidden-folded m-l-xs"><i class="glyphicon glyphicon-home"></i></span>
			</a>
			<!-- / brand -->
		</div>
		<!-- / navbar header -->

		<!-- navbar collapse -->
		<div class="collapse pos-rlt navbar-collapse box-shadow bg-dark dker"
			ng-controller="headerCtrl">
			<!-- buttons -->
			<div class="nav navbar-nav hidden-xs">
				<a href class="btn no-shadow navbar-btn"
					ng-click="app.settings.asideFolded = !app.settings.asideFolded">
					<i
					class="fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw"></i>
				</a>
			</div>
			<ul class="nav navbar-nav navbar-left" >
				<li><span class="navbar-brand text-lt" style="font-size: 13px;">余额  ：${userInfo.balance}元  <a style="margin-left: 10px;color:#00FF00" href="" onclick="notRecharge();">充值 </a></span>
				</li>
			</ul>
				<ul class="nav navbar-nav navbar-middle" style="position: fixed;left:46%;">
				<li><span class="navbar-brand text-lt">{{info.name}}</span>
				</li>
			</ul>
			<!-- / link and dropdown -->
			<ul class="nav navbar-nav navbar-right t-c">
				<li><a ui-sref="app.updatePassword" class="dropdown-toggle"> <span
						class="pull-right-xs" style="margin-left: 8px">
							欢迎您,${userInfo.name}[<span style="font-size: 12px;">{{newDate}}</span>]
					</span>
				</a>
				<li><a ng-click="logout()" class="dropdown-toggle"> <i
						class="icon-logout"></i> <span class="pull-right-xs"
						style="margin-left: 8px">注销</span>
				</a></li>
			</ul>
			<!-- / navbar right -->

		</div>
		<!-- / navbar collapse -->
	</c:when>
	<c:when test="${account.type==2}">
		<div class="navbar-header bg-dark dker">
			<button class="pull-right visible-xs dk" ui-toggle-class="show"
				data-target=".navbar-collapse">
				<i class="glyphicon glyphicon-cog"></i>
			</button>
			<button class="pull-right visible-xs" ui-toggle-class="off-screen"
				data-target=".app-aside" ui-scroll-to="app">
				<i class="glyphicon glyphicon-align-justify"></i>
			</button>
			<!-- brand -->
			<a href="#/" class="navbar-brand text-lt"> <i class="fa fa-btc"></i>
				<span class="hidden-folded m-l-xs"><i class="glyphicon glyphicon-home"></i></span>
			</a>
			<!-- / brand -->
		</div>
		<!-- / navbar header -->

		<!-- navbar collapse -->
		<div class="collapse pos-rlt navbar-collapse box-shadow bg-dark dker"
			ng-controller="headerCtrl">
			<!-- buttons -->
			<div class="nav navbar-nav hidden-xs">
				<a href class="btn no-shadow navbar-btn"
					ng-click="app.settings.asideFolded = !app.settings.asideFolded">
					<i
					class="fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw"></i>
				</a>

				<!-- <a href class="btn no-shadow navbar-btn" ui-toggle-class="show" target="#aside-user">
            <i class="icon-user fa-fw"></i>
          </a> -->

			</div>

				<ul class="nav navbar-nav navbar-middle" style="position: fixed;left:46%;">
				<li><span class="navbar-brand text-lt">{{info.name}}</span>
				</li>
			</ul>
			<!-- / link and dropdown -->
			<ul class="nav navbar-nav navbar-right t-c">
				<li><a ui-sref="app.updatePassword" class="dropdown-toggle"> <span
						class="pull-right-xs" style="margin-left: 8px">
							欢迎您,${hairdresserInfo.name}[<span style="font-size: 12px;">{{newDate}}</span>]
					</span>
				</a>
				<li><a ng-click="logout()" class="dropdown-toggle"> <i
						class="icon-logout"></i> <span class="pull-right-xs"
						style="margin-left: 8px">注销</span>
				</a></li>
			</ul>
			<!-- / navbar right -->

		</div>
		<!-- / navbar collapse -->
	</c:when>
	<c:otherwise>
			<div class="navbar-header bg-dark dker ">
				<button class="pull-right visible-xs dk" ui-toggle-class="show"
					data-target=".navbar-collapse">
					<i class="glyphicon glyphicon-cog"></i>
				</button>
				<button class="pull-right visible-xs" ui-toggle-class="off-screen"
					data-target=".app-aside" ui-scroll-to="app">
					<i class="glyphicon glyphicon-align-justify"></i>
				</button>
				<!-- brand -->
				<a href="#/" class="navbar-brand text-lt"> <i class="fa fa-btc"></i>
					<span class="hidden-folded m-l-xs"><i class="glyphicon glyphicon-home"></i></span>
				</a>
				<!-- / brand -->
			</div>
			<!-- / navbar header -->
		<!-- navbar collapse -->
		<div class="collapse pos-rlt navbar-collapse box-shadow bg-dark dker "
			ng-controller="headerCtrl">
			<!-- buttons -->
			<div class="nav navbar-nav hidden-xs">
				<a href class="btn no-shadow navbar-btn"
					ng-click="app.settings.asideFolded = !app.settings.asideFolded">
					<i
					class="fa {{app.settings.asideFolded ? 'fa-indent' : 'fa-dedent'}} fa-fw"></i>
				</a>

				<!-- <a href class="btn no-shadow navbar-btn" ui-toggle-class="show" target="#aside-user">
            <i class="icon-user fa-fw"></i>
          </a> -->

			</div>

			<ul class="nav navbar-nav navbar-middle" style="position: fixed;left:44%;">
				<li><span class="navbar-brand text-lt">{{info.name}}管理系统</span>
				</li>
			</ul>
			<!-- / link and dropdown -->
			<ul class="nav navbar-nav navbar-right t-c">
				<li><a ui-sref="app.updatePassword"  class="dropdown-toggle"> <span
						class="pull-right-xs" style="margin-left: 8px">
							欢迎您,${loginId}[<span style="font-size: 12px;">{{newDate}}</span>]
					</span>
				</a>
				<li><a ng-click="logout()" class="dropdown-toggle"> <i
						class="icon-logout"></i> <span class="pull-right-xs"
						style="margin-left: 8px">注销</span>
				</a></li>
			</ul>
			<!-- / navbar right -->

		</div>
		<!-- / navbar collapse -->
	</c:otherwise>
</c:choose>
<script>
	app
			.controller(
					'headerCtrl',
					[
							'$scope',
							'$location',
							'$window',
							'$http',
							'$filter',
							function($scope, $location, $window, $http, $filter) {
								Date.prototype.format = function(format) {
									var args = {
										"M+" : this.getMonth() + 1,
										"d+" : this.getDate(),
										"q+" : Math
												.floor((this.getMonth() + 3) / 3),
										"S" : this.getMilliseconds()
									};
									if (/(y+)/.test(format))
										format = format
												.replace(
														RegExp.$1,
														(this.getFullYear() + "")
																.substr(4 - RegExp.$1.length));
									for ( var i in args) {
										var n = args[i];
										if (new RegExp("(" + i + ")")
												.test(format))
											format = format
													.replace(
															RegExp.$1,
															RegExp.$1.length == 1 ? n
																	: ("00" + n)
																			.substr(("" + n).length));
									}
									return format;
								};
								$scope.newDate = new Date()
										.format("yyyy-MM-dd");
								console.log(new Date()
										.format("yyyy-MM-dd"))
								$http
										.get(
												'/BeautySalonManagement/storeInfo/selectStoreInfo.do')
										.success(function(data) {
											$scope.info = data.data[0];
										})

								$scope.logout = function() {
									layer
											.confirm(
													'确定退出系统？',
													{
														title : '警告',
														btn : [ '确定', '取消' ]
													},
													function() { // 确定
														$http
																.get(
																		'/BeautySalonManagement/account/logout.do')
																.success(
																		function(
																				data) {
																			window.location.href = "/BeautySalonManagement/src/login.html"
																		})
														layer
																.closeAll('dialog');
													}, function() { // 取消
													});
								}
							} ]);
    function notRecharge(){
    	layer
		.confirm(
				'线上充值暂未开通，请到店充值，优惠多多！',
				{
					title : '提示',
					btn : [ '确定']
				});
    }
</script>