/**
 * 都是第三方工具
 */

var handler = function (captchaObj) {
     // 将验证码加到id为captcha的元素里
     captchaObj.appendTo("#captcha");
 };
$.ajax({
    // 获取id，challenge，success（是否启用failback）
    url: "startCaptcha.htm",
    type: "get",
    dataType: "json", // 使用jsonp格式
    success: function (data) {
        // 使用initGeetest接口
        // 参数1：配置参数，与创建Geetest实例时接受的参数一致
        // 参数2：回调，回调的第一个参数验证码对象，之后可以使用它做appendTo之类的事件
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            product: "float", // 产品形式
            offline: !data.success
        }, handler);
    }
});



/*Facebook Login*/
window.fbAsyncInit = function() {
    FB.init({
      appId      : '934182183306291',
      xfbml      : true,
      version    : 'v2.4'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
   
   function fb_login(){
	    FB.login(function(response) {
	        if (response.authResponse) {
	            //console.log(response); // dump complete info
	            access_token = response.authResponse.accessToken; //get access token
	            user_id = response.authResponse.userID; //get FB UID
	
	            FB.api('/me', function(response) {
	                user_email = response.email; //get user email
	          // you can store this data into your database             
	            });
	
	        } else {
	            //user hit cancel button
	            console.log('User cancelled login or did not fully authorize.');
	
	        }
	    }, {
	        scope: 'publish_stream,email'
	    });
	}
		   
/*Sina Login
WB2.anyWhere(function(W){
	    W.widget.connectButton({
	        id: "wb_connect_btn",	
        type:"3,2",
        callback : {
            login:function(o){	//登录后的回调函数
            	$.ajax({
					   type: "POST",
					   url: "/user/Sinalogin.htm",
					   dataType:"json",
					   data: {sinaName:o.screen_name,url:o.avatar_large},
					   ////success: function(msg){
					   //// alert( "Data Saved: " + msg );
					   ////}
				});
            },	
            logout:function(){	//退出后的回调函数
            }
        }
    });
});*/

/*QQ Login
QC.Login({
		btnId:"qqbt"	//插入按钮的节点ids
}, function(dt, opts){
	//登录成功
	//QC.Login.fillUserInfo(opts['btnId'], dt);
	QC.api("get_user_info")
	.complete(function(c){//完成请求回调
			$.ajax({
			   type: "POST",
			   url: "/user/QQlogin.htm",
			   data: "QQinfo="+c.stringifyData(),
			  ////success: function(msg){
			   //// alert( "Data Saved: " + msg );
			 //// }
			   
			});
		});
});*/
					
/* log in */
/*** * @returns*/
//function loginQQ()
//{
  //以下为按钮点击事件的逻辑。注意这里要重新打开窗口
  //否则后面跳转到QQ登录，授权页面时会直接缩小当前浏览器的窗口，而不是打开新窗口
  //var A=window.open("oauth/index.php","TencentLogin", 
  //"width=450,height=320,menubar=0,scrollbars=1,resizable=1,status=1,titlebar=0,toolbar=0,location=1");
//	window.location.href="oauth/index.php","TencentLogin", "width=450,height=320,menubar=0,scrollbars=1,resizable=1,status=1,titlebar=0,toolbar=0,location=1";
//} 

 
function post(url, params) {        
    var temp = document.createElement("form");        
    temp.action = url;        
    temp.method = "post";        
    temp.style.display = "none";        
    for (var x in params) {        
        var opt = document.createElement("textarea");        
        opt.name = x;        
        opt.value = params[x];        
        // alert(opt.name)        
        temp.appendChild(opt);        
    }        
    document.body.appendChild(temp);        
    temp.submit();        
    return temp;        
}    

var jsPost = function(action, values) {
    var id = Math.random();
    document.write('<form id="post' + id + '" name="post'+ id +'" action="' + action + '" method="post">');
    for (var key in values) {
    	alert(key+","+values[key]['nickname']);
        document.write('<input type="hidden" name="' + key + '" value=\'' + values[key] + '\' />');
        alert('<input type="hidden" name="' + key + '" value=\'' + values[key] + '\' />');
    }
    document.write('</form>');    
    document.getElementById('post' + id).submit();
}

 
/*share*/
function shareSina(title,rLink){
     window.open('http://service.weibo.com/share/share.php?title='+encodeURIComponent(title)+'&url='+encodeURIComponent(rLink))        
}

function shareQzone(title,rLink){
    window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?title='+encodeURIComponent(title)+'&url='+encodeURIComponent(rLink))
}


function shareFacebook(link){
     window.open('https://www.facebook.com/sharer/sharer.php?u='+encodeURIComponent(link))        
}

function shareTwitter(link){
    window.open('http://twitter.com/home?status='+encodeURIComponent(link))        
}

function share2wx(link){
     window.open('http://service.weibo.com/share/share.php?title='+encodeURIComponent(title)+'&url='+encodeURIComponent(rLink)+'&appkey='+encodeURIComponent(site)+'&pic='+encodeURIComponent(pic),'_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes')        
}

