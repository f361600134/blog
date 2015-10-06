<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="request" var="stitle" value="文章编辑"/>
<c:if test="${not empty session.log}">
	<c:set scope="request" var="log" value="${session.log}"></c:set>
</c:if>
<jsp:include page="include/menu.jsp"/>
<link rel="stylesheet" href="${url}/admin/editor/themes/default/default.css" />
<link rel="stylesheet" href="${url }/admin/makedown/css/editormd.css" />
<link rel="stylesheet" href="${url}/admin/assets/css/jquery.gritter.css" />

<script src="${url}/admin/makedown/js/editormd.js"></script>

<script src="${url}/admin/assets/js/jquery.gritter.min.js"></script>
<script src="${url}/admin/assets/js/bootbox.min.js"></script>
<script>
	var change=0;
	var uploadUrl='${url}/admin/log/uploads'; //makedown设置文件上传路径
	var mdEditor;
	$(function(){
		mdEditor = editormd("editormd", {
            width: "100%",
            height: 740,
            path : '${url}/admin/makedown/lib/',
            codeFold : true,
            appendMarkdown :$("#markdown").val(),
            saveHTMLToTextarea : true,
            searchReplace : true,
            htmlDecode : "style,script,iframe,pre",
            emoji : true,
            taskList : true,
            tocm            : true,         // Using [TOCM]
            tex : true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart : true,             // 开启流程图支持，默认关闭
            sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
            dialogMaskOpacity : 0,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
            dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : uploadUrl,
            onchange : function() {
            	change=1;
            }
        });
        $(".editormd-html-textarea").attr("name","content");
        $(".editormd-html-textarea").attr("id","content");
        $(".editormd-markdown-textarea").attr("name","mdContent");
        
        function validationPost(){
			if($("#title").val()=="" || $("#content").val()=="")
			{
				$.gritter.add({
					title: '文章的标题和内容都不能为空...',
					class_name: 'gritter-error' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : ''),
				});
				return false;
			}
			return true;
		}
        
		/*function autoSave(){
			if(change && validationPost()){
				$.post('log/add.htm?scope=session',$('#addorPre').serialize(),function(data){
					if(data.add){
						var date=new Date();
						$.gritter.add({
							title: "自动保存成功 "+date.getHours()+":"+date.getMinutes() +" "+date.getSeconds(),
							class_name: 'gritter-success' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : '')
						});
					}
					change=0;
				});
			}
			
		}
		setInterval(autoSave,1000*6); */
		
		var $tags=$("#inp");
		$(".tag").click(function(e){
			$tags.val($tags.val()+$(this).text()+",");
			$(this).remove();
			e.preventDefault();
		});
		$("#translate").click(function(){
			$.post('${url}/post/api/translate',{"key":$("#title").val()},function(data){
				var d=$.parseJSON(data);
				if($("#result").length==0){
					$("#translate").after("<input name='alias' id='result' value='"+d.translate+"'>");
				}
				else{
					$("#result").attr("value",d.translate);
				}
			});
		});
		$("#saveToRubbish").click(function(){
			if(validationPost()){
				$.post('../edit/add.htm?rubbish=1',$('#addorPre').serialize(),function(data){
					if(data.add){
						$.gritter.add({
							title: '保存成功...',
							class_name: 'gritter-info' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : ''),
						});
					}
				});
			}
		});
		
		
		$("#add").click(function(){
			if(validationPost()){
				$.post('../edit/add.htm',$('#addorPre').serialize(),function(data){
					if(data.add){
						$.gritter.add({
							title: '添加成功...',
							class_name: 'gritter-info' + (!$('#gritter-light').get(0).checked ? ' gritter-light' : ''),
						});
					}
				});
			}
		});
		
		$("#preview").click(function(){
			if(validationPost()){
				document.getElementById("addorPre").submit();
			}
		});
		
		$("#reset").click(function(){
			bootbox.setDefaults({locale:"zh_CN"});
			bootbox.alert("这将会清空你输入的", function() {
				$('#title').val("");
				$('#tag').val("");
				mdEditor.markdown="";
			});
		});
		
	}); 
