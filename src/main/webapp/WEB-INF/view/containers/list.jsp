<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:set var="appname" value="user"/>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<jsp:include page="../common/mate-title.jsp" />
<link href="${base}/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container">
	<c:import  url="../common/nav.jsp" />
	<div class="panel panel-default">
	  <!-- Default panel contents -->
	  <div class="panel-heading"></div>
		<table class="table table-hover">
		<thead>
			<tr>
				<th>容器名</th>
				<th>镜像</th>
				<th class="hidden-xs">日期</th>
				<th>状态</th>
				<th><span class="glyphicon glyphicon-cog"></span>&nbsp;操作</th>
			</tr>
		</thead>
			<c:forEach items="${list}" var="xx">
			<tr>
				<td>${xx.Names}</td>
				<td>${xx.Image }</td>
				<td class="hidden-xs">${xx.Created }</td>
				<td class="hidden-xs">${xx.Status }</td>
				<td>
				
					<button class="btn btn-default btn-xs docker_start_page"  data="${xx.Id }">启动</button>
				
					<a href="${base}/containers/${xx.Id}/start" class="btn btn-info btn-xs" role="button">启动</a>
					<a href="${base}/containers/${xx.Id}/stop" class="btn btn-danger btn-xs" role="button">停止</a>
					<a href="${base}/containers/${xx.Id}/restart" class="btn btn-default btn-xs" role="button">重启</a>
					<a href="${base}/containers/${xx.Id}/kill" class="btn btn-danger btn-xs" role="button">杀死</a>
					<a href="${base}/containers/${xx.Id}/pause" class="btn btn-warning btn-xs" role="button">暂停</a>
					<a href="${base}/containers/${xx.Id}/unpause" class="btn btn-info btn-xs" role="button">恢复</a>
					<a href="${base}/containers/${xx.Id}/delete" class="btn btn-danger btn-xs" role="button">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		</div>
		
		
	</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<!-- javascript -->
	<script src="${base}/js/jquery.js"></script>
	<script src="${base}/js/jquery.balert.js"></script>
	<script src="${base}/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".${appname}nav").addClass("active");
			$(".delbtn").balert({url:"${base}/${appname}/delete/",title:'您确定需要删除该信息？'});
			
			$('.docker_start_page').click(function(){
				$.get("${base}/containers/"+$(this).attr('data')+"/startpage",function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
		});
	</script>
</body>
</html>
