<%@ page language="java" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	
	
		<meta charset="UTF-8">
        <meta name="author" content="order by dede58.com"/>
		<title>会员登录</title>
		<link rel="stylesheet" type="text/css" href="./css/login.css">
		<script type="text/javascript">		
		  function reloadCode()
		       {
			      var time=new Date().getTime();
		           document.getElementById("imagecode").src="yanzhengma?time="+time;
		       }
		</script>
		
	</head>
	<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="./index.html" target="_blank"><img src="./image/logo_top.png" alt=""></a>
			</div>
		</div>
		<form  method="post" action="user" class="form center">
		<input type="hidden" name="type" value="login"/>
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">您还不是我们的会员？<a href="./registera.jsp" target="_self">立即注册</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">用户名:&nbsp;<input class="shurukuang" type="text" name="username" placeholder="请输入你的用户名"/></div>
					<div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang" type="password" name="password" placeholder="请输入你的密码"/></div>
			  
            <div class="username">
						<div class="left fl">验证码:&nbsp;<input class="yanzhengma" type="text" name="checkCode" value="${YANZHENGMA}"/></div>
						<div class="right fl"><img id="imagecode" src="yanzhengma"></div>
						<div><a href="javascript:reloadCode()">换一张</a></div>
						<div class="clear"></div>
					</div>
					
			<c:choose>
        		<c:when test="${empty USERSINFO }">
        			<span>${ERROR }</span>
        	</c:when>	
        	<c:otherwise>
        			<span>${ERROR }</span>
        		</c:otherwise>
        	</c:choose>
				</div>
				<div class="login_submit">
					<input class="submit" type="submit" name="submit" value="立即登录" >
				</div>
				
			</div>
		</div>
		</form>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
			<div class="copyright">© RS Components Ltd 中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>

		</footer>
	</body>
</html>