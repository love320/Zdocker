<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="base" value="${pageContext.request.contextPath}"/>

<!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Docker云平台</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	  <li class="upfile"><a href="${base}/packages/list">项目管理</a></li>
              <li class="statusnav"><a href="${base}/images/list">镜像管理</a></li>
              <li class="gatewaynav"><a href="${base}/containers/list">容器管理</a></li>
              <!-- <li class="upfile"><a href="${base}/upfile/list">上传文件</a></li>
		      <li><a href="${base}/user/logout">退出</a></li> -->
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <!-- <li class="active"><a href="#">关于我们</a></li> -->
          </ul>
        </div><!--/.nav-collapse -->
      </div>


