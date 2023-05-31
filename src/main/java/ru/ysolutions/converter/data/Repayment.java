package ru.ysolutions.converter.data;

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
public class Repayment {
    private BigDecimal p94;
    private BigDecimal p97;
    private BigDecimal p98;
    private String p101;
    private String p102;
    private String p103;
    private String p104;
    private String p105;
    private LocalDate p99;
    private LocalDate p100;
}