<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!-- Modal -->
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">${title }</h4>
      </div>
      <div class="modal-body">
		      	<form id="packagesform" class="form-horizontal" role="form" action="${base}/packages/save" method="post">
		      	  <input type="hidden" class="form-control"  name="id" value="${id}">
				  <div class="form-group">
				    <label for="inputPassword" class="col-sm-2 control-label">项目名称</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="name"  name="name" value="${info.name }">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">运行环境</label>
				    <div class="col-sm-10">
				      <select class="form-control" id="image"  name="image" >
						  <c:forEach items="${images}" var="xx">
						  	<option <c:if test="${info.image == xx.Id }">selected="selected" </c:if> value="${xx.Id }">${xx.RepoTags[0]}</option>
						  </c:forEach>
						</select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">备注</label>
				    <div class="col-sm-10">
				      <textarea class="form-control" rows="3" id="note"  name="note">${info.note }</textarea>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">文件地址</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="path"  name="path" value="${info.path }">
				    </div>
				  </div>
				</form>
			
				<input class="btn" id="fileupload" type="file" name="files[]" data-url="${base}/upfile/upload/sing"  multiple>
			    <br>
				
				<div class="progress">
				    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
					</div>
				</div>
	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="btnsave">保存</button>
      </div>
    </div>
  </div>
  
<script type="text/javascript">
		$(function(){
			$('#addcbtn').click(function(){
				$.post("${base}/containers/create", { name: $('#hostname').val(),image:'${title }'},function(data){
					if(data == 'ok') location.href = '${base}/containers/list';
				});
			});
			
			$('#btnsave').click(function(){
				$("#packagesform").submit();
			});
			
			$('#fileupload').fileupload({
		        dataType: 'json',
		        done: function (e, data) {
		      	  $('#path').val(data.result[0].filePath);
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
