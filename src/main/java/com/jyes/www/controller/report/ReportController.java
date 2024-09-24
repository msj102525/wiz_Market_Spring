package com.jyes.www.controller.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jyes.www.dto.common.FileInsertRequestDTO;
import com.jyes.www.dto.report.ReportAdditionalDTO;
import com.jyes.www.dto.report.ReportAdditionalInsertRequestDTO;
import com.jyes.www.dto.report.ReportAdditionalResponseDTO;
import com.jyes.www.dto.report.ReportAdditionalUpdateRequestDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisAnalysisSaveRequestDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisResponseDTO;
import com.jyes.www.dto.report.ReportDTO;
import com.jyes.www.dto.report.ReportDetailDTO;
import com.jyes.www.dto.report.ReportDetailResponseDTO;
import com.jyes.www.dto.report.ReportDetailSaveRequestDTO;
import com.jyes.www.dto.report.ReportInformationDTO;
import com.jyes.www.dto.report.ReportInformationInsertRequestDTO;
import com.jyes.www.dto.report.ReportInformationRequestDTO;
import com.jyes.www.dto.report.ReportInformationResponseDTO;
import com.jyes.www.dto.report.ReportInformationSaveDTO;
import com.jyes.www.dto.report.ReportInsertRequestDTO;
import com.jyes.www.dto.report.ReportListResponseDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisResponseDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisSaveRequestDTO;
import com.jyes.www.dto.report.ReportSearchRequestDTO;
import com.jyes.www.dto.report.ReportViewResponseDTO;
import com.jyes.www.dto.store.StoreSearchRequestDTO;
import com.jyes.www.dto.store.StoreViewResponseDTO;
import com.jyes.www.exception.api.common.NecessaryMissingValueException;
import com.jyes.www.exception.api.common.SystemException;
import com.jyes.www.msg.Msg;
import com.jyes.www.service.common.impl.ICommonService;
import com.jyes.www.service.report.impl.IReportService;
import com.jyes.www.service.store.impl.IStoreService;
import com.jyes.www.utils.FileUtil;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.SnowflakeIdGenerator;
import com.jyes.www.utils.StringUtil;
import com.jyes.www.vo.common.AreaVo;
import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FaqTitleVo;
import com.jyes.www.vo.common.FileGroupVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.InformationVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportAdditionalVo;
import com.jyes.www.vo.report.ReportBusinessDistrictAnalysisVo;
import com.jyes.www.vo.report.ReportChangedListVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportInformationVo;
import com.jyes.www.vo.report.ReportLocationAnalysisVo;
import com.jyes.www.vo.report.ReportVo;
import com.jyes.www.vo.store.StoreListVo;
import com.jyes.www.vo.store.StoreVo;

/**
 * 리포트 관련 컨트롤러
 */
@Controller
public class ReportController {
	
	private static final Log log = LogFactory.getLog(ReportController.class);
	
	@Resource(name="storeService")
	private IStoreService storeService;
	
	@Resource(name="reportService")
	private IReportService reportService;
	
	@Resource(name="commonService")
	private ICommonService commonService;
	
	/**
	 * 리포트 등록 시, 입지 분석 화면 ( location_analysis )
	 */
	@RequestMapping(value = "/report/save/location/analysis", method = { RequestMethod.POST, RequestMethod.GET })
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
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		ReportLocationAnalysisResponseDTO reportLocationView = new ReportLocationAnalysisResponseDTO();
		
		Map<String, Object> param = new HashMap<>();
		
		if(reportId > 0){
			param.put("reportId", reportId);
			try {
				reportLocationView = reportService.getReportLocationAnalysisView(param);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService getReportLocationAnalysis : "+e.toString()+"\n");
				
			}
		}
			
		// 각종 코드 목록 셋팅
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
		
		ReportLocationAnalysisResponseDTO returnReportLocationAnalysisView = new ReportLocationAnalysisResponseDTO();
		if(reportLocationView != null){
			returnReportLocationAnalysisView = reportLocationView;
		}
		model.addAttribute("locationAnalysis", returnReportLocationAnalysisView);
		model.addAttribute("genderList", genderList);
		model.addAttribute("ageGroupList", ageGroupList);
		model.addAttribute("weekList", weekList);
		model.addAttribute("timeList", timeList);
		model.addAttribute("deliveryList", deliveryList);
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
			
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
		
		return "/report/save/locationAnalysis";
	}
	
	/**
	 * 리포트 등록 시, 상권 분석 화면 ( business_district_analysis )
	 */
	@RequestMapping(value = "/report/save/business/district/analysis", method = { RequestMethod.POST, RequestMethod.GET })
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
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		ReportBusinessDistrictAnalysisResponseDTO reportBusinessDistrictView = new ReportBusinessDistrictAnalysisResponseDTO();
		
		Map<String, Object> param = new HashMap<>();
		
		if(reportId > 0){
			param.put("reportId", reportId);
			try {
				reportBusinessDistrictView = reportService.getReportBusinessDistrictAnalysisView(param);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService getReportBusinessDistrictAnalysisView : "+e.toString()+"\n");
				
			}
		}
		
		// 각종 코드 목록 셋팅
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
		
		ReportBusinessDistrictAnalysisResponseDTO returnReportBusinessDistrictAnalysisView = new ReportBusinessDistrictAnalysisResponseDTO();
		if(reportBusinessDistrictView != null){
			returnReportBusinessDistrictAnalysisView = reportBusinessDistrictView;
		}
		model.addAttribute("businessDistrictAnalysis", returnReportBusinessDistrictAnalysisView);
		model.addAttribute("genderList", genderList);
		model.addAttribute("ageGroupList", ageGroupList);
		model.addAttribute("weekList", weekList);
		model.addAttribute("timeList", timeList);
		model.addAttribute("deliveryList", deliveryList);
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/report/save/businessDistrictAnalysis";
	}
	
	/**
	 * 리포트 등록 시, 매장  정보 등록 화면 ( detail? or store_detail? )
	 */
	@RequestMapping(value = "/report/save/detail", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveDetailView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		ReportDetailResponseDTO reportDetailView = new ReportDetailResponseDTO();
		
		Map<String, Object> param = new HashMap<>();
		
		if(reportId > 0){
			param.put("reportId", reportId);
			try {
				reportDetailView = reportService.getReportDetailView(param);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService getReportDetailView : "+e.toString()+"\n");
				
			}
		}
		
		ReportDetailResponseDTO returnReportDetailView = new ReportDetailResponseDTO();
		if(reportDetailView != null){
			returnReportDetailView = reportDetailView;
		}
		model.addAttribute("reportDetail", returnReportDetailView);
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/report/save/detail";
	}
	
	/**
	 * 리포트 등록 시, 정보 등록 화면
	 */
	@RequestMapping(value = "/report/save/information", method = { RequestMethod.POST, RequestMethod.GET })
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
		
		ReportInformationResponseDTO reportInformation = new ReportInformationResponseDTO();
		ReportViewResponseDTO getReportView = new ReportViewResponseDTO();
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", reportId);
		
		try {
			getReportView = reportService.getReportView(param);
		} catch(Exception e) {
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService getReportView : "+e.toString()+"\n");
		}
		
		List<ReportInformationDTO> reportInformationList = new ArrayList<ReportInformationDTO>();
		
		// 테스트
