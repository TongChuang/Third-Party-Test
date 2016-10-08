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
	$(function() {
		$("#layout1").ligerLayout({
			isLeftCollapse : true
		});
		$("#maingridData").ligerGrid({
			columns : [ {
				display : '样本编号',
				name : 'sampleno',
				width : 150,
			}, {
				display : 'testid',
				name : 'testid',
				width : 100,
				hide : true,
			}, {
				display : '检测名称',
				name : 'testname',
				width : 120
			}, {
				display : '检测结果',
				name : 'testresult',
				width : 120
			}, {
				display : '最高值',
				name : 'refhi',
				width : 100,
			}, {
				display : '最低值',
				name : 'reflo',
				width : 100,
			}, {
				display : '样本类型',
				name : 'sampletype',
				width : 100
			}, {
				display : '审核人',
				name : 'operator',
				width : 100
			}, {
				display : '审核时间',
				name : 'measuretime',
				width : 100
			}, {
				display : '单位',
				name : 'unit',
				width : 100
			}, {
				display : '检测方法',
				name : 'method',
				width : 100
			}, {
				display : '客户ID',
				name : 'customerid',
				width : 70,
				hide : true
			}, {
				display : '第三方条码号',
				name : 'dsfbarcode',
				width : 150,
			}, ],
			data : resultJson,
			pageSize : 30,
			width : '99%',
			height : '99%',
			checkbox : false
		});
	});

	function query() {
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var customerid = $("#customerid").val();
		var flag = false;
		if (beginTime == '' && endTime == '') {
			flag = true;
		}
		if (beginTime != '' && endTime != '') {
			flag = true;
		}
		if (customerid == '') {
			$.ligerDialog.error("请选择客户");
			return;
		}
		if (flag) {
			document.form1.action = "/jsp/updown/updown.do?method=queryExpData";
			document.form1.submit();
		} else {
			$.ligerDialog.error("起止时间不能单个为空");
		}
	}

	function expDate(type) {
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();
		var customerid = $("#customerid").val();

		var flag = false;
		if (beginTime == '' && endTime == '') {
			flag = true;
		}
		if (beginTime != '' && endTime != '') {
			flag = true;
		}
		if (customerid == '') {
			$.ligerDialog.error("请选择客户");
			return;
		}
		if (flag) {
			var m = $.ligerDialog.open({
				height : 200,
				url : '/jsp/updown/updown.do?method=exportResult&beginTime='
						+ beginTime + '&endTime=' + endTime + '&customerid='
						+ customerid + '&type=' + type
			});
			setTimeout(function() {
				m.close();
			}, 3000);
		} else {
			$.ligerDialog.error("起止时间不能单个为空");
		}
	}
</script>
</head>

<body>
	<div id="layout1">
		<div position="center" title="标题">
			<form class="l-form" ligeruiid="form1" id="form1" name="form1"
				action="" method="post">
				<input type="hidden" id="msg" name="msg" value="${msg}">
					<div class="l-form-container"></div>
					<ul>
						<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
							<ul>
								<li style="width:100px;text-align:right;">开始时间：</li>
								<li id="form1|0" style="width:170px;text-align:left;">
									<div class="l-text" style="width: 165px;">
										<input type="text" id="beginTime" name="beginTime"
											class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											value="${beginTime}" ligeruiid="beginTime"
											style="width: 164px;">
											<div class="l-text-l"></div>
											<div class="l-text-r"></div>
									</div></li>
								<li style="width:100px;text-align:right;">结束时间：</li>
								<li id="form1|1" style="width:170px;text-align:left;">
									<div class="l-text" style="width: 165px;">
										<input type="text" id="endTime" name="endTime" class="Wdate"
											onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
											value="${endTime}" ligeruiid="endTime" style="width: 164px;">
											<div class="l-text-l"></div>
											<div class="l-text-r"></div>
									</div>
								</li>
								<li style="width:60px;text-align:right;"></li>
								<li id="form1|2" style="width:170px;text-align:left;">
									<div class="l-text" style="width: 165px;">
										<select id="customerid" name="customerid"
											style="width: 170px;">
											<option value="">请选择客户</option>
											<c:forEach items="${customerInfoList}" var="cinfo">
												<option value="${cinfo.customerid}">${cinfo.customername}</option>
											</c:forEach>
										</select>
									</div>
								</li>
								<li>
									<div style="width: 40px;"></div></li>
								<li>
									<div class="l-button" ligeruiid="query" id="query"
										onclick="query()" style="width: 80px;">
										<div class="l-button-l"></div>
										<div class="l-button-r"></div>
										<span>查询结果</span>
									</div></li>
								<li>
									<div style="width: 40px;"></div></li>
								<li>
									<div class="l-button" ligeruiid="expExcel" id="expExcel"
										onclick="expDate('excel')" style="width: 100px;">
										<div class="l-button-l"></div>
										<div class="l-button-r"></div>
										<span>Excel结果导出</span>
									</div></li>
								<li>
									<div style="width: 40px;"></div></li>
								<li>
									<div class="l-button" ligeruiid="expXml" id="expXml"
										onclick="expDate('xml')" style="width: 100px;">
										<div class="l-button-l"></div>
										<div class="l-button-r"></div>
										<span>XML结果导出</span>
									</div></li>
							</ul></li>
					</ul>
					<div class="l-clear"></div>
			</form>
			<div id="maingridData" style="margin: 0; padding: 0"></div>
		</div>
		<div position="right">
			<table>
				<tr>
					<td>asfdfd</td>
				</tr>

				<tr>
					<td>asfdfd</td>
				</tr>
				<tr>
					<td>asfdfd</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	resultJson = $
	{
		result_json
	};
</script>
