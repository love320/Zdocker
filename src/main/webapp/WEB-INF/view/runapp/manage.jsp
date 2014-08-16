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
		  <div class="panel-heading">项目信息</div>
		  <div class="panel-body">
		  <div class="row">
			<div class="col-xs-6">
					<dl>
					  <dt>项目名称</dt><dd>${info.name }</dd>
					  <dt>项目文件</dt><dd>${info.path }</dd>
					  <dt>项目日期</dt><dd>${info.date_time }</dd>
					  <c:if test="${build_image == null}">
					  <dt>发布项目</dt>
					  <dd><button class="btn btn btn-default btn-xs docker_build">发布项目</button></dd>
					</c:if>
					</dl>
			</div>
 			<div class="col-xs-6">
					<dl>
					  <dt>基于镜像</dt><dd>${image.RepoTags[0] }</dd>
					  <dt>镜像作者</dt><dd>${image.Author }</dd>
					  <dt>镜像端口</dt><dd>${image.Config.ExposedPorts }</dd>
					</dl>
			</div>
		</div>
		  </div>
		</div>
		
		<c:if test="${build_image != null}">
		<div class="panel panel-default">
		  <div class="panel-heading">发布镜像</div>
		  <div class="panel-body">
		    <div class="row">
			<div class="col-xs-6">
					<dl>
					  <dt>体系结构</dt><dd>${build_image.Architecture }</dd>
					  <dt>主机名称</dt><dd>${build_image.Config.Hostname }</dd>
					  <dt>工作目录</dt><dd>${build_image.Config.WorkingDir }</dd>
					  <dt>工作目录</dt><dd>${build_image.Config.ExposedPorts }</dd>
					</dl>
			</div>
 			<div class="col-xs-6">
					<dl>
						<dt>CPU比重</dt><dd>${build_image.Config.CpuShares }</dd>
						<dt>CPU个数</dt><dd>${build_image.Config.Cpuset }</dd>
						<dt>分配内存</dt><dd>${build_image.Config.Memory }</dd>
						<dt>交换区间</dt><dd>${build_image.Config.MemorySwap }</dd>
					</dl>
			</div>
		</div>
		<button class="btn btn-default btn-xs docker_addc"  data="${info.build_name}">容器运行</button>
		</div>
		</div>
		
		<div class="panel panel-default">
		  <div class="panel-heading">运行容器</div>
		  <div class="panel-body">
		    <table class="table table-hover">
			<thead>
				<tr>
					<th>容器名</th>
					<th>镜像</th>
					<th>绑定端口</th>
					<th class="hidden-xs">日期</th>
					<th>状态</th>
					<th><span class="glyphicon glyphicon-cog"></span>&nbsp;操作</th>
				</tr>
			</thead>
				<c:forEach items="${containers}" var="xx">
				<tr>
					<td>${xx.Names}</td>
					<td>${xx.Image }</td>
					<td><c:forEach items="${xx.Ports}" var="xxx">[${xxx.PublicPort }]</c:forEach></td>
					<td class="hidden-xs">${xx.Created }</td>
					<td class="hidden-xs">${xx.Status }</td>
					<td>
					
						<button class="btn btn-default btn-xs docker_start_page"  data="${xx.Id }">启动</button>
						<button data="${base}/containers/${xx.Id}/stop" class="btn btn-danger btn-xs btn-action" >停止</button>
						<button data="${base}/containers/${xx.Id}/restart" class="btn btn-default btn-xs " role="button">重启</button>
						<button data="${base}/containers/${xx.Id}/kill" class="btn btn-danger btn-xs btn-action" role="button">杀死</button>
						<button data="${base}/containers/${xx.Id}/pause" class="btn btn-warning btn-xs btn-action" role="button">暂停</button>
						<button data="${base}/containers/${xx.Id}/unpause" class="btn btn-info btn-xs btn-action" role="button">恢复</button>
						<button data="${base}/containers/${xx.Id}/delete" class="btn btn-danger btn-xs btn-action" role="button">删除</button>
					</td>
				</tr>
				</c:forEach>
			</table>
		  </div>
		</div>
		</c:if>
		
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
			$('.docker_build').click(function(){
				$.get("${base}/runapp/html/build?packid=${info.id}",function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.docker_addc').click(function(){
				$.post("${base}/images/addc", { name: $(this).attr('data'),rurl:'runapp/${info.id}'},function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.docker_start_page').click(function(){
				$.get("${base}/containers/"+$(this).attr('data')+"/startpagerun/${info.id}",function(data){
					$('#myModal').html(data).modal({backdrop:true,show:true});
				});
			});
			$('.btn-action').click(function(){
				var url = $(this).attr('data');
				$.get(url,function(data){
					 location.href = '${base}/runapp/${info.id}';
				});
			});
			
		});
	</script>
</body>
</html>
