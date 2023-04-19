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
public class F303SpecialCondition {
    private String p49;

    // p3_50 p3_51
    private List<F303SpecialConditionProperty> conditionsCodeConds;
}
