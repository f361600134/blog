<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String myPath = request.getScheme()+"://"+request.getServerName()+path+"/front/";
// String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="include/header.jsp"/>

<!-- 	<link rel="icon" type="image/png" href="<%=path %>/images/favicon.ico"> -->
	<script>!window.jQuery && document.write('<script src="<%=path %>/js/blog/jquery-1.7.1.min.js"><\/script>')</script>
<!-- 	<link rel="stylesheet" href="<%=path %>/fancybox/jquery.fancybox.css" media="screen" /> -->
<!-- 	<link rel="stylesheet" href="<%=path %>/css/blog/video-js.css" /> -->
<!-- 	<link rel="stylesheet" href="<%=path %>/css/blog/audioplayerv1.css" media="screen" /> -->
	
		<!-- **************** - END Header - **************** -->
		<div class="content-wrapper sbr clearfix">
			
			<div class="page-header clearfix">
				
				<h1 class="alignleft">Blog</h1>
				
				<div class="search-container">
					<form id="search" action="posts_without_media.htm">
						<input placeholder="Search" type="text" name ="text" />
						<button type="submit"></button>
					</form><!--/ #search-->
				</div><!--/ .search-container-->
				
			</div><!--/ .page-header-->
			
			<section id="content">
				<!-- 循环article,达到列表的效果 -->
				<c:forEach var="blog" items="${page.list}" varStatus="status">
				
				<article class="post-item clearfix">
					<div class="image-title clearfix">
						<a href="single-post.htm?bid=${blog.bid}">
							<h2>
								<c:choose>
								   <c:when test="${blog.postType == 1}"><span class="post-type video"></span><!-- audio --></c:when>
								   <c:when test="${blog.postType == 2}"><span class="post-type audio"></span><!-- audio --></c:when>
								    <c:when test="${blog.postType == 3}"><span class="post-type picture"></span><!-- image --></c:when>
								   <c:otherwise><span class="post-type list"></span><!-- image --></c:otherwise>
								</c:choose>
								${blog.title }
							</h2>
						</a>
					</div><!--/ .image-title-->
					
					<div class="one-sixth">

						<div class="meta-entry">
							<a href="posts_without_media.htm?date=${blog.showDate}" class="date" ><span>${blog.showDate }</span></a>
							<a href="#" class="author"><span>${blog.author }</span></a>
							<!-- 循环Tags,达到标签效果 -->
							
							<span class="category">
								<c:forEach var="tag" items="${blog.tags}" varStatus="status">
									<a href="posts_without_media.htm?tagid=${tag.id}" >${tag.name}</a>
								</c:forEach>
							</span>
							<span class="comments">${blog.count} comments</span>
						</div><!--/ .meta-entry-->
						<ul data-description="Share this:" class="social-links">
							<li data-tooltip="Twitter" class="twitter">
								<a href="javascript:void(0);" onclick="shareTwitter('<%=myPath%>single-post.htm?bid=${blog.bid}')"></a>
							</li>
							<li data-tooltip="Facebook" class="facebook">
								<a href="javascript:void(0);" onclick="shareFacebook('<%=myPath%>single-post.htm?bid=${blog.bid}')">Facebook</a>
							</li>
							<li data-tooltip="Qzone" class="qzone">
								<a href="javascript:void(0);" onclick="shareQzone('${blog.title}','<%=myPath%>single-post.htm?bid=${blog.bid}')" >Qzone</a>
							</li>
							<li data-tooltip="Sina" class="sina">
								<a href="javascript:void(0);" onclick="shareSina('${blog.title}','<%=myPath%>single-post.htm?bid=${blog.bid}')" >Sina</a>
							</li>
						</ul><!--/ .social-links-->	
					</div><!--/ .one-sixth-->
					
					<div class="entry-body">
						${blog.mdContent }
						<a href="single-post.htm?bid=${blog.bid}" class="button-style-1 small">Read more</a>
					</div><!--/ .entry-body-->
					</article><!--/ .post-item-->
				</c:forEach>
				
				<div class="page-title clearfix">
					<div class="pagination">
						${link}
					</div><!--/ .pagination-->

				</div><!--/ .page-title--> 
				
			</section><!--/ #content-->
			<!-- ********** - Sidebar - ************ -->
			
			<aside id="sidebar">
				
				<div class="widget">
					
					<h1 class="widget-title">Categories</h1>

					<ul class="feature-menu">
						<c:forEach var="cate" items="${categories}" varStatus="status">
							<li><a href="posts_without_media.htm?cateid=${cate.id}">${cate.name}</a>&nbsp;<span></span></li>
						</c:forEach>
					</ul><!--/ .feature-menu-->
					
				</div><!--/ .widget-->
				
				<div class="widget">
					
					
					<!-- *************** - Tabs Container - *************** -->
					<div class="aside-tabs">
						
						<ul class="tabs-nav clearfix">

							<li class="popular" data-tooltip="Popular"><a href="#tab4">Popular Posts</a></li>
							<li class="recent" data-tooltip="Recent"><a href="#tab5">Recent Posts</a></li>
							<li class="tags" data-tooltip="Tags"><a href="#tab6">Tags</a></li>
							<li class="latest" data-tooltip="Twitter"><a href="#tab7">Latest Tweets</a></li>

						</ul><!--/ .tabs-nav -->

						<div class="tabs-container">

							<div class="tab-content" id="tab4">
								<ul class="small-thumb">
									<c:forEach var="popular_blog" items="${popular_blogs}" varStatus="status">
									<li>
										<a href="#"><img class="alignleft" src="<%=path %>/images/temp/pic_thumb_${status.count }.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">${popular_blog.title }</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>${popular_blog.showDate}</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
								</c:forEach>
								</ul>
							</div><!--/ #tab4-->

							<div class="tab-content" id="tab5">
								<ul class="small-thumb">
									<li>
										<a href="#"><img class="alignleft" src="<%=path %>/images/temp/pic_thumb_2.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=path %>/images/temp/pic_thumb_1.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=path %>/images/temp/pic_thumb_3.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
								</ul>
							</div><!--/ #tab5-->

							<div class="tab-content" id="tab6">
								<ul class="tag-list">
									<c:forEach var="tag" items="${tags}" varStatus="status">
										<li><a href="posts_with_media.htm?tagid=${tag.id}">${tag.name }</a>&nbsp;</li>
									</c:forEach>
								</ul>
							</div><!--/ #tab6-->

							<div class="tab-content" id="tab7">
								
								<div class="tweet"></div>
								
							</div><!--/ #tab7-->

						</div><!--/ .tabs-container -->	
					</div><!--/ .aside-tabs-->
		
					<!-- ************** - end Tabs Container - ************** -->	
					
				</div><!--/ .widget-->
				
				
				<div class="widget">
					
					<h1 class="content-title">Recent Projects</h1>
					
					<ul class="latest">
						<li>
							<a href="#">
								<img alt="" src="<%=path %>/images/temp/220px_1.jpg">
								<h3 class="title">Suspendisse sollicitudin</h3>
								<span class="desc">Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci...</span>
							</a>		
						</li>
						<li>
							<a href="#">
								<img alt="" src="<%=path %>/images/temp/220px_2.jpg">
								<h3 class="title">Aliquam dapibus tincidunt</h3>
								<span class="desc">Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci...</span>
							</a>		
						</li>
						<li>
							<a href="#">
								<img alt="" src="<%=path %>/images/temp/220px_3.jpg">
								<h3 class="title">Donec sagittis euismod</h3>
								<span class="desc">Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci...</span>
							</a>		
						</li>
					</ul>
					
					<div class="latest-controls">
						<a href="#" id="prev"></a>
						<a href="#" id="next"></a>
					</div>

				</div><!--/ .widget-->
				
				<div class="widget">
					
					<h1 class="content-title">Recent Comments</h1>
					
					<ul class="recent-comments">
						<li><a href="#" class="author">Sam</a>&nbsp;<small>on</small>&nbsp;<a href="#">Aenean auctor wisi et urna</a></li>
						<li><a href="#" class="author">Alice</a>&nbsp;<small>on</small>&nbsp;<a href="#">Vestibulum ante ipsum primis in</a></li>
						<li><a href="#" class="author">Admin</a>&nbsp;<small>on</small>&nbsp;<a href="#">Donec sagittis euismod purus</a></li>
						<li><a href="#" class="author">John</a>&nbsp;<small>on</small>&nbsp;<a href="#">Sed ut perspiciatis sit</a></li>
						<li><a href="#" class="author">Linda</a>&nbsp;<small>on</small>&nbsp;<a href="#">Neque porro quisquam</a></li>
					</ul><!--/ .recent-comments-->
					
				</div><!--/ .widget-->
				
				
				<div class="widget">
					
					<h1 class="widget-title">Archives</h1>

					<ul class="feature-menu">
						<li><a href="#">January 2012</a>&nbsp;<span>(9)</span></li>
						<li><a href="#">February 2012</a>&nbsp;<span>(11)</span></li>
						<li><a href="#">March 2012</a>&nbsp;<span>(7)</span></li>
						<li><a href="#">April 2012</a>&nbsp;<span>(13)</span></li>
					</ul>
					
				</div><!--/ .widget-->

				
			</aside><!--/ #sidebar-->
			
			<!-- ********** - END Sidebar - ************ -->
		</div><!--/ .content-wrapper-->
	
<!-- 	<script src="<%=path %>/js/blog/video.js"></script> -->
<!-- 	<script>_V_.options.flash.swf = '<%=path %>/js/blog/video-js.swf';</script> -->
<!-- 	<script src="<%=path %>/js/blog/jquery.ui.widget.min.js"></script> -->
<!-- 	<script src="<%=path %>/fancybox/jquery.fancybox.pack.js"></script> -->
<!-- 	<script src="<%=path %>/js/blog/audioplayerv1.js"></script> -->
<!-- 	<script src="<%=path %>/js/blog/jquery.touchSwipe-1.2.5.min.js"></script> -->

<jsp:include page="include/footer.jsp"/>


