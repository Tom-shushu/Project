<%@page import="bean.UsersInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <meta name="author" content="order by dede58.com"/>
		<title>信息修改</title>
		<link rel="stylesheet" type="text/css" href="./css/login.css">

	</head>
	<body>
	
			<%
			UsersInfo ui = new UsersInfo();
			ui = (UsersInfo)session.getAttribute("USERSINFO");

				 
			%>
		<form  method="post" action="users?type=update">
		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">信息修改</div>
					<div class="right fr"><a href="./index.html" target="_self">RS中国</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					
					<div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input class="shurukuang" type="text" name="username" placeholder="<%=ui.getUserName()%>"/></div>
					<div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="shurukuang" type="password" name="password" placeholder="<%=ui.getUserPassword()%>"/></div>
					
			
					<div class="username">手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang" type="text" name="tel" placeholder="<%=ui.getUserPhone()%>"/></div>
						<div class="username">收&nbsp;货&nbsp;地&nbsp;址:<input class="shurukuang" type="text" name="address" placeholder="<%=ui.getUserAddress()%>"/></div>
						<input type="hidden" name="userid" value="<%=ui.getUserId()%>">
					<div class="username">
						
						<div class="clear"></div>
					</div>
				</div>
				<div class="regist_submit">
					<input class="submit" type="submit" name="submit" value="确认修改" >
				</div>
				
			</div>
		</div>
		</form>
	</body>
	</html>