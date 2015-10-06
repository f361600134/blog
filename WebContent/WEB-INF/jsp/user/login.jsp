<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>     
    <title>My JSP 'login.jsp' starting page</title> 
  </head>
  
  <body>
   <!-- 这里form的action没写就是当前的路径 -->
    <form method="post" action="login.htm">
    	${errorMsg }
    	username:<input type="text" name="userName"/><br/>
    	password:<input type="password" name="password"/><br/>
    	<input type="submit" value="Login"/> 
    </form>
  </body>
</html>
