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
    private String p3_35;
    private String p3_36;
    private BigDecimal p3_37;
    private BigDecimal p3_38;
    private String p3_39;
    private String p3_40;
    private LocalDate p3_41;
    private LocalDate p3_42;
    private String p3_43;
    private BigDecimal p3_44;
    private BigDecimal p3_45;
    private BigDecimal p3_46;
    private BigInteger p3_47;
    private String p3_48;

    // p3_49 p3_50 p3_51
    private List<F303ConditionsCode> conditionsCodes;

    private String p3_52;
    private String p3_53;
    private BigDecimal p3_54;
    private BigDecimal p3_55;
}
