<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="include/header.jsp"/>
		<!-- **************** - end Header - **************** -->
		<div class="content-wrapper clearfix">
			<!-- <div id="map"></div> -->
			<div class="one-fourth">
				<h2 class="content-title noborder">Contact Info</h2>
				<div class="contact-block">
					<p>
						<span><a href="http://www.79643.com" target="_blank">Janlr Network Technology Co.</a></span>
						Room 2801, No. 197, <br />
						North of Guangzhou Avenue.
					</p>
					<ul class="contact-list">
						<li class="contact-phone">361600134</li>
						<li class="contact-mail">
							<a href="mailto:fengsc@79643.com">fengsc@79643.com</a>
						</li>
						<!-- <li class="contact-skype">skype.name</li> -->
					</ul><!--/ .contact-list-->
				</div><!--/ .contact-block-->
			</div><!--/ .one-fourth-->
			<div class="three-fourth last">
				<h2 class="content-title">Drop Me a Line</h2>
				<!-- <p></p> -->
				<form action="" class="contacts-form" id="contacts-form" method="post">
					<fieldset>
						<label class="input-block"><input type="text" name="name" value="" placeholder="Name (required)" required></label>
						<label class="input-block"><input type="text" name="email" value="" placeholder="Email (required,private)" required></label>
						<label class="input-block"><input type="text" name="website" value="" placeholder="Website(private)"></label>	
						<label class="message textarea-block"><textarea name="message" placeholder="Message (required)" required></textarea></label>
						<br class="clear">
					</fieldset>
					<button type="submit" class="button-style-2 medium">Send Message</button>
				 	<!--  <div class="mybox"></div>-->
					<!-- 临时登陆 -->
<!-- 					<ul data-description="Log in:" class="social-links"> -->
<!-- 						<li data-tooltip="Sina" class="sina" id="wb_connect_btn"> -->
<!-- 							<a href="https://api.weibo.com/oauth2/authorize?client_id=769348763&redirect_uri=http://fatiny.com/user/Sinalogin.htm&response_type=code&state=1">Sina</a> -->
<!-- 						</li> -->
<!-- 						<li data-tooltip="QQ" class="qq" id="qqbt"> -->
<!-- 							<a href="http://openapi.qzone.qq.com/oauth/show?which=ConfirmPage&display=pc&client_id=101239300&response_type=token&scope=all&state=2&redirect_uri=http%3A%2F%2Fqzonestyle.gtimg.cn%2Fqzone%2Fopenapi%2Fredirect-1.0.1.html">QQ</a> -->
<!-- 						</li> -->
<!-- 					</ul> -->
				</form>
				
			</div><!--/ .three-fourth .last-->
		</div><!--/ .content-wrapper-->
<jsp:include page="include/footer.jsp"/>
