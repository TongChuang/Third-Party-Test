<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title></title>

<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTab.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var resultJson = null;
	var contactJson = null;
	var grid;
	$(function() {
		$("form").ligerForm();
		$("#navtab").ligerTab();
		$("#layout").ligerLayout({
			leftWidth : 300
		});
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '新增客户基础信息',
				click : addBase,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改客户基础信息',
				click : modifyBase
			}, {
				line : true
			}, {
				text : '删除客户信息',
				click : deleteBase
			} ]
		});
		$("#toptoolbar1").ligerToolBar({
			items : [ {
				text : '新增客户联系人信息',
				click : addBase,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改客户联系人信息',
				click : modifyBase
			}, {
				line : true
			}, {
				text : '删除客户联系人信息',
				click : deleteBase
			} ]
		});
		$("#toptoolbar1").ligerToolBar({
			items : [

			]
		});

		grid = $("#maingridData").ligerGrid({
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
			data : resultJson,
			pageSize : 30,
			width : '99%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				$("#customerKey").val(data.customerKey);
				$("#clientnumber").val(data.clientnumber);
				$("#address").val(data.address);
				$("#customername").val(data.customername);
				$("#customerid").val(data.customerid);
			},
		});

		$("#contactGrid").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide : true,
			}, {
				display : '联系人ID',
				name : 'serialnumber',
				width : 150,
				hide : true,
			}, {
				display : '名称',
				name : 'name',
				width : 100,
			}, {
				display : '年龄',
				name : 'age',
				width : 230,
			}, {
				display : '性别',
				name : 'sex',
				width : 100,
			}, {
				display : '职位',
				name : 'position',
				width : 230,
			}, {
				display : '爱好',
				name : 'hobby',
				width : 260
			}, {
				display : '生日',
				name : 'birthday',
				width : 100,
			}, {
				display : '工作电话',
				name : 'worktelephone',
				width : 230,
			}, {
				display : '家庭电话',
				name : 'homephone',
				width : 260
			}, {
				display : '手机',
				name : 'phonenumber',
				width : 100,
			}, {
				display : '对公司态度',
				name : 'scepticsofcompany',
				width : 230,
			}, {
				display : '最佳拜访时间',
				name : 'besttimetovisit',
				width : 260
			}, {
				display : '最佳拜访地点',
				name : 'bestplacetovisit',
				width : 100,
			}, {
				display : '最佳拜访路线',
				name : 'bestcallroute',
				width : 230,
			}, {
				display : '婚姻状况',
				name : 'maritalstatus',
				width : 260
			}, {
				display : '配偶姓名',
				name : 'spousename',
				width : 230,
			}, {
				display : '配偶职业',
				name : 'spouseoccupation',
				width : 260
			}, {
				display : '配偶爱好',
				name : 'spousehobby',
				width : 100,
			}, {
				display : '备注',
				name : 'remarks',
				width : 230,
			}, ],
			data : contactJson,
			pageSize : 30,
			width : '99%',
			height : '95%',
		});
	});

	function addBase() {
		alert(1);
	}
	function deleteBase() {
		var customerid = $("#customerid").val();
		if (customerid == '') {
			$.ligerDialog.warn('请先选择客户信息！');
			return;
		}
		var customername = $("#customername").val();
		$.ligerDialog.confirm('确定要删除客户:' + customername
				+ '的基础信息，以及该信息下的所有联系人吗?', function(yes) {
			$.ajax({
				url : '/jsp/sysconf/sysConf.do?method=deleteBaseCustomerInfo',
				data : 'customerid=' + customerid,
				dataType : 'json',
				type : 'post',
				error : function(e) {
					alert('出现未知错误');
				},
				success : function(data) {
					if (data.success != undefined) {
						$.ligerDialog.success('修改客户基础信息成功！');
					}
					if (data.error != undefined) {
						$.ligerDialog.error('修改客户基础信息失败！');
					}
					grid.reload();
				}
			});
		});

	}
	function modifyBase() {
		var customerid = $("#customerid").val();
		var customerKey = $("#customerKey").val();
		var address = $("#address").val();
		var clientnumber = $("#clientnumber").val();
		var customername = $("#customername").val();
		var basicinfostate = $("#basicinfostate").val();
		if (customerid == '') {
			$.ligerDialog.warn('请先选择客户信息！');
			return;
		}
		$.ajax({
			url : '/jsp/sysconf/sysConf.do?method=modifyBaseCustomerInfo',
			data : 'customerid=' + customerid + '&customerKey=' + customerKey
					+ '&clientnumber=' + clientnumber + '&address=' + address
					+ '&customername=' + customername + '&basicinfostate='
					+ basicinfostate,
			dataType : 'json',
			type : 'post',
			error : function(e) {
				$.ligerDialog.error('出现未知错误');
			},
			success : function(data) {
				if (data.success != undefined) {
					grid.loadData(data.cjson);
					$.ligerDialog.success('修改客户基础信息成功！');
				}
				if (data.error != undefined) {
					$.ligerDialog.error('修改客户基础信息失败！');
				}
			}
		});
	}
</script>
<style type="text/css">
body {
	padding: 10px;
	margin: 0;
}

#layout {
	width: 100%;
	margin: 40px;
	height: 400px;
	margin: 0;
	padding: 0;
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
</style>
</head>

<body>
	<div id="layout">

		<div position="left" id="leftlayout1">
			<div id="maingridData" style="margin: 0; padding: 0"></div>
		</div>

		<div position="center" title="">
			<div id="navtab" style="overflow:hidden; border:1px solid #A3C0E8; ">
				<div tabid="baseinfo" title="客户基础信息" lselected="true">
					<div id="toptoolbar"></div>
					<form name="form1" method="post" action="" id="form1">
						<input type="hidden" id="customerid" />
						<table cellpadding="0" cellspacing="0" class="l-table-edit">
							<tr>
								<td align="right" class="l-table-edit-td">客户名称:</td>
								<td align="left" class="l-table-edit-td"><input
									readonly="readonly" name="customername" type="text"
									id="customername" ltype="text" />
								</td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="right" class="l-table-edit-td">客户号:</td>
								<td align="left" class="l-table-edit-td"><input
									name="clientnumber" type="text" id="clientnumber" ltype="text" />
								</td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="right" class="l-table-edit-td">客户地址:</td>
								<td align="left" class="l-table-edit-td"><input
									name="address" type="text" id="address" ltype="text" />
								</td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="right" class="l-table-edit-td">webService验证KEY:</td>
								<td align="left" class="l-table-edit-td" style="width: 400px"><input
									style="width: 400px" name="customerKey" type="text"
									id="customerKey" ltype="text" placeholder="webService验证KEY" />
								</td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="right" class="l-table-edit-td">是否有检验项目数据:</td>
								<td align="left" class="l-table-edit-td" style="width: 400px"><select
									name="basicinfostate" id="basicinfostate" ltype="select">
										<option value="1">有数据</option>
										<option value="0">无数据</option>
								</select>
								</td>
								<td align="left"></td>
							</tr>
						</table>
					</form>
				</div>
				<div tabid="home" title="客户联系人信息" lselected="true">
					<div id="contactGrid" style="margin: 0; padding: 0"></div>
				</div>
			</div>
		</div>
	</div>

	<div style="display:none;">
</body>
</html>
<script type="text/javascript">
	resultJson = ${result_json};
</script>