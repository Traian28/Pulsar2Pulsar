package com.comviva.asa.app2pulsar.ws;

import java.util.HashMap;

public class MessageData {
	
	private String messageId;
	private String serviceName;
	private String originName;
	private String siteName;
	private String operationName;
	private HashMap<String, String> parameters;
	
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public HashMap<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
    @Override
    public String toString() {
    	StringBuilder temp = new StringBuilder();

    	temp.append("MessageData: ");
    	temp.append(" MessageId: " + messageId);
    	temp.append(", ServiceName: " + serviceName);
    	temp.append(", OriginName: " + originName);
    	temp.append(", SiteName: " + siteName);
    	temp.append(", OperationName: " + operationName);
    	temp.append(", Parameters:");
    	parameters.forEach((name, value) -> {
    		temp.append("[ " + name + ": " + value + " ]");
    	});
    	return temp.toString();
    }

}
