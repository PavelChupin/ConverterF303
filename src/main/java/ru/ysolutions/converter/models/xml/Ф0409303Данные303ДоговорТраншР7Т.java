//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:02:12 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорТраншР7т complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорТраншР7т"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Р7_1" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2неотриц-лок" /&gt;
 *       &lt;attribute name="Р7_2" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2неотриц-лок" /&gt;
 *       &lt;attribute name="Р7_3н" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2неотриц-лок" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u0422\u0440\u0430\u043d\u0448\u04207\u0442")
public class Ф0409303Данные303ДоговорТраншР7Т {

    @XmlAttribute(name = "\u04207_1")
    protected BigDecimal р71;
    @XmlAttribute(name = "\u04207_2")
    protected BigDecimal р72;
    @XmlAttribute(name = "\u04207_3\u043d")
    protected BigDecimal р73Н;

    /**
     * Gets the value of the р71 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР71() {
        return р71;
    }

    /**
     * Sets the value of the р71 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР71(BigDecimal value) {
        this.р71 = value;
    }

    /**
     * Gets the value of the р72 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР72() {
        return р72;
    }

    /**
     * Sets the value of the р72 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР72(BigDecimal value) {
        this.р72 = value;
    }

    /**
     * Gets the value of the р73Н property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР73Н() {
        return р73Н;
    }

    /**
     * Sets the value of the р73Н property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР73Н(BigDecimal value) {
        this.р73Н = value;
    }

}
