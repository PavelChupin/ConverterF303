//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:25:39 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for Ф0409303ПротоколКонтроляСообщение complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303ПротоколКонтроляСообщение"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:cbr-ru:rep0409303:v4.0.4.5&gt;СтрокаНепустая"&gt;
 *       &lt;attribute name="Код" type="{urn:cbr-ru:rep0409303:v4.0.4.5}ЦелоеПолож" /&gt;
 *       &lt;attribute name="Статус" use="required" type="{urn:cbr-ru:rep0409303:v4.0.4.5}СтатусТип" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u041f\u0440\u043e\u0442\u043e\u043a\u043e\u043b\u041a\u043e\u043d\u0442\u0440\u043e\u043b\u044f\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435", propOrder = {
    "value"
})
public class Ф0409303ПротоколКонтроляСообщение {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "\u041a\u043e\u0434")
    protected BigInteger код;
    @XmlAttribute(name = "\u0421\u0442\u0430\u0442\u0443\u0441", required = true)
    protected BigInteger статус;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the код property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getКод() {
        return код;
    }

    /**
     * Sets the value of the код property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setКод(BigInteger value) {
        this.код = value;
    }

    /**
     * Gets the value of the статус property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getСтатус() {
        return статус;
    }

    /**
     * Sets the value of the статус property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setСтатус(BigInteger value) {
        this.статус = value;
    }

}
