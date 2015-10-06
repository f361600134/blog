<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE html>
<!--[if lte IE 8]>              <html class="ie8 no-js" lang="en">     <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html class="not-ie no-js" lang="en">  <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	
	<title>Fatiny</title>
	
	<meta name="description" content="">
	<meta name="author" content="Jeremy">
	<meta property="qc:admins" content="325627643766141616375" />
	<meta property="wb:webmaster" content="2816be2676200db0" />
	
	<link rel="icon" type="image/png" href="<%=path %>/images/favicon.ico">
	
	<link rel="stylesheet" href="<%=path %>/css/blog/style.css" media="screen" />
	<link rel="stylesheet" href="<%=path %>/sliders/elastslider/elastic.css" media="screen" />
	<link rel="stylesheet" href="<%=path %>/fancybox/jquery.fancybox.css" media="screen" />
	<!-- 	<link href="<%=path %>/css/about/default.css" type="text/css" rel="stylesheet"> -->
	
	<!-- HTML5 Shiv + detect touch events -->
	<script src="<%=path %>/js/blog/modernizr.custom.js"></script>
	<!-- QQ login
	<script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
	data-appid="101239300" data-redirecturi="http%3A%2F%2Ffatiny.com%2Fuser%2FQQlogin.htm" charset="utf-8"></script> -->
	<!-- data-redirecturi="http%3a%2f%2fwww.fatiny.com%2fuser%2fQQlogin.htm" -->
	<script type="text/javascript" data-appid="101239300" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" data-callback="true"></script>
	<!-- Sina login -->
	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=769348763" type="text/javascript" charset="utf-8"></script>
	<script  type="text/javascript" src="<%=path %>/js/blog/share.js"></script>
	<script type="text/javascript" src="<%=path %>/js/blog/jquery.min.js"></script>
	<script>!window.jQuery && document.write('<script src="<%=path %>/js/jquery-1.7.1.min.js"><\/script>')</script>
	
</head>
<body class="liquid light">

<div id="wrapper">
	
	<!-- ***************** - Header Top - ***************** -->	
	
	<div id="header-top" class="clearfix">
		
		<section class="container clearfix">
			
			<div class="one-half">
				
				<ul class="contact-info clearfix">
					<li class="phone">+132 **** 8641</li>
					<li class="email"><a href="mailto:361600134@qq.com">361600134@qq.com</a></li>
				</ul><!--/ .contact-info-->	
				
			</div><!--/ .one-half-->
			<!--Facebook: https://www.facebook.com/profile.php?id=100008393648777 -->
			<!--Twitter: https://twitter.com/nicc_fsc -->
			<!--Sina: http://weibo.com/2308412664 -->
			<div  class="one-half last">
				<ul data-description="Stay in touch via social networks:" class="social-links clearfix">
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
<!-- 			<div  class="one-half last"> -->
<!-- 				<ul data-description="Stay in touch via social networks:" class="social-links clearfix"> -->
<!-- 						<li data-tooltip="Sina" class="sina" id="wb_connect_btn"> -->
<!-- 							<a href="https://api.weibo.com/oauth2/authorize?client_id=769348763&redirect_uri=http://fatiny.com/user/Sinalogin.htm&response_type=code&state=1">Sina</a> -->
<!-- 						</li> -->
<!-- 						<li data-tooltip="QQ" class="qq" id="qqbt"> -->
<!-- 							<a href="http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&display=pc&client_id=101239300&response_type=token&scope=all&state=2&redirect_uri=http%3A%2F%2Fqzonestyle.gtimg.cn%2Fqzone%2Fopenapi%2Fredirect-1.0.1.html">QQ</a> -->
<!-- 						</li> -->
<!-- 				</ul>/ .social-links	 -->
<!-- 			</div>/ .one-fourth.last -->
 			</section><!--/ .container -->
		
		<a href="#" id="more"></a>
		
	</div><!--/ #header-top-->
	
	<!-- ***************** - END Header Top - ***************** -->


	<!-- ***************** - Container - ***************** -->
	
	<section class="container">

		
		<!-- ***************** - Header - ***************** -->
		
		<header id="header" class="clearfix">
			
			<div id="logo">
				<h1><a href="<%=basePath %>front/index.htm"><!-- ${logo.title } -->Fatiny</a></h1>
				<div class="slogan"><span><!--${logo.slogan }-->My Personal Blog</span></div><!--/ .slogan-->
			</div><!--/ #logo-->
			
			<nav id="navigation" class="navigation">
				<ul>
					<li class="current"><a href="<%=basePath %>front/index.htm">Home</a>					</li>
			  <!--<li><a href="features-typography.html">Features</a>
						<ul>
							<li><a href="features-typography.html">Typography</a></li>
							<li><a href="features-shortcodes-buttons.html">Shortcodes</a>
								<ul>
									<li><a href="features-shortcodes-buttons.html">Buttons & List Styles</a></li>
									<li><a href="features-shortcodes-columns.html">Columns</a></li>
									<li><a href="features-shortcodes-alert-boxes.html">Alert Boxes</a></li>
									<li><a href="features-shortcodes-elements.html">Accordion, Tabs & Toggle</a></li>
									<li><a href="features-shortcodes-pricing-tables.html">Pricing Tables</a></li>
									<li><a href="features-shortcodes-dropcaps.html">Dropcaps & Blockquotes</a></li>
									<li><a href="features-shortcodes-other-stuff.html">Other Stuff</a></li>
								</ul>
							</li>
						</ul>
					</li>
			  <li><a href="pages-about.html">Pages</a>
						<ul>
							<li><a href="pages-about.html">About Us</a></li>
							<li><a href="pages-full-width.html">Full Width</a></li>
							<li><a href="pages-with-right-sidebar.html">With Right Sidebar</a></li>
							<li><a href="pages-with-left-sidebar.html">With Left Sidebar</a></li>
							<li><a href="pages-archives.html">Archives</a></li>
							<li><a href="pages-404.html">404 Page</a></li>
						</ul>
					</li>
			  <li><a href="portfolio-2columns.html">Portfolio</a>
						<ul>
							<li><a href="portfolio-2columns.html">2 Columns</a></li>
							<li><a href="portfolio-3columns.html">3 Columns</a></li>
							<li><a href="portfolio-4columns.html">4 Columns</a></li>
							<li><a href="portfolio-single-project.html">Single Project Page</a></li>
						</ul>
			 </li>-->
			 <!-- <li><a href="<%=basePath %>front/posts_without_media.htm">Blog</a>
						<ul>
							<li><a href="<%=basePath %>front/posts_with_media.htm">Posts With Media</a></li>
							<li><a href="<%=basePath %>front/posts_without_media.htm">Posts Without Media</a></li>
						</ul>
					</li>
			 -->
			 <!--<li><a href="<%=basePath %>front/project.htm">Project</a></li>
			 <!-- <li><a href="<%=basePath %>front/about.htm">About</a></li> -->
			 <li><a href="<%=basePath %>front/contacts.htm">Contact</a></li>
			  </ul>
			</nav><!--/ #navigation-->
			
		</header><!--/ #header-->
		
		<!-- **************** - end Header - **************** 
        <div class="copyrights">Collect from <a href="http://www.baidu.com"  title="疯子">疯子</a></div>
		-->
		