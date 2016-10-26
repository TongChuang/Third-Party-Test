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
	var resultJson;
	var manager;
	var testObjectiveJson = null;
	$(function() {
		manager = $("#maingrid").ligerGrid({
			columns : [{
				display : 'id',
				name : 'id',
				width : 150,
				hide:true,
			},{ 
				display : '流水号',
				name : 'serialnumber',
				width : 150,
			},{ 
				display : '检验目的编号',
				name : 'ylxh',
				width : 150,
			}, {
				display : '检验目的名称',
				name : 'ylmc',
				width : 150,
			},{
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide:true,
			},{
				display : '专业组',
				name : 'professionalgroup',
				width : 150,
			},{
				display : '检验段',
				name : 'inspectionsection',
				width : 150,
			},],
			data : testObjectiveJson,
			pageSize : 20,
			width : '95%',
			height : '99%',
			checkbox : false,

		});

		$("#layout1").ligerLayout({
			//leftWidth : 300
			leftWidth : 200
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
			success : function(datas) {
				manager.loadData(datas.jsonresult1);
				testObjectiveJson = datas.jsonresult1;
				var data = manager.getData();
				//var data = manager.getColumn();
				alert(JSON.stringify(data))
				alert(data.length);
				for(var i=0;i<data.length;i++){
					if(data[i].serialnumber!=""){
						
					}esle{
						//需要提示弹窗修改流水号
						
					}
				}
			
				//
				$("#barcode").val("");
				$("#barcode").focus();
				if (datas.error != undefined) {
					$.ligerDialog.error(datas.error);
				//	$("#pic").attr('src',data.pic); 
				}else{
					$("#patientid").val(datas.sample_json.patientid);
					$("#departBed").val(datas.sample_json.departBed);
					$("#patientname").val(datas.sample_json.patientname);
					$("#sex").val(datas.sample_json.sex);
					$("#age").val(datas.sample_json.age);
					$("#birthday").val(datas.sample_json.birthday);
					$("#stayhospitalmode").val(datas.sample_json.stayhospitalmode);
					$("#hossection").val(datas.sample_json.hossection);
					$("#diagnostic").val(datas.sample_json.diagnostic);
					$("#ylmc").val(data.sample_json.ylmc);
					$("#sampletype").val(datas.sample_json.sampletype);
					$("#cycle").val(datas.sample_json.cycle);
					$("#ageunit").val(datas.sample_json.ageunit);
					$("#dsfbarcode").val(datas.sample_json.dsfbarcode);
					$("#patientblh").val(datas.sample_json.patientblh);
					$("#localbarcode").val(datas.sample_json.localbarcode);
					
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
		<div position="left" title="检验目的列表">
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
							name="patientid" type="text" id="patientid" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人病床号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="departBed" type="text" id="departBed" readonly="readonly" /></td>
						<td align="left"></td>
						<td rowspan="8"><img name="pic" id="pic"
							style="height:300px; with:260px" alt="" border="1" src="${pic}"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">病人名字:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="patientname" type="text" id="patientname" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人性别:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="sex" type="text" id="sex" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">病人年龄:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="age" type="text" id="age" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">病人生日:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="birthday" type="text" id="birthday" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">就诊方式:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="stayhospitalmode" type="text" id="stayhospitalmode" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">申请科室:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="hossection" type="text" id="hossection" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>

					<tr>
						<td align="right" class="l-table-edit-td">诊断结果:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="diagnostic" type="text" id="diagnostic" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">检验目的:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="ylmc" type="text" id="ylmc" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">样本类型:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="sampletype" type="text" id="sampletype" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">生理周期:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="cycle" type="text" id="cycle" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">年龄单位:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="ageunit" type="text" id="ageunit" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">客户条码号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="dsfbarcode" type="text" id="dsfbarcode" readonly="readonly" /></td>
						<td align="left"></td>
					</tr>
					<tr>
						<td align="right" class="l-table-edit-td">病人唯一号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="patientblh" type="text" id="patientblh" readonly="readonly" /></td>
						<td align="left"></td>
						<td align="right" class="l-table-edit-td">本地条码号:</td>
						<td align="left" class="l-table-edit-td" style="width:160px"><input
							name="localbarcode" type="text" id="localbarcode" readonly="readonly" /></td>
						<td align="left"></td>
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
<script type="text/javascript">
	resultJson = ${result_json};
</script>
</html>
