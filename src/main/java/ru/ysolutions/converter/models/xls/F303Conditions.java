package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Conditions {
    private String p35;
    private String p36;
    private BigDecimal p37;
    private BigDecimal p38;
    private String p39;
    private String p40;
    private LocalDate p41;
    private LocalDate p42;
    private String p43;
    private BigDecimal p44;
    private BigDecimal p45;
    private BigDecimal p46;
    private BigInteger p47;
    private String p48;

    // p49 p50 p51
    private List<F303ConditionsCode> conditionsCodes;

    private String p52;
    private String p53;
    private BigDecimal p54;
    private BigDecimal p55;
}
