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
	var resultTOjson = null;
	var resultTIjson = null;
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
			columns : [ {
				display : '医疗序号',
				name : 'ylxh',
				width : 100,
			}, {
				display : '医疗名称',
				name : 'ylmc',
				width : 200,
			},{
				display : 'profiletest',
				name : 'profiletest',
				width : 100,
				hide : true,
			}, ],
			data : $.extend(true, {}, resultTOjson),
			pageSize : 20,
			width : '99%',
			height : '99%',
			checkbox : false,
			detail : {
				onShowDetail : f_showDetail
			},

		});
	}

	function f_getTIData(profiletest) {
		var data = {
			Rows : []
		};
		var pString = profiletest.split(",");//以逗号作为分隔字符串
		for ( var i = 0; i < resultTIjson.Rows.length; i++) {
			for(var j=0;j<pString.length;j++){
				if(pString[j]==resultTIjson.Rows[i].testitem){
					data.Rows.push(resultTIjson.Rows[i]);
				}
			}
		}
		
		return data;
	}
	//显示样本详情
	function f_showDetail(row, detailPanel, callback) {
		var grid = document.createElement('div');
		$(detailPanel).append(grid);
		$(grid).css('margin', 10).ligerGrid({
			columns : [ {
				display : '检验项目编号',
				name : 'testitem',
				width : 150,
			}, {
				display : '检验项目名称',
				name : 'name',
				width : 200,
			}, ],
			isScroll : false,
			showToggleColBtn : false,
			width : '95%',
			data : f_getTIData(row.profiletest),
			showTitle : false,
			onAfterShowData : callback,
			frozen : false
		});
	}

	/* 验证导入的文件 */
	function checkFileExt(filename) {
		var flag = false; //状态
		var arr = [ "xml" ];
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
		document.form1.action = "/jsp/updown/updown.do?method=ImpBaseDataInfo";
		document.form1.submit();
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

	<div id="layout1">
		<div position="left" title="上传导入的文件">
			<table cellpadding="0" cellspacing="0" class="l-table-edit">
				<form id="form1" name="form1" action="" method="post"
					enctype="multipart/form-data">
					<tr>
						<td align="left" class="l-table-edit-td"><input type="file"
							name="uploadFile" id="uploadFile"
							onchange="checkFileExt(this.value)" />
						</td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><span
							style="weight:200px;color:green" id="s_msg"></span> <span
							style="weight:200px;color:red" id="e_msg"></span>
						</td>
					</tr>
					<tr>
						<td align="left" class="l-table-edit-td"><input id="sub"
							type="button" onclick="onSubminForm();" value="上传文件" />
						</td>
					</tr>
					</div>
				</form>
			</table>
		</div>
		<!-- 详细信息 -->

		<div position="center" title="数据来源，客户${customerid==null?'请先导入数据':customerid}，检验目的，检验项目，内容如下">
			<div id="maingrid" style="margin-top:10px"></div>
			<br />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	resultTOjson = ${result_TOjson};
	resultTIjson = ${result_TIjson};
</script>
