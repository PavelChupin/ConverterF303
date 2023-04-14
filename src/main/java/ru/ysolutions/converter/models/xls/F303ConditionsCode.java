package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303ConditionsCode {
    private String p3_49;

    // p3_50 p3_51
    private List<F303ConditionsCodeCond> conditionsCodeConds;
}
