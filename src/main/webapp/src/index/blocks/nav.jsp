<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="nav">
	<li ng-class="{active:$state.includes('app')|| $state.includes('apps')}" id="js-shixun"><a href class="auto"> <span class="pull-right text-muted"> <i class="fa fa-fw fa-angle-right text"></i> <i class="fa fa-fw fa-angle-down text-active"></i>
		</span> <i class="fa fa-flag text-success"></i> <span class="font-bold">游客</span>
	</a>
		<ul class="nav nav-sub dk">
			<li ui-sref-active="active"><a ui-sref="index.description"> <i class="fa fa-bank"></i> <span>本店介绍</span>
			</a></li>
			<li ui-sref-active="active"><a ui-sref="index.hairdressers"> <i class=" icon-user-female"></i> <span>优秀的美容美发师</span>
			</a></li>
			<li ui-sref-active="active"><a ui-sref="index.code"> <i class=" icon-tag"></i> <span>丰富的服务项目</span>
			</a></li>
		</ul></li>
</ul>
