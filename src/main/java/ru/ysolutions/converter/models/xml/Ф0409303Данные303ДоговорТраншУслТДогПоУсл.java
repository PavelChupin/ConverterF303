//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:25:39 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорТраншУслТДогПоУсл complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорТраншУслТДогПоУсл"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Р3_16" use="required" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Иден-лок" /&gt;
 *       &lt;attribute name="Р3_16рг" type="{urn:cbr-ru:rep0409303:v4.0.4.5}РегНомерКОТип" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u0422\u0440\u0430\u043d\u0448\u0423\u0441\u043b\u0422\u0414\u043e\u0433\u041f\u043e\u0423\u0441\u043b")
public class Ф0409303Данные303ДоговорТраншУслТДогПоУсл {

    @XmlAttribute(name = "\u04203_16", required = true)
    protected String р316;
    @XmlAttribute(name = "\u04203_16\u0440\u0433")
    protected String р316Рг;

    /**
     * Gets the value of the р316 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР316() {
        return р316;
    }

    /**
     * Sets the value of the р316 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР316(String value) {
        this.р316 = value;
    }

    /**
     * Gets the value of the р316Рг property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР316Рг() {
        return р316Рг;
    }

    /**
     * Sets the value of the р316Рг property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР316Рг(String value) {
        this.р316Рг = value;
    }

}
