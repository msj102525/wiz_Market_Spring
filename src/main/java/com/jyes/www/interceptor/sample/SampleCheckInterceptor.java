package com.jyes.www.interceptor.sample;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jyes.www.exception.api.common.UserAgentException;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.StringUtil;

public class SampleCheckInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log log = LogFactory.getLog(SampleCheckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean success = false;

		StringBuffer logData = new StringBuffer();
		
		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
		
		String currentUrl = request.getRequestURL().toString();
		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
		if(request.getQueryString() != null) {
			currentUrl = currentUrl+"?"+request.getQueryString();
		}
		String tag = StartUrl;
		long strartTime = System.currentTimeMillis();
		String logKey = LogUtils.getUserLogKey(request);
		
		// SSL 적용
//		String url = request.getRequestURL().toString();
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"url : "+url+"\n");
//		if(url.startsWith("http://")) {
//			url = url.replaceAll("http://", "https://");
//			response.sendRedirect(url);
//			return success;
//		}
		
		String userAgent = (String)request.getHeader("User-Agent").toLowerCase();
		String[] chkAgent = {"daumoa", "naverbot", "Cowbot", "Googlebot", "googlebot-image", "googlebot-mobile", "MSNBot", "psbot", "Yahoo-MMCrawler", "Slurp", "yahoo-blogs/v3.9", "ia_archiver", "baiduspider", "WebZIP", "Teleport", "GetRight", "WebCopier", "NetZip", "Teleport"};
		for(int i=0; i<chkAgent.length; i++){
			int state = userAgent.indexOf(chkAgent[i].toLowerCase());
			if(state > 0){
				throw new UserAgentException(strartTime, logData, tag, log);
			}
		}
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"userAgent : "+userAgent+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"User Agent Check"+"\n");
		
		//페이지 호출전 세션 및 유효성 체크
		HttpSession session = request.getSession(false);
		if (session == null) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"인증 실패"+"\n");
			//처리를 끝냄 - 컨트롤로 요청이 가지 않음.
//			response.sendRedirect("/homeNoInterceptor");
			success = false;
		} else {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"인증 성공"+"\n");
			success = true;
		}
		//임시처리
		success = true;
		
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		
		log.info(logData.toString());
		
		return success;
	}
	
}
