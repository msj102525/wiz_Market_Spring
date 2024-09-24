package com.jyes.www.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class LogUtils {

	/**
	 * Request
	 * 
	 * @throws Exception
	 */
	public static HashMap<Object, Object> GetPrameterMap(HttpServletRequest request, StringBuffer logData) {
		logData.append("\n");
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "접속 IP : " + request.getRemoteAddr()+"\n");
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- GetPrameterMap 시작 ----\n");
		HashMap<Object, Object> hp = new HashMap<Object, Object>();
		Map<String, String[]> parameter = request.getParameterMap();
		Iterator<String> it = parameter.keySet().iterator();
		Object key = null;
		String[] value = null;
		while (it.hasNext()) {
			key = it.next();
			value = (String[]) parameter.get(key);
			for (int i = 0; i < value.length; i++) {
				try {
					value[i] = StringUtil.nvl((String) value[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(value[i].length()>1000) {
					logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + key + " : " + value[i].substring(0, 1000) +"..."+ "\n");
				} else {
					logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + key + " : " + value[i] + "\n");
				}
				hp.put(key, value[i]);
			}
		}
		logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "---- GetPrameterMap 종료 ----\n");
		return hp;
	}

	/**
	 * 현재시간가져오기
	 * 
	 * @param arg
	 *            return ex)2014-24-10 14:24:17
	 */
	public static String getCurrentTime() {
		long time;
		String ltime;
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = System.currentTimeMillis();
		ltime = dayTime.format(new Date(time));
		return ltime;
	}

	/**
	 * 사용자의 고유식별키를 가져온다
	 * 
	 * @param arg
	 * return loginid or said
	 */
	public static String getUserLogKey(HttpServletRequest request) {
		String returnValue = "";
		try {
			returnValue = StringUtil.nvl(request.getParameter("salt_id"));
			if("".equals(returnValue)) {
				returnValue = "none";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return returnValue;
	}

}
