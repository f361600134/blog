<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <head>
    <title>TinyEditor</title>
    <!-- 配置文件 --> 
    <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
  </head>
  
  <body> 
  	 <form id="from" name="from" method="post" action="ckeditor.htm">
    	title:<input type="text" name="title"/><br/>
    	content:<input type="text" name="category"/><br/>
    	<!-- <textarea name="content" id="content">这里写你的初始化内容~</textarea>-->
    	<textarea id="content" name="content">这里写你的初始化内容~</textarea>
    	<input value="点这儿才提交"  type="button" onclick="send()"/>
  		<script type="text/javascript">
			CKEDITOR.replace("content",{
				
			});
			function send(){
              var oEditor = CKEDITOR.instances.content;
              if(oEditor.getData().length==0){
              		//非空判断
              		alert("不能为空");
              }else{
              		document.getElementById("from").submit();
              }
            }
		</script>
    </form>
  </body>
</html>
