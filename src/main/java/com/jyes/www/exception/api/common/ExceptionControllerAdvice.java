package com.jyes.www.exception.api.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyes.www.msg.Msg;
import com.jyes.www.utils.LogUtils;

@ControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(UserAgentException.class)
    public @ResponseBody Map<String, Object> userAgentHandleException(UserAgentException e) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<Object, Object> meta = new HashMap<Object, Object>();
		//에러 코드에 대한 메세지 정보 출력
		meta = Msg.getMeta(400);
		map.put("meta", meta);
		Log log = e.getLog();
		StringBuffer logData = e.getLogData();
		String tag = e.getTag();
		long strartTime = e.getStartTime();
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<Object, Object> map : "+map+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
        return map;
    }
	@ExceptionHandler(SystemException.class)
    public @ResponseBody Map<String, Object> handleException(SystemException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<Object, Object> meta = new HashMap<Object, Object>();
		meta = Msg.getMeta(999);
		map.put("meta", meta);
		Log log = e.getLog();
		StringBuffer logData = e.getLogData();
		String tag = e.getTag();
		long strartTime = e.getStartTime();
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<Object, Object> map : "+map+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
        return map;
    }
	@ExceptionHandler(NecessaryMissingValueException.class)
    public @ResponseBody Map<String, Object> necessaryMissingValueHandleException(NecessaryMissingValueException e) {
		Map<String, Object> map = new HashMap<String, Object>();
		HashMap<Object, Object> meta = new HashMap<Object, Object>();
		meta = Msg.getMeta(401);
		map.put("meta", meta);
		Log log = e.getLog();
		StringBuffer logData = e.getLogData();
		String tag = e.getTag();
		long strartTime = e.getStartTime();
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"HashMap<Object, Object> map : "+map+"\n");
		long endTime = System.currentTimeMillis()-strartTime; 
		logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+"\n");
		if(endTime > 15000) {
			logData.append("["+LogUtils.getCurrentTime()+"]"+" "+"tag:"+tag+":전체:endTime:"+endTime+",connection time out[15second over]"+"\n");
		}
		log.info(logData.toString());
        return map;
    }
}