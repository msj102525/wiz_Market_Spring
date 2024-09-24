package com.jyes.www.msg;

import java.util.HashMap;

public class Msg {
	
public final static String OK							= "200";
	public final static String NON_CONTENT				= "204";
	public final static String BAD_REQUEST				= "400";
	public final static String NECESSARY_MISSING_VALUE	= "401";
	public final static String INACCESSIBLE				= "404";
	public final static String UNKNOWN_ERROR			= "999";

	private static HashMap<Object, Object> msg = new HashMap<Object, Object>();
	
	static {
		msg.put(OK, new String[] 						{ "OK", 						"성공" });
		msg.put(NON_CONTENT, new String[]				{ "Non Content",				"클라이언트의 요구를 처리했으나 전송할 데이터가 없음." });
		msg.put(BAD_REQUEST, new String[]				{ "Bad Request",				"요청 실패. 문법상 오류" });
		msg.put(NECESSARY_MISSING_VALUE, new String[]	{ "Necessary Missing Value",	"필수 입력 값 누락" });
		msg.put(INACCESSIBLE, new String[]				{ "Inaccessible",				"해당 URL 접근 불가" });
		msg.put(UNKNOWN_ERROR, new String[] 			{ "Unknown Error",				"시스템 오류 (timeout, database, program error 등등 서버 내부의 오류 내용)" });
	}
	
	public static HashMap<Object, Object> getMeta(int code) {
		String s_code = code+"";
		HashMap<Object, Object> meta = new HashMap<Object, Object>();
		meta.put("code", code);
		if(!"".equals(s_code)) {
			if(!"200".equals(s_code)) {
				if(msg.get(s_code)!=null) {
					if(msg.get(s_code) instanceof String[]) {
						if(((String[])msg.get(s_code)).length > 0) {
							String error_type = ((String[])msg.get(s_code))[0];
							String error_message = ((String[])msg.get(s_code))[1];
							meta.put("error_type", error_type);
							meta.put("error_message", error_message);
						}
					}
				}
			}
		}
		return meta;
	}
	
}