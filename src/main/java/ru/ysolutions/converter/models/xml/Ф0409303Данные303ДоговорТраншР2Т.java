//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:02:12 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import java.math.BigInteger;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорТраншР2т complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорТраншР2т"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Р2_10" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Целое3неотриц-лок" /&gt;
 *       &lt;attribute name="Р2_13н" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Дата" /&gt;
 *       &lt;attribute name="Р2_21" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка255Непустая" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u0422\u0440\u0430\u043d\u0448\u04202\u0442")
public class Ф0409303Данные303ДоговорТраншР2Т {

    @XmlAttribute(name = "\u04202_10")
    protected BigInteger р210;
    @XmlAttribute(name = "\u04202_13\u043d")
    protected XMLGregorianCalendar р213Н;
    @XmlAttribute(name = "\u04202_21")
    protected String р221;

    /**
     * Gets the value of the р210 property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getР210() {
        return р210;
    }

    /**
     * Sets the value of the р210 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setР210(BigInteger value) {
        this.р210 = value;
    }

    /**
     * Gets the value of the р213Н property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getР213Н() {
        return р213Н;
    }

    /**
     * Sets the value of the р213Н property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setР213Н(XMLGregorianCalendar value) {
        this.р213Н = value;
    }

    /**
     * Gets the value of the р221 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР221() {
        return р221;
    }

    /**
     * Sets the value of the р221 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР221(String value) {
        this.р221 = value;
    }

}
