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
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerLayout.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerToolBar.js"
	type="text/javascript">
</script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>	
<script type="text/javascript">
	var controlJson = null;
	var customerJson = null;
	var lSampleTypeJson = null;
	var grid = null;
	var grid2 = null;
	var grid3 =  null;
	$(function() {
		$("#layout1").ligerLayout({
			leftWidth : 400,
			rightWidth : 300,
		});
		grid = $("#customerData").ligerGrid({
			columns : [ {
				display : 'id',
				name : 'id',
				width : 150,
				hide:true,
			},{
				display : 'customerKey',
				name : 'customerKey',
				width : 150,
				hide:true,
			},{
				display : '客户编号',
				name : 'customerid',
				width : 100,
			}, {
				display : '客户名称',
				name : 'customername',
				width : 230,
			}, {
				display : '客户地址',
				name : 'address',
				width : 260
			}, ],
			data : $.extend(true,{},customerJson),
			where : customer_getWhere(),
			pageSize : 30,
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				//alert("选中的是："+data.customername);	
				//$.ligerDialog.success("选中的是："+data.customername);
				ajaxDsfSampleTypeControl(data);
			},
		});

		grid2 = $("#DsfSampleTypeControl").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerId',
				width : 150,
				hide : true,
			}, {
				display : 'id',
				name : 'id',
				width : 150,
				hide : true,
			}, {
				display : '客户样本类型编号',
				name : 'dsfSampleTypeId',
				width : 150,
			}, {
				display : '客户样本类型名称',
				name : 'dsfSampleTypeName',
				width : 150,
			}, {
				display : '本地样本类型编号',
				name : 'lSampleTypeId',
				width : 150
			}, {
				display : '本地样本类型名称',
				name : 'lSampleTypeName',
				width : 150
			},],
			data : $.extend(true,{},controlJson),
			where : control_getWhere(),
			pageSize : 30,
			width : '100%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				ajaxGetLSampleType(data);
			},
		});
		grid3 = $("#lSampleType").ligerGrid({
			columns : [ {
				display : '本地样本类型编号',
				name : 'lSampleTypeId',
				width : 150,
			},{
				display : '本地样本类型名称',
				name : 'lSampleTypeName',
				width : 150,
			}],
			data : lSampleTypeJson,
			pageSize : 30,
			width : '100%',
			height : '99%',
		});	

	});
	function customerSearch(){
		grid.options.data = $.extend(true, {}, customerJson);
	    grid.loadData(customer_getWhere());
	}
	function customer_getWhere()
    {
        if (!grid) return null;
        var clause = function (rowdata, rowindex)
        {
            var key = $("#customerText").val();
            return rowdata.customername.indexOf(key) > -1;
        };
        return clause; 
    }
	function controlSearch(){
		var row = grid.getSelectedRow();
	    if (!row) { 
	    	$.ligerDialog.warn('客户信息行未选中'); return; 
	    }
		grid2.options.data = $.extend(true, {}, controlJson);
	    grid2.loadData(control_getWhere());
	}
	function control_getWhere()
    {
        if (!grid2) return null;
        var clause = function (rowdata, rowindex)
        {
            var key = $("#ciText").val();
            return rowdata.dsfSampleTypeName.indexOf(key) > -1;
        };
        return clause; 
    }
	function autoSampleTypeControl(){
		var row = grid.getSelectedRow();
	    if (!row) { 
	    	$.ligerDialog.warn('客户信息行未选中'); return; 
	    }
	    $.ajax({  	
			url: '/jsp/sysconf/sysConf.do?method=autoSampleTypeControl',
			dataType: 'json',
			data: "customerid=" + row.customerid,
			type: 'post',  
			success:function(datas)  
			{   
				//$.ligerDialog.success(JSON.stringify(datas.result_json));
				if (datas.success != undefined) {
					grid2.loadData(datas.result_json);
					$.ligerDialog.success(datas.success);
				}else if (datas.error != undefined) {
					$.ligerDialog.error(datas.error);
				}
			},
			error:function(){
				$.ligerDialog.error('未知错误');
			}
		});	 
	}
	//ajax显示同步表
	function ajaxDsfSampleTypeControl(data){
	$.ajax({  	
		 url: '/jsp/sysconf/sysConf.do?method=getSampleTypeControl',
		 dataType: 'json',
		 data: "customerid=" + data.customerid,
		 type: 'post',  
		 success:function(datas)  
		 {     
		 	//$.ligerDialog.success(JSON.stringify(datas.result_json));
			grid2.loadData(datas.result_json);
			controlJson = datas.result_json;
		 },
		 error:function(){
			$.ligerDialog.error('未知错误');
		 }
	});	
	}
	function ajaxGetLSampleType(data){
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=getLSampleType',
		dataType: 'json',
		data: "customerid="+data.customerid,
		type: 'post', 
		success:function(datas)  
		{
			grid3.loadData(datas.result_json);
			lSampleTypeJson = datas.result_json;
        }
	});
	}
	function sampleTypeAdd(){
		var row = grid2.getSelectedRow();
		if(!row){
			//alert("请选择需要添加的行");
			$.ligerDialog.warn('请选择需要手动添加的行');
			return;
		}
		var lSampleTypeId = $('#sampleTypeSelect option:selected').val();
		var lSampleTypeName = $('#sampleTypeSelect option:selected').text();
		if(lSampleTypeName==row.lSampleTypeName){
			$.ligerDialog.error('重复对照相同的样本类型！');
			return;
		}
		$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addDsfSampleTypeControl',
		dataType: 'json',
		data:'id='+row.id+'&customerId='+row.customerId+'&dsfSampleTypeId='+row.dsfSampleTypeId+'&dsfSampleTypeName='+row.dsfSampleTypeName+'&lSampleTypeId='+lSampleTypeId+'&lSampleTypeName='+lSampleTypeName,
		type: 'post', 
		success:function(datas)  
		{
			if(datas.success!=undefined){
				//$.ligerDialog.success(JSON.stringify(datas.result_json));
				grid3.loadData(datas.result_json);
				lSampleTypeJson = datas.result_json;
				
				grid2.loadData(datas.result_json2);
				controlJson = datas.result_json2;
			}
			
			//重载数据  --重新加载会丢失当前页面的选中行状态
			//grid2.loadData(datas.result_json2);
			//controlJson = datas.result_json2;
			//更新行 --行更新当前行数据,行状态还会存在
            //if (!row) { alert('请选择行'); return; }
            //grid2.updateCell('profiletest',row.profiletest+indexId+',',row);
        }
	  });   
	}
