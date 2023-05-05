package ru.ysolutions.converter.models.xls.f303;

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
    private String p106;
    private BigDecimal p107;
    private BigDecimal p108;
    private String p109;
    private String p110;
    private String p111;
    private String p112;
    private BigDecimal p113;
    private String p114;
}