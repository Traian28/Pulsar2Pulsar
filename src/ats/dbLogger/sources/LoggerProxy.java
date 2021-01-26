package ats.dbLogger.sources;

public class LoggerProxy implements ats.dbLogger.sources.Logger {
  private String _endpoint = null;
  private ats.dbLogger.sources.Logger logger = null;
  
  public LoggerProxy() {
    _initLoggerProxy();
  }
  
  public LoggerProxy(String endpoint) {
    _endpoint = endpoint;
    _initLoggerProxy();
  }
  
  private void _initLoggerProxy() {
    try {
      logger = (new ats.dbLogger.sources.LoggerServiceLocator()).getLoggerPort();
      if (logger != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)logger)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)logger)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (logger != null)
      ((javax.xml.rpc.Stub)logger)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ats.dbLogger.sources.Logger getLogger() {
    if (logger == null)
      _initLoggerProxy();
    return logger;
  }
  
  public ats.dbLogger.sources.WsAckRsp activityLog(java.lang.String module, java.lang.String description, java.lang.String details, java.lang.String logLevel, java.lang.String category, java.lang.String logDate, java.lang.String sessionId, java.lang.String MSISDN, java.lang.String IMSI, java.lang.String ICCID, java.lang.String instanceId) throws java.rmi.RemoteException, ats.dbLogger.sources.SOAPException{
    if (logger == null)
      _initLoggerProxy();
    return logger.activityLog(module, description, details, logLevel, category, logDate, sessionId, MSISDN, IMSI, ICCID, instanceId);
  }
  
  public ats.dbLogger.sources.WsAckRsp sdrLog(java.lang.String module, java.lang.String description, java.lang.String details, java.lang.String category, java.lang.String logDate, java.lang.String sessionId, java.lang.String MSISDN, java.lang.String IMSI, java.lang.String ICCID, java.lang.String instanceId) throws java.rmi.RemoteException, ats.dbLogger.sources.SOAPException{
    if (logger == null)
      _initLoggerProxy();
    return logger.sdrLog(module, description, details, category, logDate, sessionId, MSISDN, IMSI, ICCID, instanceId);
  }
  
  
}