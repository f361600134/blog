<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>

   <!-- 配置文件 --> 
    <script type="text/javascript" src="../ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="../ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
	<link rel=stylesheet href="../ueditor/themes/default/css/ueditor.css">

  </head>

  <body>
    <script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
    <script>
      //实例化编辑器
      var o_ueditorupload = UE.getEditor('j_ueditorupload',
      {
        autoHeightEnabled:false
      });
      o_ueditorupload.ready(function ()
      {
    	o_ueditorupload.hide();//隐藏编辑器
    	//监听图片上传
    	o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
    	{
//          alert('这是图片地址：'+arg[0].src);
			var urls = "";
			alert(arg.length);
			for(var i=0;i<arg.length;i++){
				urls = urls+arg[i].src+",";
			}
          	alert("这是图片总地址:"+urls);
    	});
	    /* 文件上传监听
	     * 需要在ueditor.all.min.js文件中找到
	     * d.execCommand("insertHtml",l)
	     * 之后插入d.fireEvent('afterUpfile',b)
	     */
        o_ueditorupload.addListener('afterUpfile', function (t, arg)
        {
        	//这里方法根本没有执行
        	alert(1);
          alert('这是文件地址：'+arg[0].url);
        });
      });

      //弹出图片上传的对话框
      function upImage()
      {
        var myImage = o_ueditorupload.getDialog("insertimage");
        myImage.open();
      }
      //弹出文件上传的对话框
      function upFiles()
      {
        var myFiles = o_ueditorupload.getDialog("attachment");
        myFiles.open();
      } 
    </script>
    <button type="button" onClick="upImage()">调用上传图片模块</button>
    <br>
    <button type="button" onClick="upFiles()">调用上传文件模块</button>
  </body>
<html>