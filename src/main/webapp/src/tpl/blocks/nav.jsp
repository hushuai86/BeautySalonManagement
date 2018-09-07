<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${account.type==4}" var="condition" scope="page">
	<ul class="nav">
		<li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><a href class="auto"> <span class="pull-right text-muted"> <i class="fa fa-fw fa-angle-right text"></i> <i class="fa fa-fw fa-angle-down text-active"></i>
			</span> <i class="fa fa-flag text-success"></i> <span class="font-bold">系统管理</span>
		</a>

			<ul class="nav nav-sub dk">
				<li ui-sref-active="active"><a ui-sref="app.system.info"> <i class="fa fa-bank"></i> <span>店铺信息维护</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.user"> <i class=" icon-users"></i> <span>会员管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.hairdresser"> <i class=" icon-user-female"></i> <span>美发师管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.code"> <i class=" icon-tag"></i> <span>服务项管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.wages"> <i class="fa fa-money"></i> <span>工资管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.order"> <i class="fa fa-database"></i> <span>订单管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.system.count"> <i class="fa  fa-bar-chart-o"></i> <span>统计报表</span>
				</a></li>
			</ul></li>
	</ul>
</c:if>
<c:if test="${account.type==3}" var="condition" scope="page">
	<ul class="nav">
		<li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><a href class="auto"> <span class="pull-right text-muted"> <i class="fa fa-fw fa-angle-right text"></i> <i class="fa fa-fw fa-angle-down text-active"></i>
			</span> <i class="fa fa-flag text-success"></i> <span class="font-bold">管理员</span>
		</a>

			<ul class="nav nav-sub dk">
				<li ui-sref-active="active"><a ui-sref="app.manager.user"> <i class=" icon-users"></i> <span>会员管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.manager.order"> <i class="fa   fa-user"></i> <span>预约管理</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.manager.order2"> <i class="fa fa-database"></i> <span>订单管理</span>
				</a></li>
			</ul></li>
	</ul>
</c:if>
<c:if test="${account.type==2}" var="condition" scope="page">
	<ul class="nav">
		<li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><a href class="auto"> <span class="pull-right text-muted"> <i class="fa fa-fw fa-angle-right text"></i> <i class="fa fa-fw fa-angle-down text-active"></i>
			</span> <i class="fa fa-flag text-success"></i> <span class="font-bold">美发师</span>
		</a>

			<ul class="nav nav-sub dk">
				<li ui-sref-active="active"><a ui-sref="app.hairdresser.info"> <i class="fa   fa-user"></i> <span>个人信息</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.hairdresser.order"> <i class="fa fa-database"></i> <span>订单查询</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.hairdresser.wages"> <i class="fa   fa-money"></i> <span>工资查询</span>
				</a></li>
			</ul></li>
	</ul>
</c:if>
<c:if test="${account.type==1}" var="condition" scope="page">
	<ul class="nav">
		<li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><a href class="auto"> <span class="pull-right text-muted"> <i class="fa fa-fw fa-angle-right text"></i> <i class="fa fa-fw fa-angle-down text-active"></i>
			</span> <i class="fa fa-flag text-success"></i> <span class="font-bold">会员</span>
		</a>

			<ul class="nav nav-sub dk">
				<li ui-sref-active="active"><a ui-sref="app.user.info"> <i class="fa   fa-user"></i> <span>个人信息</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.user.description"> <i class="fa fa-bank"></i> <span>本店介绍</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.user.hairdresser"> <i class=" icon-user-female"></i> <span>优秀的美容美发师</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.user.code"> <i class=" icon-tag"></i> <span>丰富的服务项目</span>
				</a></li>

				<li ui-sref-active="active"><a ui-sref="app.user.order"> <i class="fa   fa-user"></i> <span>我的预约</span>
				</a></li>
				<li ui-sref-active="active"><a ui-sref="app.user.order2"> <i class="fa   fa-user"></i> <span>我的订单</span>
				</a></li>
			</ul></li>
	</ul>
</c:if>