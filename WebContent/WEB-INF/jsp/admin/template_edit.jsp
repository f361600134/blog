<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="include/menu.jsp" />

<link rel="stylesheet" href="${url}/admin/CodeMirror/lib/codemirror.css">
<link rel="stylesheet" href="${url}/admin/CodeMirror/addon/dialog/dialog.css">
<link rel="stylesheet" href="${url}/admin/CodeMirror/addon/search/matchesonscrollbar.css">
<script src="${url}/admin/CodeMirror/lib/codemirror.js"></script>
<script src="${url}/admin/CodeMirror/mode/xml/xml.js"></script>
<script src="${url}/admin/CodeMirror/addon/dialog/dialog.js"></script>
<script src="${url}/admin/CodeMirror/addon/search/searchcursor.js"></script>
<script src="${url}/admin/CodeMirror/addon/search/search.js"></script>
<script src="${url}/admin/CodeMirror/addon/scroll/annotatescrollbar.js"></script>
<script src="${url}/admin/CodeMirror/addon/search/matchesonscrollbar.js"></script>
<script src="${url}/admin/CodeMirror/addon/mode/loadmode.js"></script>
<script src="${url}/admin/CodeMirror/mode/meta.js"></script>
<style type="text/css">
.CodeMirror {
	border-top: 1px solid black;
	border-bottom: 1px solid black;
}
dt {
	font-family: monospace;
	color: #666;
}
</style>

<script type="text/javascript">
	$(function(){
		var editor;
		
		loadFile($("#form-field-select-6").children('option:selected').val());
		$("#form-field-select-6").change(function(){
			loadFile($(this).children('option:selected').val()); 
		});
		function loadFile(file){
			$.get("${url}/admin/template/loadFile?file="+file,function(data){
				$("div").remove(".CodeMirror");
				CodeMirror.modeURL = "${url}/admin/CodeMirror/mode/%N/%N.js";
				$("#code").text(data.fileContent);
				editor = CodeMirror.fromTextArea(document.getElementById("code"), {
					  lineNumbers: true
				});
				 var val = file;
				  if (m = /.+\.([^.]+)$/.exec(val)) {
				    var info = CodeMirror.findModeByExtension(m[1]);
				    if (info) {
				      mode = info.mode;
				      spec = info.mime;
				    }
				  } else if (/\//.test(val)) {
				    var info = CodeMirror.findModeByMIME(val);
				    if (info) {
				      mode = info.mode;
				      spec = val;
				    }
				  } else {
				    mode = spec = val;
				  }
				  if (mode) {
				    editor.setOption("mode", spec);
				    CodeMirror.autoLoadMode(editor, mode);
				  } else {
				    alert("Could not find a mode corresponding to " + val);
				  }
			});
		}
	});
</script>
		<div class="page-header">
			<h1>
				主题
				<small>
					<i class="icon-double-angle-right"></i>
					主题编辑
				</small>
			</h1>
		</div><!-- /.page-header -->
		<form>
			 <select name="file"  id="form-field-select-6" class="form-control">
				<%!
					private List<String> fileList=new ArrayList<String>();
					 private void getFilesByPrefix(String path,String... prefix){
				        File file[]=new File(path).listFiles();
				        for(File f:file)
				        {
				            if(f.isDirectory() && new File(f.getAbsolutePath()).listFiles()!=null){
				                getFilesByPrefix(f.getAbsolutePath(), prefix);
				            }
				            else{
				            	for(String pre:prefix){
					                if(f.getAbsoluteFile().toString().endsWith(pre)){
					                    fileList.add(f.getAbsoluteFile().toString());
					                }
				            	}
				            }
				        }
				    }
				%>
				<%
					/**
				     * 根据文件后缀 查找符合要求文件列表
				     * @param path
				     * @param prefix
				     */
				     getFilesByPrefix(request.getRealPath(request.getParameter("path")),".jsp",".js",".css",".html");
					// need JRE1.7
					/* String webPath=request.getServletContext().getRealPath("/");
					File[] templates=new File(request.getServletContext().getRealPath("/include/templates/")).listFiles(); */
					String webPath=request.getRealPath("/");
					
					String strFile[]=new String[fileList.size()];
					for(int i=0;i<fileList.size();i++){
						strFile[i]=fileList.get(i).toString().substring(webPath.length()-1).replace("\\", "/");
					}
					request.setAttribute("templates", strFile);
					%>
				<c:forEach var="template" items="${templates}">
					<option  value="<c:out value="${template }"/>" <c:if test="${webs.template eq  template}">selected="selected"</c:if>  ><c:out value="${template }" /></option>
				</c:forEach>
				</select><br>
		<textarea id="code" name="code">
		${fileContent}
		</textarea></form>
<jsp:include page="include/footer.jsp" />
