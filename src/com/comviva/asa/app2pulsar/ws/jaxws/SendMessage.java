
package com.comviva.asa.app2pulsar.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sendMessage", namespace = "http://ws.orchestrator2pulsar.asa.comviva.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMessage", namespace = "http://ws.orchestrator2pulsar.asa.comviva.com/", propOrder = {
    "arg0",
    "arg1"
})
public class SendMessage {

    @XmlElement(name = "arg0", namespace = "")
    private com.comviva.asa.app2pulsar.ws.MessageData arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;

    /**
     * 
     * @return
     *     returns MessageData
     */
    public com.comviva.asa.app2pulsar.ws.MessageData getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.comviva.asa.app2pulsar.ws.MessageData arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

}
