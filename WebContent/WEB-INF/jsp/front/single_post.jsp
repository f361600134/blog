<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String myPath = request.getScheme()+"://"+request.getServerName()+path+"/front/";
String myPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/front/";
%>

<jsp:include page="include/header.jsp"/>
	<link rel="stylesheet" href="<%=path %>/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css" type="text/css"></link></head>
	<script type="text/javascript" src="<%=path %>/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
	<script type="text/javascript">
		SyntaxHighlighter.all();
	</script>
	<link rel="stylesheet" href="<%=path %>/fancybox/jquery.fancybox.css" media="screen" />
	<script>!window.jQuery && document.write('<script src="<%=path %>/js/blog/jquery-1.7.1.min.js"><\/script>')</script>
	<link rel="stylesheet" href="<%=path %>/fancybox/jquery.fancybox.css" media="screen" />
	<link rel="stylesheet" href="<%=path %>/css/blog/video-js.css" />
	<link rel="stylesheet" href="<%=path %>/css/blog/audioplayerv1.css" media="screen" />
	<script src="<%=path %>/js/blog/video.js"></script>
	<script>_V_.options.flash.swf = '<%=path %>/js/blog/video-js.swf';</script>
	<script src="<%=path %>/js/blog/jquery.ui.widget.min.js"></script>
	<script src="<%=path %>/fancybox/jquery.fancybox.pack.js"></script>
	<script src="<%=path %>/js/blog/audioplayerv1.js"></script>
	<script src="<%=path %>/js/blog/common.js"></script>
	<script src="<%=path %>/js/blog/jquery.touchSwipe-1.2.5.min.js"></script>
		
		
		<!-- **************** - END Header - **************** -->
		<div class="content-wrapper sbr clearfix">
			
			<div class="page-header clearfix">
				
				<h1 class="alignleft">Single Post</h1>
				
				<div class="search-container">
					<form id="search" action="index.htm">
						<input placeholder="Search" type="text" name ="text" />
						<button type="submit"></button>
					</form><!--/ #search-->
				</div><!--/ .search-container-->
				
			</div><!--/ .page-header-->
			
			<section id="content">
				
				<article class="post-item clearfix" >
					
					<!-- <a class="single-image picture-icon" href="<%=path %>/images/fullscreen/big-img-12.jpg">
						<img class="entry-image" src="<%=path %>/images/temp/blog_img_1.jpg" alt="" />
					</a> -->
