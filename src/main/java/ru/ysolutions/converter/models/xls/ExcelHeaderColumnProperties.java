package ru.ysolutions.converter.models.xls;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Getter
@ToString
@Accessors(fluent = true)
public class ExcelHeaderColumnProperties {
    private final String name;
    private final Short width;
}