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
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
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
			data : customerData,
			pageSize : 30,
			width : '100%',
			height : '95%',
			onSelectRow : function(data, rowindex, rowobj) {
				$("#clientnumber").val(data.clientnumber);
				$("#customername").val(data.customername);
			},
		});
	});
 	var LODOP ; //声明为全局变量 
	function print(barcode) {
		LODOP = getLodop(document.getElementById('LODOP_OB'), document
				.getElementById('LODOP_EM')); 
		LODOP.PRINT_INIT("条码打印");
		LODOP.ADD_PRINT_BARCODE(50,50,"4.4cm","2.9cm","128B",barcode);
		LODOP.SET_PRINT_STYLEA(0,"Horient",2);
		LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
		LODOP.PRINT();
	}
	
	function prn_Design() {		
		LODOP.PRINT_DESIGN();		
	};	
	function prn_Preview() {		
	  	LODOP.PREVIEW();		
	};

	function printCode() {
		var clientnumber = $("#clientnumber").val();
		var printNum = $("#printNum").val();
		var codeType = $("#codeType").val();
		if ('' == clientnumber) {
			$.ligerDialog.error('请先选择客户信息！');
			return;
		}
		if ('' == printNum) {
			$.ligerDialog.error('请填写需要打印的数量！');
			return;
		}

		$.ajax({
			url : '/jsp/queryStats/queryStats.do?method=printCode',
			data : 'clientnumber=' + clientnumber + '&printNum=' + printNum
					+ '&codeType=' + codeType,
			dataType : 'text',
			type : 'post',
			error: function (e) { alert('出现未知错误' + e); },
			success : function(data) {
				var barcode = JSON.parse(data); 
				for(var i=0;i<barcode.list.length;i++){
					for(var j=0;j<codeType;j++){
						print(barcode.list[i]);
					}
				}
			}
		});
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
					<td align="left" class="l-table-edit-td"><input
						style="width: 200px" readonly="readonly" name="clientnumber"
						type="text" id="clientnumber" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">客户名称:</td>
					<td align="left" class="l-table-edit-td"><input
						style="width: 200px" class="textReadonly" readonly="readonly"
						name="customername" type="text" id="customername" ltype="text" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">打印数量:</td>
					<td align="left" class="l-table-edit-td"><input
						style="width: 200px" name="printNum" type="text" id="printNum"
						ltype='spinner' ligerui="{type:'int'}" value="1" />
					</td>
					<td align="left"></td>
				</tr>
				<tr>
					<td align="right" class="l-table-edit-td">条码纸:</td>
					<td align="left" class="l-table-edit-td">
					<select id="codeType" name="codeType" ltype="select" style="width: 203px">
							<option value="1">一联</option>
							<option value="2">二联</option>
							<option value="3">三联</option>
							<option value="4">四联</option>
							<option value="6">六联</option>
					</select>
					</td>
				</tr>
			</table>
			<br /> <input type="submit" value="打印" id="Button1"
				onclick="printCode();" class="l-button l-button-submit" />
				<input type="button" value="打印设计" onclick="prn_Design()" class="l-button l-button-submit"/>
				
				<input type="button" value="打印预览" onclick="prn_Preview()" class="l-button l-button-submit"/>
		</div>
	</div>

	<div style="display:none;">
</body>
</html>
<script type="text/javascript">
	customerData = ${result_json};
</script>