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
 *         &lt;element ref="{}BCON"/>
 *         &lt;element ref="{}NCON"/>
 *         &lt;element ref="{}SCON"/>
 *         &lt;element ref="{}RCON"/>
 *         &lt;element ref="{}ECON"/>
 *         &lt;element ref="{}SOURF"/>
 *         &lt;element ref="{}STAT"/>
 *         &lt;element ref="{}ADD"/>
 *         &lt;element ref="{}REMK"/>
 *         &lt;element ref="{}styling"/>
 *         &lt;element ref="{}LINK"/>
 *         &lt;element ref="{}HOGR"/>
 *         &lt;element ref="{}EXNO"/>
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
@XmlRootElement(name = "EXAMP")
@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-09-18T09:30:55+03:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class EXAMP {

    @XmlElementRefs({
        @XmlElementRef(name = "STAT", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "REMK", type = REMK.class, required = false),
        @XmlElementRef(name = "EXNO", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "NCON", type = NCON.class, required = false),
        @XmlElementRef(name = "BCON", type = BCON.class, required = false),
        @XmlElementRef(name = "ADD", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "styling", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "SCON", type = SCON.class, required = false),
        @XmlElementRef(name = "LINK", type = LINK.class, required = false),
        @XmlElementRef(name = "RCON", type = RCON.class, required = false),
        @XmlElementRef(name = "ECON", type = ECON.class, required = false),
        @XmlElementRef(name = "HOGR", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "SOURF", type = SOURF.class, required = false)
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
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link REMK }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link NCON }
     * {@link BCON }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link String }
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link SCON }
     * {@link LINK }
     * {@link RCON }
     * {@link JAXBElement }{@code <}{@link BR }{@code >}
     * {@link ECON }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link SOURF }
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
