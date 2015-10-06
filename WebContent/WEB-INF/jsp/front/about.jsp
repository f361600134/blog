<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="include/header.jsp"/>

		<link href="<%=basePath%>/css/about/default.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="<%=basePath%>/js/about/garden.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/about/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/about/functions.js"></script>
		
		<!-- **************** - END Header - **************** -->
		
		<div class="content-wrapper sbr clearfix">
			
			<div id="content">
				<h1 class="content-title">About Me</h1>
				
					<div id="content-about">
					<div id="code">
						<span class="note">/**</span><br />
						<span class="space"><span class="note">* 2012-12-14,</span></span><br />
						<span class="space"><span class="note">* 2015-07-28.</span></span><br />
						<span class="space"><span class="note">*</span><span class="annotation"> @author</span><span class="note"> Jemery Feng</span><br/>
						<span class="space"><span class="note">*/</span></span><br/>
						Boy name = <span class="keyword">Mr</span> LI<br />
						Girl name = <span class="keyword">Mrs</span> ZHANG<br />
						<span class="comments">// Fall in love river. </span><br />
						The boy love the girl;<br />
						<span class="comments">// They love each other.</span><br />
						The girl loved the boy;<br />
						<span class="comments">// AS time goes on.</span><br />
						The boy can not be separated the girl;<br />
						<span class="comments">// At the same time.</span><br />
						The girl can not be separated the boy;<br />
						<span class="comments">// Both wind and snow all over the sky.</span><br />
						<span class="comments">// Whether on foot or 5 kilometers.</span><br />
						<span class="keyword">The boy</span> very <span class="keyword">happy</span>;<br />
						<span class="keyword">The girl</span> is also very <span class="keyword">happy</span>;</span><br />
						<span class="placeholder"><span class="comments">// Whether it is right now</span></span><br />
						<span class="placeholder"><span class="comments">// Still in the distant future.</span></span><br />
						<span class="placeholder">The boy has but one dream;</span><br />
						<!-- <span class="comments">// The boy wants the girl could well have been happy.</span><br /> -->
						<br>
						<br>
						I want to say:<br />
						Baby, I love you forever;<br />
					</div>
					
					</div>
					<img class="alignright" alt=""/>
				<hr /> 
 			</div><!--/ #content-->
			
			
			<!-- ********** - Sidebar - ************ -->
			
			<aside id="sidebar">
				
				<div class="widget">
					
					<h1 class="widget-title">Pages List</h1>

					<ul class="feature-menu">
						<li><a href="pages-about.html">About Us</a></li>
						<li><a href="pages-full-width.html">Full Width Page</a></li>
						<li><a href="pages-with-right-sidebar.html">Page Width Right Sidebar</a></li>
						<li><a href="pages-with-left-sidebar.html">Page Width Left Sidebar</a></li>
						<li><a href="pages-archives.html">Archives</a></li>
						<li><a href="pages-404.html">404 Page</a></li>
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
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>/images/temp/pic_thumb_1.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>/images/temp/pic_thumb_2.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>images/temp/pic_thumb_3.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
								</ul>
							</div><!--/ #tab4-->

							<div class="tab-content" id="tab5">
								<ul class="small-thumb">
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>/images/temp/pic_thumb_2.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>/images/temp/pic_thumb_1.jpg" alt="" /></a>
										<div class="entry">
											<h6><a href="#">Donec in velit vel ipsum auctor.</a></h6>
											<div class="entry-meta">
												<a class="post-date" href="#"><span>03/29/2012</span></a>
											</div><!--/ .entry-meta-->	
										</div><!--/ .entry-->
										<div class="clear"></div>
									</li>
									<li>
										<a href="#"><img class="alignleft" src="<%=basePath%>/images/temp/pic_thumb_3.jpg" alt="" /></a>
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
									<li><a href="#">Art</a>&nbsp;<span>(3)</span></li>
									<li><a href="#">Fashion</a>&nbsp;<span>(7)</span></li>
									<li><a href="#">Landscape</a>&nbsp;<span>(4)</span></li>
									<li><a href="#">Nature</a>&nbsp;<span>(1)</span></li>
									<li><a href="#">Photography</a>&nbsp;<span>(9)</span></li>
									<li><a href="#">Travelling</a>&nbsp;<span>(12)</span></li>
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
					
					<h1 class="content-title">Latest Projects</h1>
					
					<ul class="latest">
						<li>
							<a href="#">
								<img alt="" src="<%=basePath%>/images/temp/220px_1.jpg">
								<h3 class="title">Suspendisse sollicitudin</h3>
								<span class="desc">Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci...</span>
							</a>		
						</li>
						<li>
							<a href="#">
								<img alt="" src="<%=basePath%>/images/temp/220px_2.jpg">
								<h3 class="title">Aliquam dapibus tincidunt</h3>
								<span class="desc">Aenean nec eros. Vestibulum ante ipsum primis in faucibus orci...</span>
							</a>		
						</li>
						<li>
							<a href="#">
								<img alt="" src="<%=basePath%>/images/temp/220px_3.jpg">
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
			</aside><!--/ #sidebar-->
			<!-- ********** - END Sidebar - ************ -->
		</div><!--/ .content-wrapper-->
		<script type="text/javascript">$("#code").typewriter();</script>
		
<jsp:include page="include/footer.jsp"/>