</script>
		<div class="page-header">
			<h1>
				文章
				<small>
					<i class="icon-double-angle-right"></i>
					撰写文章
				</small>
			</h1>
		</div><!-- /.page-header -->
	
	<form target="_blank" class="form-horizontal" role="form" id="addorPre" method="post" action="${url }/admin/log/preview">
		    <textarea id="markdown" style="display: none;">${log.mdContent}</textarea>
		    <input name="title" id="title" size="60" maxlength="60"  value="${log.title}" class="col-xs-8" type="text" placeholder="请输入文章标题"></input>
		    <div class="col-xs-3">
		    <select name="typeId" id="form-field-select-6" class="form-control">
			  <c:forEach items="${init.types}" var="type">
			    <option <c:if test="${type.id eq log.typeId}">selected="selected"</c:if> value="${type.id}">${type.typeName}</option>
			  </c:forEach>
			</select>
			</div>
			<div class="col-sm-12">
										<div class="tabbable" style="padding: 0;">
											<ul id="myTab4" class="nav nav-tabs padding-13 tab-color-blue background-blue">
												

												<li class="active">
													<a href="#editormd" data-toggle="tab">editormd</a>
												</li>
												<!-- <li class="">
													<a href="#kindeditor" data-toggle="tab">kindeditor</a>
												</li> -->
 
											</ul>

											<div class="tab-content">
												<div class="tab-pane active" id="editormd">
													<div id="editormd"></div>
												</div>
												<div class="tab-pane " id="kindeditor">
<%-- 													<textarea
						name="content" cols="100" rows="8"  id="content"
						style="width:100%; height:740px; visibility:hidden; z-index: 9999;">${log.content}</textarea>
												</div> --%>

												

											</div>
										</div>
									</div>
			
			 

	<hr/>

	<!-- 标签 -->
	<input value="${log.keywords}" name="keywords" id="inp" size="60" maxlength="60" /><hr/>
			<div class="tags" id="tag" style="width: 100%">
			<c:forEach items="${init.tags}" var="tags">
				<span class="tag">${tags.text}</span>
			</c:forEach>
			</div>
			<hr/>
	<div class="col-xs-4">
		<label>
			<input class="ace ace-switch ace-switch-6" type="checkbox" checked="checked" name="canComment">
			<span class="lbl">&nbsp;能发布评论吗</span>
		</label>
		
	</div>
	<div class="col-xs-4">
		<label>
			<input class="ace ace-switch ace-switch-6" type="checkbox"  name="recommended">
			<span class="lbl">&nbsp;推荐吗</span>
		</label>
	</div>
	<div class="col-xs-4">
		<label>
			<input class="ace ace-switch ace-switch-6" type="checkbox"  name="private">
			<span class="lbl">&nbsp;不公开</span>
		</label>
	</div>
	<br/>
	<hr/>
					<div  class="widget-box collapsed">
			<div data-action="collapse" class="widget-header widget-header-small  header-color-blue">
					 
				<div  class="widget-toolbar inline">
					<a href="#"> <i class="icon-chevron-down"></i>
					</a>
				</div>
			</div>
			
			<div class="widget-body">
				<div class="widget-body-inner" style="display: none;">
						<textarea name="digest" cols="100" rows="8"
			style="width:100%; height:180x; z-index: 9999;">${log.digest}</textarea>

				</div>
			</div>
		</div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" id="saveToRubbish" type="button">
								<i class="icon-ok bigger-110"></i>
								存为草稿
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn btn-info" id="add" type="button">
								<i class="icon-ok bigger-110"></i>
								提        交
							</button>
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="button" id="reset">
								<i class="icon-undo bigger-110"></i>
								重置
							</button>
							
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="button" id="preview">
								<i class="icon-undo bigger-110"></i>
								预览
							</button>
						</div>
					</div>
	</form>

<jsp:include page="include/footer.jsp"/>