//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:25:39 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорТраншИстТИстСум complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорТраншИстТИстСум"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Р9_3" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2-лок" /&gt;
 *       &lt;attribute name="Р9_6" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2-лок" /&gt;
 *       &lt;attribute name="Р9_7" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Десят20.2-лок" /&gt;
 *       &lt;attribute name="Р9_13" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка3с-лок" /&gt;
 *       &lt;attribute name="Р9_14" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка3с-лок" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u0422\u0440\u0430\u043d\u0448\u0418\u0441\u0442\u0422\u0418\u0441\u0442\u0421\u0443\u043c")
public class Ф0409303Данные303ДоговорТраншИстТИстСум {

    @XmlAttribute(name = "\u04209_3")
    protected BigDecimal р93;
    @XmlAttribute(name = "\u04209_6")
    protected BigDecimal р96;
    @XmlAttribute(name = "\u04209_7")
    protected BigDecimal р97;
    @XmlAttribute(name = "\u04209_13")
    protected String р913;
    @XmlAttribute(name = "\u04209_14")
    protected String р914;

    /**
     * Gets the value of the р93 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР93() {
        return р93;
    }

    /**
     * Sets the value of the р93 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР93(BigDecimal value) {
        this.р93 = value;
    }

    /**
     * Gets the value of the р96 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР96() {
        return р96;
    }

    /**
     * Sets the value of the р96 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР96(BigDecimal value) {
        this.р96 = value;
    }

    /**
     * Gets the value of the р97 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getР97() {
        return р97;
    }

    /**
     * Sets the value of the р97 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setР97(BigDecimal value) {
        this.р97 = value;
    }

    /**
     * Gets the value of the р913 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР913() {
        return р913;
    }

    /**
     * Sets the value of the р913 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР913(String value) {
        this.р913 = value;
    }

    /**
     * Gets the value of the р914 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР914() {
        return р914;
    }

    /**
     * Sets the value of the р914 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР914(String value) {
        this.р914 = value;
    }

}