//		ReportInformationDTO reportInfo = new ReportInformationDTO();
//		ReportInformationDTO reportInfo2 = new ReportInformationDTO();
//		reportInfo.setTitle("레포트1");
//		reportInfo.setContent("정보의 바다 정보는 많을수록 좋다 ");
//		reportInfo2.setTitle("레포트2");
//		reportInfo2.setContent("더미 데이터 넣기 귀찮아");
//		reportInformationList.add(reportInfo);
//		reportInformationList.add(reportInfo2);
		
		try{
			reportInformationList = reportService.reportInformationByReportId(param);
			
			for(ReportInformationDTO reportInformationDTO : reportInformationList){
				List<FileVo> fileList = new ArrayList<FileVo>();
				if(reportInformationDTO.getFileGroupId() != null && reportInformationDTO.getFileGroupId() > 0){
					Map<String, Object> fileParam = new HashMap<>();
					fileParam.put("fileGroupId", reportInformationDTO.getFileGroupId());
					fileList = commonService.fileListByGroupId(fileParam);
				}
				reportInformationDTO.setFileList(fileList);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService getReportView : "+e.toString()+"\n");
		}
		
		List<FaqTitleVo> faqList = new ArrayList<FaqTitleVo>();
		try {
			faqList = commonService.faqTitleList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService faqTitleList : "+e.toString()+"\n");
		}
		
		reportInformation.setView(getReportView);
		reportInformation.setReportInformationList(reportInformationList);
		
		model.addAttribute("reportInformation", reportInformation);
		model.addAttribute("faqList", faqList);
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "/report/save/information";
	}
	
	/**
	 * 리포트 신규 등록을 위한 매장 검색
	 */
	@RequestMapping(value = "/report/save/store/search", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportSaveStoreSearch(@RequestBody StoreSearchRequestDTO requestDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		List<StoreListVo> storeList = new ArrayList<StoreListVo>();
		int storeListCount = 0;
		System.out.println("/report/save/store/search");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"request DTO  : "+requestDTO+"\n");
				
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
		log.info(logData.toString());
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("list", storeList);
		data.put("count", storeListCount);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 리포트 추가 정보 등록
	 */
	@RequestMapping(value = "/report/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportSave(@ModelAttribute ReportAdditionalInsertRequestDTO insertReportDTO, HttpServletRequest request, Model model) throws Exception {
		
		StringBuffer logData = new StringBuffer();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		
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
		
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "reportSave store_id : " + insertReportDTO.getStoreId() + " \n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		int insertReport = 0;
		int insertReportAdditional = 0;
		
		ReportVo insertReportVo = new ReportVo();
		Map<String, Object> param = new HashMap<>();
		
		StoreVo updateStoreVo = new StoreVo();
		
		if( insertReportDTO.getStoreId() != null && insertReportDTO.getStoreId() > 0){
			insertReportVo.setStoreId(insertReportDTO.getStoreId());
			updateStoreVo.setStoreId(insertReportDTO.getStoreId());
			updateStoreVo.setStartDate(insertReportDTO.getStartDate());
		}else{
			result.put("msg", "0002");
			return result;
		}
		
		try {
			updateStoreVo.setModId(regId);
			storeService.updateStore(updateStoreVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error updateStore : "+e.toString()+"\n");
		}
		
		try {
			
			insertReportVo.setRegId(regId);
			// TODO : url 어떻게 할지?
			insertReportVo.setUrl("#");
			insertReport = reportService.insertReport(insertReportVo);
			
			String url = "http://report.jyes.co.kr/wizmarket/report/";
			url = url + insertReportVo.getReportId();
			insertReportVo.setUrl(url);
			
			reportService.insertReportUrl(insertReportVo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertReport : "+e.toString()+"\n");
		}
		
		if(insertReport > 0){
			data.put("reportId", insertReportVo.getReportId());
			
			// 이전 리포트 비교하기위한 검색 셋팅
			param.put("storeId", insertReportDTO.getStoreId());
			param.put("reportId", insertReportVo.getReportId());
			
			ReportAdditionalVo insertReportAdditionalVo = new ReportAdditionalVo();
			
			// insertVo setting
			insertReportAdditionalVo.setReportId(insertReportVo.getReportId());
			insertReportAdditionalVo.setMainCategoryId(insertReportDTO.getMainCategoryId());
			insertReportAdditionalVo.setSubCategoryId(insertReportDTO.getSubCategoryId());
			insertReportAdditionalVo.setAddr(insertReportDTO.getAddr());
			insertReportAdditionalVo.setAddrDetail(insertReportDTO.getAddrDetail());
			if(insertReportDTO.getOnlineUrlCategoryCd() != null && !"".equals(insertReportDTO.getOnlineUrlCategoryCd())){
				insertReportAdditionalVo.setOnlineUrlCategoryCd(insertReportDTO.getOnlineUrlCategoryCd());
			}
			if(insertReportDTO.getOnlineUrl() != null && !"".equals(insertReportDTO.getOnlineUrl())){
				insertReportAdditionalVo.setOnlineUrl(insertReportDTO.getOnlineUrl());
			}
			if(insertReportDTO.getSales() != null){
				insertReportAdditionalVo.setSales(insertReportDTO.getSales());
			}
			insertReportAdditionalVo.setRegId(regId);;
			
			// 지역 시퀀스 찾기 또는 새로 생성
			// 지역 시퀀스 찾기 setting
			AreaVo tempAreaVo = new AreaVo();
			if(insertReportDTO.getSiNm() != null && !"".equals(insertReportDTO.getSiNm())){
				tempAreaVo.setSiNm(insertReportDTO.getSiNm());
			}
			if(insertReportDTO.getSggNm() != null && !"".equals(insertReportDTO.getSggNm())){
				tempAreaVo.setSggNm(insertReportDTO.getSggNm());
			}
			if(insertReportDTO.getEmdNm() != null && !"".equals(insertReportDTO.getEmdNm())){
				tempAreaVo.setEmdNm(insertReportDTO.getEmdNm());
			}
			if(insertReportDTO.getLiNm() != null && !"".equals(insertReportDTO.getLiNm())){
				tempAreaVo.setLiNm(insertReportDTO.getLiNm());
			}
			if(insertReportDTO.getRn() != null && !"".equals(insertReportDTO.getRn())){
				tempAreaVo.setRn(insertReportDTO.getRn());
			}
			
			AreaVo searchAreaVo = new AreaVo();
			
			searchAreaVo = commonService.areaByConditionSearch(tempAreaVo);
			
			// 지역 시퀀스 찾으면 리포트 추가정보 생성에 셋팅
			if(searchAreaVo != null && searchAreaVo.getAreaId() != null && searchAreaVo.getAreaId() > 0){
				// insertVo areaId setting
				insertReportAdditionalVo.setAreaId(searchAreaVo.getAreaId());
			}else{
				// 지역 시퀀스 
				// insert setting
				int insertAreaResult = 0; 
				tempAreaVo.setRegId(regId);
				try {
					insertAreaResult = commonService.insertArea(tempAreaVo);
				} catch (Exception e) {
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertArea : "+e.toString()+"\n");
					result.put("msg", "1001");
					return result;
				}
				if(insertAreaResult > 0){
					insertReportAdditionalVo.setAreaId(tempAreaVo.getAreaId());
				}
			}
			
			// 파일이 존재하면 업로드 및 파일 그룹 아이디 생성
			if(insertReportDTO.getFileList() != null && insertReportDTO.getFileList().size() > 0){
				
				// 파일 그룹 아이디 발행
				FileGroupVo fileGroupVo = new FileGroupVo();
				try {
					fileGroupVo.setRegId(regId);
					commonService.insertFileGroup(fileGroupVo);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertFileGroup : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
				
				if(fileGroupVo.getFileGroupId() != null && fileGroupVo.getFileGroupId() > 0){
					// insertVo fileGroupId setting
					insertReportAdditionalVo.setFileGroupId(fileGroupVo.getFileGroupId());
					
					try {
						commonService.saveMultipartFiles(insertReportDTO.getFileList(), fileGroupVo.getFileGroupId(), regId);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
						result.put("msg", "1001");
						
						return result;
					}
				}
			}
			
			// 이전 리포트 추가 정보와 지역, 대분류, 중분류 비교
			String isChanged = "Y";
			
			// 이전 리포트 추가정보 조회
			ReportAdditionalVo beforeReportAdditionalVo = new ReportAdditionalVo();
			beforeReportAdditionalVo = reportService.beforeReportAdditionalByStoreId(param);
			
			if(beforeReportAdditionalVo != null && beforeReportAdditionalVo.getAreaId() != null && beforeReportAdditionalVo.getAreaId() > 0){
				if(insertReportAdditionalVo.getAreaId().equals(beforeReportAdditionalVo.getAreaId()) // 지역 시퀀스 비교
						&& insertReportAdditionalVo.getAddr().equals(beforeReportAdditionalVo.getAddr())  // 주소 비교
						&& insertReportAdditionalVo.getMainCategoryId().equals(beforeReportAdditionalVo.getMainCategoryId()) // 대분류 비교 
						&& insertReportAdditionalVo.getSubCategoryId().equals(beforeReportAdditionalVo.getSubCategoryId())){ // 중분류 비교
					if(insertReportAdditionalVo.getAddrDetail() != null 
							&& insertReportAdditionalVo.getAddrDetail().equals(beforeReportAdditionalVo.getAddrDetail())){
						isChanged = "N";
					}else if(insertReportAdditionalVo.getAddrDetail() == null && beforeReportAdditionalVo.getAddrDetail() == null){
						isChanged = "N";
					}
				}
			}
			insertReportAdditionalVo.setIsChanged(isChanged);
			try {
				insertReportAdditional = reportService.insertReportAdditional(insertReportAdditionalVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertReportAdditional : "+e.toString()+"\n");
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
		log.info(logData.toString());
		
		result.put("msg", "0000");
		data.put("result", insertReportAdditional);
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 리포트 추가 정보 수정
	 */
	@RequestMapping(value = "/report/update", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportUpdate(@ModelAttribute ReportAdditionalUpdateRequestDTO updateReportDTO, HttpServletRequest request, Model model) throws Exception {

		StringBuffer logData = new StringBuffer();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		
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
		
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "reportUpdate store_id : " + updateReportDTO.getStoreId() + " \n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
		
		int updateReport = 0;
		int updateReportAdditional = 0;
		
		Map<String, Object> param = new HashMap<>();
		
		StoreVo updateStoreVo = new StoreVo();
		
		if( updateReportDTO.getStoreId() != null && updateReportDTO.getStoreId() > 0){
			updateStoreVo.setStoreId(updateReportDTO.getStoreId());
			updateStoreVo.setStartDate(updateReportDTO.getStartDate());
			
			try {
				updateStoreVo.setModId(modId);
				storeService.updateStore(updateStoreVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error storeService updateStore : "+e.toString()+"\n");
			}
		}
		
		ReportAdditionalVo updateReportAdditionalVo = new ReportAdditionalVo();
		
		// updateVo setting
		updateReportAdditionalVo.setReportAdditionalId(updateReportDTO.getReportAdditionalId());
		updateReportAdditionalVo.setOnlineUrlCategoryCd(updateReportDTO.getOnlineUrlCategoryCd());
		updateReportAdditionalVo.setOnlineUrl(updateReportDTO.getOnlineUrl());
		updateReportAdditionalVo.setSales(updateReportDTO.getSales());
		updateReportAdditionalVo.setModId(modId);
		
		data.put("reportId", updateReportDTO.getReportId());
		
		// 파일이 존재하면 업로드 진행 및 file_group_id 없으면 발행 
		Integer fileGroupId = 0;
		
		if(updateReportDTO.getFileGroupId() != null && updateReportDTO.getFileGroupId() > 0){
			fileGroupId = updateReportDTO.getFileGroupId();
		}
		
		if(updateReportDTO.getFileList() != null && updateReportDTO.getFileList().size() > 0){
			if(fileGroupId == 0){
				// 파일 그룹 아이디 발행
				FileGroupVo fileGroupVo = new FileGroupVo();
				try {
					fileGroupVo.setRegId(modId);
					commonService.insertFileGroup(fileGroupVo);
					fileGroupId = fileGroupVo.getFileGroupId();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService insertFileGroup : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
			}
			
			if(fileGroupId != null && fileGroupId > 0){
				try {
					commonService.saveMultipartFiles(updateReportDTO.getFileList(), fileGroupId, modId);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService saveFiles : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
			}
		}
		
		if(fileGroupId != null && fileGroupId > 0){
			// updateVo fileGroupId setting
			updateReportAdditionalVo.setFileGroupId(fileGroupId);
		}
		
		// 삭제 해야할 file_id 목록이 존재하면 파일 삭제(논리) 진행 
		if(updateReportDTO.getDeleteFileIdList() != null && updateReportDTO.getDeleteFileIdList().size() > 0){
			try {
				commonService.deleteFiles(updateReportDTO.getDeleteFileIdList(), modId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService deleteFiles : "+e.toString()+"\n");
				result.put("msg", "1003");
				
				return result;
			}
		}
		
		try {
			updateReportAdditional = reportService.updateReportAdditional(updateReportAdditionalVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService updateReportAdditional : "+e.toString()+"\n");
			result.put("msg", "0002");
			
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
		log.info(logData.toString());
		
		result.put("msg", "0000");
		data.put("result", updateReportAdditional);
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 리포트 추가 정보 수정
	 */
	@RequestMapping(value = "/report/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportDelete(@RequestBody ReportAdditionalUpdateRequestDTO updateReportDTO, HttpServletRequest request, Model model) throws Exception {

		StringBuffer logData = new StringBuffer();
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		
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
		
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "reportUpdate store_id : " + updateReportDTO.getStoreId() + " \n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
		
		int updateResult = 0;
		
		ReportVo deleteReportVo = new ReportVo();
		deleteReportVo.setReportId(updateReportDTO.getReportId());
		deleteReportVo.setModId(modId);
		
		// 리포트 삭제
		try {
			updateResult = reportService.deleteReport(deleteReportVo);
			reportService.deleteReportAdditionalByReportId(deleteReportVo);
			reportService.deleteReportDetailByReportId(deleteReportVo);
			reportService.deleteReportLocationAnalysisByReportId(deleteReportVo);
			reportService.deleteReportBusinessDistrictAnalysisByReportId(deleteReportVo);
			reportService.deleteReportInformationByReportId(deleteReportVo);
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
		
		result.put("msg", "0000");
		result.put("data", updateResult);
		
		return result;
	}
	
	/**
	 * 리포트 신규 지역 입지 분석 등록/수정
	 */
	@RequestMapping(value = "/report/location/analysis/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportLocationAnalysisSave(@RequestBody ReportLocationAnalysisSaveRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer locationAnalysisId = 0;
		int resultInsertReportLocation = 1;
		
		if(saveReportDTO.getLocationAnalysisId() != null && saveReportDTO.getLocationAnalysisId() > 0){
			locationAnalysisId = saveReportDTO.getLocationAnalysisId();
		}
		
		// 입력된 지역 입지 분석 내용 셋팅
		LocationAnalysisVo saveLocationAnalysisVo = new LocationAnalysisVo();
		if(saveReportDTO.getAprtmentHousehold() != null){
			saveLocationAnalysisVo.setAprtmentHousehold(saveReportDTO.getAprtmentHousehold());
		}
		if(saveReportDTO.getAprtmentTransactionPrice() != null){
			saveLocationAnalysisVo.setAprtmentTransactionPrice(saveReportDTO.getAprtmentTransactionPrice());
		}
		if(saveReportDTO.getAprtmentResidence() != null){
			saveLocationAnalysisVo.setAprtmentResidence(saveReportDTO.getAprtmentResidence());
		}
		if(saveReportDTO.getFlowPopulation() != null){
			saveLocationAnalysisVo.setFlowPopulation(saveReportDTO.getFlowPopulation());				
		}
		if(saveReportDTO.getResidencePopulation() != null){
			saveLocationAnalysisVo.setResidencePopulation(saveReportDTO.getResidencePopulation());
		}
		if(saveReportDTO.getWorkPopulation() != null){
			saveLocationAnalysisVo.setWorkPopulation(saveReportDTO.getWorkPopulation());
		}
		if(saveReportDTO.getHousehold() != null){
			saveLocationAnalysisVo.setHousehold(saveReportDTO.getHousehold());				
		}
		if(saveReportDTO.getBusiness() != null){
			saveLocationAnalysisVo.setBusiness(saveReportDTO.getBusiness());
		}
		if(saveReportDTO.getIncome() != null){
			saveLocationAnalysisVo.setIncome(saveReportDTO.getIncome());				
		}
		
		saveLocationAnalysisVo.setFlowAgeRangeCd(saveReportDTO.getFlowAgeRangeCd());
		saveLocationAnalysisVo.setFlowTimeStartCd(saveReportDTO.getFlowTimeStartCd());
		saveLocationAnalysisVo.setFlowTimeEndCd(saveReportDTO.getFlowTimeEndCd());
		saveLocationAnalysisVo.setDeliveryGenderCd(saveReportDTO.getDeliveryGenderCd());
		saveLocationAnalysisVo.setDeliveryAgeRangeCd(saveReportDTO.getDeliveryAgeRangeCd());
		saveLocationAnalysisVo.setDeliveryWeekCd(saveReportDTO.getDeliveryWeekCd());
		saveLocationAnalysisVo.setDeliveryTimeStartCd(saveReportDTO.getDeliveryTimeStartCd());
		saveLocationAnalysisVo.setDeliveryTimeEndCd(saveReportDTO.getDeliveryTimeEndCd());
		saveLocationAnalysisVo.setDeliveryMethodCd(saveReportDTO.getDeliveryMethodCd());
		
		if(locationAnalysisId > 0){
			saveLocationAnalysisVo.setLocationAnalysisId(locationAnalysisId);
			saveLocationAnalysisVo.setModId(regId);
			try {
				commonService.updateLocationAnalysis(saveLocationAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error updateLocationAnalysis : "+e.toString()+"\n");
				result.put("msg", "0002");
				return result;
			}
		}else{
			
			saveLocationAnalysisVo.setAreaId(saveReportDTO.getAreaId());
			saveLocationAnalysisVo.setRegId(regId);
			try {
				commonService.insertLocationAnalysis(saveLocationAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertLocationAnalysis : "+e.toString()+"\n");
				result.put("msg", "0001");
				return result;
			}
			locationAnalysisId = saveLocationAnalysisVo.getLocationAnalysisId();
		}
		
		Integer reportLocationAnalysisId = 0;
		if(saveReportDTO.getReportLocationAnalysisId() != null && saveReportDTO.getReportLocationAnalysisId() > 0){
			reportLocationAnalysisId = saveReportDTO.getReportLocationAnalysisId();
		}
		
		if(reportLocationAnalysisId == 0){
			ReportLocationAnalysisVo insertReportLocationAnalysisVo = new ReportLocationAnalysisVo();
			insertReportLocationAnalysisVo.setReportId(saveReportDTO.getReportId());
			insertReportLocationAnalysisVo.setLocationAnalysisId(locationAnalysisId);
			insertReportLocationAnalysisVo.setRegId(regId);
			
			try {
				resultInsertReportLocation = reportService.insertReportLocationAnalysis(insertReportLocationAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertReportLocationAnalysis : "+e.toString()+"\n");
				result.put("msg", "0001");
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
		log.info(logData.toString());
		
		result.put("msg", "0000");
		result.put("data", resultInsertReportLocation);
		
		return result;
	}
	
	/**
	 * 리포트 신규 지역 상권 분석 등록/수정
	 */
	@RequestMapping(value = "/report/business/district/analysis/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportBusinessDistrictAnalysisSave(@RequestBody ReportBusinessDistrictAnalysisAnalysisSaveRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer businessAnalysisId = 0;
		int resultInsertReportBusiness = 1;
		
		if(saveReportDTO.getBusinessDistrictAnalysisId() != null && saveReportDTO.getBusinessDistrictAnalysisId() > 0){
			businessAnalysisId = saveReportDTO.getBusinessDistrictAnalysisId();
		}
		
		// 입력된 지역 입지 분석 내용 셋팅
		BusinessDistrictAnalysisVo saveBusinessDistrictAnalysisVoAnalysisVo = new BusinessDistrictAnalysisVo();
		
//		saveBusinessDistrictAnalysisVoAnalysisVo.setBusinessDistrictAnalysisId(businessDistrictAnalysisId);
		if(saveReportDTO.getAverageCost() != null){
			saveBusinessDistrictAnalysisVoAnalysisVo.setAverageCost(saveReportDTO.getAverageCost());
		}
		if(saveReportDTO.getAverageEarning() != null){
			saveBusinessDistrictAnalysisVoAnalysisVo.setAverageEarning(saveReportDTO.getAverageEarning());
		}
		if (saveReportDTO.getOperateProfit() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setOperateProfit(saveReportDTO.getOperateProfit());
		}
		if (saveReportDTO.getOperateExpense() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setOperateExpense(saveReportDTO.getOperateExpense());
		}
		if (saveReportDTO.getAllBusinessDensity() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setAllBusinessDensity(saveReportDTO.getAllBusinessDensity());
		}
		if (saveReportDTO.getSidoBusinessDensity() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setSidoBusinessDensity(saveReportDTO.getSidoBusinessDensity());
		}
		if (saveReportDTO.getDongBusinessDensity() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setDongBusinessDensity(saveReportDTO.getDongBusinessDensity());
		}
		if (saveReportDTO.getMarketSalesScale() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setMarketSalesScale(saveReportDTO.getMarketSalesScale());
		}
		if (saveReportDTO.getStoreSalesScale() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setStoreSalesScale(saveReportDTO.getStoreSalesScale());
		}
		if (saveReportDTO.getUnitPrice() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setUnitPrice(saveReportDTO.getUnitPrice());
		}
		if (saveReportDTO.getUses() != null) {
		    saveBusinessDistrictAnalysisVoAnalysisVo.setUses(saveReportDTO.getUses());
		}
		
		saveBusinessDistrictAnalysisVoAnalysisVo.setPeakGenderCd(saveReportDTO.getPeakGenderCd());
		saveBusinessDistrictAnalysisVoAnalysisVo.setPeakAgeRangeCd(saveReportDTO.getPeakAgeRangeCd());
		saveBusinessDistrictAnalysisVoAnalysisVo.setPeakWeekCd(saveReportDTO.getPeakWeekCd());
		saveBusinessDistrictAnalysisVoAnalysisVo.setPeakTimeStartCd(saveReportDTO.getPeakTimeStartCd());
		saveBusinessDistrictAnalysisVoAnalysisVo.setPeakTimeEndCd(saveReportDTO.getPeakTimeEndCd());
		
		if(businessAnalysisId > 0){
			saveBusinessDistrictAnalysisVoAnalysisVo.setBusinessDistrictAnalysisId(businessAnalysisId);
			saveBusinessDistrictAnalysisVoAnalysisVo.setModId(regId);
			try {
				commonService.updateBusinessDistrictAnalysis(saveBusinessDistrictAnalysisVoAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error updateBusinessDistrictAnalysis : "+e.toString()+"\n");
				result.put("msg", "0002");
				return result;
			}
		}else{
			
			saveBusinessDistrictAnalysisVoAnalysisVo.setAreaId(saveReportDTO.getAreaId());
			saveBusinessDistrictAnalysisVoAnalysisVo.setMainCategoryId(saveReportDTO.getMainCategoryId());
			saveBusinessDistrictAnalysisVoAnalysisVo.setSubCategoryId(saveReportDTO.getSubCategoryId());
			
			saveBusinessDistrictAnalysisVoAnalysisVo.setRegId(regId);
			try {
				commonService.insertBusinessDistrictAnalysis(saveBusinessDistrictAnalysisVoAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertLocationAnalysis : "+e.toString()+"\n");
				result.put("msg", "0001");
				return result;
			}
			businessAnalysisId = saveBusinessDistrictAnalysisVoAnalysisVo.getBusinessDistrictAnalysisId();
		}
		
		Integer reportBusinessAnalysisId = 0;
		if(saveReportDTO.getReportBusinessDistrictAnalysisId() != null && saveReportDTO.getReportBusinessDistrictAnalysisId() > 0){
			reportBusinessAnalysisId = saveReportDTO.getReportBusinessDistrictAnalysisId();
		}
		
		if(reportBusinessAnalysisId == 0){
			ReportBusinessDistrictAnalysisVo insertReportBusinessDistrictAnalysisVo = new ReportBusinessDistrictAnalysisVo();
			insertReportBusinessDistrictAnalysisVo.setReportId(saveReportDTO.getReportId());
			insertReportBusinessDistrictAnalysisVo.setBusinessDistrictAnalysisId(businessAnalysisId);
			insertReportBusinessDistrictAnalysisVo.setRegId(regId);
			
			try {
				resultInsertReportBusiness = reportService.insertReportBusinessDistrictAnalysis(insertReportBusinessDistrictAnalysisVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertReportBusinessDistrictAnalysisVo : "+e.toString()+"\n");
				result.put("msg", "0001");
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
		log.info(logData.toString());
		
		result.put("msg", "0000");
		result.put("data", resultInsertReportBusiness);
		
		return result;
	}
	
	/**
	 * 리포트 신규 세부 등록/수정
	 */
	@RequestMapping(value = "/report/detail/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportDetailSave(@RequestBody ReportDetailSaveRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer reportDetailId = 0;
		int resultSaveDetail = 0;
		
		if(saveReportDTO.getReportDetailId() != null && saveReportDTO.getReportDetailId() > 0){
			reportDetailId = saveReportDTO.getReportDetailId();
		}
		
		// 입력된 지역 입지 분석 내용 셋팅
		ReportDetailVo saveReportDetailVo = new ReportDetailVo();
		
		if(saveReportDTO.getSubwayDistance() != null){
			saveReportDetailVo.setSubwayDistance(saveReportDTO.getSubwayDistance());
		}
		if(saveReportDTO.getBusDistance() != null){
			saveReportDetailVo.setBusDistance(saveReportDTO.getBusDistance());
		}
		if(saveReportDTO.getRoadDistance() != null){
			saveReportDetailVo.setRoadDistance(saveReportDTO.getRoadDistance());
		}
		
		saveReportDetailVo.setReportId(saveReportDTO.getReportId());
		saveReportDetailVo.setGptsStrategy(saveReportDTO.getGptsStrategy());
		saveReportDetailVo.setGptsSlogan(saveReportDTO.getGptsSlogan());
		
		if(reportDetailId > 0){
			saveReportDetailVo.setReportDetailId(reportDetailId);
			saveReportDetailVo.setModId(regId);
			try {
				resultSaveDetail = reportService.updateReportDetail(saveReportDetailVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error updateReportDetail : "+e.toString()+"\n");
				result.put("msg", "0002");
				return result;
			}
		}else{
			
			saveReportDetailVo.setRegId(regId);
			try {
				resultSaveDetail = reportService.insertReportDetail(saveReportDetailVo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertReportDetail : "+e.toString()+"\n");
				result.put("msg", "0001");
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
		log.info(logData.toString());
		
		result.put("msg", "0000");
		result.put("data", resultSaveDetail);
		
		return result;
	}
	
	/**
	 * 리포트 신규 정보 등록
	 */
	@RequestMapping(value = "/report/information/insert", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportInformationInsert(@ModelAttribute ReportInformationRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
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
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer fileGroupId = 0;
		
		int viewSeq = 0;
		
		int saveReportInformation = 0;
		
		boolean saveSkip = true;
		if(saveReportDTO.getTitle() == null){
			saveSkip = false;
		}else if("".equals(saveReportDTO.getTitle())){
			saveSkip = false;
		}
		
		ReportInformationVo saveReportInformationDTO = new ReportInformationVo();
		
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", saveReportDTO.getReportId());
		viewSeq = reportService.getReportInformationLastViewSeq(param);
		
		if(saveSkip){
			saveReportInformationDTO.setTitle(saveReportDTO.getTitle());
			saveReportInformationDTO.setContent(saveReportDTO.getContent());
			
			// 파일이 존재하면 업로드 및 파일 그룹 아이디 생성
			if(saveReportDTO.getFileList() != null && saveReportDTO.getFileList().size() > 0){
				
				if(fileGroupId == 0){
					// 파일 그룹 아이디 발행
					FileGroupVo fileGroupVo = new FileGroupVo();
					try {
						fileGroupVo.setRegId(regId);
						commonService.insertFileGroup(fileGroupVo);
						fileGroupId = fileGroupVo.getFileGroupId();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertFileGroup : "+e.toString()+"\n");
						result.put("msg", "1001");
						
						return result;
					}
				}
				
				try {
					commonService.saveMultipartFiles(saveReportDTO.getFileList(), fileGroupId, regId);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
			}
			
			// 파일 그룹 아이디 셋팅
			if(fileGroupId > 0){
				saveReportInformationDTO.setFileGroupId(fileGroupId);
			}
			
			// 리포트 정보 저장
			try {
				saveReportInformationDTO.setReportId(saveReportDTO.getReportId());
				saveReportInformationDTO.setViewSeq(viewSeq);
				saveReportInformationDTO.setRegId(regId);
				saveReportInformation = reportService.insertReportInformation(saveReportInformationDTO);
				viewSeq++;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
				result.put("msg", "1001");
				return result;
			}
		}
		
		// 마지막 저장 && 리포트 발행 시, 공통정보 저장하기
		if("Y".equals(saveReportDTO.getIsIssued())){
			
			// common information 공통 정보 찾기
			List<InformationVo> informationList = new ArrayList<InformationVo>(); 
			informationList = commonService.commonInformationByAll();
			
			if(informationList != null && informationList.size() > 0){
				
				for(InformationVo insertInformationVo : informationList){
					ReportInformationVo insertReportInformationDTO = new ReportInformationVo();
					insertReportInformationDTO.setCommonInformationId(insertInformationVo.getCommonInformationId());
					insertReportInformationDTO.setReportId(saveReportDTO.getReportId());
					insertReportInformationDTO.setTitle(insertInformationVo.getTitle());
					insertReportInformationDTO.setContent(insertInformationVo.getContent());
					// TODO : 이미지도 공통정보 따라가게 만드는지? 개개인별 이미지 및 내용 컨트롤이면 파일 그룹 생성하고 파일 복사하는 로직 생성해야함
					if(insertInformationVo.getFileGroupId()!= null && insertInformationVo.getFileGroupId() > 0){
						insertReportInformationDTO.setFileGroupId(insertInformationVo.getFileGroupId());
					}
					insertReportInformationDTO.setViewSeq(viewSeq);
					insertReportInformationDTO.setRegId(regId);
					
					viewSeq++;
					
					try {
						saveReportInformation = reportService.insertReportInformation(insertReportInformationDTO);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService insertReportInformation : "+e.toString()+"\n");
						result.put("msg", "1001");
						return result;
					}
					
					
				}
			}
		}else{
			// TODO : 이슈발행이 아니기때문에 row 한줄 넣어줘야해서 신규 등록 정보 추가
			ReportInformationDTO information = new ReportInformationDTO();
			param.put("reportInformationId", saveReportInformationDTO.getReportInformationId());
			information = reportService.getReportInformationById(param);
			
			if(information.getFileGroupId() != null && information.getFileGroupId() > 0){
				Map<String, Object> fileParam = new HashMap<>();
				fileParam.put("fileGroupId", information.getFileGroupId());
				List<FileVo> fileList = new ArrayList<FileVo>();
				try {
					fileList = commonService.fileListByGroupId(fileParam);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				information.setFileList(fileList);
			}
			data.put("information", information);
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
		
		data.put("result", saveReportInformation);
		
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 리포트 정보 수정
	 */
	@RequestMapping(value = "/report/information/update", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportInformationUpdate(@ModelAttribute ReportInformationRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
		
		Integer fileGroupId = 0;
		
		int saveReportInformation = 0;
		
		ReportInformationVo saveReportInformationDTO = new ReportInformationVo();
		saveReportInformationDTO.setTitle(saveReportDTO.getTitle());
		saveReportInformationDTO.setContent(saveReportDTO.getContent());
		
		if(saveReportDTO.getFileGroupId() != null && saveReportDTO.getFileGroupId() > 0){
			fileGroupId = saveReportDTO.getFileGroupId();
		}
		
		// 파일이 존재하면 업로드 및 파일 그룹 아이디 생성
		if(saveReportDTO.getFileList() != null && saveReportDTO.getFileList().size() > 0){
			
			if(fileGroupId == 0){
				// 파일 그룹 아이디 발행
				FileGroupVo fileGroupVo = new FileGroupVo();
				try {
					fileGroupVo.setRegId(modId);
					commonService.insertFileGroup(fileGroupVo);
					fileGroupId = fileGroupVo.getFileGroupId();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertFileGroup : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
			}
			
			try {
				commonService.saveMultipartFiles(saveReportDTO.getFileList(), fileGroupId, modId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
				result.put("msg", "1001");
				
				return result;
			}
		}
		
		// 삭제 할 파일이 존재하면 제거 실행
		if(saveReportDTO.getDeleteFileIdList() != null && saveReportDTO.getDeleteFileIdList().size() > 0){
			try {
				commonService.deleteFiles(saveReportDTO.getDeleteFileIdList(), modId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService deleteFiles : "+e.toString()+"\n");
				result.put("msg", "1003");
				
				return result;
			}
		}
		
		// 파일 그룹 아이디 셋팅
		if(fileGroupId > 0){
			saveReportInformationDTO.setFileGroupId(fileGroupId);
		}
		
		// 리포트 공통 정보 수정
		try {
			saveReportInformationDTO.setReportInformationId(saveReportDTO.getReportInformationId());
			saveReportInformationDTO.setModId(modId);
			saveReportInformation = reportService.updateReportInformation(saveReportInformationDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService updateReportInformation : "+e.toString()+"\n");
			result.put("msg", "1001");
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
		log.info(logData.toString());
		
		
		result.put("msg", "0000");
		result.put("data", saveReportInformation);
		
		return result;
	}
	
	/**
	 * 리포트 정보 수정
	 */
	@RequestMapping(value = "/report/information/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportInformationDelete(@RequestBody ReportInformationRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
		
		Integer fileGroupId = 0;
		
		int saveReportInformation = 0;
		
		ReportInformationVo deleteReportInformationDTO = new ReportInformationVo();
		deleteReportInformationDTO.setReportInformationId(saveReportDTO.getReportInformationId());
		deleteReportInformationDTO.setModId(modId);
		
		// 리포트 공통 정보 수정
		try {
			saveReportInformation = reportService.deleteReportInformation(deleteReportInformationDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService deleteReportInformation : "+e.toString()+"\n");
			result.put("msg", "1001");
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
		log.info(logData.toString());
		
		
		result.put("msg", "0000");
		result.put("data", saveReportInformation);
		
		return result;
	}
	
	/**
	 * 리포트 신규 정보 등록
	 */
	@RequestMapping(value = "/report/information/save", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportInformationSave(@ModelAttribute ReportInformationRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		Integer fileGroupId = 0;
		
		int viewSeq = 0;
		
		int saveReportInformation = 0;
		
		ReportInformationVo saveReportInformationDTO = new ReportInformationVo();
		saveReportInformationDTO.setTitle(saveReportDTO.getTitle());
		saveReportInformationDTO.setContent(saveReportDTO.getContent());
		
		if(saveReportDTO.getFileGroupId() != null && saveReportDTO.getFileGroupId() > 0){
			fileGroupId = saveReportDTO.getFileGroupId();
		}
		
		// 파일이 존재하면 업로드 및 파일 그룹 아이디 생성
		if(saveReportDTO.getFileList() != null && saveReportDTO.getFileList().size() > 0){
			
			if(fileGroupId == 0){
				// 파일 그룹 아이디 발행
				FileGroupVo fileGroupVo = new FileGroupVo();
				try {
					fileGroupVo.setRegId(regId);
					commonService.insertFileGroup(fileGroupVo);
					fileGroupId = fileGroupVo.getFileGroupId();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertFileGroup : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
			}
			
			try {
				commonService.saveMultipartFiles(saveReportDTO.getFileList(), fileGroupId, regId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
				result.put("msg", "1001");
				
				return result;
			}
		}
		
		// 삭제 할 파일이 존재하면 제거 실행
		if(saveReportDTO.getDeleteFileIdList() != null && saveReportDTO.getDeleteFileIdList().size() > 0){
			try {
				commonService.deleteFiles(saveReportDTO.getDeleteFileIdList(), regId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error commonService deleteFiles : "+e.toString()+"\n");
				result.put("msg", "1003");
				
				return result;
			}
		}
		
		// 파일 그룹 아이디 셋팅
		if(fileGroupId > 0){
			saveReportInformationDTO.setFileGroupId(fileGroupId);
		}
		
		// 리포트 공통 정보 수정
		if(saveReportDTO.getReportInformationId() != null && saveReportDTO.getReportInformationId() > 0){
			
			
			try {
				saveReportInformationDTO.setModId(regId);
				saveReportInformation = reportService.updateReportInformation(saveReportInformationDTO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService updateReportInformation : "+e.toString()+"\n");
				result.put("msg", "1001");
				return result;
			}
			
		// 리포트 정보 저장
		}else{
			try {
				Map<String, Object> param = new HashMap<>();
				param.put("reportId", saveReportDTO.getReportId());
				viewSeq = reportService.getReportInformationLastViewSeq(param);
				
				saveReportInformationDTO.setReportId(saveReportDTO.getReportId());
				saveReportInformationDTO.setViewSeq(viewSeq);
				saveReportInformationDTO.setRegId(regId);
				saveReportInformation = reportService.insertReportInformation(saveReportInformationDTO);
				viewSeq++;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
				result.put("msg", "1001");
				return result;
			}
			
			// 마지막 저장 && 리포트 발행 시, 공통정보 저장하기
			if("Y".equals(saveReportDTO.getIsIssued())){
				
				// common information 공통 정보 찾기
				List<InformationVo> informationList = new ArrayList<InformationVo>(); 
				informationList = commonService.commonInformationByAll();
				
				if(informationList != null && informationList.size() > 0){
					
					for(InformationVo insertInformationVo : informationList){
						ReportInformationVo insertReportInformationDTO = new ReportInformationVo();
						insertReportInformationDTO.setCommonInformationId(insertInformationVo.getCommonInformationId());
						insertReportInformationDTO.setReportId(saveReportDTO.getReportId());
						insertReportInformationDTO.setTitle(insertInformationVo.getTitle());
						insertReportInformationDTO.setContent(insertInformationVo.getContent());
						// TODO : 이미지도 공통정보 따라가게 만드는지? 개개인별 이미지 및 내용 컨트롤이면 파일 그룹 생성하고 파일 복사하는 로직 생성해야함
						if(insertInformationVo.getFileGroupId()!= null && insertInformationVo.getFileGroupId() > 0){
							insertReportInformationDTO.setFileGroupId(insertInformationVo.getFileGroupId());
						}
						insertReportInformationDTO.setViewSeq(viewSeq);
						insertReportInformationDTO.setRegId(regId);
						
						viewSeq++;
						
						try {
							saveReportInformation = reportService.insertReportInformation(insertReportInformationDTO);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService insertReportInformation : "+e.toString()+"\n");
							result.put("msg", "1001");
							return result;
						}
						
						
					}
				}
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
		log.info(logData.toString());
		
		
		result.put("msg", "0000");
		result.put("data", saveReportInformation);
		
		return result;
	}
	
	/**
	 * 리포트 신규 정보 등록
	 */
	@RequestMapping(value = "/report/information/saves", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportInformationSaves(@ModelAttribute ReportInformationInsertRequestDTO saveReportDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"logKey : "+logKey+" : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"StartUrl : "+StartUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CurrentUrl : "+currentUrl+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"CallUrl : "+StringUtil.nvl((String)request.getHeader("REFERER"))+"\n");
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"requestMap : "+requestMap+"\n");
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int regId = 1;
		
		// 리포트 정보는 무조건 5번째부터 시작
		int viewSeq = 5;
		
		int insertResult = 0;
		int insertReportInformation = 0;
		
		Integer reportId = 0;
		int reportInformationCount = 0;
		int commonInformationCount = 0;
		
		if(saveReportDTO.getReportId() != null && saveReportDTO.getReportId() > 0){
			reportId = saveReportDTO.getReportId();
		}
		
		if(saveReportDTO.getReportInformationList() != null && saveReportDTO.getReportInformationList().size() > 0){
			reportInformationCount = saveReportDTO.getReportInformationList().size();
		}
		
		for(ReportInformationSaveDTO reportInformationDTO : saveReportDTO.getReportInformationList()){
			ReportInformationVo insertReportInformationDTO = new ReportInformationVo();
			insertReportInformationDTO.setReportId(reportId);
			insertReportInformationDTO.setTitle(reportInformationDTO.getTitle());
			insertReportInformationDTO.setContent(reportInformationDTO.getContent());
			insertReportInformationDTO.setViewSeq(viewSeq);
			insertReportInformationDTO.setRegId(regId);
			
			viewSeq++;
			
			// 파일이 존재하면 업로드 및 파일 그룹 아이디 생성
			if(reportInformationDTO.getFileList() != null && reportInformationDTO.getFileList().size() > 0){
				
				// 파일 그룹 아이디 발행
				FileGroupVo fileGroupVo = new FileGroupVo();
				try {
					fileGroupVo.setRegId(regId);
					commonService.insertFileGroup(fileGroupVo);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error insertFileGroup : "+e.toString()+"\n");
					result.put("msg", "1001");
					
					return result;
				}
				
				if(fileGroupVo.getFileGroupId() != null && fileGroupVo.getFileGroupId() > 0){
					// insertVo fileGroupId setting
					insertReportInformationDTO.setFileGroupId(fileGroupVo.getFileGroupId());
					
					try {
						commonService.saveMultipartFiles(reportInformationDTO.getFileList(), fileGroupVo.getFileGroupId(), regId);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
						result.put("msg", "1001");
						
						return result;
					}
				}
			}
			
			try {
				insertReportInformation += reportService.insertReportInformation(insertReportInformationDTO);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
				result.put("msg", "1001");
				return result;
			}
			
		}
		
		// common information 공통 정보 찾기
		List<InformationVo> informationList = new ArrayList<InformationVo>(); 
		informationList = commonService.commonInformationByAll();
		
		if(informationList != null && informationList.size() > 0){
			commonInformationCount = informationList.size();
			
			for(InformationVo insertInformationVo : informationList){
				ReportInformationVo insertReportInformationDTO = new ReportInformationVo();
				insertReportInformationDTO.setCommonInformationId(insertInformationVo.getCommonInformationId());
				insertReportInformationDTO.setReportId(reportId);
				insertReportInformationDTO.setTitle(insertInformationVo.getTitle());
				insertReportInformationDTO.setContent(insertInformationVo.getContent());
				// TODO : 이미지도 공통정보 따라가게 만드는지? 개개인별 이미지 및 내용 컨트롤이면 파일 그룹 생성하고 파일 복사하는 로직 생성해야함
				if(insertInformationVo.getFileGroupId()!= null && insertInformationVo.getFileGroupId() > 0){
					insertReportInformationDTO.setFileGroupId(insertInformationVo.getFileGroupId());
				}
				insertReportInformationDTO.setViewSeq(viewSeq);
				insertReportInformationDTO.setRegId(regId);
				
				viewSeq++;
				
				try {
					insertReportInformation += reportService.insertReportInformation(insertReportInformationDTO);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error saveFiles : "+e.toString()+"\n");
					result.put("msg", "1001");
					return result;
				}
				
				
			}
		}
		
		if(insertReportInformation == reportInformationCount + commonInformationCount){
			insertResult = 1;
		}else{
			insertResult = 0;
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
		
		
		result.put("msg", "0000");
		result.put("data", insertResult);
		
		return result;
	}
	
	/**
	 * 리포트목록
	 */
	@RequestMapping(value = "/report/list", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportList(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트목록 "+"\n");
		
		model.addAttribute("leftCode", "0");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "report/list";
	}
	
	/**
	 * 리포트 검색
	 */
	@RequestMapping(value = "/report/search", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportSearch(@RequestBody ReportSearchRequestDTO requestDTO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트목록 "+"\n");
		
		List<ReportListResponseDTO> reportList = new ArrayList<ReportListResponseDTO>();
		int reportListCount = 0;
				
		try {
			reportList = reportService.reportListByConditionSearch(requestDTO);
			reportListCount = reportService.reportListByConditionSearchCount(requestDTO);
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		data.put("list", reportList);
		data.put("count", reportListCount);
		result.put("msg", "0000");
		result.put("data", data);
		
		return result;
	}
	
	/**
	 * 리포트 목록
	 */
	@RequestMapping(value = "/report/save/list", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportSaveStoreList(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트목록 "+"\n");
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		return "report/save/list";
		
	}
	
	/**
	 * 리포트상세보기
	 */
	@RequestMapping(value = "/report/info", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportInfo(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트정보 "+"\n");
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
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
		
		return "report/info";
		
	}
	
	/**
	 * 리포트 뷰
	 */
	@RequestMapping(value = "/report/view", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportView(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트정보 "+"\n");
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		
		
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
	 * 리포트블록상세보기
	 */
	@RequestMapping(value = "/report/block_info", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportBlockInfo(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트블록정보 "+"\n");
		
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
		
		return "report/block_info";
		
	}
	
	/**
	 * 공통정보등록
	 */
	@RequestMapping(value = "/report/common_info", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportCommonInfo(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트공통정보 "+"\n");
		
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
		
		return "report/common_info";
		
	}
	
	/**
	 * 신규리포트 등록
	 */
	@RequestMapping(value = "/report/save/additional", method = { RequestMethod.POST, RequestMethod.GET })
	public String reportSaveAdditional(HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"신규 리포트 등록 "+"\n");
		
		Map<String, Object> param = new HashMap<>();
		Integer storeId = Integer.parseInt(StringUtil.nvl(request.getParameter("storeId"), "0"));
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		ReportAdditionalResponseDTO reportAdditional = new ReportAdditionalResponseDTO();
		if(reportId > 0){
			param.put("reportId", reportId);
			try {
				reportAdditional = reportService.getReportAdditional(param);
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
			storeId = reportAdditional.getStoreId();
		}
		
		param.put("storeId", storeId);
		
		
		StoreViewResponseDTO store = new StoreViewResponseDTO();
		try {
			store = storeService.storeInfoById(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		List<CodeVo> onlineUrlList = new ArrayList<CodeVo>();
		try {
			onlineUrlList = commonService.onlineUrlList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("leftCode", "2");//left메뉴 선택 코드(0부터 차례대로)
		
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
		
		model.addAttribute("store", store);
		model.addAttribute("reportAdditional", reportAdditional);
		model.addAttribute("onlineUrlList", onlineUrlList);
		log.info(logData.toString());
		
		return "/report/save/additional";
	}
	
	/**
	 * 변경된 리포트 정보
	 */
	@RequestMapping(value = "/report/changed", method = { RequestMethod.POST, RequestMethod.GET })
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
		
		Integer reportId = Integer.parseInt(StringUtil.nvl(request.getParameter("reportId"), "0"));
		
		Map<String, Object> param = new HashMap<>();
		param.put("reportId", reportId);
		
		ReportChangedListVo changedReport = new ReportChangedListVo();
		try {
			changedReport = reportService.addrOrCategoryChanedReport(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService addrOrCategoryChanedReport : "+e.toString()+"\n");
			
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("report", changedReport);
		result.put("data", data);

		if(changedReport != null && changedReport.getReportId() > 0){
			result.put("msg", "0000");
		}else{
			result.put("msg", "0011");
		}
		
		return result;
	}
	
	/**
	 * 리포트 노출 여부 변경
	 */
	@RequestMapping(value = "/report/change/expose", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody HashMap<String, Object> reportSearch(@RequestBody ReportVo requestVO, HttpServletRequest request, Model model) throws Exception {
		
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
		
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"리포트목록 "+"\n");
		
		List<ReportListResponseDTO> reportList = new ArrayList<ReportListResponseDTO>();
		int changedExposedResult = 0;
		
		// TODO : 로그인 기능이없어 임의로 아이디값 추가
		int modId = 1;
				
		try {
			requestVO.setModId(modId);
			changedExposedResult = reportService.changeExposureReport(requestVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"query error reportService changeExposureReport : "+e.toString()+"\n");
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
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("msg", "0000");
		result.put("data", changedExposedResult);
		
		return result;
	}

}
