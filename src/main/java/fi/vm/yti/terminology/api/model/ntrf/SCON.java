//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.18 at 09:30:55 AM EEST 
//


package fi.vm.yti.terminology.api.model.ntrf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}HOGR" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="typr" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "SCON")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class SCON {

    @XmlElementRef(name = "HOGR", type = JAXBElement.class, required = false)
    @XmlMixed
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Serializable> content;
    @XmlAttribute(name = "typr")
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String typr;
    @XmlAttribute(name = "href", required = true)
    @XmlSchemaType(name = "anySimpleType")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String href;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<Serializable>();
        }
        return this.content;
    }

    /**
     * Gets the value of the typr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTypr() {
        return typr;
    }

    /**
     * Sets the value of the typr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTypr(String value) {
        this.typr = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHref(String value) {
        this.href = value;
    }

}
