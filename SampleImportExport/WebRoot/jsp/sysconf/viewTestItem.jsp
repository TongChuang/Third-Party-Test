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
<script type="text/javascript">
	var controlJson = null;
	var customerJson = null;
	var testItemJson = null;
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
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide:true,
			},{
				display : 'customerKey',
				name : 'customerKey',
				width : 150,
				hide:true,
			},{
				display : '客户编号',
				name : 'clientnumber',
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
				alert("选中的是："+data.customername);	
				ajaxControltestitems(data);
			},
		});

		grid2 = $("#controlData").ligerGrid({
			columns : [ {
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide : true,
			}, {
				display : 'id',
				name : 'id',
				width : 150,
				hide : true,
			}, {
				display : '客户检验项目ID',
				name : 'customeritems',
				width : 150,
			}, {
				display : '客户检验项目名称',
				name : 'customeritemsname',
				width : 150,
			}, {
				display : '本地检验项目ID',
				name : 'localitems',
				width : 150
			}, {
				display : '本地检验项目名称',
				name : 'localitemsname',
				width : 150
			},],
			data : $.extend(true,{},controlJson),
			where : control_getWhere(),
			pageSize : 30,
			width : '100%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				alert("选中的是：" + data.ylmc);
				alert(111);
				ajaxTestItems(data);
			},
		});
		grid3 = $("#testItems").ligerGrid({
			columns : [ {
				display : '检验项目编号',
				name : 'localitems',
				width : 150,
			},{
				display : '检验项目名称',
				name : 'localitemsname',
				width : 150,
			}],
			data : testItemJson,
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
	    	alert('客户信息行未选中'); return; 
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
            return rowdata.customeritems.indexOf(key) > -1;
        };
        return clause; 
    }
	function autoControlTestItems(){
		var row = grid.getSelectedRow();
	    if (!row) { 
	    	alert('客户信息行未选中'); return; 
	    }
	    $.ajax({  	
			url: '/jsp/sysconf/sysConf.do?method=autoControlTestItems',
			dataType: 'json',
			data: "customerid=" + row.clientnumber,
			type: 'post',  
			success:function(datas)  
			{     
				grid2.loadData(datas.result_json);
				controlJson = datas.result_json;
			},
			error:function(){
			alert(4);
			}
		});	 
	}
	//ajax显示同步表
	function ajaxControltestitems(data){
	alert(1);
	$.ajax({  	
		 url: '/jsp/sysconf/sysConf.do?method=getControltestitems',
		 dataType: 'json',
		 data: "customerid=" + data.clientnumber,
		 type: 'post',  
		 success:function(datas)  
		 {     
			grid2.loadData(datas.result_json);
			controlJson = datas.result_json;
		 },
		 error:function(){
			alert(4);
		 }
	});	
	}
	function ajaxTestItems(data){
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=getLocalInspectionItem',
		dataType: 'json',
		data: "customerid="+data.customerid,
		type: 'post', 
		success:function(datas)  
		{
			grid3.loadData(datas.result_json);
			testItemJson = datas.result_json;
        }
	});
	}
	function itemsAdd(){
		var indexId = $('#itemsSelect option:selected').val();
		var row = grid2.getSelectedRow();
		if(!row){
			alert("请选择需要添加的行");
			return;
		}
		$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addControlItems',
		dataType: 'json',
		data:'id='+row.id+'&customerid='+row.customerid+'&indexId='+indexId,
		type: 'post', 
		success:function(datas)  
		{
			grid3.loadData(datas.result_json);
			testItemJson = datas.result_json;
			//重载数据  --重新加载会丢失当前页面的选中行状态
			grid2.loadData(datas.result_json2);
			controlJson = datas.result_json2;
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
			<input id="customerText" type="text" style="margin:5px;"/>
			<input id="cButton" type="button" value="搜索" class="l-button" onclick="customerSearch();"/>
			<div id="customerData"></div>
		</div>

		<div position="center" title="客户检检验项目对照">			
			<div align="right">
			<table>
			<tr>
				<td><input id="autoButton" type="button" value="自动对照相同名称的检验项目" align="left" style="width: 210px"
						class="l-button" onclick="autoControlTestItems();"/></td>
				<td><input id="ciText" type="text" style="margin:5px" /></td>
				<td><input id="ciButton" type="button" value="搜索" align="right" class="l-button" onclick="controlSearch();"/></td>
			</tr>
			</table>
			</div>
			<div id="controlData"></div>
		</div>
		<div position="right" title="检验项目">
			<select id="itemsSelect" name="indexId" style="width: 200px;">
				<option value="">请选择检验项目</option>
				<c:forEach items="${items_list}" var="tinfo">
					<option value="${tinfo.indexId}">${tinfo.name}</option>
				</c:forEach>
			</select>
			<input id="iButton" type="button" value="添加" class="l-button" onclick="itemsAdd()"/>
			<div id="testItems"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	customerJson = ${customer_json};
</script>
</html>
