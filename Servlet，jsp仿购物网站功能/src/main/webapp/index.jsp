<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
        <meta name="author" content="order by dede58.com"/>
		<title>电子元件商城</title>
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
        			<li>
		        		<li><a href="./login.jsp" target="_blank">登录</a></li>
							<li>|</li>
							<li><a href="./registera.jsp" >注册</a></li>
							<li>|</li>
							
		        	</li>
        	</c:when>
        	<c:otherwise>
        			<li>
        				<span>欢迎,${USERSINFO.userName} </span>
        				
        				<li><a href="selfinfo?type=find">个人中心</a></li>
        				
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

	<!-- start banner_y -->
		<div class="banner_y center">
			<div class="nav">				
				<ul>
			<!--
                    	作者：1462533241@qq.com
                    	时间：2018-08-05
                    	描述：左边一列六个
                    -->
                    <c:forEach var="i" items="${LIST1 }">       
					<li>								
							<!--查询 i.typeId 的拥有的产品系列 -->		
						
						 <a href="">${i.typeName}</a>
						  <div class="pop">
						  <div class="left fl">
						 <c:forEach var="j" items="${i.list }">
							
								<div>
								
									 <div class="xuangou_left fl">
										<a href="goods?id=${j.typeId}&type=findallgoodsbyid&typename=${j.typeName}&Typename=${i.typeName}">
											<span class="fl">${j.typeName}</span>
										</a>
									</div>
									<div class="clear"></div>
								</div>
							
							<div class="clear"></div>
							 </c:forEach>
							 </div>	
						</div>	
						
					</li>
					
					</c:forEach>
					
					   <c:forEach var="i" items="${LIST2 }">       
					<li>								
							<!--查询 i.typeId 的拥有的产品系列 -->		
						
						 <a href="">${i.typeName}</a>
						  <div class="pop">
						  <div class="ctn fl">
						 <c:forEach var="j" items="${i.list }">
							
								<div>
								
									 <div class="xuangou_left fl">
										<a href="goods?id=${j.typeId}&type=findallgoodsbyid&typename=${j.typeName}&Typename=${i.typeName}">
											<span class="fl">${j.typeName}</span>
										</a>
									</div>
									<div class="clear"></div>
								</div>
							
							<div class="clear"></div>
							 </c:forEach>
							 </div>	
						</div>	
						
					</li>
					
					</c:forEach>
  
								
				</ul>
			</div>
		
		</div>	
		
	<!-- start danpin -->
		<div class="danpin center">
			
			<div class="biaoti center">新产品</div>
			<div class="main center">
		
			<c:forEach var="goods" items="${SYSP }">
			
				<div class="mingxing fl"  >
					<div class="sub_mingxing">
						<a><img src="image/2.jpg" alt="" ></a>
					</div>
					<div class="pinpai">
						<a onclick="xiangQing(${goods.goodsId})">${goods.goodsName}</a>					
					</div>
				<div class="jiage" onclick="xiangQing(${goods.goodsId})">${goods.goodsPrice}元</div>
				</div>
			</c:forEach>
			<a href="goods?id=14&type=findallgoodsbyid&typename=EMI和RFI屏蔽材料&Typename=PCB原型"><h3 style="color: blue; margin-right:100px">更多新产品请点击>>></h3></a>
				<div class="clear"></div>
			</div>
		</div>
		<footer class="mt20 center">			
			<div class="mt20">© RS Components Ltd 中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>
		</footer>
	</body>
</html>