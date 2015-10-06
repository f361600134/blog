<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="include/header.jsp"/>
		
		<!-- **************** - end Header - **************** 
		<!-- ***************** - Slider - ***************** -->
		<div id="ei-slider" class="ei-slider">
			<ul class="ei-slider-large">
				<li>
					<img src="<%=path %>/images/sliders/img_06.jpg" alt="image01" />
					<div class="ei-title">
						<h2>Trying to do it.</h2>
						<h3>Photography</h3>
					</div>
				</li>
			</ul><!-- ei-slider-large -->
		</div><!-- ei-slider -->
		
		<!-- *************** - end Slider - *************** -->	

		
		<!-- *************** - Projects Carousel - *************** -->	
		
		<div class="page-title clearfix">

			<h2>Recent Projects</h2>

		</div><!--/ .page-title-->
		
		<div class="container">
			
			<div class="one-fourth">
				<p>
<!-- 					Nulla facilisi. Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere.  -->
<!-- 					Suspendisse sollicitudin velit sed leo.  -->
				   想从百度上抄一些有意思的话.想想还是做最真实的自己!
						
				</p>
				<p>这些年做的一些小游戏.</p>
				<a href="project.htm" class="button-style-1 small">View All</a>
				
			</div><!--/ .one-fourth-->
			<div class="three-fourth last">
				<ul class="projects-carousel clearfix">
					<!-- 循环project,达到列表的效果 -->
					<c:forEach var="project" items="${recent_projects}" varStatus="status">
						<li>
							<a href="project.htm?proId=${project.id}">
								<img src="${project.pictures.get(0).picUrl}" width="220" height="134" style="height:134px" alt="${project.title}" title="${project.title}"/>
								<h3>${project.title}</h3>
								<span class="categories">${project.categories}</span>
							</a>
						</li>
					</c:forEach>
				</ul><!--/ .projects-carousel -->				
			</div><!--/ .three-fourth-->
		</div><!--/ .container-->
		<div class="clear"></div>
		
		<!-- *************** - end Projects Carousel - *************** -->	

		<!-- *************** - Tabs Container - *************** -->	
		<div class="content-tabs">
			<ul class="tabs-nav clearfix">
				<li><a href="#tab2">From the Blog</a></li>
				<li><a href="#tab3">Latest Tweets</a></li>
				<li><a href="#tab3">Blogroll</a></li>
			</ul><!--/ .tabs-nav -->

			<div class="tabs-container">
				<div class="tab-content" id="tab2">
					<c:forEach var="blogs" items="${home_blogs}" varStatus="status">
						<c:choose>
							<c:when test="${status.last}"><div class="one-fourth last"></c:when>
						    <c:otherwise><div class="one-fourth"></c:otherwise>
						</c:choose>
							<a href="/front/single-post.htm?bid=${blogs.bid }">
								<img class="entry-image" src="<%=path %>/images/temp/blog_thumb_${status.count }.jpg" alt="" />
							</a>
							<div class="entry-title">
								<a href="/front/single-post.htm?bid=${blogs.bid }"><h2><c:choose><c:when test="${blogs.postType == 1}"><span class="post-type video"></span><!-- vedio --></c:when>
								   <c:when test="${blogs.postType == 2}"><span class="post-type audio"></span><!-- audio --></c:when>
								   <c:otherwise><span class="post-type list"></span><!-- image --></c:otherwise>
								</c:choose>${blogs.title}</h2></a>
							</div>
							<div class="entry-meta">
								<a href="#" class="post-date"><span>${blogs.showDate}</span></a>
								<a href="#" class="post-comments"><span>${blogs.count} comments</span></a>		
							</div>
						</div><!--end one-fourth -->
					</c:forEach>
						
				</div><!--/ #tab2-->

				<div class="tab-content" id="tab3">
					<div class="tweet"></div>
				</div><!--/ #tab3-->
				
				<div class="tab-content" id="tab4">
					<div class="blogroll">
						
					</div>
				</div><!--/ #tab3-->

			</div><!--/ .tabs-container -->		
		</div><!--/ .content-tabs-->
		<!-- ************** - end Tabs Container - ************** -->

		
		<c:if test="${not empty home_contacts}">
			<!-- ************* - BEGIN Testimonials - *************** -->
			<div class="testimonials">
				<h1>Wonderful Message</h1>
				<div class="quote-nav">
					<span class="quote-prev">Previous</span>
					<span class="quote-next">Next</span>
				</div><!--/ .quote-nav-->
				<ul class="quoteBox">
					<c:forEach var="cons" items="${home_contacts}" varStatus="status">
					<li>
						<blockquote class="quote-text">
							${cons.message}
							<div class="quote-author">${cons.name}, ${cons.showDate}</div>
						</blockquote>					
					</li>
					</c:forEach>
				</ul><!--/ .quoteBox-->
			</div><!--/ #testimonials -->
			<!-- ************* - end Testimonials - *************** -->
		</c:if>
	
<jsp:include page="include/footer.jsp"/>
	

