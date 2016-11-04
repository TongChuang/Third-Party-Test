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
$(function () {
            //创建表单结构
            form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 90, space: 40,
                validate : true,
                fields: [
                { display: "检验单位名称", name: "name", newline: true,  type: "text", group: "检验单位修改", groupicon: groupicon,validate:{required:true,minlength:1} },
                { display: "地址", name: "address", newline: true, type: "text" ,validate:{required:true,minlength:1}},
                { display: "联系电话", name: "phone", newline: true, type: "text" },
                 { display: "id", name: "id", newline: true, type:"hidden" },
                ]
                , buttons: [{ text: "保存", width: 160, click: updateTestCenterInfo }]
            });
            form.setData(resultJson);
});
function updateTestCenterInfo(){
	//alert(alert(JSON.stringify(form.getData()))); 
	var row = form.getData();
	if(""==row.name){
		$.ligerDialog.warn('检验单位不能为空!');
		return;
	}
	if(""==row.address){
		$.ligerDialog.warn('地址不能为空!');
		return;
	}
	 var a=/^[0-9]*[1-9][0-9]*$/;
    if(!a.test(row.phone)){ 
		$.ligerDialog.warn('联系电话格式不正确!');
		return;
	}
	
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=updateTestCenterInfo',
		dataType: 'json',
		data: form.getData(),
		type: 'post', 
		success:function(datas)  
		{
			if (datas.success != undefined) {
				window.parent.grid.loadData(datas.resultjson);
				window.parent.$.ligerDialog.success(datas.success);
				colseDialog();
			}
			if (datas.error != undefined) {
				$.ligerDialog.error(datas.error);
			}
		
			
        },
        error:function(datas){
        	$.ligerDialog.error('未知错误');
        } 
	});
	
	 var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
 function colseDialog(){
	 dialog.close();//关闭dialog 
 }
 
}
</script>
</head>
<body style="padding:10px">
	<div id="form"></div>
</body>
<script type="text/javascript">
	resultJson = ${resultjson};
</script>
</html>