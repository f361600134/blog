<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<section id="comments">
<c:if test="${!empty contacts}">
	<h3>Comments</h3>
</c:if>
<ol class="comments-list">
	<!-- 循环 -->
	<c:forEach var="contact" items="${contacts}" varStatus="status">
	<li class="comment">

		<article>
			<img src="<%=path %>/images/gravatar.png" alt="Image" class="avatar">
			<div class="comment-meta">
				<h4 class="author"><a href="#">${contact.name }</a></h4>
				<div class="date">${contact.showDate }</div>
				<div class="reply">
				<a class="button-style-1 small" href="#respond" onclick="return addComment.moveForm(&quot;comment-3&quot;, &quot;3&quot;, &quot;respond&quot;, &quot;41&quot;,${contact.id})">Reply</a>
				</div>
			</div><!--/ .comment-meta -->
			<div class="comment-body">
				<p>
					<!-- Donec eget tellus non erat lacinia fermentum. Donec in velit vel ipsum auctor pulvinar. Vestibulum 
					iaculis lacinia est. Proin dictum elementum velit. Fusce euismod consequat ante. 
					 -->
					 ${contact.message }
				</p>
			</div><!--/ .comment-body -->
		</article>
		
		<c:forEach var="reply" items="${contact.conts}" varStatus="status">
		<ul class="children">
			<li class="comment">
				<article>
					<img src="<%=path %>/images/gravatar.png" alt="Image" class="avatar">
					<div class="comment-meta">
						<h4 class="author"><a href="#">${reply.name }</a></h4>
						<div class="date">${reply.showDate }</div>
						<div class="reply">
						<a class="button-style-1 small" href="#respond" onclick="return addComment.moveForm(&quot;comment-3&quot;, &quot;3&quot;, &quot;respond&quot;, &quot;41&quot;,${contact.id})">Reply</a>
						</div>
					</div><!--/ .comment-meta -->
					<div class="comment-body">
						<p>
						<!--  Lorem ipsum dolor sit amet, consectetuer adipis. Mauris accumsan nulla vel diam.
						 -->
						 ${reply.message }
						</p>
					</div><!--/ .comment-body -->
				</article>
			</li>
		</ul><!-- end .children -->
		</c:forEach>
	</li><!--/ .children-->
	</c:forEach>
</ol><!--/ .comments-list-->
</section><!--/ #comments-->
