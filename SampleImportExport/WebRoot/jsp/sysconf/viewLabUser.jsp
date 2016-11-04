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
				display : 'id',
				name : 'id',
				width : 40,
				hide:true,
			},  {
				display : '姓名',
				name : 'name',
				width : 80,
			},{
				display : '电话号码',
				name : 'phoneNumber',
				width : 130,
			}, {
				display : '用户名',
				name : 'username',
				width : 100,
			},{
				display : 'e-mail',
				name : 'email',
				width : 200,
			}, {
				display : '地址',
				name : 'address',
				width : 350,
			}],
			data : resultJson,
			pageSize : 10,
			width : '99%',
			height : '95%',
			checkbox : false,
			rownumbers: true,
		});
		$("#toptoolbar").ligerToolBar({
			items : [ {
				text : '新增账号',
				click : addLabUser,
				icon : 'add'
			}, {
				line : true
			}, {
				text : '修改账号',
				click : modifyLabUser
			}, {
				line : true
			}, {
				text : '删除账号',
				click : deleteLabUser
			} ],
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
	function addLabUser() {
		$.ligerDialog.open({
			height : 400,
			Width : 380,
			url : '/jsp/sysconf/sysConf.do?method=viewAddLabUser',
			title : "增加系统账号界面"
		});
	}

	function modifyLabUser() {
		var row = grid.getSelectedRow();
		if (!row) {
		$.ligerDialog.warn('请选择需要修改的账号！');
			return;
		} else {
			$.ligerDialog
					.open({
						height : 400,
						Width : 380,
						url : '/jsp/sysconf/sysConf.do?method=viewUpdateLabUser&id='+ row.id,
						title : "修改界面"
					});
		}
	}
	function deleteLabUser() {
		var row = grid.getSelectedRow();
		if (!row) {
			$.ligerDialog.warn('请先选择需要删除的行！');
			return;
		}
		$.ligerDialog
				.confirm(
						'确定要删除:' + row.name + '的信息吗?',
						function(yes) {
							if (yes) {
								$.ajax({
											url : '/jsp/sysconf/sysConf.do?method=deleteLabUser',
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
			<div id="maingridData" style="margin: 0; padding: 0;"></div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	resultJson = ${resultJson};
</script>
