package com.comviva.asa.pulsar2tslee;

import ats.dbLogger.sources.LoggerProxy;

public class LoggerServiceWs {

	private static LoggerServiceWs instance = null;
	private static LoggerProxy wsProxy = null;
	private static String loggerServicePort = null;

	private LoggerServiceWs(){ }
	
	public static LoggerServiceWs getInstance(){
	    if(instance == null){
	        synchronized (LoggerServiceWs.class) {
	            if(instance == null){
	                instance = new LoggerServiceWs();
	            }
	        }
	    }
	    return instance;
	}
	
	public void Close() {
		wsProxy = null;
		instance = null;
	}

	public static void InitLoggerServiceWs(String port) {
		loggerServicePort = port;
		wsProxy = new LoggerProxy(loggerServicePort);
	}
	
	public LoggerProxy getWsProxy() {
		return wsProxy;
	}
}