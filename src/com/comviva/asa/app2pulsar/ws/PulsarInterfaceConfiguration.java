package com.comviva.asa.app2pulsar.ws;

import com.ats_connection.configuration.ConfigurationError;
import com.ats_connection.configuration.ConfigurationException;
import com.ats_connection.configuration.DynamicConfiguration;

public class PulsarInterfaceConfiguration {

	private static final String     ROOT_PULSAR_INTERFACE_CFG_PATH = "/pulsar_ws";
	private static final String     PULSAR_WS_PORT_PATH        = ROOT_PULSAR_INTERFACE_CFG_PATH + "/port";

	private String 	port = null;

	// -----------------------------------------------------------------------------------------------
	public PulsarInterfaceConfiguration (DynamicConfiguration configuration, String node )  throws ConfigurationException {
		
		port = configuration.getNodeValue(node + PULSAR_WS_PORT_PATH, null);
	    if (port == null) {
	    	throw new ConfigurationException(configuration.getVersion(), ConfigurationError.InvalidValue, "Pulsar Interface Ws Port", node + PULSAR_WS_PORT_PATH);
	    }
	}

	// -----------------------------------------------------------------------------------------------
	public String getPort() {
		return port;
	}	
	
	// -----------------------------------------------------------------------------------------------
	@Override	
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((port == null) ? 0 : port.hashCode());
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
		final PulsarInterfaceConfiguration other = (PulsarInterfaceConfiguration) obj;
		if (port == null) {
			if (other.port != null) {
				return false;
			}
		}
		else if (!port.equals(other.port)) {
			return false;
		}
		return true;
	}
	
	// -----------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		
		final StringBuilder temp = new StringBuilder();

		temp.append("port: " + port);			
		return temp.toString();
	}
	
}
