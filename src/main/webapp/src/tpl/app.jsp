<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- navbar -->
<div class="app-header navbar">
	<jsp:include page="blocks/header.jsp"></jsp:include>
</div>


<!-- / navbar -->

<!-- menu -->
<div class="app-aside hidden-xs bg-light dr">
	<jsp:include page="blocks/aside.html"></jsp:include>
</div>

<!-- / menu -->

<!-- content -->
<div class="app-content">
	<div ui-butterbar></div>
	<div class="app-content-body fade-in-up" ui-view></div>
</div>
<!-- /content -->


<!-- footer -->
<div style="position: fixed; left: 43%; bottom: 0;">
	<small style="color: black">&copy; 2017-2018 .HuShuai .All Rights Reserved </small>
</div>
<!-- / footer -->