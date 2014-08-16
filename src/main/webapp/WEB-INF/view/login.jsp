<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<jsp:include page="./common/mate-title.jsp" />
<link href="${base}/css/bootstrap.css" rel="stylesheet">
<link href="${base}/css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form action="${base}/user/login" method="post"  class="form-signin" role="form">
			 <h2 class="form-signin-heading">登录 ${msg}</h2>
			 <div class="form-group">
			 <input type="text" class="form-control" name="name" value="" placeholder="账号">
			 </div><div class="form-group">
			 <input type="password" class="form-control" name="pwd" value="" placeholder="密码">
			 </div><div class="form-group">
			 <!-- <input type="text" class="form-control" name="code" value="" placeholder="验证码">
			 </div><div class="form-group"> -->
			 <button type="submit" class="btn btn-lg btn-primary btn-block" >登录</button>
			 </div>
		</form>
	</div>

	<!-- javascript -->
	<script src="${base}/js/jquery.js"></script>
	<script src="${base}/js/bootstrap.js"></script>
</body>
</html>
