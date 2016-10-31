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
var basicinfostateData = [{ basicinfostate: '1', basicinfostateName: '男' }, { basicinfostate: '2', basicinfostateName: '女'}];
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
                //{ display: "基础信息状态", name: "basicinfostate", newline: true, type: "text", validate:{required:true,maxlength:1} ,
                { display: "类别 ", name: "basicinfostate", newline: true, type: "select", comboboxName: "basicinfostateName", options: { valueFieldID: "basicinfostate" },
                	 data: basicinfostateData, 
				},
                ], buttons: [{ text: "保存", width: 160, click: addCustomerBaseInfo }]
            });
            var rs = $("#hidden_input").val();
            form.setData({id:rs});
            
});

 var dialog = frameElement.dialog; //调用页面的dialog对象(ligerui对象)
 function colseDialog(){
	 dialog.close();//关闭dialog 
 }
 
function addCustomerBaseInfo(){
	var data = form.getData();
	if(data.customername==''){
		$.ligerDialog.error('请输入客户名称！');
		return;
	}
	if(data.customerid==''){
		$.ligerDialog.error('请输入客户编号！');
		return;
	}
	var cgridData = window.parent.cgrid.getData();
	for(var i=0;i<cgridData.length;i++){
		if(data.customername==cgridData[i].customername){
			$.ligerDialog.error('客户名字已经存在，请重新填写！');
			return;
		}
		if(data.customerid==cgridData[i].customerid){
			$.ligerDialog.error('客户编号已经存在，请重新填写！');
			return;
		}
	}
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addBaseCustomerInfo',
		dataType: 'json',
		data: form.getData(),
		type: 'post', 
		success:function(datas)  
		{
			if (datas.success != undefined) {
				window.parent.cgrid.loadData(datas.result_json);
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
	<input id="hidden_input" type="hidden" name="baseInfo_id" value="${baseInfo_id}"/>
</body>
</html>