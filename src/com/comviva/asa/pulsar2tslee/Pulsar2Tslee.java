package com.comviva.asa.pulsar2tslee;


import java.util.HashMap;
// TODO
//import java.rmi.RemoteException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Map;

import javax.xml.ws.Endpoint;

import com.ats_connection.service.ConfigurationListener;
import com.ats_connection.thread.EventDrivenThread;
import com.ats_connection.log.LogPrefix;
import com.ats_connection.log.Logger;
import com.comviva.asa.app2pulsar.ws.MessageData;
import com.comviva.asa.app2pulsar.ws.PulsarInterface;
import com.comviva.asa.pulsar2tslee.LoggerServiceWs;
import com.comviva.asa.pulsar2tslee.events.Pulsar2PulsarEvent;
import com.comviva.asa.pulsar2tslee.eventtype.EventType;

public class Pulsar2Tslee extends EventDrivenThread<Pulsar2PulsarEvent> implements ConfigurationListener <Pulsar2TsleeServiceConfiguration> {

	private Logger           			logger;
	private final LogPrefix  			appPrefix = new LogPrefix("Pulsar2Tslee", 20);
	private Pulsar2TsleeService  		pulsar2TsleeService = null;
	private Endpoint 					wsPulsasInterfaceEndpoint = null;
	private Pulsar2TsleeConfiguration 	pulsar2TsleeConfiguration = null;
	private Pulsar2PulsarManager		pulsarManager = null;
	
	// -----------------------------------------------------------------------------------------------
	public Pulsar2Tslee(Pulsar2TsleeService _pulsar2TsleeService) {
		super("Pulsar2Tslee", _pulsar2TsleeService);
		
		pulsar2TsleeService = _pulsar2TsleeService;
	    logger = pulsar2TsleeService.getLogger("Pulsar2Tslee");
	    logger.info(appPrefix, "Pulsar2Tslee initialized");	    
	}

	// -----------------------------------------------------------------------------------------------
	public void stopPulsar2Tslee() {
 	    logger.info(appPrefix, "Stopping Pulsar2Tslee");

 	    StopPulsarClient();
 	    StopPulsarInterface();
 	    StopAppLauncherWs();
 	   
 	    insertEvent(new Pulsar2PulsarEvent(EventType.Stop));
 	    try
 	    {
 	      join();
 	    }
 	    catch (InterruptedException ex)
 	    {
 	      logger.error(appPrefix, "Join interrupted in Orch2Pular", ex);
 	    }
 	    
 	    logger.info(appPrefix, "CompilerRestApi stopped");
	}


	// -----------------------------------------------------------------------------------------------
	@Override
	public void newConfiguration(Pulsar2TsleeServiceConfiguration processConfiguration) {
	    insertEvent(new Pulsar2PulsarEvent(processConfiguration.getPulsar2TsleeConfiguration()));
	}

