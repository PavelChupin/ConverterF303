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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Ф0409303ПротоколКонтроля complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ф0409303ПротоколКонтроля"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Сообщение" type="{urn:cbr-ru:rep0409303:v4.0.4.5}Ф0409303ПротоколКонтроляСообщение" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "\u04240409303\u041f\u0440\u043e\u0442\u043e\u043a\u043e\u043b\u041a\u043e\u043d\u0442\u0440\u043e\u043b\u044f", propOrder = {
    "\u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435"
})
public class Ф0409303ПротоколКонтроля {

    @XmlElement(name = "\u0421\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435", required = true)
    protected List<Ф0409303ПротоколКонтроляСообщение> сообщение;

    /**
     * Gets the value of the сообщение property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the сообщение property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getСообщение().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ф0409303ПротоколКонтроляСообщение }
     * 
     * 
     */
    public List<Ф0409303ПротоколКонтроляСообщение> getСообщение() {
        if (сообщение == null) {
            сообщение = new ArrayList<Ф0409303ПротоколКонтроляСообщение>();
        }
        return this.сообщение;
    }

}
