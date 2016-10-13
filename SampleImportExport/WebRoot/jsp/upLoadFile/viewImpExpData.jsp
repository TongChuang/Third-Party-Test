<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>



<script type="text/javascript">
	var result_Sjson = null; 
	var result_Pjson = null;
	var result_Tjson = null;
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 230
		});
		f_initGrid();

		var msg = $("#msg").val();
		var success = $("#success").val();
		if (msg != '' && msg != null) {
			$.ligerDialog.error(msg);
		}
		if (success != '' && msg != null) {
			$.ligerDialog.success(success);
		}

	});
	var manager;

	function f_initGrid() {
		manager = $("#maingrid").ligerGrid({
			columns : [ 
			 //当前状态，开始为空的，扫码后修改值为1
			{display : '是否扫码验证', name : 'state', width : 100,
				render : function(item) {
					if (parseInt(item.state) == 1) {
						return "<span style='color:green'>已验证</span>";
					} else {
						return "<span style='color:red'>未验证</span>";
					}
				}
			},
			{display : '条码号',name : 'dsfbarcode',width : 100,}, 
			{display : '病人唯一号',name : 'patientblh',width : 100,},
			{display : '病人就诊号',name : 'patientid',width : 100,},
			{display : '病人名称',name : 'patientname',width : 150,},
			{display : '病人生日',name : 'birthday',width : 150,}, 
			{display : '病人性别',name : 'sex',width : 100,
				render : function(item) {
					if (parseInt(item.sex) == 1) {
						return "男";
					} 
					if (parseInt(item.sex) == 2) {
						return "女";
					}
				}
			}, 
			{display : '病人年龄',name : 'age',width : 120,}, 
			{display : '病床号',name : 'departBed',width : 100,}, 
			{display : '就诊方式',name : 'stayhospitalmode',width : 100,}, 
			{display : '申请科室',name : 'hossection',width : 100,}, 
			{display : '诊断',name : 'diagnostic',width : 100,}, 
			{display : '检验目的',name : 'inspectionname',width : 100,}, 
			{display : '检验目的id',name : 'ylxh',width : 100,}, 
			{display : '样本类型',name : 'sampletype',width : 100,}, 
			{display : '生理周期',name : 'cycle',width : 100,}, 
			{display : '年龄单位',name : 'ageunit',width : 100,}, ],
			data : $.extend(true, {}, result_Sjson),
			pageSize : 20,
			width : '95%',
			height : '99%',
			checkbox : false,
			detail: { onShowDetail: f_showDetail },
			
		});
	}
	
		function f_getPData(dsfbarcode)
        {
            var data = { Rows: [] };
            for (var i = 0; i < result_Pjson.Rows.length; i++)
            {
                if (result_Pjson.Rows[i].dsfbarcode == dsfbarcode)
                    data.Rows.push(result_Pjson.Rows[i]);
            }
            return data;
        }
        function f_getTData(dsfbarcode)
        {
            var data = { Rows: [] };
            for (var i = 0; i < result_Tjson.Rows.length; i++)
            {
                if (result_Tjson.Rows[i].dsfbarcode == dsfbarcode)
                    data.Rows.push(result_Tjson.Rows[i]);
            }
            return data;
        }
        //显示样本详情
        function f_showDetail(row, detailPanel,callback)
        {
            var grid = document.createElement('div'); 
            $(detailPanel).append(grid);
            $(grid).css('margin',10).ligerGrid({
                columns:
                  [
	                  { display: '条码号', name: 'dsfbarcode', width : 150,},
	                  { display: '检验项目ID', name: 'testitem',width : 150, },
	                  { display: '检验项目名字', name: 'name' ,width : 150,},
                  ], isScroll: false, showToggleColBtn: false, width: '90%',
                data: f_getTData(row.dsfbarcode) , showTitle: false, 
                 onAfterShowData: callback,frozen:false
            });  
			 var grid1 = document.createElement('div'); 
            $(detailPanel).append(grid1);
			$(grid1).css('margin',10).ligerGrid({
                columns:
                  [
	                  { display: '条码号', name: 'dsfbarcode', width : 150,},
	                  { display: '开单人', name: 'requester', width: 150,},
	                  { display: '开单时间', name: 'requesttime' ,width : 150,},
	                  { display: '采集人', name: 'executor' ,width : 150,},
	                  { display: '采集时间', name: 'executetime',width : 150, },
                  ], isScroll: false, showToggleColBtn: false, width: '90%',
                data: f_getPData(row.dsfbarcode) , showTitle: false
                 , onAfterShowData: callback,frozen:false
            });  
        }
	
	
	
	
	

	function viewCode() {
		var rowindex = null;
		var data = manager.getData();
		var key = $("#code").val();
		for ( var i = 0; i < data.length; i++) {
			if (data[i].dsfbarcode == key) {
				rowindex = data.indexOf(data[i]);
			}
		}
		manager.updateCell('state', "1", rowindex);
		$("#code").val("");
		$("code").focus();
	}

	function saveData() {
		var cinfoid = $("#customerid").val(); 
		var cinfoText=$("#customerid").find("option:selected").text(); 
		if(null==cinfoid||cinfoid==""){
			$.ligerDialog.error('请先选择客户');
			return ;
		}
	
		var updata = manager.getUpdated();
		var data = manager.getData();
		if(data.length<1){
			$.ligerDialog.error('请先导入数据');
			return;
		}
		if(updata.length<1){
			$.ligerDialog.error('请先扫码确认');
			return;
		}
		
		if (data.length>1&&data.length != updata.length) {
			$.ligerDialog.confirm('客户:'+cinfoText+';此次共收到'+data.length+'条记录,已确认'+updata.length+'条记录,确定保存接收的标本信息么？', function(yes) {
				if (yes) {
					var updateJson = JSON.stringify(updata);
					var pjson = JSON.stringify(result_Pjson);
					var tjson = JSON.stringify(result_Tjson);
					$.ajax({  
	                    url: '/jsp/updown/updown.do?method=saveData',  
	                    data: 'updateJson='+updateJson+'&pjson='+ pjson+'&tjson='+ tjson+'&customerid='+cinfoid,
	                    dataType: 'json',  
	                    type: 'post',  
	                    success: function (result)  
	                    {     
	                        $.ligerDialog.success('提交成功！');
	                    }
               		});
				} else {
					return;
				}
			});
		}
	}

	/* 验证导入的文件 */
	function checkFileExt(filename) {
		var flag = false; //状态
		var arr = null;
		var type = $("#type").val();
		if (type == 'excel') {
			arr = [ "xls", "xlsx" ];
		}
		if (type == 'xml') {
			arr = [ "xml" ];
		}
		//span内容
		var s = document.getElementById("s_msg");
		var e = document.getElementById("e_msg");
		if (arr != null) {
			//取出上传文件的扩展名
			var index = filename.lastIndexOf(".");
			var ext = filename.substr(index + 1);
			//循环比较
			for ( var i = 0; i < arr.length; i++) {
				if (ext == arr[i]) {
					flag = true; //一旦找到合适的，立即退出循环
					break;
				}
			}
		}
		//条件判断
		if (flag) {
			e.innerHTML = "";
			s.innerHTML = "文件合法!";
			$("#sub").attr("disabled", false);
		} else {
			s.innerHTML = "";
			e.innerHTML = "文件不合法!";
			$("#sub").attr("disabled", true);
		}
	}
	/* 提交导入的文件 */
	function onSubminForm() {
		var type = $("#type").val();
		if (type == 'excel') {
			document.form1.action = "/jsp/updown/updown.do?method=uploadExcelFile";
			document.form1.submit();
		}
		if (type == 'xml') {
			document.form1.action = "/jsp/updown/updown.do?method=uploadXmlFile";
			document.form1.submit();
		}
	}
