//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:02:12 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорР1ГВЗ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорР1ГВЗ"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Р1_10" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка255Непустая" /&gt;
 *       &lt;attribute name="Р1_11" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка255Непустая" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u04201\u0413\u0412\u0417")
public class Ф0409303Данные303ДоговорР1ГВЗ {

    @XmlAttribute(name = "\u04201_10")
    protected String р110;
    @XmlAttribute(name = "\u04201_11")
    protected String р111;

    /**
     * Gets the value of the р110 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР110() {
        return р110;
    }

    /**
     * Sets the value of the р110 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР110(String value) {
        this.р110 = value;
    }

    /**
     * Gets the value of the р111 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР111() {
        return р111;
    }

    /**
     * Sets the value of the р111 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР111(String value) {
        this.р111 = value;
    }

}
