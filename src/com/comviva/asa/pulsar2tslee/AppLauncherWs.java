package com.comviva.asa.pulsar2tslee;

import com.ats.slee.app_launcher.webservice.AppLauncherServiceProxy;

public class AppLauncherWs {

	private static AppLauncherWs instance = null;
	private static AppLauncherServiceProxy wsProxy = null;
	private static String appLancherServicePort = null;
	
	private AppLauncherWs(){
	}
	
	public static AppLauncherWs getInstance(){
	    if(instance == null){
	        synchronized (AppLauncherWs.class) {
	            if(instance == null){
	                instance = new AppLauncherWs();
	            }
	        }
	    }
	    return instance;
	}
	
	public void Close() {
		wsProxy = null;
		instance = null;
	}
	
	public static void InitAppLancherWs(String port) {
		appLancherServicePort = port;
		wsProxy = new AppLauncherServiceProxy(appLancherServicePort);
	}
	
	public AppLauncherServiceProxy getWsProxy() {
		return wsProxy;
	}
}

