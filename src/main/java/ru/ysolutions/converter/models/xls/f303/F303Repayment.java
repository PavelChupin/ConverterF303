package ru.ysolutions.converter.models.xls.f303;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Repayment {
    private LocalDate p99;
    private LocalDate p100;
}
