﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


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
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript">
</script>
<script type="text/javascript">
var testItemJson = null;
var customerJson = null;
var testObjectiveJson = null;
	$(function() {

		$("#layout1").ligerLayout({
			leftWidth : 400,
			rightWidth : 300,
		});
		$("#toptoolbar").ligerToolBar({ items: [
                {
                    text: '新增检验目的', click: addTestObjective, icon:'add'},
                { line:true },
                { text: '修改检验目的', click: modifyTestObjective },
                 { line:true },
                { text: '删除检验目的', click: deleteTestObjective }
            ]
        });
		
		$("#customerData").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide:true,
			},{
				display : 'customerKey',
				name : 'customerKey',
				width : 150,
				hide:true,
			},{
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
				alert("选中的是："+data.customername);			
			},
		});
		
		$("#testObjective").ligerGrid({
			columns : [ {
				display : '检验目的编号',
				name : 'ylxh',
				width : 150,
			}, {
				display : '检验目名称',
				name : 'ylmc',
				width : 150,
			},{
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide:true,
			},],
		data : testObjectiveJson,
			pageSize : 30,
			width : '100%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				alert("选中的是："+data.ylmc);			
			},
		});
		
	});
	
function addTestObjective(){}

function modifyTestObjective(){}

function deleteTestObjective(){}
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
			<input type="text" style="margin:5px;"/>
			<input type="button" value="搜索" class="l-button"/>
			<div id="customerData"></div>
		</div>
		
		<div position="center" title="检验目的">
			<input type="text" style="margin:5px"/>
			<input type="button" value="搜索" class="l-button"/>
			<div id="toptoolbar"></div>
			<div id="testObjective"></div>
		</div>
		
		<div position="right" title="检验项目">
			<input type="text" style="margin:5px"/>
			<input type="button" value="添加" class="l-button"/>
		</div>
	</div>
</body>
</html>
