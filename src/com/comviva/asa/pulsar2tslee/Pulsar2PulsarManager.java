package com.comviva.asa.pulsar2tslee;

import com.comviva.pulsarlib.manager.PulsarManager;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.client.impl.schema.JSONSchema;

import com.ats_connection.log.Logger;
import com.ats_connection.log.LogPrefix;
import com.ats_connection.thread.EventDrivenThread;
import com.comviva.asa.pulsar2tslee.events.Pulsar2PulsarEvent;
import com.comviva.asa.app2pulsar.ws.MessageData;

public class Pulsar2PulsarManager extends PulsarManager<MessageData> implements MessageListener<MessageData> {

	private static final long serialVersionUID = -1165719632975046227L;
	private Pulsar2TsleeConfiguration  pulsar2TsleeConfiguration = null;
	private EventDrivenThread<Pulsar2PulsarEvent> eventProcessor = null;
	
	private Logger logger;
	private LogPrefix appPrefix = new LogPrefix("Pulsar2PulsarManager", 20);
	
	// ----------------------------------------------------------------------------------------------
	public Pulsar2PulsarManager(Pulsar2TsleeConfiguration _pulsar2TsleeConfiguration, Logger _logger, Pulsar2Tslee _eventProcessor) {
		super(_pulsar2TsleeConfiguration.getPulsarServiceConfiguration(), _logger);
	
		logger = _logger;
		
		eventProcessor = _eventProcessor; 
		pulsar2TsleeConfiguration = _pulsar2TsleeConfiguration;
				
		pulsar2TsleeConfiguration.getPulsarServiceConfiguration().getProducerTopics().forEach(topic -> 
			addProducer( pulsar2TsleeConfiguration.getInstanceName(), topic, JSONSchema.of(MessageData.class)));
		

		pulsar2TsleeConfiguration.getPulsarServiceConfiguration().getConsumerTopics().forEach(topic ->
			addConsumer( pulsar2TsleeConfiguration.getInstanceName(), topic, (topic +"_"+ pulsar2TsleeConfiguration.getSiteName()), SubscriptionType.Shared, this, JSONSchema.of(MessageData.class)));		
	}

	// ----------------------------------------------------------------------------------------------
	@Override
	public void received(Consumer<MessageData> consumer, Message<MessageData> message) {
		MessageData messageData = message.getValue();
		eventProcessor.insertEvent(new Pulsar2PulsarEvent(message.getValue(), message.getTopicName(), false));
		consumer.acknowledgeAsync(message.getMessageId());
		logger.info(appPrefix, "Pulsar Message received, Consumer: " + consumer.getConsumerName() + ", Topic: "+ consumer.getTopic() + ",  Message: " + messageData.toString());
		
	}	
}