<%-- 					<c:if test="${blog.postType == 1}"> --%>
<!-- 						<video id="home_video" class="entry-video video-js vjs-default-skin"  data-aspect-ratio='2.41' data-setup="{}" controls> -->
<%-- 							<source src="${blog.medias.get(0)}" type='video/mp4' ></source> --%>
<!-- 						</video> -->
<%-- 					</c:if> --%>
<%-- 					<c:if test="${blog.postType == 2}"> --%>
<!-- 						<audio class="entry-audio AudioPlayerV1"  controls="controls"> -->
<%-- 							<source src="${blog.medias.get(0)}" type="audio/mpeg"></source> --%>
<!-- 						</audio> -->
<%-- 					</c:if> --%>
					<div class="image-title clearfix" >
						<h2>
							<span class="post-type picture"></span>
							<!-- Mauris fermentum dictum magna vestibulum sed ante donec sagittis euismod. -->
							${blog.title }
						</h2>
					</div><!--/ .image-title-->
					
					<div class="one-sixth">

						<div class="meta-entry">
							<a href="index.htm?date=${blog.showDate}" class="date"><span>${blog.showDate }</span></a>
							<a href="#" class="author"><span>${blog.author }</span></a>
							<span class="category">
								<c:forEach var="tag" items="${blog.tags}" varStatus="status">
									<a href="index.htm?tagid=${tag.id}">${tag.name}</a>
								</c:forEach>
							</span>
							<span class="comments">${blog.count} comments</span>
						</div><!--/ .meta-entry-->

						<ul data-description="Share this:" class="social-links">
							<li data-tooltip="Twitter" class="twitter">
								<a href="javascript:void(0);" onclick="shareTwitter('<%=myPath%>single_post.htm?bid=${blog.bid}')">Twitter</a>
							</li>
							<li data-tooltip="Facebook" class="facebook">
								<a href="javascript:void(0);" onclick="shareFacebook('<%=myPath%>single_post.htm?bid=${blog.bid}')">Facebook</a>
							</li>
							<li data-tooltip="Qzone" class="qzone">
								<a href="javascript:void(0);" onclick="shareQzone('${blog.title}','<%=myPath%>single_post.htm?bid=${blog.bid}')" >Qzone</a>
							</li>
							<li data-tooltip="Sina" class="sina">
								<a href="javascript:void(0);" onclick="shareSina('${blog.title}','<%=myPath%>single_post.htm?bid=${blog.bid}')" >Sina</a>
							</li>
						</ul><!--/ .social-links-->	

					</div><!--/ .one-sixth-->
					
					<div class="entry-body">
						${blog.content }
						<a class="button-style-1 small" href="#respond" onclick="return addComment.moveForm(&quot;comment-3&quot;, &quot;3&quot;, &quot;respond&quot;, &quot;41&quot;,0)">Leave a Comment</a>
					</div><!--/ .entry-body-->
					
				</article><!--/ .post-item-->
				
		
				<div class="page-title clearfix">

					<div class="pagination">
						<a href="single_post.htm?bid=${blog.bid}&oper=previous" class="prev-project">Previous Post</a><!--/ .prev-project-->
						<a href="single_post.htm?bid=${blog.bid}&oper=next" class="next-project">Next Post</a><!--/ .next-project-->
					</div><!--/ .pagination-->
				</div><!--/ .page-title-->
				
				
				<section id="comments">
					<c:if test="${!empty contacts}">
						<h3>Comments</h3>
					</c:if>
					<ol class="comments-list">
						<!-- 循环 -->
						<c:forEach var="contact" items="${contacts}" varStatus="status">
						<li class="comment">

							<article>
								<img src="<%=path %>/images/gravatar.png" alt="Image" class="avatar">
								<div class="comment-meta">
									<h4 class="author"><a href="#">${contact.name }</a></h4>
									<div class="date">${contact.showDate }</div>
									<div class="reply">
									<a class="button-style-1 small" href="#respond" onclick="return addComment.moveForm(&quot;comment-3&quot;, &quot;3&quot;, &quot;respond&quot;, &quot;41&quot;,${contact.id})">Reply</a>
									</div>
								</div><!--/ .comment-meta -->
								<div class="comment-body">
									<p>
										<!-- Donec eget tellus non erat lacinia fermentum. Donec in velit vel ipsum auctor pulvinar. Vestibulum 
										iaculis lacinia est. Proin dictum elementum velit. Fusce euismod consequat ante. 
										 -->
										 ${contact.message }
									</p>
								</div><!--/ .comment-body -->
							</article>
							
							<c:forEach var="reply" items="${contact.conts}" varStatus="status">
							<ul class="children">
								<li class="comment">
									<article>
										<img src="<%=path %>/images/gravatar.png" alt="Image" class="avatar">
										<div class="comment-meta">
											<h4 class="author"><a href="#">${reply.name }</a></h4>
											<div class="date">${reply.showDate }</div>
											<div class="reply">
											<a class="button-style-1 small" href="#respond" onclick="return addComment.moveForm(&quot;comment-3&quot;, &quot;3&quot;, &quot;respond&quot;, &quot;41&quot;,${contact.id})">Reply</a>
											</div>
										</div><!--/ .comment-meta -->
										<div class="comment-body">
											<p>
											<!--  Lorem ipsum dolor sit amet, consectetuer adipis. Mauris accumsan nulla vel diam.
											 -->
											 ${reply.message }
											</p>
										</div><!--/ .comment-body -->
									</article>
								</li>
							</ul><!-- end .children -->
							</c:forEach>
						</li><!--/ .children-->
						</c:forEach>
					</ol><!--/ .comments-list-->

				</section><!--/ #comments-->
				
				<section id="respond">
					<h3 class="content-title">Leave a Comment</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod  aliqua.</p>
					<form method="post" id="comments-form" class="comments-form" action="single_post.htm"><!-- action="single-post.htm?bid=${blog.bid}&cid=0 "-->
						<input type="hidden" id="bid" name="bid" value="${blog.bid}"> 
						<input type="hidden" id="cid" name="cid" value="0"> 
						<fieldset class="input-fieldset">
							<p class="input-block"><input type="text" name="name" value="" placeholder="Name (required)" id="comment-name" required></p>
							<p class="input-block"><input type="text" name="email" value="" placeholder="Email (required)" id="comment-email" required></p>
							<p class="input-block"><input type="text" name="website" value="" placeholder="Website" id="comment-website"></p>
						</fieldset>
						<fieldset class="textarea-block">
							<textarea name="message" placeholder="Message (required)" id="comment-message" required></textarea>
						</fieldset>

						<button class="button-style-2 medium" type="submit">Submit Comment</button>
					</form><!--/ .comments-form-->

				</section><!--/ #respond-->
				
			</section><!--/ #content-->
			
			
			<!-- ********** - Sidebar - ************ -->
			
			<aside id="sidebar">
				
				<div class="widget">
					
					<h1 class="widget-title">Categories</h1>

					<ul class="feature-menu">
					<c:forEach var="cate" items="${categories}" varStatus="status">
							<li><a href="index.htm?cateid=${cate.id}">${cate.name}</a>&nbsp;<span></span></li>
					</c:forEach>
					</ul><!--/ .feature-menu-->	
					
				</div><!--/ .widget-->
				
				<div class="widget">
					
					<!-- *************** - Tabs Container - *************** -->
					<div class="aside-tabs">
						
						<ul class="tabs-nav clearfix">

							<li class="popular" data-tooltip="Popular"><a href="#tab4">Popular Posts</a></li>
							<li class="tags" data-tooltip="Tags"><a href="#tab6">Tags</a></li>
							<li class="recent" data-tooltip="Facebook"><a href="#tab5">Recent Posts</a></li>
							<li class="latest" data-tooltip="Twitter"><a href="#tab7">Latest Tweets</a></li>

						</ul><!--/ .tabs-nav -->

						<div class="tabs-container">

							<div class="tab-content" id="tab4">
								<ul class="small-thumb">
								<c:forEach var="popular_blog" items="${popular_blogs}" varStatus="status">
									<li>
										<a href="<%=myPath%>single_post.htm?bid=${popular_blog.bid}"><img class="alignleft" src="<%=path %>/images/temp/pic_thumb_${status.count }.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="<%=myPath%>single_post.htm?bid=${popular_blog.bid}">${popular_blog.title }</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>${popular_blog.showDate}</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
								</c:forEach>
								</ul>
							</div><!--/ #tab4-->
							<div class="tab-content" id="tab6">
								<ul class="tag-list">
								<c:forEach var="tag" items="${tags}" varStatus="status">
									<li><a href="index.htm?tagid=${tag.id}">${tag.name }</a>&nbsp;</li>
								</c:forEach>
								</ul>
							</div><!--/ #tab6-->
							<div class="tab-content" id="tab5">
								
								<div class="facebook"></div>
								
							</div><!--/ #tab5-->
							
							<div class="tab-content" id="tab7">
								
								<div class="tweet"></div>
								
							</div><!--/ #tab7-->

						</div><!--/ .tabs-container -->	
					</div><!--/ .aside-tabs-->
					<!-- ************** - end Tabs Container - ************** -->	
					
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
		
		<script>!window.jQuery && document.write('<script src="<%=path %>/js/blog/jquery-1.7.1.min.js"><\/script>')</script>
		
<jsp:include page="include/footer.jsp"/>