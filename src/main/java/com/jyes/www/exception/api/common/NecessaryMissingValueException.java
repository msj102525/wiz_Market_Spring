package com.jyes.www.exception.api.common;

import org.apache.commons.logging.Log;

public class NecessaryMissingValueException extends Exception {
	private Log log;
	private StringBuffer logData;
	private String tag;
	private long strartTime;
	public NecessaryMissingValueException(Throwable exc) {
		super(exc);
	}

	public NecessaryMissingValueException(String exc) {
		super(exc);
	}

	public NecessaryMissingValueException() {
		super();
	}

	public NecessaryMissingValueException(long strartTime, StringBuffer logData, String tag, Log log) {
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