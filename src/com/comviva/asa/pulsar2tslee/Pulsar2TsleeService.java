package com.comviva.asa.pulsar2tslee;

import com.ats_connection.bcpp.asrv.ats_store.StoreException;
import com.ats_connection.configuration.ConfigurationException;
import com.ats_connection.configuration.DynamicConfiguration;
import com.ats_connection.service.Service;
import com.ats_connection.statistics.StatisticRange;
import com.comviva.asa.pulsar2tslee.constants.Pulsar2TsleeVersion;

public class Pulsar2TsleeService extends Service<Pulsar2TsleeServiceConfiguration> {

	private Pulsar2Tslee pulsar2Tslee;
	
	// -----------------------------------------------------------------------------------------------
	public Pulsar2TsleeService() {
		super(Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_NAME, Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_VERSION, false);
		
	    initializationLog.off("Pulsa2TsleeService version: " + Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_VERSION + ", Package: " + Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_NAME);
	    logger.off("Pulsa2TsleeService version: " + Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_VERSION + ", Package: " + Pulsar2TsleeVersion.PUSAR2TSLEE_MODULE_NAME);
	    
	    pulsar2Tslee = new Pulsar2Tslee(this);
	}

	// -----------------------------------------------------------------------------------------------
	@Override
	protected void serviceStarting() {
		super.serviceStarting();
		pulsar2Tslee.start();
	}
  
	// -----------------------------------------------------------------------------------------------
	@Override
	protected void serviceStopping() {
		pulsar2Tslee.stopPulsar2Tslee();
		super.serviceStopping();
	}
  
	//-----------------------------------------------------------------------------------------------
	@Override
	protected void serviceStopped()	{
		super.serviceStopped();
	}
		
	// -----------------------------------------------------------------------------------------------
	// dump statistics
	@Override
	public void UpdateNodes(StatisticRange range) throws StoreException {
		// TODO Auto-generated method stub
		
	}

	// -----------------------------------------------------------------------------------------------
	@Override
	protected Pulsar2TsleeServiceConfiguration buildConfiguration(DynamicConfiguration dynamicConfiguration)
			throws ConfigurationException {

	    return new Pulsar2TsleeServiceConfiguration(dynamicConfiguration);
	}

}
