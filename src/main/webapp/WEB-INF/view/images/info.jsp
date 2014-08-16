<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!-- Modal -->
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">${title }</h4>
      </div>
      <div class="modal-body">
      	<dl class="dl-horizontal">
		  <dt>体系结构</dt><dd>${info.Architecture }</dd>
		  <dt>作者</dt><dd>${info.Author }</dd>
		  <c:forEach var="item" items="${info.Config}"> 
		  		<dt>${item.key}</dt><dd>${item.value}</dd>
		  </c:forEach>
		</dl>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
