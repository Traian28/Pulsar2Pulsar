package com.comviva.asa.pulsar2tslee;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ats_connection.configuration.ConfigurationException;
import com.ats_connection.configuration.DynamicConfiguration;
import com.comviva.asa.app2pulsar.ws.PulsarInterfaceConfiguration;
import com.comviva.pulsarlib.manager.PulsarManagerConfiguration;

public class Pulsar2TsleeConfiguration {

	private static final String     ROOT_CONFIGURATION_PATH = "/configuration/pulsar_2_tslee";
	private static final String     INSTANCE_NAME_PATH		= ROOT_CONFIGURATION_PATH + "/name";
	private static final String     SITE_NAME_PATH    		= ROOT_CONFIGURATION_PATH + "/site_name";
	private static final String     APP_LAUNCHER_SERVICE_PORT_PATH	= ROOT_CONFIGURATION_PATH + "/app_launcher/service_port";
	private static final String     APP_LAUNCHER_APPLICATIONS_PATH	= ROOT_CONFIGURATION_PATH + "/app_launcher/applications/application_";
	private static final String     LOGGER_SERVICE_PORT	    = ROOT_CONFIGURATION_PATH + "/logger_service_port";
			
	private String instanceName = null;
	private String siteName = null;
	private String appLauncherServicePort = null;
	private String loggerServicePort = null;

	private Map<String, String> appLauncherApplications;
	private PulsarManagerConfiguration pulsarServiceConfiguration;
	private PulsarInterfaceConfiguration pulsarInterfaceConfiguration;
	
	// -----------------------------------------------------------------------------------------------
	public Pulsar2TsleeConfiguration( DynamicConfiguration dynamicConfiguration ) throws ConfigurationException {
	
		instanceName = dynamicConfiguration.getNodeValue(INSTANCE_NAME_PATH, null);
		siteName 	 = dynamicConfiguration.getNodeValue(SITE_NAME_PATH, null);
		appLauncherServicePort = dynamicConfiguration.getNodeValue(APP_LAUNCHER_SERVICE_PORT_PATH, null);
		loggerServicePort = dynamicConfiguration.getNodeValue(LOGGER_SERVICE_PORT, null);
		
		appLauncherApplications = new HashMap<String, String>();
		int i = 0;
		String applicationNode = APP_LAUNCHER_APPLICATIONS_PATH + i; 
		while (dynamicConfiguration.existsNode(applicationNode)) {
			String topicNode = applicationNode  + "/topic";
			String appNode = applicationNode  + "/appName";
			appLauncherApplications.put(dynamicConfiguration.getNodeValue(topicNode, ""), dynamicConfiguration.getNodeValue(appNode, ""));
			i++;
			applicationNode = APP_LAUNCHER_APPLICATIONS_PATH + i;
		}
		
		pulsarServiceConfiguration = new PulsarManagerConfiguration(dynamicConfiguration, ROOT_CONFIGURATION_PATH);
		pulsarInterfaceConfiguration = new PulsarInterfaceConfiguration(dynamicConfiguration, ROOT_CONFIGURATION_PATH); 
	}

	// -----------------------------------------------------------------------------------------------
	public String getInstanceName() {
		return instanceName;
	}

	// -----------------------------------------------------------------------------------------------
	public String getSiteName() {
		return siteName;
	}

	// -----------------------------------------------------------------------------------------------
	public String getLoggerServicePort() {
		return loggerServicePort;
	}
	
	// -----------------------------------------------------------------------------------------------
	public String getAppLauncherServicePort() {
		return appLauncherServicePort;
	}

	
	public Map<String, String> 	getAppLauncherApplications() {
		return appLauncherApplications;
	}
	
	// -----------------------------------------------------------------------------------------------
	public PulsarManagerConfiguration getPulsarServiceConfiguration() {
		return pulsarServiceConfiguration;
	}

	// -----------------------------------------------------------------------------------------------
	public PulsarInterfaceConfiguration getPulsarInterfaceConfiguration() {
		return pulsarInterfaceConfiguration;
	}
	
	// -----------------------------------------------------------------------------------------------
	@Override	
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instanceName == null) ? 0 : instanceName.hashCode());
		result = prime * result + ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((loggerServicePort == null) ? 0 : loggerServicePort.hashCode());
		result = prime * result + ((appLauncherServicePort == null) ? 0 : appLauncherServicePort.hashCode());
		result = prime * result + ((appLauncherApplications == null) ? 0 : appLauncherApplications.hashCode());
		result = prime * result + ((pulsarServiceConfiguration == null) ? 0 : pulsarServiceConfiguration.hashCode());
		result = prime * result + ((pulsarInterfaceConfiguration == null) ? 0 : pulsarInterfaceConfiguration.hashCode());
		return result;
	}
	
	// -----------------------------------------------------------------------------------------------
	@Override
	public boolean equals( Object obj ) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final Pulsar2TsleeConfiguration other = (Pulsar2TsleeConfiguration) obj;
		
		if (instanceName == null) {
			if (other.instanceName != null) {
				return false;
			}
		}
		else if (!instanceName.equals(other.instanceName)) { 
			return false;
		}
		
		if (siteName == null) {
			if (other.siteName != null) {
				return false;
			}
		}
		else if (!siteName.equals(other.siteName)) { 
			return false;
		}

		if (loggerServicePort == null) {
			if (other.loggerServicePort != null) {
				return false;
			}
		}
		else if (!loggerServicePort.equals(other.loggerServicePort)) { 
			return false;
		}
		
		if (appLauncherServicePort == null) {
			if (other.appLauncherServicePort != null) {
				return false;
			}
		}
		else if (!appLauncherServicePort.equals(other.appLauncherServicePort)) { 
			return false;
		}
		
		if (appLauncherApplications == null) {
			if (other.appLauncherApplications != null) {
				return false;
			}
		}
		else if (!appLauncherApplications.equals(other.appLauncherApplications)) { 
			return false;
		}
		
		if (pulsarServiceConfiguration == null) {
			if (other.pulsarServiceConfiguration != null) {
				return false;
			}
		}
		else if (!pulsarServiceConfiguration.equals(other.pulsarServiceConfiguration)) {
			return false;
		}
		if (pulsarInterfaceConfiguration == null) {
			if (other.pulsarInterfaceConfiguration != null) {
				return false;
			}
		}
		else if (!pulsarInterfaceConfiguration.equals(other.pulsarInterfaceConfiguration)) {
			return false;
		}
		return true;
	}
		
	// -----------------------------------------------------------------------------------------------
	@Override
	public String toString() {
	
		final StringBuffer sf = new StringBuffer();
		
	    sf.append("[Pulsar2Tslee Configuration]");
	    sf.append(", Instance : " + instanceName);
	    sf.append(", Site : " + siteName);
	    sf.append(", LoggerServicePort : " + loggerServicePort);
	    sf.append(", AppLauncherServicePort : " + appLauncherServicePort);
	    sf.append(", AppLauncherApplications :");
	    appLauncherApplications.forEach((k,v) -> sf.append(", Topic: " + k + ", AppCode: " + v));
	    sf.append(", Pulsar Client Configuration: " + pulsarServiceConfiguration.toString());
	    sf.append(", Pulsar Interface Configuration: " + pulsarInterfaceConfiguration.toString());
	    return sf.toString();   
	}	
}
