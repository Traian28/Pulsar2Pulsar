
package com.comviva.asa.app2pulsar.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "sendMessageResponse", namespace = "http://ws.orchestrator2pulsar.asa.comviva.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMessageResponse", namespace = "http://ws.orchestrator2pulsar.asa.comviva.com/")
public class SendMessageResponse {

    @XmlElement(name = "return", namespace = "")
    private com.comviva.asa.app2pulsar.ws.RequestResponse _return;

    /**
     * 
     * @return
     *     returns RequestResponse
     */
    public com.comviva.asa.app2pulsar.ws.RequestResponse getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.comviva.asa.app2pulsar.ws.RequestResponse _return) {
        this._return = _return;
    }

}
