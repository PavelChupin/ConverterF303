//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.04.07 at 12:02:12 PM NOVT 
//


package ru.ysolutions.converter.models.xml;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ТипПериодаТип.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;simpleType name="ТипПериодаТип"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="суточная"/&gt;
 *     &lt;enumeration value="пятидневная"/&gt;
 *     &lt;enumeration value="еженедельная"/&gt;
 *     &lt;enumeration value="декадная"/&gt;
 *     &lt;enumeration value="месячная"/&gt;
 *     &lt;enumeration value="квартальная"/&gt;
 *     &lt;enumeration value="полугодовая"/&gt;
 *     &lt;enumeration value="годовая"/&gt;
 *     &lt;enumeration value="нерегулярная"/&gt;
 *     &lt;enumeration value="по требованию"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "\u0422\u0438\u043f\u041f\u0435\u0440\u0438\u043e\u0434\u0430\u0422\u0438\u043f")
@XmlEnum
public enum ТипПериодаТип {

    @XmlEnumValue("\u0441\u0443\u0442\u043e\u0447\u043d\u0430\u044f")
    СУТОЧНАЯ("\u0441\u0443\u0442\u043e\u0447\u043d\u0430\u044f"),
    @XmlEnumValue("\u043f\u044f\u0442\u0438\u0434\u043d\u0435\u0432\u043d\u0430\u044f")
    ПЯТИДНЕВНАЯ("\u043f\u044f\u0442\u0438\u0434\u043d\u0435\u0432\u043d\u0430\u044f"),
    @XmlEnumValue("\u0435\u0436\u0435\u043d\u0435\u0434\u0435\u043b\u044c\u043d\u0430\u044f")
    ЕЖЕНЕДЕЛЬНАЯ("\u0435\u0436\u0435\u043d\u0435\u0434\u0435\u043b\u044c\u043d\u0430\u044f"),
    @XmlEnumValue("\u0434\u0435\u043a\u0430\u0434\u043d\u0430\u044f")
    ДЕКАДНАЯ("\u0434\u0435\u043a\u0430\u0434\u043d\u0430\u044f"),
    @XmlEnumValue("\u043c\u0435\u0441\u044f\u0447\u043d\u0430\u044f")
    МЕСЯЧНАЯ("\u043c\u0435\u0441\u044f\u0447\u043d\u0430\u044f"),
    @XmlEnumValue("\u043a\u0432\u0430\u0440\u0442\u0430\u043b\u044c\u043d\u0430\u044f")
    КВАРТАЛЬНАЯ("\u043a\u0432\u0430\u0440\u0442\u0430\u043b\u044c\u043d\u0430\u044f"),
    @XmlEnumValue("\u043f\u043e\u043b\u0443\u0433\u043e\u0434\u043e\u0432\u0430\u044f")
    ПОЛУГОДОВАЯ("\u043f\u043e\u043b\u0443\u0433\u043e\u0434\u043e\u0432\u0430\u044f"),
    @XmlEnumValue("\u0433\u043e\u0434\u043e\u0432\u0430\u044f")
    ГОДОВАЯ("\u0433\u043e\u0434\u043e\u0432\u0430\u044f"),
    @XmlEnumValue("\u043d\u0435\u0440\u0435\u0433\u0443\u043b\u044f\u0440\u043d\u0430\u044f")
    НЕРЕГУЛЯРНАЯ("\u043d\u0435\u0440\u0435\u0433\u0443\u043b\u044f\u0440\u043d\u0430\u044f"),
    @XmlEnumValue("\u043f\u043e \u0442\u0440\u0435\u0431\u043e\u0432\u0430\u043d\u0438\u044e")
    ПО_ТРЕБОВАНИЮ("\u043f\u043e \u0442\u0440\u0435\u0431\u043e\u0432\u0430\u043d\u0438\u044e");
    private final String value;

    ТипПериодаТип(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ТипПериодаТип fromValue(String v) {
        for (ТипПериодаТип c: ТипПериодаТип.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
