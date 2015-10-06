<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'updateOrAdd.jsp' starting page</title>
  </head>
  
  <body>
    <p>Update And Add</p>
    <!-- 这里form的action没写就是当前的路径 -->
    <sf:form method="post" modelAttribute="user">
    	username:<sf:input path="userName"/><sf:errors path="userName"/><br/>
    	password:<sf:password path="password"/><sf:errors path="password"/><br/>
    	level:<sf:input path="level"/><sf:errors path="level"/><br/>
    	<input type="submit" />
    </sf:form>
  </body>
</html>
