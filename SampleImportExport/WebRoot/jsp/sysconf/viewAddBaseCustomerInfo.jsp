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
$(function () {
             //创建表单结构
             form = $("#form").ligerForm({
                inputWidth: 170, labelWidth: 90, space: 40,
                validate : true,
                fields: [
                { display: "客户名称", name: "customername", newline: true, type: "text", group: "基础信息增加", groupicon: groupicon },
                { display: "客户号", name: "customerid", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "id", name: "id", newline: true, type: "hidden" },
                { display: "客户地址", name: "address", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "客户Key", name: "customerKey", newline: true, type: "text", validate:{required:true,minlength:1} },
                { display: "基础信息状态", name: "basicinfostate", newline: true, type: "text", validate:{required:true,maxlength:1} },
                ]
                , buttons: [{ text: "保存", width: 60, click: addCustomerBaseInfo }]
            });
            var rs = $("#hidden_input").val();
            //alert("新增id值："+rs);
            //设置表单内容
            form.setData({id:rs});
            
});

function addCustomerBaseInfo(){
	var data = form.getData();
	//alert(JSON.stringify(data));
	//alert(JSON.stringify($("#ylmc").val()));
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addBaseCustomerInfo',
		dataType: 'json',
		data: form.getData(),
		type: 'post', 
		success:function(datas)  
		{
			alert(datas.success);
			if (datas.nameExist != undefined) {
				$.ligerDialog.error('客户名字未填或已相同！');
			}
			if (datas.success != undefined) {
				$.ligerDialog.success('修改客户基础信息成功！');
				window.parent.grid.loadData(datas.result_json);
			}
			if (datas.error != undefined) {
				$.ligerDialog.error('修改客户基础信息失败！');
			}
		
			
        },
        error:function(datas){
        	alert(未知错误);
        } 
	});
}
</script>
</head>
<body style="padding:10px">
	<div id="form"></div>
	<input id="hidden_input" type="hidden" name="baseInfo_id" value="${baseInfo_id}"/>
</body>
</html>