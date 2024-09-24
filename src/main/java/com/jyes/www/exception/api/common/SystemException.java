package com.jyes.www.exception.api.common;

import org.apache.commons.logging.Log;

public class SystemException extends Exception {
	private Log log;
	private StringBuffer logData;
	private String tag;
	private long strartTime;
	public SystemException(Throwable exc) {
		super(exc);
	}

	public SystemException(String exc) {
		super(exc);
	}

	public SystemException() {
		super();
	}

	public SystemException(long strartTime, StringBuffer logData, String tag, Log log) {
		this.logData = logData;
		this.strartTime = strartTime;
		this.tag = tag;
		this.log = log;
	}

	public String getMessage() {
		return super.getMessage();
	}

	public StringBuffer getLogData() {
		return this.logData;
	}

	public long getStartTime() {
		return this.strartTime;
	}

	public String getTag() {
		return this.tag;
	}

	public Log getLog() {
		return this.log;
	}
	
}