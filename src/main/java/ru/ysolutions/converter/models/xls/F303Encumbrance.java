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
public class F303Encumbrance {
    private String p2_26;
    private String p2_27;
    private String p2_28;
    private String p2_29;
    private String p2_30;
    private BigDecimal p2_31;
    private LocalDate p2_32;
}