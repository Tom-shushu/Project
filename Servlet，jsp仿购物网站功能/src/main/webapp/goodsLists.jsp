<%@page import="bean.GoodsInfo"%>
<%@page import="java.util.List"%>
<%@page import="biz.GoodsBiz"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


	<style type="text/css">
	a:hover {
	color: red;
}
a:visited {
	color: blue;
}
a:active {
	color: green;
}
	
	</style>


	<link rel="stylesheet" href="css/bootstrap.min.css">  
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


<script type="text/javascript">

</script>


<meta charset="utf-8">
<meta name="author" content="order by dede58.com" />
<title>商品列表</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">

<!--
        	作者：1462533241@qq.com
        	时间：2018-08-06
        	描述：分页
        -->
<link rel="stylesheet"href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
	
	function xiangQing(id) {
		location.href="goods?id="+id+"&type=xiangqing";
	}
	
	
	function findAllGoodsByPage(page) {
		location.href = "goods?page="+page+"&type=findallgoodsbypage";
	}
	
	
	</script>



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
							
		        	</li>
        	</c:when>
        	<c:otherwise>
        			<li >
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

	<div class="banner_x center">
		<div class="nav fl">
			<ul>
				<li><a href="./index.jsp">主页></a></li>

				<li><a href="./index.jsp">
				${TYPEname}>
				</a></li>
			<li><a href="">
				${TYPENAME}
			</a></li>
			</ul>
		</div>
	</div>

	
	
	<div class="danpin center">

		<div class="biaoti center"></div>

		<div class="main center">
			<c:forEach var="goods" items="${ALLGOODSBYTYPEID }">
				<div class="mingxing fl" onclick="xiangQing(${goods.goodsId})" >
					<div class="sub_mingxing">
						<a><img src="image/3.jpg" alt="" onclick="xiangQing(${goods.goodsId})"></a>
					</div>
					<div class="pinpai">
						<a onclick="xiangQing(${goods.goodsId})">${goods.goodsName}</a>					
					</div>
				<div class="jiage" onclick="xiangQing(${goods.goodsId})">${goods.goodsPrice}元</div>
				</div>
			</c:forEach>
		</div>

	</div>
<div align="center">
		<ul class="pagination">
			<li><a href="#">&laquo;</a></li>
		
			<c:forEach var="i" begin="1" end="${param.allpage }" step="1">
			<li><a href="goods?page=${i}&type=findallgoodsbypage" id="a">${i}</a></li>
			</c:forEach>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>
	



	<footer class="mt20 center">
		<div class="mt20">© RS Components Ltd
			中国上海市黄浦区延安东路618号远洋商业大厦二期23楼; 邮编：200001</div>
			</footer>
</body>
</html>