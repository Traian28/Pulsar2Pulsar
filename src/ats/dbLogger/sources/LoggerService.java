/**
 * LoggerService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ats.dbLogger.sources;

public interface LoggerService extends javax.xml.rpc.Service {
    public java.lang.String getLoggerPortAddress();

    public ats.dbLogger.sources.Logger getLoggerPort() throws javax.xml.rpc.ServiceException;

    public ats.dbLogger.sources.Logger getLoggerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
