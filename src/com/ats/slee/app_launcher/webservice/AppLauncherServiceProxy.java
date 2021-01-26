package com.ats.slee.app_launcher.webservice;

public class AppLauncherServiceProxy implements com.ats.slee.app_launcher.webservice.AppLauncherService {
  private String _endpoint = null;
  private com.ats.slee.app_launcher.webservice.AppLauncherService appLauncherService = null;
  
  public AppLauncherServiceProxy() {
    _initAppLauncherServiceProxy();
  }
  
  public AppLauncherServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initAppLauncherServiceProxy();
  }
  
  private void _initAppLauncherServiceProxy() {
    try {
      appLauncherService = (new com.ats.slee.app_launcher.webservice.AppLauncherServiceImplServiceLocator()).getAppLauncherServiceImplPort();
      if (appLauncherService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)appLauncherService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)appLauncherService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (appLauncherService != null)
      ((javax.xml.rpc.Stub)appLauncherService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ats.slee.app_launcher.webservice.AppLauncherService getAppLauncherService() {
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    return appLauncherService;
  }
  
  public void launchApp(java.lang.String responseUrl, java.lang.String appCode, java.lang.String param01, java.lang.String param02, java.lang.String param03, java.lang.String param04, java.lang.String param05, java.lang.String param06, java.lang.String param07, java.lang.String param08, java.lang.String param09, java.lang.String param10, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchApp(responseUrl, appCode, param01, param02, param03, param04, param05, param06, param07, param08, param09, param10, trace);
  }
  
  public void launchGenericApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, boolean trace, java.lang.String extraParam) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchGenericApp(jobId, responseUrl, appCode, trace, extraParam);
  }
  
  public void launchSmppApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchSmppApp(jobId, responseUrl, appCode, msisdnOrg, msisdnDst, trace);
  }
  
  public void launchSmppAppV2(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, java.lang.String extraParam, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchSmppAppV2(jobId, responseUrl, appCode, msisdnOrg, msisdnDst, extraParam, trace);
  }
  
  public void launchUssdApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchUssdApp(jobId, responseUrl, appCode, msisdnOrg, msisdnDst, trace);
  }
  
  public void launchUssdAppV2(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, java.lang.String extraParam, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.launchUssdAppV2(jobId, responseUrl, appCode, msisdnOrg, msisdnDst, extraParam, trace);
  }
  
  public void stopAllApps() throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.stopAllApps();
  }
  
  public void stopApp(int jobId) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean{
    if (appLauncherService == null)
      _initAppLauncherServiceProxy();
    appLauncherService.stopApp(jobId);
  }
  
  
}