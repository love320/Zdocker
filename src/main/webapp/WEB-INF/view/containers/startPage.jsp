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
		      	<form class="form-horizontal" role="form">
				  <div class="form-group">
				    <label for="inputPassword" class="col-sm-2 control-label">端口</label>
				      <div class="row">
						  <div class="col-xs-3">
						    <input type="text" class="form-control" id="inport" placeholder="内部端口">
						  </div>
						  <div class="col-xs-3">
						    <input type="text" class="form-control"  id="outport" placeholder="对外端口">
						  </div>
						</div>
				  </div>
				  <div class="form-group">
				    <label class="col-sm-2 control-label">内部端口</label>
				    <div class="col-sm-10">
				      <p class="form-control-static">${info.Config.ExposedPorts }</p>
				    </div>
				  </div>
				</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="addcbtn">启动容器</button>
      </div>
    </div>
  </div>
  
<script type="text/javascript">
		$(function(){
			$('#addcbtn').click(function(){
				$.get("${base}/containers/${id}/start/"+$('#outport').val()+"/"+$('#inport').val(),function(data){
					if(data == 'ok') location.href = '${base}/${rurl}';
				});
			});
		});
	</script>
