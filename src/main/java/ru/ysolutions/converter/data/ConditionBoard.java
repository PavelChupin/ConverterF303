package ru.ysolutions.converter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class ConditionBoard {
    private Integer rowIndex;
    private String condition;
}
