//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:02:12 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303Данные303ДоговорТраншУслТ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303Данные303ДоговорТраншУслТ"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ДогПоУсл" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Ф0409303Данные303ДоговорТраншУслТДогПоУсл" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Р3_15" use="required" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Симв10-Спр-лок" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0414\u0430\u043d\u043d\u044b\u0435303\u0414\u043e\u0433\u043e\u0432\u043e\u0440\u0422\u0440\u0430\u043d\u0448\u0423\u0441\u043b\u0422", propOrder = {
    "\u0434\u043e\u0433\u041f\u043e\u0423\u0441\u043b"
})
public class Ф0409303Данные303ДоговорТраншУслТ {

    @XmlElement(name = "\u0414\u043e\u0433\u041f\u043e\u0423\u0441\u043b")
    protected List<Ф0409303Данные303ДоговорТраншУслТДогПоУсл> догПоУсл;
    @XmlAttribute(name = "\u04203_15", required = true)
    protected String р315;

    /**
     * Gets the value of the догПоУсл property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the догПоУсл property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getДогПоУсл().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ф0409303Данные303ДоговорТраншУслТДогПоУсл }
     * 
     * 
     */
    public List<Ф0409303Данные303ДоговорТраншУслТДогПоУсл> getДогПоУсл() {
        if (догПоУсл == null) {
            догПоУсл = new ArrayList<Ф0409303Данные303ДоговорТраншУслТДогПоУсл>();
        }
        return this.догПоУсл;
    }

    /**
     * Gets the value of the р315 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getР315() {
        return р315;
    }

    /**
     * Sets the value of the р315 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setР315(String value) {
        this.р315 = value;
    }

}
