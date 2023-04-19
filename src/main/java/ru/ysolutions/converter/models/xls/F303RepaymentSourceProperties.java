package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303RepaymentSourceProperties {
    //@XmlAttribute(name = "\u04209_3")
    private BigDecimal p94;
    //@XmlAttribute(name = "\u04209_6")
    private BigDecimal p97;
    //@XmlAttribute(name = "\u04209_7")
    private BigDecimal p98;
    //@XmlAttribute(name = "\u04209_13")
    private String p104;
    //@XmlAttribute(name = "\u04209_14")
    private String p105;
}