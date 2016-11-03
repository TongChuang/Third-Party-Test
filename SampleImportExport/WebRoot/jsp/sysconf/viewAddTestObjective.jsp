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
<script src="/resources/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="/resources/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="/resources/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="/resources/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript">
var groupicon = "/resources/ligerUI/skins/icons/communication.gif";
var form = null;
var dialog = frameElement.dialog;
$(function () {
             //创建表单结构
             form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 90, space: 40,
                validate : true,
                fields: [
                { display: "客户编号", name: "customerid", newline: true, type: "text", group: "检验目的增加", groupicon: groupicon },
                { display: "id", name: "id", newline: true, type: "hidden" },
                { display: "医疗序号", name: "ylxh", newline: true, type: "digits", validate:{required:true,minlength:5} },
                { display: "医疗名称", name: "ylmc", id:"ylmc", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "项目编号", name: "profiletest", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "专业组", name: "professionalgroup", newline: true, type: "text", validate:{required:true,minlength:1} }, 
                { display: "检验段", name: "inspectionsection", newline: true, type: "text", validate:{required:true,minlength:1} }, 
                ]
                , buttons: [{ text: "保存", width: 60, click: addTestObjectiveButton }]
            });
            var rs = $("#hidden_input").val();
            //设置表单内容
            form.setData({customerid:rs});
            
});
function colseDialog(){
 	dialog.close();//关闭dialog 
}
function addTestObjectiveButton(){
	var data = form.getData();
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addTestObjective',
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
	<input id="hidden_input" type="hidden" name="customerid" value="${customerid}"/>
</body>
</html>