package ru.ysolutions.converter.models.xls;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303RepaymentSource {
    //@XmlAttribute(name = "\u04209_10", required = true)
    private String p101;

    //p102/p103
    private List<F303RepaymentSourceContract> f303RepaymentSourceContracts;

    //p94/p97/p98/p104/p105
    private List<F303RepaymentSourceProperties> f303RepaymentSourceProperties;

}