	// -----------------------------------------------------------------------------------------------
	@Override
	protected void main() {
		logger.info(appPrefix, "Pulsar2Tslee running.");
	    
	    try {
	    	pulsar2TsleeService.addConfigurationListener(this);
	      
	    	boolean running = true;
	      
	    	while (running) {
	    		Pulsar2PulsarEvent event = getEvent();
	        
	    		try {
	    			switch (event.eventType) {
	    				case Stop: {
	    					running = false;
	    					break;
	    				}
	    				case NewConfiguration: {
	    					if ((pulsar2TsleeConfiguration == null) || (!pulsar2TsleeConfiguration.equals(event.pulsar2TsleeConfiguration))) {
	    				 	    StopPulsarClient();
	    				 	    StopPulsarInterface();
	    				 	    StopAppLauncherWs();
	    				 	    StopLoggerServiceWs();
	    						
	    				 	    logger.info(appPrefix, "New configuration: " + event.pulsar2TsleeConfiguration.toString());
	    				 	    
	    						pulsarManager = new Pulsar2PulsarManager(event.pulsar2TsleeConfiguration, logger, this);
		    			 		logger.off(appPrefix, "PulsarManager WebService started.");	    			 			    						
	    								    			 		
		    			 		wsPulsasInterfaceEndpoint = Endpoint.publish(event.pulsar2TsleeConfiguration.getPulsarInterfaceConfiguration().getPort(), new PulsarInterface(this));
		    			 		logger.off(appPrefix, "PulsarInterface WebService started.");
		    			 		
		    			 		AppLauncherWs.InitAppLancherWs(event.pulsar2TsleeConfiguration.getAppLauncherServicePort());
		    			 		logger.off(appPrefix, "AppLauncherWs initialized.");
		    			 		 
		    			 		LoggerServiceWs.InitLoggerServiceWs(event.pulsar2TsleeConfiguration.getLoggerServicePort());
		    			 		logger.off(appPrefix, "LoggerServiceWs initialized.");
	    					}
	    					pulsar2TsleeConfiguration = event.pulsar2TsleeConfiguration;
	    			 		break;
	    				}
	    				case MessageDataRequest: {
	    					// send message to pulsar
	    					pulsarManager.sendMessageAsync(event.topic, event.messageData);
	    			 		logger.info(appPrefix, "TSLEE >>> PULSAR: " + event.toString());
	    					break;	    					    			 		
	    				}
	    				case MessageDataReceive: {
	    					// receive message from pulsar
	    					logger.info(appPrefix, "PULSAR >>> TSLEE: " + event.toString());
	    						    					
	    					// send  response to TSLEE's
	    					if (AppLauncherWs.getInstance().getWsProxy() != null) {
	    						SendMessageToAppLauncher(event.topic, event.messageData);
	    					}
	    					break;	    				
	    				}
	    			}
				} catch (Exception e) {
					logger.error(appPrefix, "Main Exception Pular2Tslee: " + e.getMessage(), e);
				}
	    		finally
	    		{
	    			logger.info(appPrefix, "(" + event.toString() + ")");
	    		}
	    	}
	    }
	    finally
	    {
	    	pulsar2TsleeService.removeConfigurationListener(this);
	    	logger.info(appPrefix, "Pulsar2Tslee exited.");
	    }
	}

	// -----------------------------------------------------------------------------------------------
	private HashMap<String, String> GetSerializedParameters(String strInputParamValue) {
		
		HashMap<String,String> hmParamValue = new HashMap<String,String>();

		if (strInputParamValue !=null ) {
			String strDelimiterRegEx = ";";
			String[] splitedParamValueList = strInputParamValue.split(strDelimiterRegEx);
			for(String paramValue: splitedParamValueList )
			{  
			  String[] splitedParamValue = paramValue.split("=");
			  if (splitedParamValue.length>1)
			    hmParamValue.put(splitedParamValue[0].trim(), splitedParamValue[1].trim());
			}
		}
		return hmParamValue;		
	}
	
