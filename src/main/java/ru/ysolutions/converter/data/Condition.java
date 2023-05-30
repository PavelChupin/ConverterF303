package ru.ysolutions.converter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class Condition {
    private String p49;
    private String p50;
    private String p51;
}
