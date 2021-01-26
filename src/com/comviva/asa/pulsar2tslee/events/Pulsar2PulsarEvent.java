package com.comviva.asa.pulsar2tslee.events;

import com.comviva.asa.pulsar2tslee.Pulsar2TsleeConfiguration;
import com.comviva.asa.pulsar2tslee.eventtype.EventType;
import com.comviva.asa.app2pulsar.ws.MessageData;

public class Pulsar2PulsarEvent {

    public EventType     				eventType;
    public Pulsar2TsleeConfiguration 	pulsar2TsleeConfiguration = null;
    public MessageData 					messageData = null;
    public String  						topic = null;
    
    public Pulsar2PulsarEvent( EventType _eventType )
    {
      eventType = _eventType;
    }
    
    public Pulsar2PulsarEvent( Pulsar2TsleeConfiguration _pulsar2TsleeConfiguration )
    {
      eventType = EventType.NewConfiguration;
      pulsar2TsleeConfiguration = _pulsar2TsleeConfiguration;
    }

    public Pulsar2PulsarEvent( MessageData _messageData, String _topic, Boolean send )
    {
    	if (send) {
    		eventType = EventType.MessageDataRequest;
    	} 
    	else {
    		eventType = EventType.MessageDataReceive;    		
    	}
      messageData = _messageData;
      topic = _topic;
    }
    
    @Override
    public String toString()
    {
      StringBuilder temp = new StringBuilder();
      
      temp.append("Event: " + eventType);
      temp.append(", Topic: " + topic);
      
      if ((eventType == EventType.NewConfiguration) && (pulsar2TsleeConfiguration !=null)) {
    	  temp.append(", Parameter: " + pulsar2TsleeConfiguration.toString());
      }
      
      if (((eventType == EventType.MessageDataRequest) || (eventType == EventType.MessageDataReceive)) && (messageData !=null)) {
    	  temp.append(", Parameter: " + messageData.toString());
      }

      return temp.toString();
    }
}
