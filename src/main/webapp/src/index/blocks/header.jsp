<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
        <ul class="nav navbar-nav navbar-right t-c" >
          <li>
          <a  href="login.html"  class="dropdown-toggle" >
              <span class="pull-right-xs" style="margin-left: 8px"> 未登录[<span style="font-size:12px;">{{newDate}}</span>]</span>
            </a>
	        </ul>
        <!-- / navbar right -->

      </div>
      <!-- / navbar collapse -->
      </div>
          <script>
		app.controller('headerCtrl', [
				'$scope',
				'$location',
				'$window',
				'$http',
				'$filter',
				function($scope, $location, $window, $http, $filter) {
					Date.prototype.format = function (format) {
				           var args = {
				               "M+": this.getMonth() + 1,
				               "d+": this.getDate(),
				               "q+": Math.floor((this.getMonth() + 3) / 3),  
				               "S": this.getMilliseconds()
				           };
				           if (/(y+)/.test(format))
				               format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
				           for (var i in args) {
				               var n = args[i];
				               if (new RegExp("(" + i + ")").test(format))
				                   format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
				           }
				           return format;
				       };
					$scope.newDate=new Date().format("yyyy-MM-dd");
					console.log(new Date().format("yyyy-MM-dd"))
					$http.get('/BeautySalonManagement/storeInfo/selectStoreInfo.do').success(
					function(data) {
						console.log(1111)
						console.log(data)
						$scope.info = data.data[0];
					})
				} ]);
	</script>