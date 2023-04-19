package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303RepaymentSourceContract {
    //@XmlAttribute(name = "\u04209_11")
    private String p102;
    //@XmlAttribute(name = "\u04209_12")
    private String p103;
}
