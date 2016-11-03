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
<script src="/resources/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script type="text/javascript">
var groupicon = "/resources/ligerUI/skins/icons/communication.gif";
var form = null;
$(function () {
             //创建表单结构
             form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 90, space: 40,
                validate : true,
                fields: [
                { display: "检验单位名称", name: "name", newline: true, type: "text", group: "检验单位增加", groupicon: groupicon,validate:{required:true,minlength:1} },
                { display: "地址", name: "address", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "联系电话", name: "phone", newline: true, type: "text" },
                ], buttons: [{ text: "保存", width: 160, click: addTestCenterInfo }]
            });
});

 var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
 function colseDialog(){
	 dialog.close();//关闭dialog 
 }
 
function addTestCenterInfo(){
	var data = form.getData();
	if(data.name==''){
		$.ligerDialog.error('请输入检验单位名称！');
		return;
	}
	if(data.address==''){
		$.ligerDialog.error('请输入地址！');
		return;
	}

	var cgridData = window.parent.grid.getData();
	for(var i=0;i<cgridData.length;i++){
		if(data.name==cgridData[i].name){
			$.ligerDialog.error('检验单位已经存在，请重新填写！');
			return;
		}
	}
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addTestCenterInfo',
		dataType: 'json',
		data: form.getData(),
		type: 'post', 
		success:function(datas)  
		{

			if (datas.success != undefined) {
				window.parent.grid.loadData(datas.resultjson);
				parent.$.ligerDialog.success(datas.success);
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
}
</script>
</head>
<body style="padding:10px">
	<div id="form"></div>
</body>
</html>