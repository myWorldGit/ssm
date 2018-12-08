<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>蓝胖子后台管理系统-login</title>
<link href="${pageContext.request.contextPath}/static/css/base.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="login">
<form action="${pageContext.request.contextPath}/other/loginVerify" method="post" id="form">
	<div class="logo"></div>
    <div class="login_form">
    	<div class="user">
        	<input class="text_value" value="" name="username" type="text" id="username">
            <input class="text_value" value="" name="password" type="password" id="password">
        </div>
        <button class="button" id="submit" type="submit">登录</button>
    </div>
    
    <div id="tip"></div>
    <div class="foot">
    Copyright © 2011-2015  All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
    </div>
    </form>
</div>

<script>

</script>
</body>
</html>