	// -----------------------------------------------------------------------------------------------
	private void SendMessageToAppLauncher(String topic, MessageData messageData) {

		Map<String, String> applications = pulsar2TsleeConfiguration.getAppLauncherApplications();
		Map<String, String> parameters = messageData.getParameters();
		
		topic = topic.substring(topic.lastIndexOf('/') + 1);
		logger.info(appPrefix, "SendMessageToAppLauncher. Topic: " + topic + ", " + messageData.toString());
		
		String appCode = applications.get(topic);
		if (appCode == null) {
			logger.error(appPrefix, "Error in SendMessageToAppLauncher. There is not an application to Topic: " + topic);
		
			MessageData errorMessageData=new MessageData();
			errorMessageData.setMessageId(messageData.getMessageId());
			errorMessageData.setOperationName(messageData.getOperationName());
			errorMessageData.setOriginName(pulsar2TsleeConfiguration.getInstanceName());
			errorMessageData.setServiceName(messageData.getServiceName());
			errorMessageData.setSiteName(pulsar2TsleeConfiguration.getSiteName());		
			errorMessageData.setParameters(messageData.getParameters());
			errorMessageData.getParameters().put("resultcode", "10");
			errorMessageData.getParameters().put("resultdescription", "There is not an application to Topic: " + topic);

			String errorTopic = topic+"-response";
			insertEvent(new Pulsar2PulsarEvent(errorMessageData, errorTopic, true));
			return;
		}
		
		String param1 = parameters.get("sessionInfo");
		String param2 = parameters.get("subscriberInfo");
		
		String param3 = "messageId="     + messageData.getMessageId() 	  	+ ";" +
						"operationName=" + messageData.getOperationName() 	+ ";" +
						"originName="    + messageData.getOriginName() 		+ ";" +
						"serviceName="   + messageData.getServiceName() 	+ ";" +
						"siteName="      + messageData.getSiteName();
		String param4="";
		String param5="";
		String param6="";
		String param7="";
		String param8="";
		String param9="";
		String param10="";
		
		int index = 4;
		for (Map.Entry<String,String> entry : parameters.entrySet()) {
			String k = entry.getKey();
			if (!k.equals("sessionInfo") && !k.equals("subscriberInfo") && !k.equals("requestInfo")) {
				switch(index) {
				case 4: param4= k + "=" + entry.getValue(); break;
				case 5: param5= k + "=" + entry.getValue(); break;
				case 6: param6= k + "=" + entry.getValue(); break;
				case 7: param7= k + "=" + entry.getValue(); break;
				case 8: param8= k + "=" + entry.getValue(); break;
				case 9: param9= k + "=" + entry.getValue(); break;
				case 10: param10= k + "=" + entry.getValue(); break;
				default: break;
				}
				index++;
			}
		}
		
		try {
			logger.info(appPrefix, "AppLauncher. Parameters: " + 
					"AppCode: " + appCode +
					",Param01: " + param1 +
					",Param02: " + param2 +
					",Param03: " + param3 +
					",Param04: " + param4 +
					",Param05: " + param5 +
					",Param06: " + param6 +
					",Param07: " + param7 +
					",Param08: " + param8 +
					",Param09: " + param9 +
					",Param10: " + param10);
			AppLauncherWs.getInstance().getWsProxy().launchApp(pulsar2TsleeConfiguration.getAppLauncherServicePort(), 
					appCode, param1,param2,param3,param4,param5,param6,param7, param8, param9, param10, false);
		} catch (Exception e) {
			logger.error(appPrefix, "SendMessageToAppLauncher Exception: " + e.getMessage(), e);	 

			MessageData errorMessageData=new MessageData();
			errorMessageData.setMessageId(messageData.getMessageId());
			errorMessageData.setOperationName(messageData.getOperationName());
			errorMessageData.setOriginName(pulsar2TsleeConfiguration.getInstanceName());
			errorMessageData.setServiceName(messageData.getServiceName());
			errorMessageData.setSiteName(pulsar2TsleeConfiguration.getSiteName());		
			errorMessageData.setParameters(messageData.getParameters());
			errorMessageData.getParameters().put("resultcode", "20");
			if (e.getMessage() != null) { 
				errorMessageData.getParameters().put("resultdescription", e.getMessage());
			}
			else {
				errorMessageData.getParameters().put("resultdescription", e.toString());				
			}

			String errorTopic = topic+"-response";
			insertEvent(new Pulsar2PulsarEvent(errorMessageData, errorTopic, true));
		}
	}
	
	// -----------------------------------------------------------------------------------------------
	private void StopPulsarClient() {
		if (pulsarManager != null) {
	 		logger.off(appPrefix, "Stopping Pulsar Manager.");	    			 			    						
			pulsarManager.close();
			pulsarManager = null;
	 		logger.off(appPrefix, "Pulsar Manager Stopped.");	    			 			    						
		}		
	}
	
	// -----------------------------------------------------------------------------------------------
	private void StopPulsarInterface() {
		if (wsPulsasInterfaceEndpoint != null) {
	 		logger.off(appPrefix, "Stopping PulsarInterface WebService.");	    			 			    						
			wsPulsasInterfaceEndpoint.stop();
			wsPulsasInterfaceEndpoint = null;
	 		logger.off(appPrefix, "PulsarInterface WebService stopped.");	    			 			    						
		}
	}
	
	// -----------------------------------------------------------------------------------------------
	private void StopAppLauncherWs() {
		if (AppLauncherWs.getInstance().getWsProxy() != null) {
	 		logger.off(appPrefix, "Stopping AppLauncher WebService.");	    			 			    						
	 		AppLauncherWs.getInstance().Close();
	 		logger.off(appPrefix, "AppLauncherWs stopped.");	    			 			    						
		}
	}

	private void StopLoggerServiceWs() {
		if (LoggerServiceWs.getInstance().getWsProxy() != null) {
	 		logger.off(appPrefix, "Stopping LoggerService WebService.");	    			 			    						
	 		LoggerServiceWs.getInstance().Close();
	 		logger.off(appPrefix, "LoggerServiceWs stopped.");	    			 			    						
		}
	}
	
	public Logger getLogger() {
		return logger;
	}
}
