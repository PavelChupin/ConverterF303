package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Tranche {
    private LocalDate p62;
    private String p63;
    private BigDecimal p64;
    private String p65;
    private String p66;
    private BigDecimal p67;
    private String p68;
    private BigDecimal p69;
    private String p70;
    private F303InfoDebtOD f303InfoDebtOD;
}