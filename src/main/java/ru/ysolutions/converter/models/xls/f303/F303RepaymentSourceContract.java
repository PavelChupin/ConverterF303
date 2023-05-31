package ru.ysolutions.converter.models.xls.f303;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303RepaymentSourceContract {
    private String p102;
    private String p103;
}