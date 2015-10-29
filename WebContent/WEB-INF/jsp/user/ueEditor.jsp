<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Ueditor</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    
    <!-- 配置文件 --> 
    <script type="text/javascript" src="../ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
    <link rel=stylesheet href="../ueditor/themes/default/css/ueditor.css">
  	
  	<script type="text/javascript">
		function addTag(name){
			var old = document.getElementById("key").value;
			if(old.split(",").length-1 == 3){
				document.getElementById("error").innerText="标签不能超过三个";
				setTimeout("direction()",3000);
			}else{
				document.getElementById("key").value=old+name+",";
			alter(document.getElementById("key").value);
			}
		}
	    function direction()
	    {
	        document.getElementById("error").innerText="";
     	}
  	</script> 	
  </head>
  
  <body style="font-family: microsoft yahei"> 
  	 <form id="from" name="from" method="post" action="ueeditor.htm">
    	标题 : <input type="text" id="title" name="title"/>
    	分类 : <select name="cate" id="" class="">
		  <c:forEach items="${categories}" var="cate" varStatus="status">
		  	<option value="${cate.id}">
		  			${cate.name}
		  	</option>
		  </c:forEach>
		</select><p>
		
    	<!-- <textarea name="content" id="myEditor">这里写你的初始化内容~</textarea>-->
    	<div id="myEditor" name="content"></div><p>
    	<input type="hidden" id="mdContent" name="mdContent" value="">
		标签:
		<input value="" id= "key" name="key" maxlength="30" />
		<div id="error" style="font-size:0.5em;color:red;"></div>
		<hr/>
		<div class="tags" id="tag">
			<c:forEach items="${tags}" var="tag">
				<a class="tag" href="#" onclick="addTag('${tag.name}')">${tag.name}</a>
			</c:forEach>
		</div><p>
		
    	<input value="只有点这儿才会提交" type="button" onclick="send()"/>
    	
  		<script type="text/javascript">
  			UE.getEditor('myEditor');
  			function send() {
  				var data = UE.getEditor('myEditor').getContentTxt();
  				var title = document.getElementById("title").value;
  				if(data.length == 0 || title.length == 0){
  					alert("输入内容");
  				}else{
  					document.getElementById("mdContent").value=data;
  					document.getElementById("from").submit();
  				}
			}   
  		</script>
    </form>
  </body>
</html>







