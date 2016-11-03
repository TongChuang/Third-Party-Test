<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title></title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/My97DatePicker/WdatePicker.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript"></script>
<style type="text/css">
body {
	padding: 10px;
	margin: 0;
}

#layout1 {
	width: 100%;
	margin: 40px;
	height: 400px;
	margin: 0;
	padding: 0;
}

#accordion1 {
	height: 270px;
}

h4 {
	margin: 20px;
}
</style>
<script type="text/javascript">
	var resultJson = null;
	var grid = null;
	var grid2 = null;
	$(function() {
		$("#layout1").ligerLayout({
			isLeftCollapse : true
		});

		grid = $("#maingridData").ligerGrid({
			columns : [
			//当前状态，开始为空的，扫码后修改值为1
			{
				display : 'ID',
				name : 'id',
				width : 50,
			}, {
				display : '检验单位名称',
				name : 'name',
				width : 200,
			}, {
				display : '地址',
				name : 'address',
				width : 300,
			}, {
				display : '联系电话',
				name : 'phone',
				width : 200,
			} ],
			data : resultJson,
			pageSize : 30,
			width : '99%',
			height : '99%',
			checkbox : false,
		});
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '新增检验单位',
				click : addTestCenter,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改检验单位',
				click : modifyTestCenter
			}, {
				line : true
			}, {
				text : '删除检验单位',
				click : deleteTestCenter
			} ]
		});
	});

	function query() {
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var flag = false;
		if (beginTime == '' && endTime == '') {
			flag = true;
		}
		if (beginTime != '' && endTime != '') {
			flag = true;
		}
		if (flag) {
			document.form1.action = "/jsp/updown/updown.do?method=queryExpData";
			document.form1.submit();
		} else {
			$.ligerDialog.error("起止时间不能单个为空");
		}
	}

	//检验结果ajax方法
	function ajaxTestResult(data) {
		$.ajax({
			url : '/jsp/updown/updown.do?method=getQueryResult',
			dataType : 'json',
			data : "sampleno=" + data.sampleno,
			type : 'post',
			success : function(datas) {
				grid2.loadData(datas.resultjson);
				testItemJson = datas.resultjson;
			}
		});
	}
	function addTestCenter() {
		$.ligerDialog.open({
			height : 400,
			Width : 380,
			url : '/jsp/sysconf/sysConf.do?method=viewAddTestCenterInfo',
			title : "增加客户信息界面"
		});
	}

	function modifyTestCenter() {
		var row = grid.getSelectedRow();
		if (!row) {
		$.ligerDialog.warn('请选择需要修改的检验单位！');
			return;
		} else {
			$.ligerDialog
					.open({
						height : 400,
						Width : 380,
						url : '/jsp/sysconf/sysConf.do?method=viewUpdateTestCenterInfo&id='+ row.id,
						title : "修改界面"
					});
		}
	}
	function deleteTestCenter() {
		var row = grid.getSelectedRow();
		if (!row) {
			$.ligerDialog.warn('请先选择检验单位！');
			return;
		}
		$.ligerDialog
				.confirm(
						'确定要删除:' + row.name + '的信息吗?',
						function(yes) {
							if (yes) {
								$
										.ajax({
											url : '/jsp/sysconf/sysConf.do?method=deleteTestCenterInfo',
											data : 'id=' + row.id,
											dataType : 'json',
											type : 'post',
											error : function(e) {
												$.ligerDialog.error('出现未知错误');
											},
											success : function(data) {
												if (data.success != undefined) {
													$.ligerDialog
															.success(data.success);
														grid.remove(row) ;
												}
												if (data.error != undefined) {
													$.ligerDialog
															.error(data.error);
												}
											}
										});
							}

						});

	}
</script>
</head>

<body>
	<div id="layout1">
		<div position="center" title="检验单位信息">
			<div id="toptoolbar"></div>
			<div id="maingridData" style="margin: 0; padding: 0"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	resultJson = ${resultJson};
</script>
