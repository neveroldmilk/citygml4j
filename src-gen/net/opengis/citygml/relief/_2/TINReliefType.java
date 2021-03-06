//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2016.08.10 um 09:13:50 PM CEST 
//


package net.opengis.citygml.relief._2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Type describing the TIN component of a relief feature. As subclass of _CityObject, a TINRelief inherits
 * 				all attributes and relations, in particular an id, names, external references, and generalization relations.
 * 			
 * 
 * <p>Java-Klasse für TINReliefType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TINReliefType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/citygml/relief/2.0}AbstractReliefComponentType">
 *       &lt;sequence>
 *         &lt;element name="tin" type="{http://www.opengis.net/citygml/relief/2.0}tinPropertyType"/>
 *         &lt;element ref="{http://www.opengis.net/citygml/relief/2.0}_GenericApplicationPropertyOfTinRelief" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TINReliefType", propOrder = {
    "tin",
    "_GenericApplicationPropertyOfTinRelief"
})
public class TINReliefType
    extends AbstractReliefComponentType
{

    @XmlElement(required = true)
    protected TinPropertyType tin;
    @XmlElementRef(name = "_GenericApplicationPropertyOfTinRelief", namespace = "http://www.opengis.net/citygml/relief/2.0", type = JAXBElement.class, required = false)
    protected List<JAXBElement<Object>> _GenericApplicationPropertyOfTinRelief;

    /**
     * Ruft den Wert der tin-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TinPropertyType }
     *     
     */
    public TinPropertyType getTin() {
        return tin;
    }

    /**
     * Legt den Wert der tin-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TinPropertyType }
     *     
     */
    public void setTin(TinPropertyType value) {
        this.tin = value;
    }

    public boolean isSetTin() {
        return (this.tin!= null);
    }

    /**
     * Gets the value of the genericApplicationPropertyOfTinRelief property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericApplicationPropertyOfTinRelief property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    get_GenericApplicationPropertyOfTinRelief().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Object>> get_GenericApplicationPropertyOfTinRelief() {
        if (_GenericApplicationPropertyOfTinRelief == null) {
            _GenericApplicationPropertyOfTinRelief = new ArrayList<JAXBElement<Object>>();
        }
        return this._GenericApplicationPropertyOfTinRelief;
    }

    public boolean isSet_GenericApplicationPropertyOfTinRelief() {
        return ((this._GenericApplicationPropertyOfTinRelief!= null)&&(!this._GenericApplicationPropertyOfTinRelief.isEmpty()));
    }

    public void unset_GenericApplicationPropertyOfTinRelief() {
        this._GenericApplicationPropertyOfTinRelief = null;
    }

    public void set_GenericApplicationPropertyOfTinRelief(List<JAXBElement<Object>> value) {
        this._GenericApplicationPropertyOfTinRelief = value;
    }

}
