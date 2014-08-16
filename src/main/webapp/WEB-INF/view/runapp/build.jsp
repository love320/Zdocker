<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>
<!-- Modal -->
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    	<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title" id="mySmallModalLabel">生成镜像</h4>
        </div>
        <div class="modal-body">
          <div class="input-group">
		      <input type="text" class="form-control"  id="buildName" placeholder="填写镜像名">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="button" id="buildImage" data="${packid[0]}">生成</button>
		      </span>
		 </div><!-- /input-group -->
        </div>
        <div class="modal-footer buildProgress" >
	        <div class="progress">
			  <div class="progress-bar progress-bar-striped"  role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width: 2%">
			  	 <span>处理中...</span>
			  </div>
			</div>
	      </div>
    </div>
  </div>

<script type="text/javascript">
		$(function(){
			$('.buildProgress').hide();
			$('#buildImage').click(function(){
				var name = $('#buildName').val();
				if(name.length > 0){
					$('.buildProgress').show(function(){
						$('.progress .progress-bar').css('width', '75%' );
					});
					var url = "${base}/runapp/build/"+$(this).attr('data')+"?name="+name;
					$.get(url,function(data){
						$('.progress .progress-bar').css('width', '100%' );
						$('.progress .progress-bar span').html('发布完成');
						if(data == 'ok') location.href = '${base}/runapp/${packid[0]}';
					});
				}
			});
		});
	</script>
