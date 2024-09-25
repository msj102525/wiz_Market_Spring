package com.jyes.www.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jyes.www.common.Config;
import com.jyes.www.dto.report.ReportAdditionalDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisDTO;
import com.jyes.www.dto.report.ReportDTO;
import com.jyes.www.dto.report.ReportDetailDTO;
import com.jyes.www.dto.report.ReportInformationDTO;
import com.jyes.www.dto.report.ReportInformationSaveDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisDTO;
import com.jyes.www.service.common.impl.ICommonService;
import com.jyes.www.service.report.impl.IReportService;
import com.jyes.www.utils.FileUtil;
import com.jyes.www.utils.ImageUtil;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.PageBean;
import com.jyes.www.utils.SnowflakeIdGenerator;
import com.jyes.www.utils.StringUtil;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FaqTitleVo;
import com.jyes.www.vo.common.FileGroupVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.InformationVo;
import com.jyes.www.vo.report.ReportInformationVo;

/**
 * 리포트 관련 컨트롤러
 */

@Controller
public class CommonController {
	
	private static final Log log = LogFactory.getLog(CommonController.class);
	
	@Resource(name="commonService")
	private ICommonService commonService;
	
	@Resource(name="reportService")
	private IReportService reportService;
	
	/**
	 * 레포트 상세(수정)
	 */
	@RequestMapping(value = "/report/information", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportInformation(HttpServletRequest request, Model model) throws Exception {
		
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
		
		return "/report/information";
		
	}
	
	/**
	 * 레포트 제공(고객용)
	 */
	@RequestMapping(value = "/wizmarket/report/{report_id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportView(HttpServletRequest request, Model model, @PathVariable("report_id") String report_id) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"report_id : "+report_id+"\n");
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(report_id, "0"));
		
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", reportId);
		
		ReportDTO report = new ReportDTO();
		try {
			report = reportService.viewReport(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		ReportAdditionalDTO reportAdditional = new ReportAdditionalDTO();
		
		try {
			reportAdditional = reportService.viewReportAdditional(param);
			List<FileVo> fileList = new ArrayList<FileVo>();
			if(reportAdditional.getFileGroupId() != null && reportAdditional.getFileGroupId() > 0){
				Map<String, Object> fileParam = new HashMap<>();
				fileParam.put("fileGroupId", reportAdditional.getFileGroupId());
				fileList = commonService.fileListByGroupId(fileParam);
			}
			reportAdditional.setFileList(fileList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		ReportLocationAnalysisDTO reportLocationAnalysis = new ReportLocationAnalysisDTO();
		
		try {
			reportLocationAnalysis = reportService.viewReportLocationAnalysis(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ReportBusinessDistrictAnalysisDTO reportBusinessDistrictAnalysis = new ReportBusinessDistrictAnalysisDTO();
		
		try {
			reportBusinessDistrictAnalysis = reportService.viewReportBusinessDistrictAnalysis(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		ReportDetailDTO reportDetail = new ReportDetailDTO();
		
		try {
			reportDetail = reportService.viewReportDetail(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		List<ReportInformationDTO> reportInformaionList = new ArrayList<ReportInformationDTO>();
		List<ReportInformationDTO> reportCommonInformaionList = new ArrayList<ReportInformationDTO>();
		List<ReportInformationDTO> reportNonCommonInformaionList = new ArrayList<ReportInformationDTO>();
		
		
		try {
			reportInformaionList = reportService.viewReportInformation(param);
			
			for(ReportInformationDTO reportInformation : reportInformaionList){
				List<FileVo> fileList = new ArrayList<FileVo>();
				if(reportInformation.getFileGroupId() != null && reportInformation.getFileGroupId() > 0){
					Map<String, Object> fileParam = new HashMap<>();
					fileParam.put("fileGroupId", reportInformation.getFileGroupId());
					fileList = commonService.fileListByGroupId(fileParam);
				}
				reportInformation.setFileList(fileList);
				
				if(reportInformation.getCommonInformationId() != null && reportInformation.getCommonInformationId() > 0){
					reportCommonInformaionList.add(reportInformation);
				}else{
					reportNonCommonInformaionList.add(reportInformation);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("report", report);
		model.addAttribute("reportAdditional", reportAdditional);
		model.addAttribute("reportLocationAnalysis", reportLocationAnalysis);
		model.addAttribute("reportBusinessDistrictAnalysis", reportBusinessDistrictAnalysis);
		model.addAttribute("reportDetail", reportDetail);
		model.addAttribute("reportInformaionList", reportInformaionList);
		model.addAttribute("reportCommonInformaionList", reportCommonInformaionList);
		model.addAttribute("reportNonCommonInformaionList", reportNonCommonInformaionList);
		
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
		
		return "/report/view";
		
	}
	
	/**
	 * index 페이지 연결
	 */
	@RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(HttpServletRequest request, Model model) throws Exception {
		
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
		
		return "redirect:/common/list/information/view";
		
	}
	
	/**
	 * 공통 입지 분석 등록 화면 ( location_analysis )
	 */
	@RequestMapping(value = "/common/save/location/analysis/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveLocationAnalysisView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
		}
		
//		model.addAttribute("sampleList", lvo);
		
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
		
		return "/common/save/locationAnalysis";
	}
	
	/**
	 * 공통 상권 분석 등록 화면 ( business_district_analysis )
	 */
	@RequestMapping(value = "/common/save/business/district/analysis/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveBusinessDistrictAnalysisView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		try {
			
		} catch(Exception e) {
			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
		}
		
//		model.addAttribute("sampleList", lvo);
		
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
		
		return "/common/save/businessDistrictAnalysis";
	}
	
	/**
	 * 공통 정보 화면
	 */
	@RequestMapping(value = "/common/information", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveInformationView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		int commonInformationId = Integer.parseInt(StringUtil.nvl(request.getParameter("commonInformationId"), "0"));
		
		String searchKeyword = StringUtil.nvl(request.getParameter("searchKeyword"));
		int curpage = Integer.parseInt(StringUtil.nvl(request.getParameter("curpage"), "1"));
		int blockCount = Integer.parseInt(StringUtil.nvl(request.getParameter("blockCount"), "10"));
		
		InformationVo vo = new InformationVo();
		try {
			vo.setCommonInformationId(commonInformationId);
			vo = (InformationVo)commonService.commonInformationById(vo);
			if(vo!=null&&vo.getFileGroupId()!=null&&0!=vo.getFileGroupId()) {
				//파일 조회
				FileVo fvo = new FileVo();
				try {
					fvo.setFileGroupId(vo.getFileGroupId());
					ArrayList<Object> afvo = (ArrayList<Object>)commonService.fileByGroupId(fvo);
					model.addAttribute("fileVoList", afvo);
				} catch(Exception e) {
					e.printStackTrace();
//					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
//			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertDataTransaction : "+e.toString()+"\n");
		}
		
		model.addAttribute("commonInformationId", commonInformationId);
		model.addAttribute("informationVo", vo);
		
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("curpage", curpage + "");
		model.addAttribute("blockCount", blockCount);
		
		model.addAttribute("topCode", "0");
		model.addAttribute("leftCode", "3");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/common/save/information";
	}
	
	/**
	 * 공통 정보 목록 화면
	 */
	@RequestMapping(value = "/common/list/information/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String listInformationView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		String searchKeyword = StringUtil.nvl(request.getParameter("searchKeyword"));
		int curpage = Integer.parseInt(StringUtil.nvl(request.getParameter("curpage"), "1"));
		int blockCount = Integer.parseInt(StringUtil.nvl(request.getParameter("blockCount"), "10"));
		
		/**
		 * 공통정보 목록
		 */
		ArrayList<Object> commonInfoList = null;
		try {
			int startIndex = (curpage - 1) * blockCount;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("searchKeyword", searchKeyword);
			params.put("startIndex", startIndex+"");
			params.put("blockCount", blockCount+"");
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start commonInformationByConditionSearch HashMap<String, String> params : " + params + "\n");
			commonInfoList = (ArrayList<Object>) commonService.commonInformationByConditionSearch(params);
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error commonInformationByConditionSearch : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end commonInformationByConditionSearch ArrayList<Object> commonInfoList : " + commonInfoList + "\n");
		
		/**
		 * 공통정보 목록 카운트
		 */
		int totalCnt = 0;
		try {
			int startIndex = (curpage - 1) * blockCount;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("searchKeyword", searchKeyword);
			params.put("startIndex", startIndex+"");
			params.put("blockCount", blockCount+"");
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start commonInformationByConditionSearchCount HashMap<String, String> params : " + params + "\n");
			totalCnt = (int) commonService.commonInformationByConditionSearchCount(params);
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error commonInformationByConditionSearchCount : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end commonInformationByConditionSearchCount int totalCnt : " + totalCnt + "\n");
		
		// 페이지 네비게이션
		PageBean pg = new PageBean(curpage, totalCnt, blockCount);
		String paging_html = pg.setPagingHtmlv(request.getContextPath() + currentUrl);
		model.addAttribute("paging_html", paging_html);
		
		model.addAttribute("commonInfoList", commonInfoList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("curpage", curpage + "");
		model.addAttribute("blockCount", blockCount);
		
		model.addAttribute("topCode", "1");
		model.addAttribute("leftCode", "3");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/common/list/information";
	}
	
	/**
	 * 공통정보 등록 화면
	 */
	@RequestMapping(value = "/common/save/information/saveDataAjax", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> saveDataAjax(HttpServletRequest request, Model model) {

		StringBuffer logData = new StringBuffer();
		
		HashMap requestMap = LogUtils.GetPrameterMap(request, logData);
		
		String currentUrl = request.getRequestURL().toString();
		String StartUrl = "/"+currentUrl.substring(currentUrl.indexOf(currentUrl.split("/")[3]));
		if(request.getQueryString() != null) {
			currentUrl = currentUrl+"?"+request.getQueryString();
		}
		String tag = StartUrl;
		long strartTime = System.currentTimeMillis();
		String logKey = LogUtils.getUserLogKey(request);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : 이미지 때문에 생략"+"\n");
		
		int commonInformationId = Integer.parseInt(StringUtil.nvl(request.getParameter("commonInformationId"), "0"));
		
		String title = StringUtil.nvl(request.getParameter("title"));
		String content = StringUtil.nvl(request.getParameter("content"));
		String fileGroupId = StringUtil.nvl(request.getParameter("fileGroupId"));
		
		String regId  = StringUtil.nvl(request.getParameter("regId"),"");
		String etc  = StringUtil.nvl(request.getParameter("etc"),"");
		String modId  = StringUtil.nvl(request.getParameter("modId"),"");
		
		String[] image_url = request.getParameterValues("image_url");
		String[] fileIds = request.getParameterValues("fileId");
		
		//아이디 1 처리
		if("".equals(regId)) {
			regId = "1";
		}
		
		//아이디 1 처리
		if("".equals(modId)) {
			modId = "1";
		}
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"title:"+title+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"content:"+content+"\n");
		
//		String dir_path = "/eclipse-workspace/ai_report_20240625/src/main/webapp/resources/assets/images/";
		String dir_path = Config.IMAGE_PATH;
		String file_path = "";
		String fileExtension = "";
		
		String msg = "";
		String imgUrl = "";
		
		LinkedHashMap<String, FileVo> mfvo = new LinkedHashMap<String, FileVo>();
		LinkedHashMap<String, FileVo> dfvo = new LinkedHashMap<String, FileVo>();
		
		try {

			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			
			MultipartHttpServletRequest multipartHttpServletRequest = null;
			Iterator<String> iteratorIsEmpty = null;
			List<MultipartFile> multipartFile = null;
			try {
				if(request instanceof MultipartHttpServletRequest) {
					multipartHttpServletRequest = (MultipartHttpServletRequest)request;
					if(multipartHttpServletRequest != null) {
						iteratorIsEmpty = multipartHttpServletRequest.getFileNames();
						multipartFile = null;
						while(iteratorIsEmpty.hasNext()) {
							multipartFile = multipartHttpServletRequest.getFiles(iteratorIsEmpty.next());
							if(!(multipartFile.size()==1 && multipartFile.get(0).getOriginalFilename().equals(""))) {
								for (int i = 0; i < multipartFile.size(); i++) {
									String image_name = multipartFile.get(i).getName();
									String originalFilename = multipartFile.get(i).getOriginalFilename();
									String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
									fileExtension = extension;
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"------------- file start -------------"+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"name : "+multipartFile.get(i).getName()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"filename : "+multipartFile.get(i).getOriginalFilename()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"size : "+multipartFile.get(i).getSize()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"-------------- file end --------------\n");
									byte[] byte_file = FileUtil.getFileByte(multipartFile.get(i));
//									imgUrl = "http://localhost:82/assets/images/";
									imgUrl = Config.IMAGE_HOST;
									byte[] image_file_byte = null;
									if(image_url!=null&&image_url.length-1>=i&&image_url[i].startsWith("data:image")) {
										image_file_byte = Base64.decodeBase64(image_url[i].replaceAll("data:image/"+fileExtension+";base64,", ""));
									} else {
										image_file_byte = byte_file;
									}
									String saveName = SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
									String savePath = dir_path;
									imgUrl += saveName;
									
									FileVo fv = new FileVo();
									fv.setOriginalName(originalFilename);
									fv.setSavePath(savePath);
									fv.setSaveName(saveName);
									fv.setEtc(etc);
									fv.setRegId(Integer.parseInt(regId));
									fv.setUrl(imgUrl);
									mfvo.put("image_url_file_"+i, fv);
									
									file_path = savePath+saveName;
									FileUtil.saveImgFile(dir_path, file_path, image_file_byte);
								}
							}
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+e.toString()+"\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		int resultCnt = -1;
		
		if(0==commonInformationId) {

			/**
			 * 공통정보 등록
			 */
			try {
				InformationVo ivo = new InformationVo();
				ivo.setRegId(Integer.parseInt(regId));
				ivo.setTitle(title);
				ivo.setContent(content);
				FileGroupVo fgvo = new FileGroupVo();
				fgvo.setRegId(Integer.parseInt(regId));
				logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start insertCommonInformation InformationVo ivo : " + ivo + " ArrayList<FileVo> mfvo : " + mfvo + " FileGroupVo fgvo : " + fgvo + "\n");
				resultCnt = commonService.insertCommonInformation(ivo, mfvo, fgvo, logData);
			} catch (Exception e) {
				e.printStackTrace();
				logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error insertCommonInformation : " + e.toString() + "\n");
			}
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end insertCommonInformation int resultCnt : " + resultCnt + "\n");
			
		} else {
			
			//삭제 담기
			if(fileIds!=null) {
				for (int i = 0; i < fileIds.length; i++) {
	                String fileId = fileIds[i];
	                FileVo fv = new FileVo();
	                fv.setFileId(Integer.parseInt(fileId));
	                fv.setEtc(etc);
					fv.setModId(Integer.parseInt(modId));
					dfvo.put("image_url_file_"+i, fv);
	            }
			}
			
			/**
			 * 공통정보 수정
			 */
			try {
				InformationVo ivo = new InformationVo();
				ivo.setCommonInformationId(commonInformationId);
				ivo.setModId(Integer.parseInt(modId));
				ivo.setTitle(title);
				ivo.setContent(content);
				FileGroupVo fgvo = new FileGroupVo();
				fgvo.setRegId(Integer.parseInt(modId));
				if(!"".equals(fileGroupId)) {
					fgvo.setFileGroupId(Integer.parseInt(fileGroupId));
				}
				logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start updateInformation InformationVo ivo : " + ivo + " ArrayList<FileVo> mfvo : " + mfvo + " FileGroupVo fgvo : " + fgvo + "\n");
				resultCnt = commonService.updateInformation(ivo, mfvo, dfvo, fgvo);
			} catch (Exception e) {
				e.printStackTrace();
				logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error updateInformation : " + e.toString() + "\n");
			}
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end updateInformation int resultCnt : " + resultCnt + "\n");
			
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("msg", msg);
		result.put("data", imgUrl);
		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"msg\") : "+result.get("msg")+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"data\") : "+((result.get("data").toString().length()>1000)?result.get("data").toString().substring(0, 1000)+"...":result.get("data").toString().substring(0, result.get("data").toString().length()-1))+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
	}
	
	/**
	 * 이미지 크롭
	 */
	@RequestMapping(value = "/common/getJCropImageData", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> getImageData(HttpServletRequest request, Model model) {

		StringBuffer logData = new StringBuffer();
		
		HashMap requestMap = LogUtils.GetPrameterMap(request, logData);
		
		String currentUrl = request.getRequestURL().toString();
		String StartUrl = "/"+currentUrl.substring(currentUrl.indexOf(currentUrl.split("/")[3]));
		if(request.getQueryString() != null) {
			currentUrl = currentUrl+"?"+request.getQueryString();
		}
		String tag = StartUrl;
		long strartTime = System.currentTimeMillis();
		String logKey = LogUtils.getUserLogKey(request);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : 이미지 때문에 생략"+"\n");

		String msg = "";
		int resultCnt = -1;
		String imgUrl = "";
		try {

			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			
			String x1_Str = StringUtil.nvl(request.getParameter("x1"), "0");
			String y1_Str = StringUtil.nvl(request.getParameter("y1"), "0");
			String w_Str = StringUtil.nvl(request.getParameter("w"), "0");
			String h_Str = StringUtil.nvl(request.getParameter("h"), "0");
			
			if(x1_Str.indexOf(".") > -1)x1_Str = x1_Str.substring(0, x1_Str.indexOf("."));
			if(y1_Str.indexOf(".") > -1)y1_Str = y1_Str.substring(0, y1_Str.indexOf("."));
			if(w_Str.indexOf(".") > -1)w_Str = w_Str.substring(0, w_Str.indexOf("."));
			if(h_Str.indexOf(".") > -1)h_Str = h_Str.substring(0, h_Str.indexOf("."));
			
			int x1=Integer.parseInt(x1_Str);
		    int y1=Integer.parseInt(y1_Str);
		    int w=Integer.parseInt(w_Str);
		    int h=Integer.parseInt(h_Str);
		    
		    if(x1<0) {
		    	x1 = 0;
		    }
		    if(y1<0) {
		    	y1 = 0;
		    }
		    if(w<0) {
		    	w = 0;
		    }
		    if(h<0) {
		    	h = 0;
		    }
		    
			// 원본 이미지
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			MultipartFile multipartFile = null;
			while(iterator.hasNext()) {
				multipartFile = multipartHttpServletRequest.getFile(iterator.next());
				if(multipartFile.isEmpty() == false) {
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"------------- file start -------------"+"\n");
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"name : "+multipartFile.getName()+"\n");
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"filename : "+multipartFile.getOriginalFilename()+"\n");
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"size : "+multipartFile.getSize()+"\n");
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"-------------- file end --------------"+"\n");
					String fileName = multipartFile.getOriginalFilename();
					String fileExtension = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1, fileName.length());
					// jpg, jpeg, png, gif, bmp만 업로드 되도록 수정(jpg, png, bmp)
					if (fileName.toLowerCase().endsWith(".jpg") 
							|| fileName.toLowerCase().endsWith(".png") 
							|| fileName.toLowerCase().endsWith(".bmp")) {
							byte[] byte_file = FileUtil.getFileByte(multipartFile);
							//용량 조절 추가 시작
							try {
								byte_file = ImageUtil.crop(byte_file, x1, y1, w, h);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//용량 조절 추가 종료
							imgUrl = ImageUtil.getImageData(byte_file, fileExtension);
							resultCnt = 1;
					} else {
						resultCnt = 2;
					}
				}
			}
			if (resultCnt == 1) {
				msg = "1";
			} else if (resultCnt == 2) {
				msg = "2";
			} else if (resultCnt == 3) {
				msg = "3";
			} else if (resultCnt == -1) {
				msg = "";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("msg", msg);
		result.put("data", imgUrl);
		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"msg\") : "+result.get("msg")+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"data\") : "+((result.get("data").toString().length()>1000)?result.get("data").toString().substring(0, 1000)+"...":result.get("data").toString().substring(0, result.get("data").toString().length()-1))+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
	}
	
	/**
	 * 이미지 업로드 샘플
	 */
	@RequestMapping(value = "/common/setJCropImageData", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> setJCropImageData(HttpServletRequest request, Model model) {

		StringBuffer logData = new StringBuffer();
		
		HashMap requestMap = LogUtils.GetPrameterMap(request, logData);
		
		String currentUrl = request.getRequestURL().toString();
		String StartUrl = "/"+currentUrl.substring(currentUrl.indexOf(currentUrl.split("/")[3]));
		if(request.getQueryString() != null) {
			currentUrl = currentUrl+"?"+request.getQueryString();
		}
		String tag = StartUrl;
		long strartTime = System.currentTimeMillis();
		String logKey = LogUtils.getUserLogKey(request);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey:"+logKey+":"+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : 이미지 때문에 생략"+"\n");
		
		String image_url_0 = StringUtil.nvl(request.getParameter("image_url_0"));
		String image_url_1 = StringUtil.nvl(request.getParameter("image_url_1"));
		String image_url_2 = StringUtil.nvl(request.getParameter("image_url_2"));
		String image_url_3 = StringUtil.nvl(request.getParameter("image_url_3"));
		
		byte[] image_file_byte_0 = null;
		byte[] image_file_byte_1 = null;
		byte[] image_file_byte_2 = null;
		byte[] image_file_byte_3 = null;
		
		String dir_path = Config.IMAGE_PATH;
		String file_path = "";
		String fileExtension = "";
		
		String msg = "";
		String imgUrl = "";
		
		try {

			HttpSession session = request.getSession();
			String user_id = (String)session.getAttribute("user_id");
			
			MultipartHttpServletRequest multipartHttpServletRequest = null;
			Iterator<String> iteratorIsEmpty = null;
			List<MultipartFile> multipartFile = null;
			try {
				if(request instanceof MultipartHttpServletRequest) {
					multipartHttpServletRequest = (MultipartHttpServletRequest)request;
					if(multipartHttpServletRequest != null) {
						iteratorIsEmpty = multipartHttpServletRequest.getFileNames();
						multipartFile = null;
						while(iteratorIsEmpty.hasNext()) {
							multipartFile = multipartHttpServletRequest.getFiles(iteratorIsEmpty.next());
							if(!(multipartFile.size()==1 && multipartFile.get(0).getOriginalFilename().equals(""))) {
								for (int i = 0; i < multipartFile.size(); i++) {
									String image_name = multipartFile.get(i).getName();
									String originalFilename = multipartFile.get(i).getOriginalFilename();
									String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
									fileExtension = extension;
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"------------- file start -------------"+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"name : "+multipartFile.get(i).getName()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"filename : "+multipartFile.get(i).getOriginalFilename()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"size : "+multipartFile.get(i).getSize()+"\n");
									logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"-------------- file end --------------\n");
									byte[] byte_file = FileUtil.getFileByte(multipartFile.get(i));
									if("image_url_file_0".equals(image_name)) {
										if(image_url_0.startsWith("data:image")) {
											image_file_byte_0 = Base64.decodeBase64(image_url_0.replaceAll("data:image/"+fileExtension+";base64,", ""));
										} else {
											image_file_byte_0 = byte_file;
										}
										file_path = dir_path+SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
										FileUtil.saveImgFile(dir_path, file_path, image_file_byte_0);
									}
									if("image_url_file_1".equals(image_name)) {
										if(image_url_1.startsWith("data:image")) {
											image_file_byte_1 = Base64.decodeBase64(image_url_1.replaceAll("data:image/"+fileExtension+";base64,", ""));
										} else {
											image_file_byte_1 = byte_file;
										}
										file_path = dir_path+SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
										FileUtil.saveImgFile(dir_path, file_path, image_file_byte_1);
									}
									if("image_url_file_2".equals(image_name)) {
										if(image_url_2.startsWith("data:image")) {
											image_file_byte_2 = Base64.decodeBase64(image_url_2.replaceAll("data:image/"+fileExtension+";base64,", ""));
										} else {
											image_file_byte_2 = byte_file;
										}
										file_path = dir_path+SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
										FileUtil.saveImgFile(dir_path, file_path, image_file_byte_2);
									}
									if("image_url_file_3".equals(image_name)) {
										if(image_url_3.startsWith("data:image")) {
											image_file_byte_3 = Base64.decodeBase64(image_url_3.replaceAll("data:image/"+fileExtension+";base64,", ""));
										} else {
											image_file_byte_3 = byte_file;
										}
										file_path = dir_path+SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
										FileUtil.saveImgFile(dir_path, file_path, image_file_byte_3);
									}
								}
							}
						}
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+e.toString()+"\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("msg", msg);
		result.put("data", imgUrl);
		
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"msg\") : "+result.get("msg")+"\n");
//		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result.get(\"data\") : "+((result.get("data").toString().length()>1000)?result.get("data").toString().substring(0, 1000)+"...":result.get("data").toString().substring(0, result.get("data").toString().length()-1))+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
	}
	
	/**
	 * 자주하는 질문 제목 목록 조회
	 * @ResponseBody 
	 */
	@RequestMapping(value = "/common/getFaqTitleListAjax", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<Object, Object> getFaqTitleListAjax(HttpServletRequest request, Model model) throws Exception {
		
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

		/**
		 * 자주 사용하는 질문 제목 목록
		 */
		ArrayList<Object> faqTitleList = null;
		try {
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start selectFaqTitle" + "\n");
			faqTitleList = (ArrayList<Object>)commonService.selectFaqTitle();
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error selectFaqTitle : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end selectFaqTitle ArrayList<Object> faqTitleList : " + faqTitleList + "\n");
		
		HashMap<Object, Object> result = new HashMap<Object, Object>();
		result.put("msg", faqTitleList);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
		
	}
	
	/**
	 * 자주하는 질문 제목 등록
	 * @ResponseBody 
	 */
	@RequestMapping(value = "/common/addFaqTitleAjax", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<Object, Object> addFaqTitleAjax(HttpServletRequest request, Model model) throws Exception {
		
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

		String title  = StringUtil.nvl(request.getParameter("title"),"");
		String etc  = StringUtil.nvl(request.getParameter("etc"),"");
		String reg_id  = StringUtil.nvl(request.getParameter("reg_id"),"");
		
		//아이디 1 처리
		if("".equals(reg_id)) {
			reg_id = "1";
		}

		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"title : "+title+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"etc : "+etc+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"reg_id : "+reg_id+"\n");
		
		/**
		 * 자주 사용하는 질문 제목 추가
		 */
		int resultCnt = -1;
		try {
			FaqTitleVo ftv = new FaqTitleVo();
			ftv.setTitle(title);
			ftv.setEtc(etc);
			ftv.setReg_id(reg_id);
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start insertFaqTitle FaqTitleVo ftvs : " + ftv + "\n");
			resultCnt = commonService.insertFaqTitle(ftv);
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error insertFaqTitle : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end insertFaqTitle int resultCnt : " + resultCnt + "\n");
		
		HashMap<Object, Object> result = new HashMap<Object, Object>();
		result.put("msg", resultCnt);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
		
	}
	
	/**
	 * 자주하는 질문 제목 변경
	 * @ResponseBody 
	 */
	@RequestMapping(value = "/common/modFaqTitleAjax", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<Object, Object> modFaqTitleAjax(HttpServletRequest request, Model model) throws Exception {
		
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

		String faq_title_id  = StringUtil.nvl(request.getParameter("faq_title_id"),"");
		String is_deleted  = StringUtil.nvl(request.getParameter("is_deleted"),"");
		String mod_id  = StringUtil.nvl(request.getParameter("mod_id"),"");
		
		//아이디 1 처리
		if("".equals(mod_id)) {
			mod_id = "1";
		}
		
		if("".equals(is_deleted)) {
			is_deleted = "Y";
		}

		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"faq_title_id : "+faq_title_id+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"is_deleted : "+is_deleted+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"mod_id : "+mod_id+"\n");
		
		/**
		 * 자주 사용하는 질문 제목 삭제
		 */
		int resultCnt = -1;
		try {
			FaqTitleVo ftv = new FaqTitleVo();
			ftv.setFaq_title_id(faq_title_id);
			ftv.setIs_deleted(is_deleted);
			ftv.setMod_id(mod_id);
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start updateFaqTitle FaqTitleVo ftvs : " + ftv + "\n");
			resultCnt = commonService.updateFaqTitle(ftv);
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error updateFaqTitle : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end updateFaqTitle int resultCnt : " + resultCnt + "\n");
		
		HashMap<Object, Object> result = new HashMap<Object, Object>();
		result.put("msg", resultCnt);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
		
	}
	
	/**
	 * 자주하는 질문 제목 삭제
	 * @ResponseBody 
	 */
	@RequestMapping(value = "/common/delFaqTitleAjax", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<Object, Object> delFaqTitleAjax(HttpServletRequest request, Model model) throws Exception {
		
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

		String faq_title_id  = StringUtil.nvl(request.getParameter("faq_title_id"),"");

		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"faq_title_id : "+faq_title_id+"\n");
		
		/**
		 * 자주 사용하는 질문 제목 삭제
		 */
		int resultCnt = -1;
		try {
			FaqTitleVo ftv = new FaqTitleVo();
			ftv.setFaq_title_id(faq_title_id);
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query start deleteFaqTitle FaqTitleVo ftvs : " + ftv + "\n");
			resultCnt = commonService.deleteFaqTitle(ftv);
		} catch (Exception e) {
			e.printStackTrace();
			logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query error deleteFaqTitle : " + e.toString() + "\n");
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "query end deleteFaqTitle int resultCnt : " + resultCnt + "\n");
		
		HashMap<Object, Object> result = new HashMap<Object, Object>();
		result.put("msg", resultCnt);
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<String, Object> result : "+result+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
		
		return result;
		
	}
	
	/**
	 * 대분류, 중분류 가져오기 
	 */
	@RequestMapping(value = "/common/category/lists", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> commonCategoryLists(HttpServletRequest request, Model model) throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
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
		
		List<CategoryVo> mainCategoryList = new ArrayList<CategoryVo>();
		List<CategoryVo> subCategoryList = new ArrayList<CategoryVo>();
		
		try {
			mainCategoryList = commonService.mainCategoryList();
			subCategoryList = commonService.subCategoryList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("mainCategoryList", mainCategoryList);
		data.put("subCategoryList", subCategoryList);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 온라인 URL 종류 가져오기
	 */
	@RequestMapping(value = "/common/code/online/lists", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> commonCodeOnlineLists(HttpServletRequest request, Model model) throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
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
		
		List<CodeVo> onlineUrlList = new ArrayList<CodeVo>();
		
		try {
			onlineUrlList = commonService.onlineUrlList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("onlineUrlList", onlineUrlList);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 통계 수치 총 목록 가져오기
	 */
	@RequestMapping(value = "/common/code/stats/lists", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> commonCodeStatsLists(HttpServletRequest request, Model model) throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
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
		
		// 성별
		List<CodeVo> genderList = new ArrayList<CodeVo>();
		
		// 연령대
		List<CodeVo> ageGroupList = new ArrayList<CodeVo>();
		
		// 요일
		List<CodeVo> weekList = new ArrayList<CodeVo>();
		
		// 시간대
		List<CodeVo> timeList = new ArrayList<CodeVo>();
		
		// 배달 앱
		List<CodeVo> deliveryList = new ArrayList<CodeVo>();
		
		try {
			
			genderList = commonService.genderList();
			ageGroupList = commonService.ageGroupList();
			weekList = commonService.weekList();
			timeList = commonService.timeList();
			deliveryList = commonService.deliveryList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("genderList", genderList);
		data.put("ageGroupList", ageGroupList);
		data.put("weekList", weekList);
		data.put("timeList", timeList);
		data.put("deliveryList", deliveryList);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
}
