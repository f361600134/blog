<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/menu.jsp"/>
<script type="text/javascript" src="js/set_update.js"></script>
<link rel="stylesheet" href="${url}/admin/editor/themes/default/default.css" />
<script src="${url}/admin/editor/kindeditor-min.js"></script>
		
<script type="text/javascript" >
	
KindEditor.ready(function(K) {
	var uploadbutton = K.uploadbutton({
		button : K('#uploadButton')[0],
		fieldName : 'imgFile',
		url : '${url}/admin/log/upload',
		afterUpload : function(data) {
			if (data.error === 0) {
				var url = K.formatUrl(data.url, 'absolute');
				var dialog = K.dialog({
					width : 500,
					title : '上传成功',
					body : '<div style="margin:10px;"><strong>文件上传成功</strong></div>',
					closeBtn : {
						name : '关闭',
						click : function(e) {
						}
					},
					yesBtn : {
						name : '确定',
						click : function(e) {
							dialog.remove();
							K('#logo').val(url);
						}
					},
					noBtn : {
						name : '取消',
						click : function(e) {
							dialog.remove();
						}
					}
				});
				
			} else {
				
			}
		},
		afterError : function(str) {
			alert('自定义错误信息: ' + str);
		}
	});
	uploadbutton.fileBox.change(function(e) {
		uploadbutton.submit();
	});
});

</script>

						<div class="page-header">
							<h1>
								网站信息设置
								<small>
									<i class="icon-double-angle-right"></i>
									基本信息设置
								</small>
							</h1>
						</div><!-- /.page-header -->

						
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form role="form" class="form-horizontal" id="ajaxbaseMsg">
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站标题 </label>

										<div class="col-sm-9">
											<input type="text" name="title" value="${webs.title }" class="col-xs-10 col-sm-5" placeholder="请求输入网站标题 " id="form-field-1">
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站副标题 </label>

										<div class="col-sm-9">
											<input type="text" name="second_title" value="${webs.second_title }" class="col-xs-10 col-sm-5" placeholder="请求输入网站副标题 " id="form-field-1">
											
										</div>
									</div>
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站关键词 </label>

										<div class="col-sm-9">
											<input type="text" name="keywords" value="${webs.keywords}" class="col-xs-10 col-sm-5" placeholder="请求输入网站关键词 " id="form-field-1">
										</div>
									</div>
									
									<div class="space-4"></div>

									<div class="form-group">
										<label for="form-field-2" class="col-sm-3 control-label no-padding-right"> 网站描述 </label>

										<div class="col-sm-9">
											<input type="text" name="description" value="${webs.description}"  class="col-xs-10 col-sm-5" placeholder="存放目录" id="form-field-2">
											<span class="help-inline col-xs-12 col-sm-7">
												<span class="middle"></span>
											</span>
										</div>
									</div>
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站&nbsp;Logo </label>

										<div class="col-sm-9">
											<div class="upload">
												<input type="text" id="logo" value="${webs.logo }" class="col-xs-10 col-sm-5" name="logo" readonly="readonly" />&nbsp;&nbsp; <input type="button"  id="uploadButton" value="上传" />
											</div>
										</div>
									</div>
									
									<div class="space-4"></div>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button id="baseMsg" type="button" class="btn btn-info">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>

											&nbsp; &nbsp; &nbsp;
											<button type="reset" class="btn">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>

								</form>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						
			
					
					<div class="page-header">
							<h1>
								网站信息设置
								<small>
									<i class="icon-double-angle-right"></i>
									其他信息设置
								</small>
							</h1>
						</div><!-- /.page-header -->

						
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form role="form" class="form-horizontal" id="ajaxotherMsg">
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> ICP备案信息 </label>

										<div class="col-sm-9">
											<textarea name="icp" class="col-xs-10 col-sm-5" cols="30" rows="3" id="form-field-1">${webs.icp}</textarea>
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站统计 </label>

										<div class="col-sm-9">
											<textarea name="webCm" class="col-xs-10 col-sm-5" cols="30" rows="3" id="form-field-1">${webs.webCm}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 网站模板 </label>

										<div class="col-sm-9">
											<select name="template" class="col-xs-10 col-sm-5" id="form-field-1">
											<%
												// need JRE1.7
												/* String webPath=request.getServletContext().getRealPath("/");
												File[] templates=new File(request.getServletContext().getRealPath("/include/templates/")).listFiles(); */
												String webPath=request.getRealPath("/");
												File[] templates=new File(request.getRealPath("/include/templates/")).listFiles();
												
												String strFile[]=new String[templates.length];
												for(int i=0;i<templates.length;i++){
													strFile[i]=templates[i].toString().substring(webPath.length()-1).replace("\\", "/");
												}
												request.setAttribute("templates", strFile);
												%>
												<%												
											%>
											<c:forEach var="template" items="${templates}">
												<option  value="<c:out value="${template }"/>" <c:if test="${webs.template eq  template}">selected="selected"</c:if>  ><c:out value="${template }" /></option>
											</c:forEach>
											</select>
										</div>
									</div>
									
									 
									<div class="space-4"></div>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button id="otherMsg" type="button" class="btn btn-info">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>

											&nbsp; &nbsp; &nbsp;
											<button type="reset" class="btn">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>

								</form>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
					<div class="page-header">
							<h1>
								网站信息设置
								<small>
									<i class="icon-double-angle-right"></i>
									邮件服务
								</small>
							</h1>
						</div><!-- /.page-header -->
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<form role="form" class="form-horizontal" id="ajaxemailServiceMsg">
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 登陆名 </label>

										<div class="col-sm-9">
											<input type="text" name="mail_from" value= "${webs.mail_from}" class="col-xs-10 col-sm-5" id="form-field-1">
										</div>
									</div>
									
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> SMTP服务器 </label>

										<div class="col-sm-9">
											<input type="text" name="mail_smtpServer" value="${webs.mail_smtpServer}" class="col-xs-10 col-sm-5" id="form-field-1">
										</div>
									</div>
									<div class="form-group">
										<label for="form-field-1" class="col-sm-3 control-label no-padding-right"> 密码  </label>

										<div class="col-sm-9">
											<input type="password" name="mail_password"  value="${webs.mail_password }" class="col-xs-10 col-sm-5" id="form-field-1">
										</div>
									</div>
									
									 
									<div class="space-4"></div>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button id="emailServiceMsg" type="button" class="btn btn-info">
												<i class="icon-ok bigger-110"></i>
												提交
											</button>

											&nbsp; &nbsp; &nbsp;
											<button type="reset" class="btn">
												<i class="icon-undo bigger-110"></i>
												重置
											</button>
										</div>
									</div>

								</form>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
<jsp:include page="include/footer.jsp"/>