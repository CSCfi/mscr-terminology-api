//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.09.18 at 09:30:55 AM EEST 
//


package fi.vm.yti.terminology.api.model.ntrf;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlRootElement;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{}REMK"/>
 *         &lt;element ref="{}B"/>
 *         &lt;element ref="{}I"/>
 *         &lt;element ref="{}BR"/>
 *         &lt;element ref="{}LINK"/>
 *       &lt;/choice>
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
@XmlRootElement(name = "REFTEXT")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class REFTEXT {

    @XmlElementRefs({
        @XmlElementRef(name = "REMK", type = REMK.class, required = false),
        @XmlElementRef(name = "BR", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "I", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "LINK", type = LINK.class, required = false),
        @XmlElementRef(name = "B", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected List<Object> content;

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
     * {@link REMK }
     * {@link JAXBElement }{@code <}{@link BR }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link LINK }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

}
