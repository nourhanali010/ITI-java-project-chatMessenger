//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.01.20 at 10:47:26 AM EET 
//


package XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author asmaa
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageType", propOrder = {
    "from",
    "to",
    "color",
    "size",
    "family",
    "body"
})
public class MessageType {

    /**
     *
     */
    @XmlElement(required = true)
    protected String from;

    /**
     *
     */
    @XmlElement(required = true)
    protected String to;

    /**
     *
     */
    @XmlElement(required = true)
    protected String color;

    /**
     *
     */
    @XmlSchemaType(name = "positiveInteger")
    protected int size;

    /**
     *
     */
    @XmlElement(required = true)
    protected String family;

    /**
     *
     */
    @XmlElement(required = true)
    protected String body;

 
    public String getFrom() {
        return from;
    }

  
    public void setFrom(String value) {
        this.from = value;
    }

    public String getTo() {
        return to;
    }

      public void setTo(String value) {
        this.to = value;
    }

    public String getColor() {
        return color;
    }

   
    public void setColor(String value) {
        this.color = value;
    }

  
    public int getSize() {
        return size;
    }

   
    public void setSize(int value) {
        this.size = value;
    }

 
    public String getFamily() {
        return family;
    }


    public void setFamily(String value) {
        this.family = value;
    }

    public String getBody() {
        return body;
    }

  
    
    public void setBody(String value) {
        this.body = value;
    }

}
