<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="aside-wrap">
  <!-- if you want to use a custom scroll when aside fixed, use the slimScroll
    <div class="navi-wrap" ui-jq="slimScroll" ui-options="{height:'100%', size:'8px'}">
  -->
  <div class="navi-wrap">
    

    <!-- nav -->
     <!-- <nav ui-nav class="navi clearfix" ng-include="'tpl/blocks/nav.jsp'"></nav>-->
    <!-- nav -->
	<nav class="navi clearfix">
		<jsp:include page="./nav.jsp"></jsp:include>
	</nav>
   
  </div>
</div>