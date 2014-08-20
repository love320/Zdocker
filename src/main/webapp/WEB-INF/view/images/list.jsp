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
				<th>镜像名</th>
				<th>虚拟大小</th>
				<th class="hidden-xs">日期</th>
				<th><span class="glyphicon glyphicon-cog"></span>&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/${appname}/add" class="btn btn-default btn-xs"><i class="icon-plus"></i>添加</a></th>
			</tr>
		</thead>
			<c:forEach items="${list}" var="xx">
			<tr>
				<td>${xx.RepoTags}</td>
				<td>${xx.VirtualSize }</td>
				<td class="hidden-xs">${xx.Created }</td>
				<td>
				
				<button class="btn btn-info btn-xs docker_info"  data="${xx.RepoTags[0]}">INFO</button>
				<button class="btn btn-default btn-xs docker_addc"  data="${xx.RepoTags[0]}">容器运行</button>
				<button class="btn btn-default btn-xs docker_delete"  data="${xx.RepoTags[0]}">删除</button>
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
			$('.docker_info').click(function(){
				$.post("${base}/images/name/json", { name: $(this).attr('data')},function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.docker_addc').click(function(){
				$.post("${base}/images/addc", { name: $(this).attr('data'),rurl:'containers/list'},function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.docker_delete').click(function(){
				var url = "${base}/images/"+$(this).attr('data')+"/delete";
				$.get(url,function(data){
					if(data == 'ok') location.href = '${base}/images/list';
				});
			});
		});
	</script>
</body>
</html>
