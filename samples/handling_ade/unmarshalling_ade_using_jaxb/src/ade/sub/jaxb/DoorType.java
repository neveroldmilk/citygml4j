/*
 * This file is part of citygml4j.
 * Copyright (c) 2007 - 2012
 * Institute for Geodesy and Geoinformation Science
 * Technische Universität Berlin, Germany
 * http://www.igg.tu-berlin.de/
 *
 * The citygml4j library is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. If not, see 
 * <http://www.gnu.org/licenses/>.
 * 
 * $Id: DoorType.java 542 2012-11-29 20:56:44Z nagel $
 */

package ade.sub.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.citygml4j.jaxb.citygml.core._1.AddressPropertyType;


/**
 *  Type for doors in walls. Used in LOD3 and LOD4 only . As subclass of _CityObject, a Door inherits all attributes and relations, in particular an id,
 * 				names, external references, and generalization relations. 
 * 
 * <p>Java class for DoorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DoorType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.citygml.org/ade/sub/0.9.0}AbstractOpeningType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.opengis.net/citygml/1.0}AddressPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.citygml.org/ade/sub/0.9.0}_GenericApplicationPropertyOfDoor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoorType", propOrder = {
    "address",
    "genericApplicationPropertyOfDoor"
})
public class DoorType
    extends AbstractOpeningType
{

    protected List<AddressPropertyType> address;
    @XmlElement(name = "_GenericApplicationPropertyOfDoor")
    protected List<Object> genericApplicationPropertyOfDoor;

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressPropertyType }
     * 
     * 
     */
    public List<AddressPropertyType> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressPropertyType>();
        }
        return this.address;
    }

    public boolean isSetAddress() {
        return ((this.address!= null)&&(!this.address.isEmpty()));
    }

    public void unsetAddress() {
        this.address = null;
    }

    /**
     * Gets the value of the genericApplicationPropertyOfDoor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericApplicationPropertyOfDoor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    get_GenericApplicationPropertyOfDoor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> get_GenericApplicationPropertyOfDoor() {
        if (genericApplicationPropertyOfDoor == null) {
            genericApplicationPropertyOfDoor = new ArrayList<Object>();
        }
        return this.genericApplicationPropertyOfDoor;
    }

    public boolean isSet_GenericApplicationPropertyOfDoor() {
        return ((this.genericApplicationPropertyOfDoor!= null)&&(!this.genericApplicationPropertyOfDoor.isEmpty()));
    }

    public void unset_GenericApplicationPropertyOfDoor() {
        this.genericApplicationPropertyOfDoor = null;
    }

}
