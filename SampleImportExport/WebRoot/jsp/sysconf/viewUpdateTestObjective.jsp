<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
</style>
<script src="/resources/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>  
<script src="/resources/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerWindow.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script type="text/javascript">
var groupicon = "/resources/ligerUI/skins/icons/communication.gif";
var resultJson = null;
var form = null;
var dialog = frameElement.dialog;
$(function () {
            //创建表单结构
            form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 90, space: 40,
                validate : true,
                fields: [
                { display: "客户编号", name: "customerid", newline: true, readonly: true, type: "text", group: "检验目的修改", groupicon: groupicon },
                { display: "id", name: "id", newline: true, type: "hidden" },
                { display: "医疗序号", name: "ylxh", newline: true, type: "text" },
                { display: "医疗名称", name: "ylmc", newline: true, type: "text" },
                { display: "项目编号", name: "profiletest", newline: true, type: "text" },
                { display: "专业组", name: "professionalgroup", newline: true, type: "text" }, 
                { display: "检验段", name: "inspectionsection", newline: true, type: "text" }, 
                ]
                , buttons: [{ text: "保存", width: 60, click: updateTestObjectiveButton }]
            });
            form.setData(resultJson);
});
function colseDialog(){
 	dialog.close();//关闭dialog 
}
function updateTestObjectiveButton(){
	//alert(alert(JSON.stringify(form.getData()))); 
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=updateTestObjective',
		dataType: 'json',
		data: form.getData(),
		type: 'post', 
		success:function(datas)  
		{
			if (datas.success != undefined) {
				window.parent.grid2.loadData(datas.result_json);
				parent.$.ligerDialog.success(datas.success);
				colseDialog();
			}
        }
	});
}
</script>
</head>
<body style="padding:10px">
	<div id="form"></div>
</body>
<script type="text/javascript">
	resultJson = ${result_json};
</script>
</html>