<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<title>标本数据收发系统</title>

<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="/assets/css/reset.css">
<link rel="stylesheet" href="/assets/css/supersized.css">
<link rel="stylesheet" href="/assets/css/style.css">
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>


<script type="text/javascript">
	$(function() {
		var loginResult = $("#loginResult").val();
		if (loginResult == '2') {
		}
		if (loginResult == '3') {
		}
	});
</script>
</head>

<body>
	<div class="page-container">
		<h1></h1>
		<form action="/login.do?method=login" method="post">
			<div>
				<c:if test="${param.loginResult==2}">
					<span style="color: red">系统无此用户！</span>
				</c:if>
				<c:if test="${param.loginResult==3}">
					<span style="color: red">输入的密码错误，请重新输入！</span>
				</c:if>
			</div>
			<input type="text" id="userName" name="userName" class="username" placeholder="用户名"> 
			<input type="password" id="passWord" name="passWord" class="password" placeholder="密码">
			<button type="submit">登录</button>
		</form>
	</div>
	<div style=" margin:0 auto; margin-top:250px; "></div>
	<div align="center" style="color: black;">Copyright © 2016-2017 杭州同烁信息科技有限公司</div>
	<!-- Javascript -->
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<script src="assets/js/supersized.3.2.7.min.js"></script>
	<script src="assets/js/supersized-init.js"></script>
	<script src="assets/js/scripts.js"></script>

</body>

</html>
