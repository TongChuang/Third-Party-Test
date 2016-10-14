<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title></title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript">
	
</script>
<script type="text/javascript">
	var contorlJson = null;
	var customerJson = null;
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 400,
			rightWidth : 300,
		});
		

		$("#customerData").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide : true,
			}, {
				display : 'customerKey',
				name : 'customerKey',
				width : 150,
				hide : true,
			}, {
				display : '客户编号',
				name : 'clientnumber',
				width : 100,
			}, {
				display : '客户名称',
				name : 'customername',
				width : 230,
			}, {
				display : '客户地址',
				name : 'address',
				width : 260
			}, ],
			data : customerJson,
			pageSize : 30,
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				alert("选中的是：" + data.customername);
			},
		});

		$("#contorlData").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide : true,
			}, {
				display : 'id',
				name : 'id',
				width : 150,
				hide : true,
			}, {
				display : '客户检验项目名称',
				name : 'customeritemsname',
				width : 150,
			}, {
				display : '客户检验项目ID',
				name : 'customeritems',
				width : 150,
			}, {
				display : '本地检验项目ID',
				name : 'localitems',
				width : 150
			}, {
				display : '本地检验项目名称',
				name : 'localitemsname',
				width : 150
			},],
			data : contorlJson,
			pageSize : 30,
			width : '100%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				alert("选中的是：" + data.ylmc);
			},
		});

	});


</script>

<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
}

#layout1 {
	width: 100%;
	margin: 0;
	padding: 0;
}

.l-page-top {
	height: 80px;
	background: #f8f8f8;
	margin-bottom: 3px;
}
</style>
</head>
<body style="padding:10px">
	<div id="layout1">
		<div position="left" title="客户信息">
			<input type="text" style="margin:5px;" /> <input type="button"
				value="搜索" class="l-button" />
			<div id="customerData"></div>
		</div>

		<div position="center" title="客户检检验项目对照">
					
						<div align="right"><td>
							<input type="button" value="自动对照相同名称的检验项目" style="width: 210px"
								class="l-button" /></td>
							<input type="text" style="margin:5px" /></td>
							<input type="button" value="搜索" align="right" class="l-button" /></td>
						</div>
					
			<div id="contorlData"></div>
		</div>

		<div position="right" title="检验项目">
			<input type="text" style="margin:5px" /> 
		</div>
	</div>
</body>
</html>
