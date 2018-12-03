<%@ page language="java" contentType="text/html; charset=utf-8"
	isELIgnored="false" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="order by dede58.com" />
<title>用户注册</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/login.css">
<script type="text/javascript">
var fuser=0;
var fpass=0;
var frepass=0;
var fphone=0;
	function changeImage() {
		
		var time = new Date().getTime();
		document.getElementById("imageId").src = "yanzhengma?time=" + time;
	}
	
	function checkPass(obj,num) {
		switch(num){
		
		case 1://检查用户名
			if(""==obj.value){ //如果文本框中的内容是空的
				document.getElementById("usererror").innerHTML = "用户名不能为空!";
				fuser=0;
			}else{
				document.getElementById("usererror").innerHTML = "";
				document.getElementById("yzmerror").innerHTML = "";
				
				fuser=1;
			}
			break;
		case 2://检查密码
			if(""==obj.value){ //如果文本框中的内容是空的
				document.getElementById("passerror").innerHTML = "密码不能为空!";
				fpass=0;
			}else{
				if(obj.value.length<8||obj.value.length>16){
					document.getElementById("passerror").innerHTML = "密码长度为8-16位";
				}else{
					document.getElementById("passerror").innerHTML = ""; 
					fpass=1;
				}
				
			}
			break;
		case 3://检查重复密码
			if(""==obj.value){ //如果文本框中的内容是空的
				document.getElementById("repasserror").innerHTML = "二次密码不能为空!";
			    frepass=0;
			}else{
				//检查密码与重复密码是否一致
				var onepass = document.getElementById("onepassid").value;
				var twopass = document.getElementById("twopassid").value;
				if(onepass == obj.value){
					document.getElementById("repasserror").innerHTML = "两次密码输入一致！"; 
					frepass=1;
				}else{
					document.getElementById("repasserror").innerHTML = "两次密码输入不一致！";
				}
			}
			break;
		case 4://检查手机号
			if(""==obj.value){ //如果文本框中的内容是空的
				document.getElementById("phoneerror").innerHTML = "手机号不能为空!";
				fphone=0;
			}else{
				document.getElementById("phoneerror").innerHTML = "";
			}
			break;
	
		}	 
	}

</script>
</head>
<body>	
	<c:if test="${empty ERROR}">
		<c:redirect url="regist_submit"></c:redirect>
	</c:if>
	<form method="post" action="regist_submit">

		<div class="regist">
			<div class="regist_center">
				<div class="regist_top">
					<div class="left fl">会员注册</div>
					<div class="right fr">
						<a href="./index.jsp" target="_self">RS中国</a>
					</div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="regist_main center">
					<div class="username">
						用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input id="uid"
							class="shurukuang" type="text" name="username"
							placeholder="请输入你的用户名" onblur="checkPass(this,1)" /> <span
							id="usererror"></span>
					</div>
					<div class="username">
						密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input
							class="shurukuang" type="password" name="password" id="onepassid"
							placeholder="请输入你的密码" onblur="checkPass(this,2)" /> <span
							id="passerror"></span>
					</div>

					<div class="username">
						确认密码:&nbsp;&nbsp;<input class="shurukuang" type="password"
							id="twopassid" name="repassword" id="pass2" placeholder="请确认你的密码"
							onblur="checkPass(this,3)" /><span id="repasserror"></span>
					</div>

					<div class="username">
						手&nbsp;&nbsp;机&nbsp;&nbsp;号:&nbsp;&nbsp;<input class="shurukuang"
							id="phoneid" type="text" name="tel" placeholder="请填写正确的手机号"
							onblur="checkPass(this,4)" /><span id="phoneerror"></span>
					</div>
					<div class="username">
						<div class="left fl">
							验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="yanzhengma"
								id="yzmid" type="text" name="yzm" placeholder="请输入验证码"
								 />
						</div>
						<div class="right fl">
							<img id="imageId" src="yanzhengma" />
						</div>
						<div>
							<a href="javascript:changeImage()"
								style="text-decoration: underline;">换一张</a><span id="yzmerror">${ERROR}</span>
							<div>
								<div class="override"></div>
							</div>
						</div>
					</div>


					<div class="inputbg inputcode">
						<div class="clear"></div>
					</div>
				</div>
				<div class="regist_submit">
					<input class="submit" type="submit" name="submit" value="立即注册">
				</div>

			</div>
		</div>
	</form>
</body>
</html>