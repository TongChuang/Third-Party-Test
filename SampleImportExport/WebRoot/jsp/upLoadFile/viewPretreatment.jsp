<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
	<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
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

<script type="text/javascript">
	var eee;
	var result_Sjson;
	var manager;
	$(function() {
		manager = $("#maingrid").ligerGrid({
			columns : [
			//当前状态，开始为空的，扫码后修改值为1
			{
				display : '病人名称',
				name : 'patientname',
				width : 150,
			},{
				display : '条码号',
				name : 'dsfbarcode',
				width : 100,
			}, {
				display : '病人唯一号',
				name : 'patientblh',
				width : 100,
			}, {
				display : '病人就诊号',
				name : 'patientid',
				width : 100,
			}, {
				display : '病人生日',
				name : 'birthday',
				width : 150,
			}, {
				display : '病人性别',
				name : 'sex',
				width : 100,
				render : function(item) {
					if (parseInt(item.sex) == 1) {
						return "男";
					}
					if (parseInt(item.sex) == 2) {
						return "女";
					}
				}
			}, {
				display : '病人年龄',
				name : 'age',
				width : 120,
			}, {
				display : '病床号',
				name : 'departBed',
				width : 100,
			}, {
				display : '就诊方式',
				name : 'stayhospitalmode',
				width : 100,
			}, {
				display : '申请科室',
				name : 'hossection',
				width : 100,
			}, {
				display : '诊断',
				name : 'diagnostic',
				width : 100,
			}, {
				display : '检验目的',
				name : 'inspectionname',
				width : 100,
			}, {
				display : '检验目的id',
				name : 'ylxh',
				width : 100,
			}, {
				display : '样本类型',
				name : 'sampletype',
				width : 100,
			}, {
				display : '生理周期',
				name : 'cycle',
				width : 100,
			}, {
				display : '年龄单位',
				name : 'ageunit',
				width : 100,
			}, ],
			data : null,
			pageSize : 20,
			width : '95%',
			height : '99%',
			checkbox : false,

		});

		$("#layout1").ligerLayout({
			leftWidth : 300
		});

		$("form").ligerForm();
		//提交数据
		/* $(".l-button-submit").click(function() {
			alert("提交数据");
		}); */

	});

	function getSampleInfo() {
		var barcode = $("#barcode").val();
		$.ajax({
			url : '/jsp/updown/updown.do?method=getSampleInfoByBarCode',
			data : 'barcode=' + barcode,
			dataType : 'json',
			type : 'post',
			error : function(e) {
				alert('出现未知错误');
			},
			success : function(data) {
				$("#barcode").val("");
				$("#barcode").focus();
				if (data.error != undefined) {
					$.ligerDialog.error(data.error);
				//	$("#pic").attr('src',data.pic); 
				}else{
					$("#patientname").val(data.patientname);
					$("#patientid").val(data.patientid);
					$("#departBed").val(data.departBed);
					$(".select").find("option[text='pxx']").attr("selected",true);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
					$("#patientname").val(data.patientname);
				}
				
			}
		});
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
	<div id="layout1">
		<div position="left" title="待接收样本">
			<div id="maingrid"></div>
		</div>
		<div position="center" title="样本详情">
			<input type="hidden" id="error" />
			<form name="form" method="post" id="form" commandName="LSample"
				action="/jsp/updown/updown.do?method=addManualEntry"
				enctype="multipart/form-data">
				<table cellpadding="0" cellspacing="0" class="l-table-edit">
					<tr>
						<td align="right" class="l-table-edit-td">病人就诊号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="patientid" type="text" id="patientid" ltype="text" />
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人病床号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="departBed" type="text" id="departBed" ltype="text" />
						</td>
						<td align="left"></td>
						<td rowspan="8"><img name="pic" id="pic"
							style="height:300px; with:260px" alt="" border="1" src="${pic}">
						</td>
					</tr>

					<tr>
						<td align="right" class="l-table-edit-td">病人名字:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="patientname" type="text" id="patientname" ltype="text"
							validate="{required:true,minlength:3,maxlength:10}" />
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人性别:</td>
						<td align="left" class="l-table-edit-td" style="width:180px"><select
							name="sex" id="sex" ltype="select">
								<option value="1">男</option>
								<option value="2">女</option>
						</select></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">病人年龄:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="age" type="text" id="age" ltype='spinner'
							ligerui="{type:'int'}" value="" class="required"
							validate="{digits:true,min:0,max:200}" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人生日:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="birthday" type="text" id="birthday" ltype="date"
							validate="{required:true}" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">就诊方式:</td>
						<td align="left" class="l-table-edit-td" style="width:180px"><select
							name="stayhospitalmode" id="stayhospitalmode" ltype="select">
								<option value="1">门诊</option>
								<option value="2">住院</option>
								<option value="3">急诊</option>
						</select></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">申请科室:</td>
						<td align="left" class="l-table-edit-td" style="width:180px"><select
							name="hossection" id="hossection" ltype="select">
								<option value="">请选择</option>
						</select></td>
						<td align="left"></td>
					</tr>

					<tr>
						<td align="right" class="l-table-edit-td">诊断结果:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="diagnostic" type="text" id="diagnostic" ltype="text"
							validate="{required:true}" />
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">检验目的:</td>
						<td align="left" class="l-table-edit-td" style="width:180px"><select
							name="ylxh" id="ylxh" ltype="select">
								<option value="">请选择</option>
						</select></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">样本类型:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><select
							name="sampletype" id="sampletype" ltype="select">
								<option value="">请选择</option>
						</select>
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">生理周期:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="cycle" type="text" id="cycle" ltype="text" />
						</td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">年龄单位:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><select
							name="ageunit" id="ageunit" ltype="select">
								<option value="">请选择</option>
						</select>
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">客户条码号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="dsfbarcode" type="text" id="dsfbarcode" ltype="text"
							validate="{required:true}" />
						</td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">病人唯一号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="patientblh" type="text" id="patientblh" ltype="text"
							validate="{required:true}" />
						</td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">本地条码号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="localbarcode" type="text" id="localbarcode" ltype="text"
							validate="{required:true}" />
						</td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div position="top" align="center">
			<font color="blue" size="6">请输入条码号:<input type="text"
				id="barcode" onchange="getSampleInfo()" /> </font>
		</div>
		<div style="display:none">
			<!--  数据统计代码 -->
		</div>
	</div>
</body>
</html>
