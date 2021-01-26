/**
 * Logger.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ats.dbLogger.sources;

public interface Logger extends java.rmi.Remote {
    public WsAckRsp activityLog(java.lang.String module, java.lang.String description, java.lang.String details, java.lang.String logLevel, java.lang.String category, java.lang.String logDate, java.lang.String sessionId, java.lang.String MSISDN, java.lang.String IMSI, java.lang.String ICCID, java.lang.String instanceId) throws java.rmi.RemoteException, SOAPException;
    public WsAckRsp sdrLog(java.lang.String module, java.lang.String description, java.lang.String details, java.lang.String category, java.lang.String logDate, java.lang.String sessionId, java.lang.String MSISDN, java.lang.String IMSI, java.lang.String ICCID, java.lang.String instanceId) throws java.rmi.RemoteException, SOAPException;
}
