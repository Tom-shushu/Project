<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="order by dede58.com" />
<title>详情页面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<!-- start header -->
		<header>
			<div class="top center">
				
				<div class="right fr">
					<div class="gouwuche fr"><a href="gouwuche?type=find">购物车</a></div>
					<div class="fr">
						<ul>
								<c:choose>
        		<c:when test="${empty USERSINFO}">
	        			<li >
	        				<li><a href="index.jsp">首页</a></li>
	        				<li>|</li>
			        		<li><a href="./login.jsp" target="_blank">登录</a></li>
							<li>|</li>
							<li><a href="./registera.jsp" >注册</a></li>
							<li>|</li>
							
		        	</li>
        	</c:when>
        	<c:otherwise>
        			<li >
        			<li><a href="index.jsp">首页</a></li>
	        				<li>|</li>
        				<span>欢迎,${USERSINFO.userName} </span>
        				
        				<li><a href="selfinfo?type=find">个人中心</a></li>
        				<li>|</li>
        				<li><a href="logout">注销</a></li>
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


	<!-- xiangqing -->
		<div class="xiangqing">
			<div class="neirong w">
				<div class="xiaomi6 fl">${GOODSINFO.goodsName}</div>
				<nav class="fr"></nav>
				<div class="clear"></div>
			</div>
		</div>

		<div class="jieshao mt20 w">
			<div class="left fl">
				<img src="./image/2.png">
			</div>
			<div class="right fr">
				<div class="h3 ml20 mt20">${GOODSINFO.goodsName}</div>
				<div class="jianjie mr40 ml20 mt10">${GOODSINFO.goodsIntro}</div>
				<div class="jiage ml20 mt10">
					<h1>${GOODSINFO.goodsPrice}</h1>
				</div>

				<div class="xqxq mt20 ml20">
					<div class="top1 mt10">
						<div class="left1 fl" style="font-size: 1em">${GOODSINFO.goodsIntro}</div>
						<div class="right1 fr"></div>
						<div class="clear"></div>
					</div>

				</div>
				<div class="xiadan ml20 mt20">
					<!-- 加入购物车操作 -->

					<c:choose>
						<c:when test="${empty USERSINFO}">
							<a href="login.jsp"><input
								class="jrgwc" type="button" name="jrgwc" value="请登录" /></a>
						</c:when>
						<c:otherwise>
						<a href="addShopping?goodsId=${GOODSINFO.goodsId}"><input
								class="jrgwc" type="button" name="jrgwc" value="加入购物车" /></a>
						<span>${ERROR1}</span>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
			<div class="clear"></div>
		</div>
	<!-- footer -->


	<footer class="mt20 center">
		<div class="mt20">© RS Components Ltd
			中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>
	</footer>

</body>
</html>