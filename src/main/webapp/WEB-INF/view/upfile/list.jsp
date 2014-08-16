<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:set var="appname" value="user"/>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ZPro 基础架构 -冰迪网络(出品)</title>
<link href="${base}/css/bootstrap.css" rel="stylesheet">
<link href="${base}/css/bootstrap-theme.css" rel="stylesheet">
<link href="${base}/css/dropzone.css" type="text/css" rel="stylesheet" />
</head>

<body>

<div class="container">
	<c:import url="../common/nav.jsp" /> 
	
    <input class="btn btn-primary btn-sm" id="fileupload" type="file" name="files[]" data-url="${base}/upfile/upload"  multiple>
    <br>
	
	<div class="progress">
	    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
		</div>
	</div>

	<table id="uploaded-files" class="table">
		<tr>
			<th>文件名</th>
			<th>文件大小</th>
			<th>文件类型</th>
			<th>下载文件</th>
		</tr>
	</table>
	</div>

<!-- javascript -->

<script src="${base}/js/jquery.js"></script>
<script src="${base}/js/bootstrap.js"></script>
<script src="${base}/js/vendor/jquery.ui.widget.js"></script>
<script src="${base}/js/jquery.iframe-transport.js"></script>
<script src="${base}/js/jquery.fileupload.js"></script>

	<script type="text/javascript">
		$(function(){
			$(".upfilenav").addClass("active");
			
			$('#fileupload').fileupload({
		        dataType: 'json',
		        
		        done: function (e, data) {
		        	$("tr:has(td)").remove();
		            $.each(data.result, function (index, file) {
		            	
		            	
		                $("#uploaded-files").append(
		                		$('<tr/>')
		                		.append($('<td/>').text(file.fileName))
		                		.append($('<td/>').text(file.fileSize))
		                		.append($('<td/>').text(file.fileType))
		                		.append($('<td/>').html("<a href='${base}/images/build/"+index+"'>Click</a>"))
		                		)//end $("#uploaded-files").append()
		            }); 
		        },
		        
		        progressall: function (e, data) {
			        var progress = parseInt(data.loaded / data.total * 100, 10);
			        $('.progress .progress-bar').css(
			            'width',
			            progress + '%'
			        );
		   		},
		   		
				//dropZone: $('#dropzone')
		    });
		});
	</script>

</body> 
</html>
