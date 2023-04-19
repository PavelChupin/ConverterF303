package ru.ysolutions.converter.models.xls;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303Client {
    private String name_p1;
    private String ogrn_p2;
    private String ogrnIp_p3;
    private LocalDate ogrnDate_p4;
    private String inn_p5;
    private String innDescription_p6;
    private String okpo_p7;
    private String countryCode_p8;
    private String settingKo_p9;
    private String businessCode_p10;
    private List<F303ClientGVZ> clientGVZs;
}
