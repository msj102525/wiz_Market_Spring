package com.jyes.www.controller.shop;

import java.util.HashMap;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jyes.www.service.shop.impl.IShopService;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShopController {
	
	private static final Log log = LogFactory.getLog(ShopController.class);
	
	@Resource(name="shopService")
	private IShopService shopService;
	
	/**
	 * 매장목록
	 */
	@RequestMapping(value = "/shop/list", method = { RequestMethod.POST, RequestMethod.GET })
	public String shopList(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"매장목록 "+"\n");
		
		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
		Iterator<String> iterator = hashMap.keySet().iterator();
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return "shop/list";
		
	}
	
	/**
	 * 매장정보
	 */
	@RequestMapping(value = "/shop/info", method = { RequestMethod.POST, RequestMethod.GET })
	public String shopInfo(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"매장정보 "+"\n");
		
		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
		Iterator<String> iterator = hashMap.keySet().iterator();
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return "shop/info";
		
	}
	
	/**
	 * 매장추가정보
	 */
	@RequestMapping(value = "/shop/info2", method = { RequestMethod.POST, RequestMethod.GET })
	public String shopInfo2(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"매장추가정보 "+"\n");
		
		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
		Iterator<String> iterator = hashMap.keySet().iterator();
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return "shop/info2";
		
	}
	
}
