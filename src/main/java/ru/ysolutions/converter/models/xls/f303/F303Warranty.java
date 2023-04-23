package ru.ysolutions.converter.models.xls.f303;

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
public class F303Warranty {
    private String p56;
    private BigDecimal p57;
    private LocalDate p58;
    private BigDecimal p59;
    private BigDecimal p60;
    private BigDecimal p61;
}