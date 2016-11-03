<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>标本数据收发系统</title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="mylink" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/resources/jquery.cookie.js"></script>
<script type="text/javascript">
	var tab = null;
	var tabItems = [];
	$(function() {
		$("#mainMenu").ligerMenuBar({
			items : [ {
				text : '基础信息标本导入',
				menu : baseDataMenu
			}, {
				text : '标本接收/导入',
				menu : menu1
			}, {
				text : '标本结果导出/打印',
				menu : menu2
			}, {
				text : '查询统计/条码打印',
				menu : menu3
			}, {
				text : '基础信息设置',
				menu : baseInfo
			} ]
		});
		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			height : '100%',
			heightDiff : -34,
			space : 4,
			onLeftToggle : function() {
				tab && tab.trigger('sysWidthChange');
			},
			onRightToggle : function() {
				tab && tab.trigger('sysWidthChange');
			}
		});

		var height = $(".l-layout-center").height();

		//Tab
		tab = $("#framecenter").ligerTab({
			height : height,
			showSwitchInTab : true,
			showSwitch : true,
			onAfterAddTabItem : function(tabdata) {
				tabItems.push(tabdata);
				saveTabStatus();
			},
			onAfterRemoveTabItem : function(tabid) {
				for ( var i = 0; i < tabItems.length; i++) {
					var o = tabItems[i];
					if (o.tabid == tabid) {
						tabItems.splice(i, 1);
						saveTabStatus();
						break;
					}
				}
			},
			onReload : function(tabdata) {
				var tabid = tabdata.tabid;
				addFrameSkinLink(tabid);
			}
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});

		function openNew(url) {
			var jform = $('#opennew_form');
			if (jform.length == 0) {
				jform = $('<form method="post" />').attr('id', 'opennew_form')
						.hide().appendTo('body');
			} else {
				jform.empty();
			}
			jform.attr('action', url);
			jform.attr('target', '_blank');
			jform.trigger('submit');
		}
		;
		tab = liger.get("framecenter");
		$("#pageloading").hide();
		pages_init();
	});

	//按钮   
	var baseDataMenu = {
		width : 130,
		items : [ {
			text : '基本信息导入',
			click : impBaseData
		}, ]
	};

	var menu1 = {
		width : 200,
		items : [ {
			text : '手工录入标本信息',
			click : manualEntry
		}, {
			line : true
		}, {
			text : '前处理标本信息',
			click : pretreatment
		}, {
			line : true
		}, {
			text : '通过Excel导入接收的标本',
			click : impExcel
		}, {
			text : '通过Xml导入接收的标本',
			click : impXml
		}, ]
	};

	var menu2 = {
		width : 200,
		items : [ {
			text : '查询/导出标本检验结果',
			click : expResultData
		},{
			text : '查询标本信息',
			click : querySampleData
		} ]
	};

	var menu3 = {
		width : 120,
		items : [ {
			text : '查询统计',
			click : cxtj
		}, {
			line : true
		}, {
			text : '条码打印',
			click : tmdy
		}, ]
	};
	var baseInfo = {
		width : 180,
		items : [ {
			text : '系统基础信息设置',
			click : xtjcxxsz
		}, {
			text : '客户相关信息设置',
			children : [ {
				text : '客户信息',
				click : cinfo
			}, ],
		}, {
			text : '检验信息设置',
			children : [ {
				text : '检验信息设置',
				click : inspectionInfo
			}, {
				line : true
			}, {
				text : '检验目的对照',
				click : inspectionItemControl
			}, ]
		}, {
			text : '折扣信息设置',
			children : [ {
				text : '基础折扣设置',
				click : baseDiscount
			}, ]
		}, {
			text : '检验单位信息设置',
			click : testCenter
		} , {
			text : '系统账号管理',
			click : labuser
		}]
	}

	function manualEntry() {
		f_addTab("manualEntry", "手工录入标本信息",
				"/jsp/updown/updown.do?method=viewManualEntry");
	}
	function pretreatment() {
		f_addTab("pretreatment", "前处理标本信息",
				"/jsp/updown/updown.do?method=viewPretreatment");
	}
	function baseDiscount() {
		f_addTab("baseDiscount", "基础折扣", "");
	}
	function testCenter() {
		f_addTab("testCenter", "检验单位信息设置",
				"/jsp/sysconf/sysConf.do?method=viewTestCenter");
	}
	function cinfo() {
		f_addTab("cinfo", "客户信息",
				"/jsp/sysconf/sysConf.do?method=viewCustomerInfo");
	}
	function inspectionInfo() {
		f_addTab("inspectionInfo", "检验信息设置",
				"/jsp/sysconf/sysConf.do?method=viewTestObjective");
	}
	function labuser() {
		f_addTab("labuser", "系统账号管理",
				"/jsp/sysconf/sysConf.do?method=viewTestObjective");
	}
	function inspectionItemControl() {
		f_addTab("inspectionItemControl", "检验项目对照",
				"/jsp/sysconf/sysConf.do?method=viewTestItem");
	}

	function impBaseData() {
		f_addTab("impBaseData", "基本信息导入",
				"/jsp/updown/updown.do?method=viewImpBaseData");
	}
	function impExcel() {
		f_addTab("impExcel", "通过Excel导入接收的标本",
				"/jsp/updown/updown.do?method=viewUpdatePage&type=excel");
	}
	function impXml() {
		f_addTab("impXml", "通过Xml导入接收的标本",
				"/jsp/updown/updown.do?method=viewUpdatePage&type=xml");
	}
	function expResultData() {
		f_addTab("expResultData", "查询/导出标本检验结果",
				"/jsp/updown/updown.do?method=viewDownLoadPage");
	}
	function querySampleData(){
		f_addTab("querySampleData", "查询样本信息",
				"/jsp/updown/updown.do?method=viewSampleInfoPage");
	}
	function cxtj() {
		f_addTab("cxtj", "查询统计",
				"/jsp/queryStats/queryStats.do?method=viewQueryStats");
	}
	function tmdy() {
		f_addTab("tmdy", "条码打印",
				"/jsp/queryStats/queryStats.do?method=viewPrintCode");
	}
	function xtjcxxsz() {
		f_addTab("xtjcxxsz", "系统基础信息",
				"/jsp/sysconf/sysConf.do?method=viewSystemConfig");
	}

	//按钮   

	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url,
		});
	}
	function pages_init() {
		var tabJson = $.cookie('liger-home-tab');
		if (tabJson) {
			var tabitems = JSON.parse(tabJson);
			for ( var i = 0; tabitems && tabitems[i]; i++) {
				f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
			}
		}
	}
	function saveTabStatus() {
		$.cookie('liger-home-tab', JSON.stringify(tabItems));
	}
	function logout() {
		$.ligerDialog.confirm('确定退出系统吗？', function(yes) {
			if (yes) {
				document.logoutForm.submit();
			}
		});
	}
