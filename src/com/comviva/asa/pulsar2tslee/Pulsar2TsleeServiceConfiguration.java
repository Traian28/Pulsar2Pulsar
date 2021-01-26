package com.comviva.asa.pulsar2tslee;

import com.ats_connection.utils.Version;
import com.ats_connection.configuration.ConfigurationException;
import com.ats_connection.configuration.DynamicConfiguration;

public class Pulsar2TsleeServiceConfiguration {

	private Version             		version;  
	private Pulsar2TsleeConfiguration  	pulsar2TsleeConfiguration;

	// -----------------------------------------------------------------------------------------------
	public Pulsar2TsleeServiceConfiguration( DynamicConfiguration dynamicConfiguration ) throws ConfigurationException {
		version = dynamicConfiguration.getVersion();
		pulsar2TsleeConfiguration = new Pulsar2TsleeConfiguration(dynamicConfiguration);
	}
	  
	// -----------------------------------------------------------------------------------------------
	public Version getVersion() {	  
		return version;
	}

	// -----------------------------------------------------------------------------------------------
	public Pulsar2TsleeConfiguration getPulsar2TsleeConfiguration() {
		return pulsar2TsleeConfiguration;
	}
	  
	// -----------------------------------------------------------------------------------------------
	@Override
	public int hashCode() { 
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pulsar2TsleeConfiguration == null) ? 0 : pulsar2TsleeConfiguration.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
	    Pulsar2TsleeServiceConfiguration other = (Pulsar2TsleeServiceConfiguration) obj;
	    
	    if (pulsar2TsleeConfiguration == null) {
		    if (other.pulsar2TsleeConfiguration != null) {
		    	return false;
		    }
	    }
	    else if (!pulsar2TsleeConfiguration.equals(other.pulsar2TsleeConfiguration)) {
	    	return false;
	    }
	    if (version == null) {
			if (other.version != null)  {
				return false;
			}
	    }
	    else if (!version.equals(other.version)) {
	    	return false;
	    }
	    return true;    
	}
	  
	// -----------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		StringBuilder temp = new StringBuilder();		    
		temp.append("Version: " + version);
		temp.append("\nPulsar2TsleeConfiguration: " + pulsar2TsleeConfiguration.toString());
		  
		return temp.toString();  
	}
}
