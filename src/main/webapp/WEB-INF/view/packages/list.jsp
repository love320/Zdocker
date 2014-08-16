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
				<th>项目名</th>
				<th>文件大小</th>
				<th>运行环境</th>
				<th class="hidden-xs">日期</th>
				<th><span class="glyphicon glyphicon-cog"></span>&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="btn btn-default btn-xs docker_add" ><i class="icon-plus"></i>添加</button>
				</th>
			</tr>
		</thead>
			<c:forEach items="${list}" var="xx">
			<tr>
				<td>${xx.name}</td>
				<td>${xx.path }</td>
				<td>
						<c:forEach items="${images}" var="xxx">
						  	 <c:if test="${xx.image == xxx.Id }">${xxx.RepoTags[0]}</c:if>
						</c:forEach>
				</td>
				<td class="hidden-xs">${xx.date_time }</td>
				<td>
					<a href="${base}/runapp/${xx.id}" class="btn btn-primary btn-xs" >进入</a>
					<button class="btn btn-default btn-xs docker_edit"  data="${xx.id}">编辑</button>
					<a href="${base}/packages/delete/${xx.id}" class="btn btn-danger btn-xs" >删除</a>
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
	<script src="${base}/js/vendor/jquery.ui.widget.js"></script>
	<script src="${base}/js/jquery.iframe-transport.js"></script>
	<script src="${base}/js/jquery.fileupload.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".${appname}nav").addClass("active");
			$(".delbtn").balert({url:"${base}/${appname}/delete/",title:'您确定需要删除该信息？'});
			$('.docker_add').click(function(){
				$.get("${base}/packages/input",function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.docker_edit').click(function(){
				$.get("${base}/packages/input/"+$(this).attr('data'),function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			
		});
	</script>
</body>
</html>
