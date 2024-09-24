package com.jyes.www.controller.store;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyes.www.dto.store.StoreSaveDTO;
import com.jyes.www.dto.store.StoreSearchRequestDTO;
import com.jyes.www.dto.store.StoreViewResponseDTO;
import com.jyes.www.service.common.impl.ICommonService;
import com.jyes.www.service.report.impl.IReportService;
import com.jyes.www.service.store.impl.IStoreService;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.StringUtil;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.report.ReportChangedListVo;
import com.jyes.www.vo.report.ReportVo;
import com.jyes.www.vo.store.StoreListVo;
import com.jyes.www.vo.store.StoreVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StoreController {
	
	private static final Log log = LogFactory.getLog(StoreController.class);
	
	@Resource(name="storeService")
	private IStoreService storeService;
	
	@Resource(name="reportService")
	private IReportService reportService;
	
	@Resource(name="commonService")
	private ICommonService commonService;
	
	/**
	 * 매장검색
	 */
	@RequestMapping(value = "/store/list", method = { RequestMethod.POST, RequestMethod.GET })
	public String storeList(HttpServletRequest request, Model model) throws Exception {
		
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
		
		model.addAttribute("leftCode", "1");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/store/list";
		
	}
	
	/**
	 * 매장 검색
	 */
	@RequestMapping(value = "/store/search", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> storeSearch(@RequestBody StoreSearchRequestDTO requestDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		List<StoreListVo> storeList = new ArrayList<StoreListVo>();
		int storeListCount = 0;
				
		try {
			storeList = storeService.storeListByConditionSearch(requestDTO);
			storeListCount = storeService.storeListByConditionSearchCount(requestDTO);
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("list", storeList);
		data.put("count", storeListCount);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 매장 검색 ( 매장명으로만 검색 / 리포트 등록에서 사용 )
	 */
	@RequestMapping(value = "/store/search/name", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> storeSearchByName(@RequestBody StoreSearchRequestDTO requestDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		String searchKeyword = StringUtil.nvl(request.getParameter("searchKeyword"));
		
		List<StoreListVo> storeList = new ArrayList<StoreListVo>();
		int storeListCount = 0;
				
		try {
			storeList = storeService.storeListByName(requestDTO);
			storeListCount = storeService.storeListByNameCount(requestDTO);
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("list", storeList);
		data.put("count", storeListCount);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 매장 저장 ( 등록 / 수정 )
	 */
	@RequestMapping(value = "/store/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> storeSave(@RequestBody StoreSaveDTO saveDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"매장목록 "+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer storeId = 0;
		int saveResult = 0;
		
		StoreVo saveStoreVo = new StoreVo();
		saveStoreVo.setStartDate(saveDTO.getStartDate());
		
		if(saveDTO.getStoreId() != null && saveDTO.getStoreId() > 0){
			storeId = saveDTO.getStoreId();
		}
		
		if(storeId > 0){
			saveStoreVo.setStoreId(storeId);
			saveStoreVo.setModId(regId);
			
			try {
				saveResult = storeService.updateStore(saveStoreVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error updateStore : "+e.toString()+"\n");
				result.put("msg", "1002");
				return result;
			}

		}else{
			saveStoreVo.setName(saveDTO.getName());
			saveStoreVo.setRegId(regId);
			
			try {
				saveResult = storeService.insertStore(saveStoreVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertStore : "+e.toString()+"\n");
				result.put("msg", "1001");
				return result;
				
			}
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
		
		result.put("msg", "0000");
		result.put("data", saveResult);
		
		return result;
		
	}
	
	/**
	 * 매장 삭제
	 */
	@RequestMapping(value = "/store/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> storeDelete(@RequestBody StoreSaveDTO deleteDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"매장목록 "+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
		int deleteResult = 0;
		
		StoreVo deleteStoreVo = new StoreVo();
		deleteStoreVo.setModId(modId);
		
		if(deleteDTO.getStoreId() != null && deleteDTO.getStoreId() > 0){
			deleteStoreVo.setStoreId(deleteDTO.getStoreId());
		}
		
		try {
			deleteResult = storeService.deleteStore(deleteStoreVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error deleteResult deleteStore : "+e.toString()+"\n");
			result.put("msg", "0003");
			return result;
		}
		
		// 리포트 삭제
		try {
			reportService.deleteReportByStoreId(deleteStoreVo);
			reportService.deleteReportAdditionalByStoreId(deleteStoreVo);
			reportService.deleteReportDetailByStoreId(deleteStoreVo);
			reportService.deleteReportLocationAnalysisByStoreId(deleteStoreVo);
			reportService.deleteReportBusinessDistrictAnalysisByStoreId(deleteStoreVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService deleteReport relevant : "+e.toString()+"\n");
			result.put("msg", "0003");
			return result;
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
		
		if(deleteResult > 0){
			result.put("msg", "0000");
		}else{
			result.put("msg", "0003");
		}
		result.put("data", deleteResult);
		
		return result;
		
	}
	
	/**
	 * 신규매장 등록
	 */
	@RequestMapping(value = "/store/new", method = { RequestMethod.POST, RequestMethod.GET })
	public String storeNew(HttpServletRequest request, Model model) throws Exception {
		
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
		
		model.addAttribute("leftCode", "1");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "store/save";
	}
	
	/**
	 * 매장 정보
	 */
	@RequestMapping(value = "/store/info", method = { RequestMethod.POST, RequestMethod.GET })
	public String storeInfo(HttpServletRequest request, Model model) throws Exception {
		
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
		
		String storeId = StringUtil.nvl(request.getParameter("storeId"));
		
		Map<String, Object> param = new HashMap<>();
		param.put("storeId", storeId);
		
		StoreViewResponseDTO storeInfo = new StoreViewResponseDTO();
		try {
			
			storeInfo = storeService.storeInfoById(param);
			if(storeInfo != null){
				storeInfo.setDisplayStartDateDayPass(StringUtil.calculateTimeElapsed(storeInfo.getStartDate()));
			}
		} catch (SQLException se){
			se.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error storeService storeInfoById : "+se.toString()+"\n");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StringUtil error calculateTimeElapsed : "+e.toString()+"\n");
		}
		model.addAttribute("store", storeInfo);
		
		List<ReportChangedListVo> changedReportList = new ArrayList<ReportChangedListVo>();
		changedReportList = reportService.addrOrCategoryChanedReportList(param);
		for(ReportChangedListVo changedReport : changedReportList){
			
			try {
				if(changedReport != null){
					changedReport.setDisplaySales(StringUtil.formatNumber(changedReport.getSales()));
				}
				
				if(changedReport.getFileGroupId() != null && changedReport.getFileGroupId() > 0){
					List<FileVo> fileList = new ArrayList<FileVo>();
					Map<String, Object> fileParam = new HashMap<>();
					fileParam.put("fileGroupId", changedReport.getFileGroupId());
					fileList = commonService.fileListByGroupId(fileParam);
					changedReport.setFileList(fileList);
				}
			
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService fileListByGroupId : "+se.toString()+"\n");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StringUtil error formatNumber : "+e.toString()+"\n");
			}
		}
		model.addAttribute("reportList", changedReportList);
		
		List<CodeVo> onlineUrlList = new ArrayList<CodeVo>();
		try {
			onlineUrlList = commonService.onlineUrlList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("onlineUrlList", onlineUrlList);
		
		List<CategoryVo> mainCategoryList = new ArrayList<CategoryVo>();
		try {
			mainCategoryList = commonService.mainCategoryList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("mainCategoryList", mainCategoryList);
		
		List<CategoryVo> subCategoryList = new ArrayList<CategoryVo>();
		try {
			subCategoryList = commonService.subCategoryList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		model.addAttribute("subCategoryList", subCategoryList);
		
		model.addAttribute("leftCode", "1");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "store/info";
	}
	
	/**
	 * 매장 정보
	 */
	@RequestMapping(value = "/store/information", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> storeInformation(HttpServletRequest request, Model model) throws Exception {
		
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
		
		String storeId = StringUtil.nvl(request.getParameter("storeId"));
		
		Map<String, Object> param = new HashMap<>();
		param.put("storeId", storeId);
		
		StoreVo storeInfo = new StoreVo();
		storeInfo = storeService.storeById(param);
		model.addAttribute("info", storeInfo);
		
		List<ReportVo> changedReportList = new ArrayList<ReportVo>();
		changedReportList = reportService.addrOrCategoryChanedReportSimpleList(param);
		model.addAttribute("list", changedReportList);
		
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("store", storeInfo);
		data.put("list", changedReportList);
		result.put("data", data);

		if(storeInfo != null && storeInfo.getStoreId() > 0){
			result.put("msg", "0000");
		}else{
			result.put("msg", "0011");
		}
		
		return result;
	}
	
	/**
	 * 매장추가정보
	 */
	@RequestMapping(value = "/store/info2", method = { RequestMethod.POST, RequestMethod.GET })
	public String storeInfo2(HttpServletRequest request, Model model) throws Exception {
		
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
		
		return "store/info2";
		
	}
	
}