</script>


<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
	font-size: 12px;
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

h4 {
	margin: 20px;
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

	<input type="hidden" id="type" name="type" value="${requestScope.type}">
	<div id="layout1">
		<div position="left" title="上传导入的文件">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">
				<form id="form1" name="form1" action="" method="post"
					enctype="multipart/form-data">
					<tr>
						<td align="left" class="l-table-edit-td"><input type="file"
							name="uploadFile" id="uploadFile"
							onchange="checkFileExt(this.value)" /></td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><span
							style="weight:200px;color:green" id="s_msg"></span> <span
							style="weight:200px;color:red" id="e_msg"></span></td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input id="sub"
							type="button" onclick="onSubminForm();" value="上传文件" /></td>
					</tr>
					</div>
				</form>
			</table>
		</div>
		<!-- 详细信息 -->

		<div position="center" title="具体内容">
			<form id="form2" name="form2" action="" method="post">
				<table cellpadding="0" cellspacing="0" class="l-table-edit"
					style="margin-top:10px">
					<tr>
						<td align="right" class="l-table-edit-td">条码扫描确认区:</td>
						<td align="left" class="l-table-edit-td"><input
							onchange="viewCode()" value="" name="code" type="text"
							style="width: 200px;" id="code" ltype="text" />
						</td>
						<td align="left" style="width: 80px;"></td>
						
						<td align="left" class="l-table-edit-td">
							<select id="customerid" name="customerid" style="width: 200px;">
								<option value="">请选择客户</option>
								<c:forEach items="${customerInfoList}" var="cinfo">
									<option value="${cinfo.customerid}">${cinfo.customername}</option>
								</c:forEach>
							</select>
						</td>
						<td align="left" style="width: 80px;"></td>

						<td>
							<div class="l-button" ligeruiid="Button1000" onclick="saveData()"
								style="width: 120px;">
								<div class="l-button-l"></div>
								<div class="l-button-r"></div>
								<span>保存已确认数据</span>
							</div></td>
						<!-- <td>
									<div class="l-button" ligeruiid="Button1000"
										onclick="viewCode()" style="width: 120px;">
										<div class="l-button-l"></div>
										<div class="l-button-r"></div>
										<span>保存已确认数据</span>
									</div></td> -->
					</tr>
				</table>
				</from>
				<div id="maingrid" style="margin-top:10px"></div>
				<br />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	result_Sjson = ${result_Sjson};
	result_Pjson = ${result_Pjson};
	result_Tjson = ${result_Tjson};
</script>
