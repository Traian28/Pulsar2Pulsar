/**
 * AppLauncherServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ats.slee.app_launcher.webservice;

public class AppLauncherServiceImplServiceLocator extends org.apache.axis.client.Service implements com.ats.slee.app_launcher.webservice.AppLauncherServiceImplService {

    public AppLauncherServiceImplServiceLocator() {
    }


    public AppLauncherServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AppLauncherServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AppLauncherServiceImplPort
    private java.lang.String AppLauncherServiceImplPort_address = "http://renio.ats-ar.com.ar:8180/AppLauncher-webservice";

    public java.lang.String getAppLauncherServiceImplPortAddress() {
        return AppLauncherServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AppLauncherServiceImplPortWSDDServiceName = "AppLauncherServiceImplPort";

    public java.lang.String getAppLauncherServiceImplPortWSDDServiceName() {
        return AppLauncherServiceImplPortWSDDServiceName;
    }

    public void setAppLauncherServiceImplPortWSDDServiceName(java.lang.String name) {
        AppLauncherServiceImplPortWSDDServiceName = name;
    }

    public com.ats.slee.app_launcher.webservice.AppLauncherService getAppLauncherServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AppLauncherServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAppLauncherServiceImplPort(endpoint);
    }

    public com.ats.slee.app_launcher.webservice.AppLauncherService getAppLauncherServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ats.slee.app_launcher.webservice.AppLauncherServiceBindingStub _stub = new com.ats.slee.app_launcher.webservice.AppLauncherServiceBindingStub(portAddress, this);
            _stub.setPortName(getAppLauncherServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAppLauncherServiceImplPortEndpointAddress(java.lang.String address) {
        AppLauncherServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ats.slee.app_launcher.webservice.AppLauncherService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ats.slee.app_launcher.webservice.AppLauncherServiceBindingStub _stub = new com.ats.slee.app_launcher.webservice.AppLauncherServiceBindingStub(new java.net.URL(AppLauncherServiceImplPort_address), this);
                _stub.setPortName(getAppLauncherServiceImplPortWSDDServiceName());
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
        if ("AppLauncherServiceImplPort".equals(inputPortName)) {
            return getAppLauncherServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.app_launcher.slee.ats.com/", "AppLauncherServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.app_launcher.slee.ats.com/", "AppLauncherServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AppLauncherServiceImplPort".equals(portName)) {
            setAppLauncherServiceImplPortEndpointAddress(address);
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
