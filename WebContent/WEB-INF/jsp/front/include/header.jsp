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
	data-appid="101239300" data-redirecturi="http%3A%2F%2Ffatiny.com%2Fuser%2FQQlogin.htm" charset="utf-8"></script> 
	<!-- data-redirecturi="http%3a%2f%2fwww.fatiny.com%2fuser%2fQQlogin.htm" -->
	<!--<script type="text/javascript" data-appid="101239300" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" charset="utf-8" data-callback="true"></script> -->
	<!-- Sina login
	<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=769348763" type="text/javascript" charset="utf-8"></script>
	 -->
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
					<li class="phone">361600134</li>
					<li class="email"><a href="mailto:361600134@qq.com">361600134@qq.com</a></li>
				</ul><!--/ .contact-info-->	
			</div><!--/ .one-half-->
			
			<div  class="one-half last">
				<ul data-description="Stay in touch via social networks:" class="social-links clearfix">
						<li data-tooltip="WeChat:huuzii" class="wechat" id="">
							<a href="https://twitter.com/nicc_fsc" target="_blank">WeChat</a>
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
				<h1><a href="<%=basePath %>front/index.htm">${logo.title }</a></h1>
				<div class="slogan"><span>${logo.slogan }</span></div><!--/ .slogan-->
			</div><!--/ #logo-->
			
			<nav id="navigation" class="navigation">
				<ul>
					<li class="current"><a href="<%=basePath %>front/index.htm">Home</a></li>
				 	<li><a href="<%=basePath %>front/contacts.htm">Contact</a></li>
			  </ul>
			</nav><!--/ #navigation-->
			
		</header><!--/ #header-->
		