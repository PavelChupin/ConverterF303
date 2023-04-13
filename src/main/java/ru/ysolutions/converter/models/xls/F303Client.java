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
    private String name_p1_1;
    private String ogrn_p1_2;
    private String ogrnIp_p1_3;
    private LocalDate ogrnDate_p1_4;
    private String inn_p1_5;
    private String innDescription_p1_6;
    private String okpo_p1_7;
    private String countryCode_p1_8;
    private String settingKo_p1_9;
    private String businessCode_p1_10;
    private List<F303ClientGVZ> clientGVZs;
}
