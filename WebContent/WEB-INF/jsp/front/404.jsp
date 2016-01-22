<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<jsp:include page="include/header.jsp"/>

		<div class="content-wrapper clearfix">
			
			<div class="error404">
				<h1>404</h1>
				<div class="title-error">Page Not Found!</div>
				<div class="description-error">Sorry, it appears the page you were looking for doesn't exist anymore or might have been moved.</div>
			</div><!--/ .error404-->
						
		</div><!--/ .content-wrapper-->
		
<jsp:include page="include/footer.jsp"/>
