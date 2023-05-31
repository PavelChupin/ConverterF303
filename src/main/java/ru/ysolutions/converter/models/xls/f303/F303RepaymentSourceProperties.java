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
public class F303RepaymentSourceProperties {
    private BigDecimal p94;
    private BigDecimal p97;
    private BigDecimal p98;
    private String p104;
    private String p105;
}