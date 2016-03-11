package com.fatiny.sdk.gee;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.fatiny.util.LogContext;

public class GeetestVerify {
	
	private static Logger log = LogContext.LOG_MODULE_GEE;
	
	public static void startCaptcha(HttpServletRequest request, HttpServletResponse response){
		try {
			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
			String resStr = "{}";
			
			//自定义userid
			String userid = "test";

			//进行验证预处理
			int gtServerStatus = gtSdk.preProcess(userid);
			
			//将服务器状态设置到session中
			request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
			//将userid设置到session中
			request.getSession().setAttribute("userid", userid);
			
			resStr = gtSdk.getResponseStr();
			PrintWriter out = response.getWriter();
			out.println(resStr);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	
	public void verifyLogin(HttpServletRequest request, HttpServletResponse response){
		try {
			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
			String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
			String validate = request.getParameter(GeetestLib.fn_geetest_validate);
			String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
				
			//从session中获取gt-server状态
			int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
			
			//从session中获取userid
			String userid = (String)request.getSession().getAttribute("userid");
			
			int gtResult = 0;

			if (gt_server_status_code == 1) {
				//gt-server正常，向gt-server进行二次验证
					
				gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
				System.out.println(gtResult);
			} else {
				// gt-server非正常情况下，进行failback模式验证
					
				System.out.println("failback:use your own server captcha validate");
				gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
				System.out.println(gtResult);
			}

			if (gtResult == 1) {
				// 验证成功
				PrintWriter out = response.getWriter();
				out.println("success:" + gtSdk.getVersionInfo());
			}
			else {
				// 验证失败
				PrintWriter out = response.getWriter();
				out.println("fail:" + gtSdk.getVersionInfo());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	
	

}
