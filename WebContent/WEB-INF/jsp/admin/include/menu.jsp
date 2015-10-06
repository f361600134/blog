<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"admin/";
request.setAttribute("url", request.getScheme()+"://"+request.getHeader("host")+request.getContextPath());
request.setAttribute("suburl", request.getRequestURL().substring(basePath.length()));
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
  	<base href="<%=basePath%>">
  	<c:set var="webs" value="${init.webSite}" scope="session"></c:set>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
		
		
		<title>${webs.title} - 后台管理  ${stitle} </title>
		<link href="<%=basePath %>assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/font-awesome.min.css" />

		<link rel="stylesheet" href="<%=basePath %>assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/jquery.gritter.css" />
		
		
		<link rel="shortcut icon" href="${url}/favicon.ico" />
		<script src="<%=basePath %>assets/js/jquery-2.0.3.min.js"></script>
		<script src="<%=basePath %>assets/js/bootstrap.min.js"></script>
		<script src="<%=basePath %>assets/js/typeahead-bs2.min.js"></script>
		<script src="<%=basePath %>assets/js/ace-elements.min.js"></script>
		<script src="<%=basePath %>assets/js/ace.min.js"></script>
		<script src="<%=basePath %>assets/js/ace-extra.min.js"></script>
		<script src="<%=basePath %>assets/js/jquery.gritter.min.js"></script>
	</head>

<body>
		<div class="navbar navbar-default" id="navbar">

			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="avbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="${url}" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							${webs.title}
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="green">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="icon-envelope"></i>
								<span class="badge badge-success">${session.comments.records}</span>
							</a>
		
							<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
								<li class="dropdown-header">
									<i class="icon-envelope-alt"></i>
									${session.comments.records}条消息
								</li>
								<c:forEach items="${session.comments.rows}" var="comment">
								<li>
									<a href="#">
										<img alt="Alex's Avatar" class="msg-photo" src="<%=basePath %>/assets/avatars/avatar.png">
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">${comment.userName}</span>
												<%-- ${comment.userComment} --%>
											</span>
										</span>
									</a>
								</li>
								</c:forEach>
								<li>
									<a href="comment">
										查看所有消息
										<i class="icon-arrow-right"></i>
									</a>
								</li>
							</ul>
						</li>
						
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!-- <img class="nav-user-photo" src="<%=path %>/${user.icon}" alt="${user.nickName}" />-->
								<img class="nav-user-photo" src="images/header.png" alt="${user.nickName}" />
								<span class="user-info">
									<small>欢迎光临,</small>
									${user.nickName}
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="webSite">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>
								<li>
									<a href="set_duoshuo">
										<i class="icon-cog"></i>
										多说设置
									</a>
								</li>
								<li>
									<a href="set_yunstore">
										<i class="icon-cog"></i>
										云储存设置
									</a>
								</li>

								<li>
									<a href="user">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>
								
								<li>
									<a href="user_changepassword">
										<i class="icon-user"></i>
										更改密码
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="logout">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">

					<ul class="nav nav-list">
						<li <c:if test="${'index.jsp'==suburl}">class="active"</c:if>>
						
							<a href="index.htm">
								<i class="icon-dashboard"></i>
								<span class="menu-text"> 控制台 </span>
							</a>
						</li>

						<li <c:if test="${'edit.jsp'==suburl}">class="active"</c:if>>
							<a href="edit.htm">
								<i class="icon-edit"></i>
								<span class="menu-text"> 文章撰写 </span>
							</a>
						</li>

						<li <c:if test="${'log.jsp'==suburl}">class="active"</c:if>>
							<a href="log.htm">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 文章管理 </span>

							</a>
						</li>

						
						<li <c:if test="${'comment.jsp'==suburl}">class="active"</c:if>>
							<a href="contact.htm" class="dropdown-toggle">
								<i class="icon-comment"></i>
								<span class="menu-text"> 评论管理 </span>
							</a>
						</li>

						 

							

						<li <c:if test="${'webSite.jsp'==suburl or 'user.jsp'==suburl or 'template_center.jsp'==suburl}">class="active open"</c:if>>
							<a href="#" class="dropdown-toggle">
								<i class="icon-cogs"></i>
								<span class="menu-text"> 设置  </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">

								<li>
									<a href="user.htm" <c:if test="${'user.jsp'==suburl}">class="active"</c:if>>
										<i class="icon-double-angle-right"></i>
										<span class="menu-text">个人信息</span>
									</a>
								</li>
								<li>
									<a href="webSite.htm" <c:if test="${'webSite.jsp'==suburl}">class="active"</c:if>>
										<i class="icon-double-angle-right"></i>
										<span class="menu-text">网站设置</span>
									</a>
								</li>
								<li>
									<a href="template.htm" <c:if test="${'template.jsp'==suburl}">class="active"</c:if>>
										<i class="icon-double-angle-right"></i>
										<span class="menu-text">主题设置</span>
									</a>
								</li>
							</ul>
						</li>
						

						<li <c:if test="${'type.jsp'==suburl or 'tag.jsp'==suburl or  'plugin.jsp'==suburl or 'nav.jsp'==suburl or 'link.jsp'==suburl or 'plugin_center.jsp'==suburl}">class="active open"</c:if>>
							<a href="#" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text">
									其他
								</span>
							</a>

							<ul class="submenu">

								<li>
									<a href="category.htm" <c:if test="${'type.jsp'==suburl}">class="active"</c:if>>
										<i class="icon-double-angle-right"></i>
										<span class="menu-text">分类管理</span>
									</a>
								</li>
								<li>
									<a href="tag.htm" <c:if test="${'tag.jsp'==suburl}">class="active"</c:if>>
										<i class="icon-double-angle-right"></i>
										<span class="menu-text">查看标签</span>
									</a>
								</li>
								<li <c:if test="${'link.jsp'==suburl}">class="active"</c:if>>
							<a href="link.htm" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text"> 链接管理 </span>
							</a>
						</li>
						<li <c:if test="${'plugin.jsp'==suburl}">class="active"</c:if>>
							<a href="project.htm" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text"> 项目管理 </span>
								 
							</a>
						</li>
						<li <c:if test="${'plugin_center.jsp'==suburl}">class="active"</c:if>>
							<a href="plugin_center.htm" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text"> 插件中心 </span>
								 
							</a>
						</li>
						
						<li <c:if test="${'nav.jsp'==suburl}">class="active"</c:if>>
							<a href="nav.htm" class="dropdown-toggle">
								<i class="icon-double-angle-right"></i>
								<span class="menu-text"> 导航栏管理 </span>
							</a>
						</li>
							</ul>
						
						</li>
						 
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
				</div>
				<div class="main-content">
					<div class="page-content">