package com.jyes.www.controller.sample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springbyexample.web.servlet.view.tiles2.TilesUrlBasedViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jyes.www.exception.api.common.NecessaryMissingValueException;
import com.jyes.www.exception.api.common.SystemException;
import com.jyes.www.msg.Msg;
import com.jyes.www.service.sample.impl.ISampleService;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.StringUtil;
import com.jyes.www.vo.sample.SampleVo;
import com.jyes.www.vo.sample.SampleXmlListVo;


/**
 * Handles requests for the application home page.
 */
@Controller
public class SampleHomeController {
	
//	private static final Log log = LogFactory.getLog(SampleHomeController.class);
//	
//	@Autowired
//	private TilesUrlBasedViewResolver tilesUrlBasedViewResolver;
//	
//	@Resource(name="sampleService")
//	private ISampleService sampleService;
//	
//	/**
//	 * mvc 인터셉터 테스트
//	 */
//	@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
//	public String home(HttpServletRequest request, Model model) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		try {
//			SampleVo vo = new SampleVo();
//			vo.setUser_name("여대현");
//			vo.setUser_age(34);
////			sampleService.insertDataTransaction(vo);URL 호출시 데이터 계속 추가 되서 막아 놓음 열어도 상관없음...
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
//		}
//		
//		List<SampleVo> lvo = null;
//		try {
//			lvo = (List<SampleVo>)this.sampleService.selectDataList();
//			int length = lvo.size();
//			if(length>2) {
//				length = 1;
//			}
//			for (int i = 0; i < length; i++) {
//				SampleVo svo = (SampleVo)lvo.get(i);
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getUser_name() : "+svo.getUser_name()+"\n");
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getUser_age() : "+svo.getUser_age()+"\n");
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getInsert_date() : "+svo.getInsert_date()+"\n");
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error selectDataList : "+e.toString()+"\n");
//		}
//		
//		model.addAttribute("sampleList", lvo);
//		
//		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
//		Iterator<String> iterator = hashMap.keySet().iterator();
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
//		}
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return "home";
//		
//	}
//	
//	/**
//	 * mvc 테스트
//	 */
//	@RequestMapping(value = "/homeNoInterceptor", method = { RequestMethod.POST, RequestMethod.GET })
//	public String homeInterceptor(HttpServletRequest request, Model model) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		try {
//			SampleVo vo = new SampleVo();
//			vo.setUser_name("여대현");
//			vo.setUser_age(34);
////			sampleService.insertDataTransaction(vo);URL 호출시 데이터 계속 추가 되서 막아 놓음 열어도 상관없음...
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
//		}
//		
//		List<SampleVo> lvo = null;
//		try {
//			lvo = (List<SampleVo>)this.sampleService.selectDataList();
//			int length = lvo.size();
//			if(length>2) {
//				length = 1;
//			}
//			for (int i = 0; i < length; i++) {
//				SampleVo svo = (SampleVo)lvo.get(i);
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getUser_name() : "+svo.getUser_name()+"\n");
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getUser_age() : "+svo.getUser_age()+"\n");
//				logData.append("[" + LogUtils.getCurrentTime() + "]" + " "+"svo.getInsert_date() : "+svo.getInsert_date()+"\n");
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error selectDataList : "+e.toString()+"\n");
//		}
//		
//		model.addAttribute("sampleList", lvo);
//		
//		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
//		Iterator<String> iterator = hashMap.keySet().iterator();
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
//		}
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return "home";
//		
//	}
//	
//	/**
//	 * @ResponseBody HashMap<Object, Object> form(ajax) MultipartHttpServletRequest 테스트 
//	 */
//	@RequestMapping(value = "/formData", method = { RequestMethod.POST, RequestMethod.GET })
//	public @ResponseBody HashMap<Object, Object> formData(HttpServletRequest request, Model model) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		if(request instanceof MultipartHttpServletRequest) {
//			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
//			if(multipartHttpServletRequest!=null) {
//				Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//				MultipartFile multipartFile = null;
//				while(iterator.hasNext()) {
//					multipartFile = multipartHttpServletRequest.getFile(iterator.next());
//					if(multipartFile.isEmpty() == false) {
//						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"------------- file start -------------"+"\n");
//						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"name : "+multipartFile.getName()+"\n");
//						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"filename : "+multipartFile.getOriginalFilename()+"\n");
//						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"size : "+multipartFile.getSize()+"\n");
//						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"-------------- file end --------------"+"\n");
//					}
//				}
//			}
//		}
//		
//		String input_id  = StringUtil.nvl(request.getParameter("input_id"),"");
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"input_id : "+input_id+"\n");
//		
//		HashMap<Object, Object> result = new HashMap<Object, Object>();
//		result.put("msg", "0");
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return result;
//		
//	}
//	
//	/**
//	 * @ResponseBody string 테스트
//	 */
//	@RequestMapping(value = "/stringData", method = { RequestMethod.POST, RequestMethod.GET })
//	public @ResponseBody String getStringData(HttpServletRequest request) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		String param = StringUtil.nvl(request.getParameter("param"));
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"String param : "+param+"\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return param;
//		
//	}
//	
//	/**
//	 * @ResponseBody json 테스트
//	 */
//	@RequestMapping(value = "/jsonData", method = { RequestMethod.POST, RequestMethod.GET })
//	public @ResponseBody HashMap<Object, Object> getJsonData(HttpServletRequest request) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		String param = StringUtil.nvl(request.getParameter("param"));
//		if(param.equals("")) {
//			throw new NecessaryMissingValueException(strartTime, logData, tag, log);
//		}
//
//		HashMap<Object, Object> map = new HashMap<Object, Object>();
//		HashMap<Object, Object> meta = new HashMap<Object, Object>();
//		List<SampleVo> lvo = null;
//		try {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query start selectDataList"+"\n");
//			lvo = (List<SampleVo>)this.sampleService.selectDataList();
//			if(lvo==null) {
//				meta = Msg.getMeta(204);
//				map.put("meta", meta);
//			} else {
//				map.put("data", lvo);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error selectDataList : "+e.toString()+"\n");
//			throw new SystemException(strartTime, logData, tag, log);
//		}
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query end selectDataList List<Object> lvo : "+lvo+"\n");
//		//OK 성공
//		if(meta.get("code")==null) {
//			meta = Msg.getMeta(200);
//			map.put("meta", meta);
//		}
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<Object, Object> map : "+map+"\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return map;
//		
//	}
//	
//	/**
//	 * @ResponseBody xml 테스트(크롬에서 확인...)
//	 */
//	@RequestMapping(value = "/xmlData", method = { RequestMethod.POST, RequestMethod.GET })
//	public @ResponseBody SampleXmlListVo getXmlData(HttpServletRequest request) throws Exception {
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		String param = StringUtil.nvl(request.getParameter("param"));
//		if(param.equals("")) {
//			throw new NecessaryMissingValueException(strartTime, logData, tag, log);
//		}
//
//		List<SampleVo> lvo = null;
//		try {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query start selectDataList"+"\n");
//			lvo = (List<SampleVo>)this.sampleService.selectDataList();
//		} catch(Exception e) {
//			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error selectDataList : "+e.toString()+"\n");
//			throw new SystemException(strartTime, logData, tag, log);
//		}
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query end selectDataList List<Object> lvo : "+lvo+"\n");
//		
//		SampleXmlListVo sampleXmlListVo = new SampleXmlListVo();
//		
//		sampleXmlListVo.setSampleXmlListVo(lvo);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query end selectDataList List<Object> lvo : "+lvo+"\n");
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"SampleXmlListVo sampleXmlListVo : "+sampleXmlListVo+"\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return sampleXmlListVo;
//		
//	}
//	
//	/**
//	 * Tiles2 구성
//	 */
//	@RequestMapping(value = "/tiles2", method = { RequestMethod.POST, RequestMethod.GET })
//	public String tiles2(HttpServletRequest request, Model model) throws Exception {
//		tilesUrlBasedViewResolver.clearCache();
//		tilesUrlBasedViewResolver.setTilesDefinitionName("layouts-tiles");
//		
//		StringBuffer logData = new StringBuffer();
//		
//		HashMap<Object, Object> requestMap = LogUtils.GetPrameterMap(request, logData);
//		
//		String currentUrl = request.getRequestURL().toString();
//		String StartUrl = currentUrl.substring(currentUrl.indexOf(currentUrl.split("//")[currentUrl.split("//").length-1].split("/")[0]));
//		if(request.getQueryString() != null) {
//			currentUrl = currentUrl+"?"+request.getQueryString();
//		}
//		String tag = StartUrl;
//		long strartTime = System.currentTimeMillis();
//		String logKey = LogUtils.getUserLogKey(request);
//		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
//		
//		HashMap<String, Object> hashMap = (HashMap<String, Object>)model.asMap();
//		Iterator<String> iterator = hashMap.keySet().iterator();
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 시작 ----\n");
//		while (iterator.hasNext()) {
//			String key = (String) iterator.next();
//			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "key = " + key+"\n");
//		}
//		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- model 종료 ----\n");
//		long endTime = System.currentTimeMillis()-strartTime; 
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
//		if(endTime > 15000) {
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
//		}
//		log.info(logData.toString());
//		
//		return "body";
//		
//	}
	
}
