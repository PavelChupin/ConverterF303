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
 * <p>Java class for Ф0409303СоставительГлавБух complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303СоставительГлавБух"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="Должность" use="required" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка255Непустая" /&gt;
 *       &lt;attribute name="ФИО" use="required" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Строка255Непустая" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u0421\u043e\u0441\u0442\u0430\u0432\u0438\u0442\u0435\u043b\u044c\u0413\u043b\u0430\u0432\u0411\u0443\u0445")
public class Ф0409303СоставительГлавБух {

    @XmlAttribute(name = "\u0414\u043e\u043b\u0436\u043d\u043e\u0441\u0442\u044c", required = true)
    protected String должность;
    @XmlAttribute(name = "\u0424\u0418\u041e", required = true)
    protected String фио;

    /**
     * Gets the value of the должность property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getДолжность() {
        return должность;
    }

    /**
     * Sets the value of the должность property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setДолжность(String value) {
        this.должность = value;
    }

    /**
     * Gets the value of the фио property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getФИО() {
        return фио;
    }

    /**
     * Sets the value of the фио property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setФИО(String value) {
        this.фио = value;
    }

}
