<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html>
<html>
<head>
	
<link rel="stylesheet" href="<%=path %>/css/blog/style.css" media="screen" />
<script type="text/javascript" src="<%=path %>/js/blog/jquery.min.js"></script>
<script  type="text/javascript" src="<%=path %>/js/blog/gt.js"></script>
<script  type="text/javascript" src="<%=path %>/js/blog/share.js"></script>
<style type="text/css">

</style>

</head>
<body>
<br><br><br><br><br><br><br><br><br><br>
<div id="captcha"></div>
</body>	
</html>




