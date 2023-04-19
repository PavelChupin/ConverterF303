package ru.ysolutions.converter.models.xls;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303ClientGVZ {
    private String p11;
    private String p12;
}
