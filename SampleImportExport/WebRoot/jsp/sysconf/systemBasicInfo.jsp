<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>血站发血信息</title>
<link href="/resources/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<script src="/resources/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script src="/resources/ligerUI/js/plugins/ligerResizable.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function before() {
		var webserviceUrl = $("#webserviceUrl").val();
		if (webserviceUrl == "") {
			$.ligerDialog.error('请输入血站的接口地址！');
			return;
		} 
		var userId = $("#userId").val();
		if (userId == "") {
			$.ligerDialog.error('请输入接入用户ID！');
			return;
		}
		var accessCode = $("#accessCode").val();
		if (accessCode == "") {
			$.ligerDialog.error('请输入接入验证码！');
			return;
		}
		document.form.submit();
	}

	$(function() {
		var msg = $("#msg").val();
		if (msg != '') {
			if (msg == 'success') {
				$.ligerDialog.success('提交成功！');
			} else {
				$.ligerDialog.error(msg);
			}
		}
	});
</script>
</head>

<body>
	<input type="hidden" id="msg" name="msg" value="${msg}">
		<form class="l-form" ligeruiid="form1" id="form" name="form"
			action="/jsp/sysconf/sysConf.do?method=updateSystemConfig"
			method="post">
			<div class="l-form-container">
				<div class="l-group l-group-hasicon" style="width: 988px;">
					<img src="/resources/ligerUI/skins/icons/communication.gif"><span>基础信息配置</span>
				</div>
			</div>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
				<li style="width:150px;text-align:right;">所属医院：</li>
				<li id="form1|0" style="width:200px;text-align:left;"><div
						class="l-text" style="width: 198px;">
							<select name="hospital" id="hospital" class="textReadonly"
							ltype="select" style="width:198px">
								<c:forEach items="${ddList}" var="ddinfo">
									<option value="${ddinfo.code}"
										<c:if test="${ddinfo.code==hospital}">selected="selected"</c:if>
									>${ddinfo.name}</option>
								</c:forEach>
						</select>
						<div class="l-text-l"></div>
						<div class="l-text-r"></div>
					</div></li>
				<li style="width:190px;"><span class="l-star">*(此项修改后必须重新登录！)</span></li>
				</li>
			</ul>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
				<li style="width:150px;text-align:right;">血液中心：</li>
				<li id="form1|0" style="width:200px;text-align:left;"><div
						class="l-text" style="width: 198px;">
							<select name="bloodbank" id="bloodbank" class="textReadonly"
							ltype="select" style="width:198px">
								<c:forEach items="${ddList}" var="ddinfo">
									<option value="${ddinfo.code}"
										<c:if test="${ddinfo.code==bloodbank}">selected="selected"</c:if>
									>${ddinfo.name}</option>
								</c:forEach>
						</select>
						<div class="l-text-l"></div>
						<div class="l-text-r"></div>
					</div></li>
				<li style="width:40px;"><span class="l-star">*</span></li>
				</li>
			</ul>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
				<li style="width:150px;text-align:right;">血站接口地址：</li>
				<li id="form1|0" style="width:300px;text-align:left;"><div
						class="l-text" style="width: 298px;">
						<input type="text" id="webserviceUrl" name="webserviceUrl" value="${weburl}"
							class="l-text-field" ligeruiid="webserviceUrl"
							style="width: 298px;">
							<div class="l-text-l"></div>
							<div class="l-text-r"></div>
					</div></li>
				<li style="width:40px;"><span class="l-star">*</span></li>
				</li>
			</ul>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
				<li style="width:150px;text-align:right;">接入用户ID：</li>
				<li id="form1|0" style="width:200px;text-align:left;"><div
						class="l-text" style="width: 198px;">
						<input type="text" id="userId" name="userId" class="l-text-field" value="${userid}"
							ligeruiid="userId" style="width: 198px;">
							<div class="l-text-l"></div>
							<div class="l-text-r"></div>
					</div></li>
				<li style="width:40px;"><span class="l-star">*</span></li>
				</li>
			</ul>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
				<li style="width:150px;text-align:right;">接入验证码：</li>
				<li id="form1|0" style="width:200px;text-align:left;"><div
						class="l-text" style="width: 198px;">
						<input type="text" id="accessCode" name="accessCode" value="${accesscode}"
							class="l-text-field" ligeruiid="accessCode" style="width: 198px;">
						<div class="l-text-l"></div>
						<div class="l-text-r"></div>
					</div></li>
				<li style="width:40px;"><span class="l-star">*</span></li>
				</li>
			</ul>
			<ul>
				<br />
			</ul>
			<ul>
				<li class="l-fieldcontainer l-fieldcontainer-first" fieldindex="0">
					<li style="width:150px;text-align:right;"></li>
				<li><div class="l-button" ligeruiid="Button1000"
						onclick="before()" style="width: 120px;">
						<div class="l-button-l"></div>
						<div class="l-button-r"></div>
						<span>保存数据</span>
					</div>
				</li>
				<li style="width:40px;"><span class="l-star"></span></li>
				</li>
			</ul>

			</li>
			</ul>
			<div class="l-clear"></div>
			</div>
			<ul class="l-form-buttons">

			</ul>
		</form>
		<div id="maingrid" style="margin: 0; padding: 0"></div>
</body>
</html>
