/**
 * AppLauncherService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ats.slee.app_launcher.webservice;

public interface AppLauncherService extends java.rmi.Remote {
    public void launchApp(java.lang.String responseUrl, java.lang.String appCode, java.lang.String param01, java.lang.String param02, java.lang.String param03, java.lang.String param04, java.lang.String param05, java.lang.String param06, java.lang.String param07, java.lang.String param08, java.lang.String param09, java.lang.String param10, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void launchGenericApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, boolean trace, java.lang.String extraParam) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void launchSmppApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void launchSmppAppV2(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, java.lang.String extraParam, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void launchUssdApp(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void launchUssdAppV2(int jobId, java.lang.String responseUrl, java.lang.String appCode, java.lang.String msisdnOrg, java.lang.String msisdnDst, java.lang.String extraParam, boolean trace) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void stopAllApps() throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
    public void stopApp(int jobId) throws java.rmi.RemoteException, com.ats.slee.app_launcher.webservice.AppLauncherFaultBean;
}
