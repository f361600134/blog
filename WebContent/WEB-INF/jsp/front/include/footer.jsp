<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<!-- ************* - Main Footer - *************** -->
		<footer id="footer" class="clearfix">
			
			<div class="one-half">
				<nav id="footer-nav" class="clearfix">
					<ul>
						<li><a href="index.htm">Home</a></li>
						<li><a href="contacts.htm">Contact</a></li>
					</ul>
				</nav><!--/ #footer-nav-->
				
				<div class="copyright">&copy; 2012 Fatiny.<a href="http://www.miitbeian.gov.cn" title="备案" target="_blank">粤ICP备15049260号 </a></div><!--/ .copyright-->
				
			</div><!--/ .one-half-->
			
			<!-- Login <div class="mybox"></div>-->
			<div  class="one-half last">
				<ul data-description="Stay in touch via social networks:" class="social-links clearfix">
						<li data-tooltip="WeChat:huuzii" class="wechat" id="">
							<a href="#">WeChat</a>
						</li>
						<li data-tooltip="Sina" class="sina" id="">
							<a href="http://weibo.com/2308412664" target="_blank">Sina</a>
						</li>
						<li data-tooltip="Facebook" class="facebook" id="">
							<a href="https://www.facebook.com/profile.php?id=100008393648777" target="_blank">Facebook</a>
						</li>
						<li data-tooltip="Twitter" class="twitter" id="">
							<a href="https://twitter.com/nicc_fsc" target="_blank">Twitter</a>
						</li>
						<!-- <li data-tooltip="QQ" class="qq" id="">
							<a href="javascript:void(0);">QQ</a>
						</li> -->
				</ul><!-- / .social-links	-->
 			</div><!-- / .one-fourth.last -->
<!-- 			<ul data-description="Stay in touch via social networks:" class="social-links clearfix"> -->
<!-- 				<li data-tooltip="Sina" class="sina" id="wb_connect_btn"> -->
<!-- 					<a href="https://api.weibo.com/oauth2/authorize?client_id=769348763&redirect_uri=http://fatiny.com/user/Sinalogin.htm&response_type=code&state=1">Sina</a> -->
<!-- 				</li> -->
<!-- 				<li data-tooltip="QQ" class="qq" id="qqbt"> -->
<!-- 					<a href="http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&display=pc&client_id=101239300&response_type=token&scope=all&state=2&redirect_uri=http%3A%2F%2Fqzonestyle.gtimg.cn%2Fqzone%2Fopenapi%2Fredirect-1.0.1.html">QQ</a> -->
<!-- 				</li> -->
<!-- 			</ul>/ .social-links -->
			
		</footer>
		<!-- ************ - end Footer - ************ -->
	</section><!--/ .container-->
	<!-- *************** - end Container - *************** -->
	
	<!-- ************ - Footer Bottom - ************ -->
	<footer id="footer-bottom">
		
		<a href="#" id="footer-more"></a>
		
  			<section class="container panel clearfix">
			
			<div class="one-half">
				
				<div class="widget-text">
					<h1>About Me</h1>
					<p>
						我是程序猿黑咔嚓
					</p>
					<p>
						Java开启了我的程序之路。 现在正在修行Jbjective-C。<br>
					</p>
					<p>
						先就这样吧,哈哈!
					</p>
				</div><!--/ .widget-text-->
				
			</div><!--/ .one-half-->
			
			<div class="one-fourth">
				<div class="widget-flickr">
					<h1>From Flickr</h1>
					<ul id="flickr-badge" class="clearfix"></ul>
				</div><!--/ .widget-flickr-->
			</div><!--/ .one-fourth-->
			
			
			<div class="one-fourth last">
				<div id="signup">
					<h1>Join to Our Newsletter</h1>
					<form method="post" action="/" id="signup_form">
						<fieldset>
							<label for="s-email">Ut pharetra augue nec augue. Nam elit agna, endrerit sit amet.</label>
							<input type="text" placeholder="Enter your email address" name="s-email" id="s-email"/>
							<input type="submit" name="signup_submit" id="signup_submit" value="Submit" />
						</fieldset>
					</form><!--/ #signup_form-->
				</div><!--/ #signup -->
				
				
			</div><!--/ .one-fourth.last-->
		</section><!--/ .container-->
	</footer><!--/ #footer-bottom-->
	
	<!-- ************ - end Footer - ************ -->

	
</div><!--/ #wrapper-->
<!-- index -->
<script src="<%=path %>/js/blog/respond.min.js"></script>
<script src="<%=path %>/js/blog/jquery.easing-1.3.min.js"></script>
<script src="<%=path %>/sliders/elastslider/jquery.eislideshow.js"></script>
<script src="<%=path %>/js/blog/jquery.jcarousel.min.js"></script>
<script src="<%=path %>/js/blog/jquery.cycle.all.min.js"></script>
<script src="<%=path %>/js/blog/custom.js"></script>
<script src="<%=path %>/themeChanger/js/colorpicker.js"></script>
<script src="<%=path %>/themeChanger/js/themeChanger.js"></script>
<!-- end -->

<!-- post-singel -->
<script src="<%=path %>/fancybox/jquery.fancybox.pack.js"></script>
<script src="<%=path %>/js/blog/jquery.cycle.all.min.js"></script>
<script src="<%=path %>/js/blog/jquery.touchSwipe-1.2.5.min.js"></script>


</body>
</html>

