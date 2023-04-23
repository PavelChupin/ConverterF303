package ru.ysolutions.converter.models.xls.f303;

import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303p10 {
    protected String p106;
    protected BigDecimal p107;
    protected BigDecimal p108;
    protected String p109;
    protected String p110;
    protected String p111;
    protected String p112;
    protected BigDecimal p113;
    protected String p114;
}