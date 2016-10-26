<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="/resources/ligerUI/skins/Gray/css/all.css" rel="stylesheet"
	type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerPopupEdit.js"></script>
<script src="/resources/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTip.js"
	type="text/javascript"></script>
<script src="/resources/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="/resources/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>
<script src="/resources/jquery-validation/messages_cn.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
	<script src="/resources/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <script src="/resources/ligerUI/js/plugins/ligerFilter.js"></script>
<script type="text/javascript">
	var eee;
	//下拉表格中的查询条件
	$(function() {
		$("#collectiontime").ligerDateEditor({ showTime: true, labelWidth: 100, labelAlign: 'left' });
		$("#ylxh").ligerComboBox({
                width: 179,
                slide: false,
                selectBoxWidth: 500,
                selectBoxHeight: 240,
                valueField: 'index_id',
                textField: 'name',
                grid: getGridOptions(true),
                onButtonClick:f_buttonclick,
                condition: { fields: [{ name: 'name', label: '名称', width: 90, type: 'text' }] }
         });

		$.metadata.setType("attr", "validate");
		var v = $("form").validate(
				{
					//调试状态，不会提交数据的
					//debug : false,
					errorPlacement : function(lable, element) {

						if (element.hasClass("l-text-field")) {
							element.parent().addClass("l-text-invalid");
						}

						var nextCell = element.parents("td:first").next("td");
						nextCell.find("div.l-exclamation").remove();
						$(
								'<div class="l-exclamation" title="'
										+ lable.html() + '"></div>').appendTo(
								nextCell).ligerTip();
					},
					success : function(lable) {
						var element = $("#" + lable.attr("for"));
						var nextCell = element.parents("td:first").next("td");
						if (element.hasClass("l-text-field")) {
							element.parent().removeClass("l-text-invalid");
						}
						nextCell.find("div.l-exclamation").remove();
					},
					//类似onsubmit
					submitHandler : function(form) {
						form.submit();
					}
				});
		$("form").ligerForm();
		//提交数据
		/* $(".l-button-submit").click(function() {
			alert("提交数据");
		}); */

		var error = $("#error").val();
		var success = $("#success").val();
		if (error != '' && error != null) {
			$.ligerDialog.error(error);
		}
		if (success != '' && msg != null) {
			$.ligerDialog.success(success);
		}
	});

	// 点击上传按钮预览图片

	function changePicUrl() {
		var dFile = document.getElementById('mpImg');
		var dImg = document.getElementById('pic');
		if (dFile.files) {
			dImg.src = window.URL.createObjectURL(dFile.files[0]);
		} else if (dFile.value.indexOf('\\') > -1
				|| dFile.value.indexOf('\/') > -1) {
			dImg.src = dFile.value;
		}
	}

	function f_buttonclick() {
		var dsfcustomerid = $("#dsfcustomerid").val();
		if ("0" == dsfcustomerid) {
			$.ligerDialog.error("请先选择客户信息!");
			return false;
		} else {
			
		}
	}
	function getGridOptions(checkbox) {
			var options = {
				columns : [ {
					display : '检验项目ID',
					name : 'index_id',
					align : 'left',
					width : 100,
					minWidth : 70
				}, {
					display : '检验项目名称',
					name : 'name',
					minWidth : 120,
					width : 200
				}, ],
				switchPageSizeApplyComboBox : false,
				data : $.extend({}, null),
				pageSize : 10,
				width : 30,
				checkbox : checkbox
			};
			return options;
		}
</script>
<style type="text/css">
body {
	font-size: 12px;
}

.l-table-edit-td {
	padding: 4px;
}

.l-button-submit {
	width: 120px;
	float: center;
	margin-left: 60px;
	padding-bottom: 2px;
	color: blue;
}

.l-verify-tip {
	left: 230px;
	top: 120px;
}
</style>

</head>

