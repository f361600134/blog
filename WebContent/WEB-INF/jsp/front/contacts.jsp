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
						<span>The Company Name Inc.</span>
						8901 Marmora Road, <br />
						Glasgow, D04 89GR.
					</p>
					<ul class="contact-list">
						<li class="contact-phone">+132 4913 8641</li>
						<li class="contact-mail">
							<a href="mailto:361600134@qq.com">361600134@qq.com</a>
						</li>
						<li class="contact-skype">skype.name</li>
					</ul><!--/ .contact-list-->
				</div><!--/ .contact-block-->
			</div><!--/ .one-fourth-->
			<div class="three-fourth last">
				<h2 class="content-title">Drop Me a Line</h2>
				<p>要登录才能发表留言哦,精彩留言会显示在网站首页哦~</p>
				<form action="" class="contacts-form" id="contacts-form" method="post">
					<fieldset>
						<label class="input-block"><input type="text" name="name" value="" placeholder="Name (required)"></label>
						<label class="input-block"><input type="text" name="email" value="" placeholder="Email (required,private)"></label>
						<label class="input-block"><input type="text" name="website" value="" placeholder="Website(private)"></label>	
						<label class="message textarea-block"><textarea name="message" placeholder="Message (required)"></textarea></label>
						<br class="clear">
					</fieldset>
					<button type="submit" class="button-style-2 medium">Send Message</button>
					<!--<label style="margin-left:1px;font-family:Microsoft YaHei">请先登录,登陆在网站底部</label>
					 Login <div class="mybox"></div>-->
					 <!-- 临时登陆 -->
					<ul data-description="Log in:" class="social-links">
						<li data-tooltip="Facebook" class="facebook" id="FB" >
							<a href="javascript:void(0);">Facebook</a>
						</li>
						<li data-tooltip="QQ" class="qq" id="qqbt" >
							<a href="javascript:void(0);">QQ</a>
						</li>
						<li data-tooltip="Sina" class="sina">
							<a href="javascript:void(0);">Sina</a>
						</li>
					</ul><!--/ .social-links-->
				</form>
				
			</div><!--/ .three-fourth .last-->
		</div><!--/ .content-wrapper-->
<jsp:include page="include/footer.jsp"/>
