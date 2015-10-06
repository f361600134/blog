<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="include/header.jsp"/>
		<!-- jQuery -->
		<script src="<%=path %>/js/project/jquery-1.10.2.min.js"></script>
		
		<!-- Fotorama -->
		<link href="<%=path %>/css/project/fotorama.css" rel="stylesheet">
		<script src="<%=path %>/js/project/fotorama.js"></script>
  
  
		<!-- **************** - end Header - **************** -->
		<div class="content-wrapper sbr clearfix">
			
			<h1 class="content-title">Project</h1>
			
			<!-- 这里要做轮播图 -->
			<div id="content">
 				<!-- data-width="700" data-ratio="700/467" data-max-width="100%" -->
 				
				<div class="fotorama" data-width="700" data-ratio="700/467" data-max-width="100%">
					<c:forEach var="picture" items="${project.pictures}" varStatus="status">
						 <img src="${picture.picUrl}">
					</c:forEach>
				</div>
				
<!-- 				<h2>${project.title}</h2> -->
				<div class="project-body">
					<div class="title">${project.title}</div>
	<!-- 			<h6>${project.categories}。《斗剑录》收录中华上下五千年历史长河中的百把名剑，并将御剑之术与横版格斗ARPG的畅爽体验完美结合，带来全新的视觉享受与感官刺激！一键操作连招，解放你的手指，全屏大招，带来无限快感！怪物浮空、自动锁怪、无限连击，唯美的战斗场景与明快的BGM交相辉映，这场战斗注定酷炫到底！</h6> -->
					<div class="project-categories">${project.detail}</div>
					<!-- updown link -->
					<c:if test="${not empty project.downloadurl }">
						<a href="${project.downloadurl}" class="button-style-2 small">本地下载</a>
					</c:if>
	<!-- 			<h6>本产品版权归<a href="http://www.79643.com" target="_blank">泰尼游戏</a>所有</h6> -->
				</div>
				
			</div><!-- #content-->
			
			<!-- ********** - Sidebar - ************ -->
			<aside id="sidebar">
				<div class="widget">
					<h1 class="widget-title">Projects List</h1>
					
					<ul class="feature-menu">
					<c:forEach var="pro" items="${projects}" varStatus="status">
						<li><a href="project.htm?proId=${pro.id}">${pro.title}</a></li>
					</c:forEach>
<!-- 						<li><a href="#.html">铸剑传说</a></li> -->
<!-- 						<li><a href="#.html">棋牌系列</a> -->
<!-- 							<ul> -->
<!-- 								<li><a href="#.html">二人麻将</a></li> -->
<!-- 								<li><a href="#.html">老板斗地主</a></li> -->
<!-- 							</ul> -->
<!-- 						</li> -->
						
					</ul><!--/ .feature-menu-->	
					
				</div><!--/ .widget-->
				
			</aside><!--/ #sidebar-->
			
			<!-- ********** - end Sidebar - ************ -->
		</div><!--/ .content-wrapper-->
<!-- jQuery -->

<jsp:include page="include/footer.jsp"/>

