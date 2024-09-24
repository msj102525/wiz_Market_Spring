package com.jyes.www.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;

import com.jyes.www.utils.NetUtils;

public class Config {
	
	private static final Log log = LogFactory.getLog(Config.class);
	
	/**
	 * ###dev true:개발, false: 상용
	 */
	public static boolean isDevMode = false;

	/**
	 *  log4j 파일 변수
	 */
	public static String LOG4J_XML_DEV = "log4j_dev.xml";
	public static String LOG4J_XML_DEP = "log4j_dep.xml";
	public static String LOG4J_XML = "";
	
	public static String IMAGE_HOST = "http://reportimg.jyes.co.kr/";
	public static String IMAGE_PATH = "/home/server/web/wizmarket/images/";
	
	public static void init() {
		if(isDevMode) {
			LOG4J_XML = LOG4J_XML_DEV;
		} else {
			LOG4J_XML = LOG4J_XML_DEP;
		}
		DOMConfigurator.configure(Config.class.getClassLoader().getResource(LOG4J_XML));
		if(isDevMode) {
			log.info("개발 모드 IP:"+NetUtils.getServerIp());
		} else {
			log.info("상용 모드 IP:"+NetUtils.getServerIp());
		}
	}
}