</script>

<style type="text/css">
body {
	padding: 5px;
	margin: 0;
	padding-bottom: 15px;
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
</style>
</head>
<body style="padding:10px">
	<div id="layout1">
		<div position="left" title="客户信息">
			<input id="customerText" type="text" placeholder="请输入客户名称" style="margin:5px;"/>
			<input id="cButton" type="button" value="搜索" class="l-button" onclick="customerSearch();"/>
			<div id="customerData"></div>
		</div>

		<div position="center" title="客户样本类型对照">			
			<div align="left">
			<table>
			<tr>
				<td style="width:30%" align="right" ><input id="autoButton" type="button" value="自动对照相同名称的样本类型" style="width: 210px"
						class="l-button" onclick="autoSampleTypeControl();"/></td>
				<td align="right"><input id="ciText" type="text" placeholder="请输入客户样本类型名称" style="margin:5px" /></td>
				<td><input id="ciButton" type="button" value="搜索" align="right" class="l-button" onclick="controlSearch();"/></td>
			</tr>
			</table>
			</div>
			<div id="DsfSampleTypeControl"></div>
		</div>
		<div position="right" title="样本类型">
			<select id="sampleTypeSelect" name="lSampleTypeId" style="width: 200px;">
				<option value="">请选样本类型</option>
				<c:forEach items="${l_sample_type_list}" var="lst">
					<option value="${lst.sampleTypeId}">${lst.sampleTypeName}</option>
				</c:forEach>
			</select>
			<input id="iButton" type="button" value="添加" class="l-button" onclick="sampleTypeAdd()"/>
		<div id="lSampleType"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	customerJson = ${dsf_customer_base_info_json};
</script>
</html>
