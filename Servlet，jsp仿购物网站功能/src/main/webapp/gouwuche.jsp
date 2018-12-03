<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta name="author" content="order by dede58.com" />
<title>我的购物车</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
        		<c:when test="${empty USERSINFO }">
        		
        			<li >
        					<li><a href="./login.jsp" target="_blank">登录</a></li>
							<li>|</li>
							<li><a href="./registera.jsp" >注册</a></li>
							<li>|</li>
							<li><a href="index.jsp">首页</a></li>
							
		        	</li>
        	</c:when>
        	<c:otherwise>
        			<li >
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
		<a href="./index.html" target="_blank">
			<div class="logo fl"></div>
		</a>
		<div class="wdgwc fl ml40">我的购物车</div>
		<div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算!</div>
		<div class="clear"></div>
	</div>
	
	
	<div class="xiantiao"></div>
	<div class="gwcxqbj">
		<div class="gwcxd center">
			<div class="top2 center">
				<div class="sub_top fl">商品图片</div>
				<div class="sub_top fl">商品名称</div>
				<div class="sub_top fl">单价</div>
				<div class="sub_top fl">数量</div>
				<div class="sub_top fl">小计</div>
				<div class="sub_top fr">操作</div>
				<div class="clear"></div>
			</div>

			<c:choose>
				<c:when test="${empty SHOPPINGLIST}">
					<!-- 这个人购物车没有东西 -->
				</c:when>
				<c:otherwise>
				
					<c:forEach var="i" items="${SHOPPINGLIST}">
						<div class="content2 center">
							<div class="sub_content fl "></div>
							<div class="sub_content fl ">
								<img style="width: 120%; height: 60% " src="./image/a.jpg">
							</div>
							<div class="sub_content fl ft20">${i.gs.goodsName}</div>
							<div class="sub_content fl ">${i.gs.goodsPrice}</div>
							<div class="sub_content fl">${i.goodsNum}</div>
							<div class="sub_content fl"></div>
							<div class="sub_content fl">
								<a
									href="gouwuche?type=delete&goodsid=${i.gs.goodsId}&userid=${i.userId}&shopnum=${i.goodsNum}">×</a>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>


			<div align="center">
				<ul class="pagination">
					<li><a href="#">&laquo;</a></li>
					<c:forEach var="i" begin="1" end="${param.allpage }" step="1">
						<li><a href="gouwuche?page=${i}&type=findgoodsbypage" id="a">${i}</a></li>
					</c:forEach>
					<li><a href="#">&raquo;</a>
				</ul>
			</div>
		</div>
		
		<div class="jiesuandan mt20 center">
			<div>
				<ul>
					<a href="index.jsp">
						<h5>
							<input class="jsan" type="submit" name="jiesuan" value="继续购物"
								style="border-color: white-space; background-color: #ff6700; width: 120px; height: 40px;" />
						</h5>
					</a>
					<div class="clear"></div>
				</ul>
			</div>
			<div class="jiesuan fr">
				<div class="jiesuanjiage fl">
					合计（不含运费）：<a>${param.sum}</a>
				</div>
				<div class="jsanniu fr">
					
					<a href="history?type=save"><input class="jsan" type="submit" name="jiesuan" value="去结算" /></a>	
					
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>


	<!-- footer -->
	<footer class="mt20 center">
		<div class="mt20">© RS Components Ltd
			中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>
	</footer>
</body>
</html>
