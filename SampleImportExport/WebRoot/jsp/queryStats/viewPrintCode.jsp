<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-
transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-form.css"
	rel="stylesheet" type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<style type="text/css">
</style>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script language="javascript" src="/resources/lodop/LodopFuncs.js"></script>
<object   id="LODOP_OB"
	classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>
	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
</object>


<script type="text/javascript">
	var customerData = null;
	$(function() {

		$("#layout1").ligerLayout({
			rightWidth : 300
		});

		$("#maingridData").ligerGrid({
			columns : [ {
				display : '客户ID',
				name : 'customerid',
				width : 150,
				hide : true,
			}, {
				display : '客户编号',
				name : 'clientnumber',
				width : 150,
			}, {
				display : '客户名称',
				name : 'customername',
				width : 220,
			}, {
				display : '客户地址',
				name : 'address',
				width : 200
			}, ],
			//data : customerData,
			url:'/jsp/queryStats/queryStats.do?method=viewPrintCode',
			pageSize : 30,
			width : '100%',
			height : '95%',
		});
	});

	function printCode() {
		var LODOP = getLodop(document.getElementById('LODOP_OB'), document
				.getElementById('LODOP_EM'));
		LODOP.PRINT_INIT("条码打印");
		//ADD_PRINT_BARCODE(Top,Left,Width,Height,BarCodeType,BarCodeValue);
		LODOP.ADD_PRINT_BARCODE(366, 260, 352, 352, "128B", "A12345678");
		LODOP.PRINT();
	}
</script>

<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit {
	
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit,.l-button-reset {
	width: 80px;
	float: left;
	margin-left: 10px;
	padding-bottom: 2px;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>
</head>
<body style="padding:10px">
	<div id="layout1">
		<div position="center">
			<div id="maingridData" style="margin: 0; padding: 0"></div>
		</div>
		<div position="right">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">
				<tr>
					<td align="right" class="l-table-edit-td">客户编号:</td>
					<td align="left" class="l-table-edit-td"><input readonly="readonly"
						name="clientnumber" type="text" id="clientnumber" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">客户名称:</td>
					<td align="left" class="l-table-edit-td"><input class="textReadonly" readonly="readonly"
						name="customername" type="text" id="customername" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">打印数量:</td>
					<td align="left" class="l-table-edit-td"><input 
						name="printNum" type="text" id="printNum" ltype='spinner'
						ligerui="{type:'int'}" value="1" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">条码纸:</td>
					<td align="left" class="l-table-edit-td"><select id="codeType"
						name="codeType" ltype="select" style="width: 135px">
							<option value="1">一联</option>
							<option value="13">一联三列</option>
							<option value="2">二联</option>
							<option value="3">三联</option>
							<option value="4">四联</option>
							<option value="6">六联</option>
					</select></td>
				</tr>
			</table>
			<br />
			<input type="submit" value="打印" id="Button1" class="l-button l-button-submit" /> 
		</div>
	</div>

	<div style="display:none;">
</body>
</html>
<script type="text/javascript">
	customerData = $
	{
		result_json
	};
</script>