package com.comviva.asa.app2pulsar.ws;

import javax.jws.WebService;

import com.ats_connection.thread.EventDrivenThread;
import com.comviva.asa.pulsar2tslee.events.Pulsar2PulsarEvent;

@WebService
public class PulsarInterface {

	EventDrivenThread<Pulsar2PulsarEvent> eventProcessor;
	
	// ----------------------------------------------------------------------------------------------
	public PulsarInterface( EventDrivenThread<Pulsar2PulsarEvent> _eventProcessor) {
		eventProcessor = _eventProcessor;
	}
	
	// ----------------------------------------------------------------------------------------------
	public RequestResponse sendMessage(MessageData _messageData, String topic) {
		
		eventProcessor.insertEvent(new Pulsar2PulsarEvent(_messageData, topic, true));
				
		RequestResponse result = new RequestResponse();
		result.setMessageId("1");
		result.setResultCode("0");
		result.setResultDescription("Ok");
		return result;
	}
}