</script>
<style type="text/css">
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('/resources/images/loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 31px;
	line-height: 31px;
	background: url('/resources/images/top.jpg') repeat-x bottom;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: #E7E7E7;
	padding-left: 35px;
	line-height: 26px;
	background: url('/resources/images/topicon.gif') no-repeat 10px 5px;
}

.l-topmenu-welcome {
	position: absolute;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 2px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: #E7E7E7;
	text-decoration: underline
}

.body-gray2014 #framecenter {
	margin-top: 3px;
}

.viewsourcelink {
	background: #B3D9F7;
	display: block;
	position: absolute;
	right: 10px;
	top: 3px;
	padding: 6px 4px;
	color: #333;
	text-decoration: underline;
}

.viewsourcelink-over {
	background: #81C0F2;
}

.l-topmenu-welcome label {
	color: white;
}

#skinSelect {
	margin-right: 6px;
}
</style>
</head>
<body style="padding:0px;background:#EAEEF5;">
	<form id="logoutForm" name="logoutForm"
		action="/login.do?method=logout" method="post"></form>
	<div id="pageloading"></div>
	<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo">标本数据收发系统</div>
		<div class="l-topmenu-welcome">
			<a href="#" onclick="logout();return false" class="l-link2"
				target="_blank">退出</a>
		</div>
	</div>
	<div id="mainMenu" style="width:99.2%; margin:0 auto; margin-top:1px; "></div>
	<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:1px; ">
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页" style="height:300px">
				<iframe frameborder="0" name="home" id="home" src="/index.jsp"></iframe>
			</div>
		</div>

	</div>
	<div style="height:32px; line-height:32px; text-align:center;">
		Copyright © 2016-2017 杭州同烁信息科技有限公司</div>
	<div style="display:none"></div>
</body>
</html>
