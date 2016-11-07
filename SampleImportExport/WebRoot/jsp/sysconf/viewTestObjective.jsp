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
<script src="/resources/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<link rel="stylesheet" href="/resources/ligerUI/js/jquery-ui.min.css">
<script src="/resources/ligerUI/js/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript">
var testItemJson = null;
var customerJson = null;
var testObjectiveJson = null;
var autocompleteJson = null;
var manager = null;
var mangerTestObjective = null;
var manager3 = null;
var grid = null;
var grid2 = null;
var grid3 = null;
var grid4 = null;
var availableTags = null;

(function( $ ) {
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
      },
 
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr( "title", "" )
          //.addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .addClass( "custom-combobox-input ui-widget " )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" )
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });
 
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },
 
          autocompletechange: "_removeIfInvalid"
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )
          .attr( "title", "显示所有检验项目" )
          .tooltip()
          .appendTo( this.wrapper )
          
          .removeClass( "ui-corner-all" )
          //.addClass( "custom-combobox-toggle ui-corner-right" )
          .addClass( "custom-combobox-toggle" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // 如果已经可见则关闭
            if ( wasOpen ) {
              return;
            }
 
            // 传递空字符串作为搜索的值，显示所有的结果
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
          if ( this.value && ( !request.term || matcher.test(text) ) )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
 
      _removeIfInvalid: function( event, ui ) {
 
        // 选择一项，不执行其他动作
        if ( ui.item ) {
          return;
        }
 
        // 搜索一个匹配（不区分大小写）
        var value = this.input.val(),
          valueLowerCase = value.toLowerCase(),
          valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });
 
        // 找到一个匹配，不执行其他动作
        if ( valid ) {
          return;
        }
 
        // 移除无效的值
        this.input
          .val( "" )
          .attr( "title", value + " didn't match any item" )
          .tooltip( "open" );
        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );
	$(function() {

		$("#layout1").ligerLayout({
			leftWidth : 400,
			rightWidth : 300,
		});
		$("#toptoolbar").ligerToolBar({ items: [
                { text: '新增检验目的', click: addTestObjective, icon:'add'},
                { line:true },
                { text: '修改检验目的', click: modifyTestObjective },
                 { line:true },
                { text: '删除检验目的', click: deleteTestObjective }
                
            ]
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
				ajaxTestObjective(data);
			},
		});
		
		grid2 = $("#testObjective").ligerGrid({
			columns : [{
				display : 'id',
				name : 'id',
				width : 150,
				hide:true,
			},{
				display : '检验目的编号',
				name : 'ylxh',
				width : 150,
			}, {
				display : '检验目的名称',
				name : 'ylmc',
				width : 150,
			},{
				display : 'customerid',
				name : 'customerid',
				width : 150,
				hide:true,
			},{
				display : '检验项目编号',
				name : 'profiletest',
				width : 150,
			},{
				display : '专业组',
				name : 'professionalgroup',
				width : 150,
			},{
				display : '检验段',
				name : 'inspectionsection',
				width : 150,
			},],
			data : $.extend(true,{},testObjectiveJson),
			where : testObjective_getWhere(),
			pageSize : 30,
			width : '100%',
			height : '99%',
			checkbox : false,
			onSelectRow : function(data, rowindex, rowobj) {
				ajaxTestItems(data);
			},
		});
		grid3 = $("#testItems").ligerGrid({
			columns : [ {
				display : '检验项目编号',
				name : 'indexId',
				width : 150,
			},{
				display : '检验项目名称',
				name : 'name',
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
 	function testObjectiveSearch(){
	 	var row = grid.getSelectedRow();
	    if (!row) { 
	    	alert('客户信息行未选中'); return; 
	    }
		grid2.options.data = $.extend(true, {}, testObjectiveJson);
        grid2.loadData(testObjective_getWhere());
	}
	function testObjective_getWhere()
    {
        if (!grid2) return null;
        var clause = function (rowdata, rowindex)
        {
            var key = $("#testText").val();
            return rowdata.ylxh.indexOf(key) > -1;
        };
        return clause; 
    }
	
//检验目的ajax方法
function ajaxTestObjective(data){
	if(data.customerid == "AAAAA"){
		alert("查询本地检验目的表");
	}else{
	$.ajax({  
		 url: '/jsp/sysconf/sysConf.do?method=getInspectionInfo',
		 dataType: 'json',
		 data: "customerid=" + data.customerid,
		 type: 'post',  
		 success:function(datas)  
		 {     	 	
			grid2.loadData(datas.result_json);
			testObjectiveJson = datas.result_json;
		 },
		 error:function(){
			alert(4);
		 }
		});	
	}
}
//检验项目ajax方法
function ajaxTestItems(data){
	$.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=getInspectionItem',
		dataType: 'json',
		//data: "profiletest="+data.profiletest+"&profiletest2="+data.profiletest2+"&profiletest3="+data.profiletest3,
		data: "profiletest="+data.profiletest+"&customerid="+data.customerid,
		type: 'post', 
		success:function(datas)  
		{
			grid3.loadData(datas.result_json);
			testItemJson = datas.result_json;
        }
	});
}
	
function addTestObjective(){
	var row = grid.getSelectedRow();
    if (!row) { 
    	$.ligerDialog.warn('客户信息行未选中');
    	return; 
    }
    else{
    	$.ligerDialog.open({ height: 400, Width: 350, url: '/jsp/sysconf/sysConf.do?method=viewAddTestObjective&customerid='+row.customerid,title:"增加界面" });
    }
}


function modifyTestObjective(){
	//1$.ligerWindow.show({ target: $("#updateTestObjective").clone(), width: 300, height: 400, title:"修改界面" });
	var row = grid2.getSelectedRow();
    if (!row) { 
    	$.ligerDialog.warn('请选择需要修改的检验目的行');
    	return; 
    }
    else{
    	//$.ligerDialog.success(row.ylmc+':'+row.professionalgroup+':'+row.inspectionsection);
    	//访问控制器之前打印可以显示中文，到了控制器就编乱码了
    	//$.ligerDialog.open({ height: 400, Width: 350,url: '/jsp/sysconf/sysConf.do?method=viewUpdateTestObjective&id='+row.id+'&ylxh='+row.ylxh+'&ylmc='+row.ylmc+'&customerid='+row.customerid+'&profiletest='+row.profiletest+'&professionalgroup='+row.professionalgroup+'&inspectionsection='+row.inspectionsection,title:"修改界面" });
    	$.ligerDialog.open({ height: 400, Width: 350,url: '/jsp/sysconf/sysConf.do?method=viewUpdateTestObjective&id='+row.id,title:"修改界面" });
    
    }
}
function deleteTestObjective(){
	var row = grid2.getSelectedRow();
    if (!row) { 
      $.ligerDialog.warn('请选择需要删除的检验目的行');
      return; 
    }
    else{
      	if(confirm('确认删除'+row.ylmc+'？'))
		{
			$.ajax({  
	      	url: '/jsp/sysconf/sysConf.do?method=deleteTestObjective',
			dataType: 'json',
			data:'id='+row.id+'&customerid='+row.customerid,
			type: 'post', 
			success:function(datas)  
			{
				grid2.loadData(datas.result_json);
				testObjectiveJson = datas.result_json;
				$.ligerDialog.success(datas.success);
	        },
	        error:function(){
	        	$.ligerDialog.success('删除失败!');
	        }
	      	});
		} 
      	return;
      }
}
function itemsAdd(){
	  var indexId = $('#itemsSelect option:selected').val();
      var row = grid2.getSelectedRow();
      if (!row) { $.ligerDialog.warn('检验目的行未选中'); return; }
      //alert(row.profiletest.indexOf(indexId)+"测试用");
      //可以减少后台更新
      if(-1 != row.profiletest.indexOf(indexId)){
      	$.ligerDialog.error("该检验项目已经存在！");
      	return;
      }
 	  $.ajax({  
		url: '/jsp/sysconf/sysConf.do?method=addTestItems',
		dataType: 'json',
		data:'customerid='+row.customerid+'&indexId='+indexId+'&ylxh='+row.ylxh+'&profiletest='+row.profiletest,
		type: 'post', 
		success:function(datas)  
		{
			grid3.loadData(datas.result_json);
			testItemJson = datas.result_json;
			//重载数据  --重新加载会丢失当前页面的选中行状态
			//grid2.loadData(datas.result_json2);
			//testObjectiveJson = datas.result_json2;
			//更新行 --行更新当前行数据,行状态还会存在
            if (!row) { $.ligerDialog.warn('请选择行'); return; }
            grid2.updateCell('profiletest',row.profiletest+indexId+',',row);
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

 .custom-combobox {
    position: relative;
    display: inline-block;
  }
  .custom-combobox-toggle {
    position: absolute;
    top: 0;
    bottom: 0;
    margin-left: -1px;
    padding: 0;
  }
  .custom-combobox-input {
    margin: 0;
    padding: 0.3em;
  }

</style>
</head>
<body style="padding:10px">
	<div id="addTestObjective" style="width: 200px; margin: 3px; display: none;">
		<form id="form">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">id:</td>
                <td align="left" class="l-table-edit-td"><input name="id" type="text" id="form_id" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">客户编号:</td>
                <td align="left" class="l-table-edit-td"><input name="customerid" type="text" id="form_customerid" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">医疗序号:</td>
                <td align="left" class="l-table-edit-td"><input name="ylxh" type="text" id="form_ylxh" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>   
            <tr>
                <td align="right" class="l-table-edit-td">医疗名称:</td>
                <td align="left" class="l-table-edit-td"><input name="ylmc" type="text" id="form_ylmc" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
				<td align="left"></td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">检验项目编号:</td>
                <td align="left" class="l-table-edit-td"><input name="profiletest" type="text" id="form_profiletest" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr> 
             <tr>
                <td align="right" class="l-table-edit-td">专业组:</td>
                <td align="left" class="l-table-edit-td"><input name="professionalgroup" type="text" id="form_professionalgroup" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">检验段:</td>
                <td align="left" class="l-table-edit-td"><input name="inspectionsection" type="text" id="form_inspectionsection" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">
                	<input type="button" value="确认增加" onclick="addTestObjectiveButton()"/>
                </td>
                <td align="left" class="l-table-edit-td">
                    <input type="button" value="重置"/>
                </td>
                <td align="left"></td>
            </tr>
        </table>
		</form> 
    </div>
    <div id="updateTestObjective" style="width: 200px; margin: 3px; display: none;">
		<form id="form2">
		<table cellpadding="0" cellspacing="0" class="l-table-edit" >
            <tr>
                <td align="right" class="l-table-edit-td">id:</td>
                <td align="left" class="l-table-edit-td"><input name="id" type="text" id="id" value="1" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">医疗序号:</td>
                <td align="left" class="l-table-edit-td"><input name="ylxh" type="text" id="ylxh" value="2" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>   
            <tr>
                <td align="right" class="l-table-edit-td">医疗名称:</td>
                <td align="left" class="l-table-edit-td"><input name="ylmc" type="text" id="ylmc" value="3" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
				<td align="left"></td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">检验项目编号:</td>
                <td align="left" class="l-table-edit-td"><input name="profiletest" type="text" id="profiletest" value="4" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr> 
             <tr>
                <td align="right" class="l-table-edit-td">专业组:</td>
                <td align="left" class="l-table-edit-td"><input name="professionalgroup" type="text" id="professionalgroup" value="5" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr> 
             <tr>
                <td align="right" class="l-table-edit-td">检验段:</td>
                <td align="left" class="l-table-edit-td"><input name="inspectionsection" type="text" id="inspectionsection" value="6" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr> 
            <tr>
                <td align="right" class="l-table-edit-td">
                	<input type="button" value="确认修改"/>
                </td>
                <td align="left" class="l-table-edit-td">
                    <input type="button" value="重置"/>
                </td>
                <td align="left"></td>
            </tr>
        </table>
		</form> 
    </div>
	<div id="layout1">
		<div position="left" title="客户信息">
			<input id="customerText" type="text" placeholder="请输入客户名称" style="margin:5px;"/>
			<input id="cButton" type="button" value="搜索" class="l-button" onclick="customerSearch();"/>
			<div id="customerData"></div>
		</div>
		
		<div position="center" title="检验目的">
			<input id="testText" type="text" placeholder="请输入客户编号" style="margin:5px"/>
			<input id="tButton" type="button" value="搜索" class="l-button" onclick="testObjectiveSearch()";/>
			<div id="toptoolbar"></div>
			<div id="testObjective"></div>
		</div>
		
		<div position="right" title="检验项目">
			<select id="itemsSelect" name="indexId" style="width: 400px;">
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
	autocompleteJson = ${items_json};
</script>
</html>
