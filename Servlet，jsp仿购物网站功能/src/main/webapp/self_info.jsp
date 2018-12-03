<%@page import="bean.UsersInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <meta name="author" content="order by dede58.com"/>
		<title>个人中心</title>
		<style type="text/css">
		.sel_btn{
            height: 21px;
            line-height: 21px;
            padding: 0 11px;
            background: #ff6700;
            border: 1px #ff6700 solid;
            border-radius: 3px;
            display: inline-block;
            text-decoration: none;
            font-size: 21px;
            outline: none;
        }
		</style>
		<link rel="stylesheet" type="text/css" href="./css/style.css">
	</head>
	<body>
		<!-- start header -->
		<header>
		<%
			
			UsersInfo ui =new UsersInfo();
			ui = (UsersInfo)session.getAttribute("USERSINFO");
			
			%>
			
			<div class="top center">
				
				<div class="right fr">
					<div class="gouwuche fr"><a href="gouwuche?type=find">购物车</a></div>
					<div class="fr">
						<ul>
						
			<c:choose>
        		<c:when test="${empty USERSINFO }">
        			<li>
		        		<li><a href="./login.jsp" target="_blank">登录</a></li>
							<li>|</li>
							<li><a href="./registera.jsp" >注册</a></li>
							<li>|</li>
							<li><a href="index.jsp">首页</a></li>
							
		        	</li>
        	</c:when>
        	<c:otherwise>
        			<li>
        				<span>欢迎,${USERSINFO.userName} </span>
        				
        				<li><a href="selfinfo?type=find">个人中心</a></li>
        				
        				<li><a href="logout">注销</a></li>
        				<li><a href="index.jsp">首页</a></li>
        			</li>
        		</c:otherwise>
        	</c:choose>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</div>
		</header>
	<!--end header -->

<!-- start banner_x -->
		<div class="banner_x center">
			<a href="./index.html" target="_blank"><div class="logo fl"></div></a>
			<a href=""><div class=""></div></a>
			<div class="nav fl">
				<ul>
					<li><h2 style="color: blue;">我们竭诚以待，找我们就对了</h1></li>
					
				</ul>
			</div>
			<div class="search fr">
				<form action="search.jsp" method="post">
					<div class="text fl">
						<input type="text" class="shuru"  placeholder="根据关键字或者产品编号来搜索"  name="wd" value="<%=request.getParameter("wd")==null?"":request.getParameter("wd") %>"       >
					</div>
					<div class="submit fl">
						<input type="submit" class="sousuo" value="搜索"/>
					</div>
					<div class="clear"></div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
<!-- end banner_x -->
	
<!-- self_info -->
	<div class="grzxbj">
		<div class="selfinfo center">
		<div class="lfnav fl">
			<div class="ddzx">订单中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="dingdanzhongxin.jsp?page=1" >历史记录</a></li>
				</ul>
			</div>
			<div class="ddzx">个人中心</div>
			<div class="subddzx">
				<ul>
					<li><a href="self_info.jsp" style="color:#ff6700;font-weight:bold;">我的个人中心</a></li>
				</ul>
			</div>
		</div>
		<div class="rtcont fr">
			<div class="grzlbt ml40"></div>
			<div class="subgrzl ml40"><span>昵称</span><span><%=ui.getUserName()%></span></div>
			<div class="subgrzl ml40"><span>手机号</span><span><%=ui.getUserPhone() %></span></div>
			<div class="subgrzl ml40"><span>密码</span><span><%=ui.getUserPassword() %></span></div>
			<div class="subgrzl ml40"><span>收货地址</span><span><%=ui.getUserAddress() %></span></div>
			<br><br><br><br>
				<div align="center">
				<a href="users?type=update" class="sel_btn"  id="sel_btn3" >修改</a>
				</div>			
		</div>
		<div class="clear"></div>		
		</div>
	</div>		
		<footer class="mt20 center">			
			<div class="mt20">© RS Components Ltd 中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>
		</footer>
	</body>
</html>