<body style="padding:10px">
	<input type="hidden" id="success" />
	<input type="hidden" id="error" />
	<form name="form" method="post" id="form" commandName="LSample"
		action="/jsp/updown/updown.do?method=addManualEntry"
		enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" class="l-table-edit">
			<tr>
				<td align="right" class="l-table-edit-td">病人就诊号:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="patientid" type="text" id="patientid" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">当前客户:</td>
				<td align="left" class="l-table-edit-td" style="width:160px">
					<select id="dsfcustomerid"  ltype="select">
						<option value="0">请选择</option>
						<c:forEach items="${customerList}" var="dsfcinfo">
							<option value="${dsfcinfo.customerid}"><c:out value="${dsfcinfo.customername}"></c:out> </option>
						</c:forEach>
					</select>
				</td>
				
				<td align="left"></td>
				<td rowspan="10"><img name="pic" id="pic"
					style="height:400px; with:300px" alt="" border="1" src="${pic}">
				</td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td">病人名字:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="patientname" type="text" id="patientname" ltype="text"
					validate="{required:true,minlength:3,maxlength:10}" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">病人性别:</td>
				<td align="left" class="l-table-edit-td" style="width:180px"><select
					name="sex" id="sex" ltype="select">
						<option value="1">男</option>
						<option value="2">女</option>
				</select>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">病人年龄:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="age" type="text" id="age" ltype='spinner'
					ligerui="{type:'int'}" value="" class="required"
					validate="{digits:true,min:0,max:200}" />
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">病人生日:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="birthday" type="text" id="birthday" ltype="date"
					validate="{required:true}" />
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">就诊方式:</td>
				<td align="left" class="l-table-edit-td" style="width:180px"><select
					name="stayhospitalmode" id="stayhospitalmode" ltype="select">
						<option value="1">门诊</option>
						<option value="2">住院</option>
						<option value="3">急诊</option>
				</select>
				</td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">申请科室:</td>
				<td align="left" class="l-table-edit-td" style="width:180px"><select
					name="hossection" id="hossection" ltype="select">
						<option value="x">请选择</option>
				</select>
				</td>
				<td align="left"></td>
			</tr>

			<tr>
				<td align="right" class="l-table-edit-td">诊断结果:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="diagnostic" type="text" id="diagnostic" ltype="text"
					validate="{required:true}" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">检验目的:</td>
				<td align="left" class="l-table-edit-td" style="width:180px"><input
					type="text" id="ylxh" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">样本类型:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><select
					name="sampletype" id="sampletype" ltype="select">
						<option value="x">请选择</option>
				</select></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">生理周期:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="cycle" type="text" id="cycle" ltype="text" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">年龄单位:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><select
					name="ageunit" id="ageunit" ltype="select">
						<option value="岁">岁</option>
						<option value="日">日</option>
						<option value="月">月</option>
						<option value="周">周</option>
				</select></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">客户条码号:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="dsfbarcode" type="text" id="dsfbarcode" ltype="text"
					validate="{required:true}" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">病人唯一号:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="patientblh" type="text" id="patientblh" ltype="text"
					validate="{required:true}" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">本地条码号:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="localbarcode" type="text" id="localbarcode" ltype="text"
					validate="{required:true}" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="right" class="l-table-edit-td">病人病床号:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="departBed" type="text" id="departBed" ltype="text" /></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">采集人:</td>
				<td align="left" class="l-table-edit-td" style="width:160px">
					<select id="collectionpersonnel"  ltype="select">
						<c:forEach items="${userList}" var="user">
							<option value="${user.username}"><c:out value="${user.name}"></c:out> </option>
						</c:forEach>
					</select>
				</td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="right" class="l-table-edit-td">采集时间:</td>
				<td align="left" class="l-table-edit-td" style="width:160px"><input
					name="collectiontime" type="text" id="collectiontime"  ltype="date" /></td>
				<td align="left"></td>
			</tr>
			<tr>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td align="left"></td>
				<td><font color="red">上传文件：&nbsp;&nbsp; <input
						type="file" name="mpImg" id="mpImg" onChange="changePicUrl()">
				</font></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="提交样本信息"
					id="Button1" class="l-button l-button-submit" /> <input
					type="reset" value="重置" class="l-button l-button-reset" /></td>
			</tr>
		</table>
		<br />
	</form>
	<div style="display:none">
		<!--  数据统计代码 -->
	</div>



</body>
</html>
