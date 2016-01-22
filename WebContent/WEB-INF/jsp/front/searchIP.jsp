<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<script type="text/javascript" src="<%=path %>/js/blog/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
	    $('#lookup').click(function(){
	    	$.post(
                "searchIP.htm?ip="+$('#ip').val(),
                function(data){
					$('#ip').val("");
	   				$('#resText').html(data);
                }
             );
	    });
	});
	
// 	$(function(){
// 	    $('#testAjax').click(function(){
// 	    	$.get(
//                 "testAjax.htm",
//                 function(data){
// 	   				$('#testAjaxRes').html(data);
//                 }
//              );
// 	    });
// 	});
</script>

<jsp:include page="include/header.jsp"/>

	<div class="content-wrapper sbr clearfix">
		<div class="one-fourth">
			<h2 class="content-title noborder">Local Address</h2>
			<div class="contact-block">
				<p>
					<span>Local IP: ${visitor.ip}</span>
					Device: ${visitor.device}<br/>
					Browser: ${visitor.browser}<br/>
					GEO Address:<br/>${visitor.address}<br/><br/>
					Ali Address:<br/> ${visitor.dizhi}<br/>
				</p>
			</div><!--/ .contact-block-->
		</div><!--/ .one-fourth-->
		
		<div class="three-fourth last">
			<h2 class="content-title">Input Your IP Address</h2>
			<form action="" class="contacts-form" id="contacts-form" method="post">
				<label class="input-block"><input type="text" id="ip" name="ip" value="" placeholder="Host/IP" required>
				</label><br class="clear">
				<button type="button" name="lookup" id="lookup" class="button-style-2 medium">Lookup</button>
			</form>
			<div id="resText"></div>
		</div>
	</div><!--/ .content-wrapper-->
		
<jsp:include page="include/footer.jsp"/>
