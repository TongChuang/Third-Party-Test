<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>操作成功！</title>
		<%
			String path = "";
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<script type="text/javascript" src="<%=path%>/js/common.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/style/global.css">
	</head>
	<body>
		<table width="100%" cellspacing="20">
			<tr><td height="100">&nbsp;</td></tr>
			<tr><td align="center"><font size="3">操作成功</font></td></tr>
			<tr><td colspan="2" align="center"><input type="button" class="button" id="btn_back" value="返回" onclick="javascript:history.go(-1)"></td>
		</table>
	</body>
</html>
