<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title> Home </title>
    
    <!-- 支持ajax -->
    <script type="text/javascript" src="../javascript/jquery-1.8.2.js"></script>
  </head>
  
  <body>
    <p>test jsp</p>
    <div id="display">
   		<a href="add.htm">新增</a></td>
		<table>
			<tr><td>序号</td><td>主键</td><td>登录名</td><td>等级</td><td>最后登录时间</td><td>操作</td></tr>
			<c:forEach var="user" items="${userlist }" varStatus="status">
			<tr <c:if test="${status.index%2!=0 }"> style="background-color: #E5DBE2;"</c:if>>
				<td><c:out value="${status.index+1 }" /></td>
				<td><c:out value="${user.userId }" /></td>
				<td><c:out value="${user.userName }" /></td>
				<td><c:out value="${user.level }" /></td>
				<td><c:out value="${user.lastloginTime }" /></td>
				<!--  <a href="javascript:void(0);" onclick='del(this,<c:out value="${user.userId}" />);' class="del">删除</a> -->
				<td><a href="delete/${user.userId}.htm" class="del">删除</a>
					<a href="update/${user.userId}.htm">修改</a>
					<a href="detail/${user.userId}.htm">详细</a>
			</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
