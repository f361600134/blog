﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE html>
<html>
<head>
    <title>Angel_000你是个大二货</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    
    <link rel="stylesheet" href="<%=path %>/css/love/default.css" type="text/css"></link>
	<script type="text/javascript" src="<%=path %>/js/love/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex-parser.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex-jit.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex-builderbase.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex-async.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/jscex-async-powerpack.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/love/functions.js" charset="utf-8"></script>
	<script type="text/javascript" src="<%=path %>/js/love/love.js" charset="utf-8"></script>
	<script type="text/javascript"> 
	window.onload=function(){
	document.onselectstart =function() {return   false;}     
	document.ondragstart   =function() {return   false;}      
	document.oncontextmenu =function() {return   false;}        
	new AI.TGame();
	}
	</script>
	
	<style type="text/css">
	<!--
	.STYLE1 {color: #666666}
	-->
	</style> 

</head>

 <body>
		<audio autoplay="autopaly" loop="loop">
				<source src="<%=path %>/music/renxi.mp3" type="audio/mp3" />
		</audio>  
        <div id="main">
            <div id="error">本页面采用HTML5编辑，目前您的浏览器无法显示，请换成谷歌(<a href="http://www.google.cn/chrome/intl/zh-CN/landing_chrome.html?hl=zh-CN&brand=CHMI">Chrome</a>)或者火狐(<a href="http://firefox.com.cn/download/">Firefox</a>)浏览器，或者其他游览器的最新版本。</div>
             <div id="wrap">
                <div id="text">
			        <div id="code">
			      <font color="#fe6673">  	
			      		<span class="say"> 二货,作为一个闷骚的程序猿</span><br>
			      		<span class="say"> 不懂情调,不会说一些动人催泪的话 </span><br>
						<span class="say"> </span><br>
						<span class="say"> </span><br>
						<span class="say"> 这些年,我一直认为: </span><br>
			        	<span class="say">要努力做好现在该做的事</span><br>
			        	<span class="say"> </span><br>
			        	<span class="say">爱情就会来临的，是礼物的降临。</span><br>
			        	<span class="say">有些人需要练习恋爱</span><br>
					    <span class="say">有些人却只需等待金风玉露一相逢。</span><br>
						<span class="say">做好自己的事情，把自己变得优秀,爱情终会如期而至。</span><br>
 						<span class="say">我还不够优秀, 所以我一定会加油的!</span><br>
 						<span class="say"> </span><br>
						<span class="say">夏酷暑，冬严寒，春也不死吾心，心所向，将所成。</span><br>
						<span class="say"></span><br>
                        <span class="say"><span class="space"></span> --by Jeremy Feng</span>
                        <span class="say"><span class="space"></span>  2015.12.23日</span><br>
                        <span class="say"></span><br>
                        <span class="say">以前不知道电影&lt;one day&gt;中的那句, </span><br>
                        <span class="say">我爱你, 但是不会再喜欢你了! </span><br>
                        <span class="say">现在终于知道, 这句话饱含的这种感情: </span><br>
                        <span class="say">我会回想我们的过去, 但是我不会再去想我们的将来... </span><br>
			  </font></p>
      </div>
 </div>
                <div id="clock-box">
                <span class="STYLE1"></span><font color="#fe6673"> 我喜欢你, </font>
<span class="STYLE1">止于</span><br><br>
                  <!-- <div id="clock"></div> -->
                  第 <span class="digit">170</span> 天 
                  <span class="digit">00</span> 小时 
                  <span class="digit">19</span> 分钟 
                  <span class="digit">11</span> 秒
              </div>
                <canvas id="canvas" width="1100" height="680"></canvas>
            </div>
            
        </div>
     
    <script>
    (function(){
        var canvas = $('#canvas');
		
        if (!canvas[0].getContext) {
            $("#error").show();
            return false;
        }

        var width = canvas.width();
        var height = canvas.height();
        
        canvas.attr("width", width);
        canvas.attr("height", height);

        var opts = {
            seed: {
                x: width / 2 - 20,
                color: "rgb(190, 26, 37)",
                scale: 2
            },
            branch: [
                [535, 680, 570, 250, 500, 200, 30, 100, [
                    [540, 500, 455, 417, 340, 400, 13, 100, [
                        [450, 435, 434, 430, 394, 395, 2, 40]
                    ]],
                    [550, 445, 600, 356, 680, 345, 12, 100, [
                        [578, 400, 648, 409, 661, 426, 3, 80]
                    ]],
                    [539, 281, 537, 248, 534, 217, 3, 40],
                    [546, 397, 413, 247, 328, 244, 9, 80, [
                        [427, 286, 383, 253, 371, 205, 2, 40],
                        [498, 345, 435, 315, 395, 330, 4, 60]
                    ]],
                    [546, 357, 608, 252, 678, 221, 6, 100, [
                        [590, 293, 646, 277, 648, 271, 2, 80]
                    ]]
                ]] 
            ],
            bloom: {
                num: 700,
                width: 1080,
                height: 650,
            },
            footer: {
                width: 1200,
                height: 5,
                speed: 10,
            }
        }

        var tree = new Tree(canvas[0], width, height, opts);
        var seed = tree.seed;
        var foot = tree.footer;
        var hold = 1;

        canvas.click(function(e) {
            var offset = canvas.offset(), x, y;
            x = e.pageX - offset.left;
            y = e.pageY - offset.top;
            if (seed.hover(x, y)) {
                hold = 0; 
                canvas.unbind("click");
                canvas.unbind("mousemove");
                canvas.removeClass('hand');
            }
        }).mousemove(function(e){
            var offset = canvas.offset(), x, y;
            x = e.pageX - offset.left;
            y = e.pageY - offset.top;
            canvas.toggleClass('hand', seed.hover(x, y));
        });

        var seedAnimate = eval(Jscex.compile("async", function () {
            seed.draw();
            while (hold) {
                $await(Jscex.Async.sleep(10));
            }
            while (seed.canScale()) {
                seed.scale(0.95);
                $await(Jscex.Async.sleep(10));
            }
            while (seed.canMove()) {
                seed.move(0, 2);
                foot.draw();
                $await(Jscex.Async.sleep(10));
            }
        }));

        var growAnimate = eval(Jscex.compile("async", function () {
            do {
    	        tree.grow();
                $await(Jscex.Async.sleep(10));
            } while (tree.canGrow());
        }));

        var flowAnimate = eval(Jscex.compile("async", function () {
            do {
    	        tree.flower(2);
                $await(Jscex.Async.sleep(10));
            } while (tree.canFlower());
        }));

        var moveAnimate = eval(Jscex.compile("async", function () {
            tree.snapshot("p1", 240, 0, 610, 680);
            while (tree.move("p1", 500, 0)) {
                foot.draw();
                $await(Jscex.Async.sleep(10));
            }
            foot.draw();
            tree.snapshot("p2", 500, 0, 610, 680);

            // 会有闪烁不得意这样做, (＞﹏＜)
            canvas.parent().css("background", "url(" + tree.toDataURL('image/png') + ")");
            canvas.css("background", "#ffe");
            $await(Jscex.Async.sleep(300));
            canvas.css("background", "none");
        }));

        var jumpAnimate = eval(Jscex.compile("async", function () {
            var ctx = tree.ctx;
            while (true) {
                tree.ctx.clearRect(0, 0, width, height);
                tree.jump();
                foot.draw();
                $await(Jscex.Async.sleep(25));
            }
        }));

        var textAnimate = eval(Jscex.compile("async", function () {
		    var together = new Date();
		    together.setFullYear(2016,1,17); 			//时间年月日,month:0~11 表示 1~12月份
		    together.setHours(0);						//小时	
		    together.setMinutes(0);					//分钟
		    together.setSeconds(0);					//秒前一位
		    together.setMilliseconds(2);				//秒第二位

		    $("#code").show().typewriter();
            $("#clock-box").fadeIn(500);
            while (true) {
                timeElapse(together);
                $await(Jscex.Async.sleep(1000));
            }
        }));

        var runAsync = eval(Jscex.compile("async", function () {
            $await(seedAnimate());
            $await(growAnimate());
            $await(flowAnimate());
            $await(moveAnimate());

            textAnimate().start();

            $await(jumpAnimate());
        }));

        runAsync().start();
    })();
    </script>
    </body></html>
