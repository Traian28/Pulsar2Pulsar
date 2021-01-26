/**
 * LoggerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ats.dbLogger.sources;

public class LoggerServiceLocator extends org.apache.axis.client.Service implements ats.dbLogger.sources.LoggerService {

    public LoggerServiceLocator() {
    }


    public LoggerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LoggerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LoggerPort
    private java.lang.String LoggerPort_address = "http://localhost:8093/Logger";

    public java.lang.String getLoggerPortAddress() {
        return LoggerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LoggerPortWSDDServiceName = "LoggerPort";

    public java.lang.String getLoggerPortWSDDServiceName() {
        return LoggerPortWSDDServiceName;
    }

    public void setLoggerPortWSDDServiceName(java.lang.String name) {
        LoggerPortWSDDServiceName = name;
    }

    public ats.dbLogger.sources.Logger getLoggerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LoggerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLoggerPort(endpoint);
    }

    public ats.dbLogger.sources.Logger getLoggerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ats.dbLogger.sources.LoggerPortBindingStub _stub = new ats.dbLogger.sources.LoggerPortBindingStub(portAddress, this);
            _stub.setPortName(getLoggerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLoggerPortEndpointAddress(java.lang.String address) {
        LoggerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ats.dbLogger.sources.Logger.class.isAssignableFrom(serviceEndpointInterface)) {
                ats.dbLogger.sources.LoggerPortBindingStub _stub = new ats.dbLogger.sources.LoggerPortBindingStub(new java.net.URL(LoggerPort_address), this);
                _stub.setPortName(getLoggerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LoggerPort".equals(inputPortName)) {
            return getLoggerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sources.dbLogger.ats/", "LoggerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sources.dbLogger.ats/", "LoggerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LoggerPort".equals(portName)) {
            setLoggerPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
