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
    private String p26;
    private String p27;
    private String p28;
    private String p29;
    private String p30;
    private BigDecimal p31;
    private LocalDate p32;
}