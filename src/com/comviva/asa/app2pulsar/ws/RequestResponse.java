package com.comviva.asa.app2pulsar.ws;

public class RequestResponse {
	
	private String messageId;
	private String resultCode;
	private String resultDescription;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	
    @Override
    public String toString() {
    	StringBuilder temp = new StringBuilder();

    	temp.append("\nRequestResponse: ");
    	temp.append("MessageId: " + messageId);
    	temp.append("ResultCode: " + resultCode);
    	temp.append("ResultDescription: " + resultDescription);
    	return temp.toString();
    }
}
